package com.github.sokyranthedragon.slightlyrebrushed.mixin;

import com.github.sokyranthedragon.slightlyrebrushed.SlightlyRebrushedMod;
import com.github.sokyranthedragon.slightlyrebrushed.enchantment.SlightlyRebrushedEnchantmentHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrushableBlockEntity.class)
public abstract class MixinBrushableBlockEntity extends BlockEntity
{
    // Uniques
    @Unique
    private static final String ADDITIONAL_ITEM_TAG = SlightlyRebrushedMod.MOD_ID + "$additional_item";

    @Unique
    private ItemStack brushUp$additionalItem;

    // Shadows
    @Shadow
    private ItemStack item;

    @Shadow
    private long lootTableSeed;

    @Shadow
    protected abstract void unpackLootTable(ServerLevel serverLevel, Player player, ItemStack itemStack);

    @Shadow
    protected abstract void dropContent(ServerLevel serverLevel, Player player, ItemStack itemStack);

    @Inject(method = "<init>", at = @At("RETURN"))
    private void initialize(BlockPos blockPos, BlockState blockState, CallbackInfo ci)
    {
        // Make sure the additional item isn't null by assigning it in the constructor
        brushUp$additionalItem = ItemStack.EMPTY;
    }

    @Inject(method = "dropContent", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/entity/BrushableBlockEntity;item:Lnet/minecraft/world/item/ItemStack;", opcode = Opcodes.PUTFIELD))
    private void injectExtraLootChance(ServerLevel serverLevel, Player player, ItemStack itemStack, CallbackInfo ci)
    {
        // Injected after this.level != null && this.level.getServer() != null, a simple sanity check
        assert level != null;
        assert level.getServer() != null;
        // It should only ever be ItemStack.EMPTY or have an actual item they hold
        assert brushUp$additionalItem != null;

        // Since this is called after the original item dropped, we'll replace it with additional one.
        // If additional one is empty, do nothing.
        if (brushUp$additionalItem.isEmpty())
            return;

        ItemStack useItem = player.getUseItem();
        if (useItem.isEmpty())
            return;

        float luck = SlightlyRebrushedEnchantmentHelper.getBonusArcheologyItemChance(serverLevel, itemStack, player);
        if (luck <= 0 || level.random.nextFloat() > luck)
        {
            // If unlucky, just remove the additional item since we've finished brushing.
            brushUp$additionalItem = ItemStack.EMPTY;
        }
        else
        {
            // Replace the original item with additional one,
            item = brushUp$additionalItem;
            // clear the additional item
            brushUp$additionalItem = ItemStack.EMPTY;
            // and run the method once again to drop the extra item.
            dropContent(serverLevel, player, itemStack);
        }
    }

    // Due to the loot table being discarded, as well as (effectively) the seed as well,
    // we need to generate, store, and save/load an extra item after the loot table was unpacked.
    @Inject(method = "unpackLootTable", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/entity/BrushableBlockEntity;lootTable:Lnet/minecraft/resources/ResourceKey;", opcode = Opcodes.PUTFIELD), cancellable = true)
    private void injectAdditionalItem(ServerLevel serverLevel, Player player, ItemStack itemStack, CallbackInfo ci)
    {
        // Both should only ever be ItemStack.EMPTY
        assert item != null;
        assert brushUp$additionalItem != null;

        // If additional item was generated, it means the extra call finished.
        if (!brushUp$additionalItem.isEmpty())
        {
            // Use the vanilla-intended item as the original item.
            ItemStack temp = brushUp$additionalItem;
            brushUp$additionalItem = item;
            item = temp;

            return;
        }

        // If the vanilla item got discarded, didn't generate, etc.
        if (item.isEmpty())
            return;

        // Temporarily set the original item to the additional item field
        brushUp$additionalItem = item;
        item = ItemStack.EMPTY;

        // Make a copy of the old seed, restore it later just in case
        long oldSeed = lootTableSeed;
        // Temporarily replace the seed with a new one
        RandomSource rand = RandomSource.create(lootTableSeed);
        rand.consumeCount(3);
        lootTableSeed = rand.nextLong();

        // Call the original method, generating a second item with a new loot.
        unpackLootTable(serverLevel, player, itemStack);

        // Restore the vanilla loot table seed.
        // It'll get discarded when saving NBT data, but may as well keep it as
        // close to vanilla for as long as possible in case other mods need it.
        lootTableSeed = oldSeed;

        // If the recursive call was successful - cancel the remainder of the call (setting loot table to null, call setChanged(), handled by recursive call)
        if (!item.isEmpty())
            ci.cancel();
    }

    @Inject(method = "loadAdditional", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;parse(Lnet/minecraft/core/HolderLookup$Provider;Lnet/minecraft/nbt/Tag;)Ljava/util/Optional;"))
    private void injectLoadAdditionalItem(CompoundTag compoundTag, HolderLookup.Provider provider, CallbackInfo ci)
    {
        // Load the additional item
        if (compoundTag.contains(ADDITIONAL_ITEM_TAG))
            brushUp$additionalItem = ItemStack.parse(provider, compoundTag.getCompound(ADDITIONAL_ITEM_TAG)).orElse(ItemStack.EMPTY);
            // Just in case, set it to empty - shouldn't be null, but don't risk it
        else
            brushUp$additionalItem = ItemStack.EMPTY;
    }

    @Inject(method = "saveAdditional", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;save(Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/nbt/Tag;"))
    private void injectSaveAdditionalItem(CompoundTag compoundTag, HolderLookup.Provider provider, CallbackInfo ci)
    {
        assert brushUp$additionalItem != null;

        // Save the additional item
        if (!brushUp$additionalItem.isEmpty())
            compoundTag.put(ADDITIONAL_ITEM_TAG, brushUp$additionalItem.save(provider));
    }

    // Required due to inheritance
    public MixinBrushableBlockEntity()
    {
        super(null, BlockPos.ZERO, null);
        throw new AssertionError();
    }
}

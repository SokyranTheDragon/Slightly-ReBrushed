package com.github.sokyranthedragon.slightlyrebrushed.mixin.fabric;

import com.github.sokyranthedragon.slightlyrebrushed.enchantment.SlightlyRebrushedEnchantmentHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class MixinPlayer extends LivingEntity
{
    @Inject(method = "getDestroySpeed", at = @At("HEAD"), cancellable = true)
    private void injectDestroySpeed(BlockState blockState, CallbackInfoReturnable<Float> cir)
    {
        if (SlightlyRebrushedEnchantmentHelper.canBreakBlock(this, blockState))
            cir.setReturnValue(0f);
    }

    // Required due to inheritance
    protected MixinPlayer(EntityType<? extends LivingEntity> entityType, Level level)
    {
        super(entityType, level);
    }
}
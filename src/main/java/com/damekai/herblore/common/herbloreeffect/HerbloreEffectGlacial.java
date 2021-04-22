package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;

import java.util.function.Supplier;

public class HerbloreEffectGlacial extends HerbloreEffect implements HerbloreEffect.ITickable
{
    public HerbloreEffectGlacial(Supplier<Effect> guiEffect)
    {
        super(guiEffect);
    }

    @Override
    public void onTick(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        FrostWalkerEnchantment.freezeNearby(livingEntity, livingEntity.world, livingEntity.getPosition(), 0);
    }
}

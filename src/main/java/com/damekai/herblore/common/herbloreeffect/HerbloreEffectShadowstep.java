package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public class HerbloreEffectShadowstep extends HerbloreEffect implements HerbloreEffect.IApplicable, HerbloreEffect.IExpirable
{
    public HerbloreEffectShadowstep(Supplier<Effect> guiEffect)
    {
        super(guiEffect);
    }

    @Override
    public void onApply(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        CompoundNBT tag = herbloreEffectInstance.getOrCreateTag();
        tag.putDouble("start_pos_x", livingEntity.getX());
        tag.putDouble("start_pos_y", livingEntity.getY());
        tag.putDouble("start_pos_z", livingEntity.getZ());
    }

    @Override
    public void onExpire(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        CompoundNBT tag = herbloreEffectInstance.getOrCreateTag();
        livingEntity.teleportTo(tag.getDouble("start_pos_x"), tag.getDouble("start_pos_y"), tag.getDouble("start_pos_z"));
        livingEntity.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
    }
}

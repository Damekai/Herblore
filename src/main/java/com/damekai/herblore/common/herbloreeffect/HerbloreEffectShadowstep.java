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
        tag.putDouble("start_pos_x", livingEntity.getPosX());
        tag.putDouble("start_pos_y", livingEntity.getPosY());
        tag.putDouble("start_pos_z", livingEntity.getPosZ());
    }

    @Override
    public void onExpire(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        CompoundNBT tag = herbloreEffectInstance.getOrCreateTag();
        livingEntity.attemptTeleport(tag.getDouble("start_pos_x"), tag.getDouble("start_pos_y"), tag.getDouble("start_pos_z"), true);
        livingEntity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
    }
}

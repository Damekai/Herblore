package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.capability.herbloreeffecthandler.HerbloreEffectHandler;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.potion.Effect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;

import java.util.List;
import java.util.function.Supplier;

public class HerbloreEffectComet extends HerbloreEffect
{
    private static final float PERCENT_DAMAGE_PREVENTED = 0.8f;
    private static final int DAMAGE_RADIUS = 8;

    public HerbloreEffectComet(Supplier<Effect> guiEffect)
    {
        super(guiEffect);
    }

    public static void onLivingDamage(LivingDamageEvent event)
    {
        if (EffectiveSide.get() == LogicalSide.CLIENT)
        {
            return;
        }

        LivingEntity livingEntity = event.getEntityLiving();

        if (event.getSource().msgId.equals(DamageSource.FALL.msgId))
        {
            HerbloreEffectHandler herbloreEffectHandler = HerbloreEffectHandler.getHerbloreEffectHandlerOf(livingEntity);

            if (herbloreEffectHandler != null)
            {
                HerbloreEffectInstance comet = herbloreEffectHandler.getHerbloreEffectInstance(ModHerbloreEffects.COMET.get());

                if (comet != null)
                {
                    // Prevent the approproate amount of damage.
                    float initialAmount = event.getAmount();
                    float damagePrevented = initialAmount * PERCENT_DAMAGE_PREVENTED;

                    event.setAmount(initialAmount - damagePrevented);

                    // Cause damage to all entities in radius.
                    World world = livingEntity.level;

                    List<MobEntity> mobsInRange = world.getEntitiesOfClass(MobEntity.class, new AxisAlignedBB(
                            livingEntity.getX() - DAMAGE_RADIUS,
                            livingEntity.getY() - 1,
                            livingEntity.getZ() - DAMAGE_RADIUS,
                            livingEntity.getX() + DAMAGE_RADIUS,
                            livingEntity.getY() + 1,
                            livingEntity.getZ() + DAMAGE_RADIUS));

                    mobsInRange.forEach((mob) -> mob.hurt(DamageSource.mobAttack(livingEntity), damagePrevented));

                    if (event.getAmount() <= 0f)
                    {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}

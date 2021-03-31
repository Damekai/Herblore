package com.damekai.herblore.common.herbloreeffect.base;

import com.damekai.herblore.common.Herblore;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;

import java.util.UUID;
import java.util.function.Supplier;

public abstract class AttributeHerbloreEffect extends HerbloreEffect implements HerbloreEffect.IApplicable, HerbloreEffect.ITickable, HerbloreEffect.IExpirable
{
    private final UUID uuid;
    private final int updateFrequency;
    private final ImmutableList<AttributePotencyFactor> attributePotencyFactors;

    protected AttributeHerbloreEffect(HerbloreEffect.Properties properties, UUID uuid, int updateFrequency, AttributePotencyFactor... attributePotencyFactors)
    {
        super(properties);

        this.uuid = uuid;
        this.updateFrequency = updateFrequency;
        this.attributePotencyFactors = ImmutableList.copyOf(attributePotencyFactors);
    }

    @Override
    public void onApply(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        applyAttributesModifiers(herbloreEffectInstance, livingEntity);
    }

    @Override
    public void onTick(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        if (herbloreEffectInstance.getDurationRemaining() % updateFrequency != 0)
        {
            return;
        }

        applyAttributesModifiers(herbloreEffectInstance, livingEntity); // Application function removes, then applies.
    }


    @Override
    public void onExpire(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        removeAttributesModifiers(herbloreEffectInstance, livingEntity);
    }

    private void applyAttributesModifiers(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        AttributeModifierManager attributeModifierManager = livingEntity.getAttributeManager();
        attributePotencyFactors.forEach((attributePotencyFactor) ->
        {
            ModifiableAttributeInstance modifiableAttributeInstance = attributeModifierManager.createInstanceIfAbsent(attributePotencyFactor.attribute.get());
            if (modifiableAttributeInstance != null)
            {
                modifiableAttributeInstance.removeModifier(uuid);
                AttributeModifier attributeModifier = new AttributeModifier(uuid,
                        this::getTranslationKey,
                        attributePotencyFactor.amount.get(this, herbloreEffectInstance, livingEntity),
                        attributePotencyFactor.operation);
                modifiableAttributeInstance.applyPersistentModifier(attributeModifier);

                Herblore.LOGGER.debug("Applied modifier: " + attributeModifier.toString());
            }
        });
    }

    private void removeAttributesModifiers(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        AttributeModifierManager attributeModifierManager = livingEntity.getAttributeManager();
        attributePotencyFactors.forEach((attributePotencyFactor) ->
        {
            ModifiableAttributeInstance modifiableAttributeInstance = attributeModifierManager.createInstanceIfAbsent(attributePotencyFactor.attribute.get());
            if (modifiableAttributeInstance != null)
            {
                modifiableAttributeInstance.removeModifier(uuid);
                Herblore.LOGGER.debug("Removed modifier: uuid=" + uuid.toString());
            }
        });
    }

    protected static class AttributePotencyFactor
    {
        public interface ModifierAmount { float get(AttributeHerbloreEffect attributeFlaskEffect, HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity); }

        private final Supplier<Attribute> attribute;
        private final AttributeModifier.Operation operation;
        private final ModifierAmount amount;

        public AttributePotencyFactor(Supplier<Attribute> attribute, AttributeModifier.Operation operation, ModifierAmount amount)
        {
            this.attribute = attribute;
            this.operation = operation;
            this.amount = amount;
        }
    }
}

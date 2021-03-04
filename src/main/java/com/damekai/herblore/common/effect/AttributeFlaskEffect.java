package com.damekai.herblore.common.effect;

import com.damekai.herblore.common.Herblore;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;

import java.util.UUID;

public abstract class AttributeFlaskEffect extends TickingFlaskEffect
{
    private final UUID uuid;
    private final ImmutableList<AttributePotencyFactor> attributePotencyFactors;

    protected AttributeFlaskEffect(String translationName, UUID uuid, int attributesUpdateFrequency, AttributePotencyFactor... attributePotencyFactors)
    {
        super(translationName, attributesUpdateFrequency);

        this.uuid = uuid;
        this.attributePotencyFactors = ImmutableList.copyOf(attributePotencyFactors);
    }

    @Override
    public void onApply(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        super.onApply(livingEntity, potency, durationFull, durationRemaining);

        applyAttributesModifiers(livingEntity, potency, durationFull, durationRemaining);
    }

    @Override
    public void onExpire(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        super.onExpire(livingEntity, potency, durationFull, durationRemaining);

        removeAttributesModifiers(livingEntity, potency, durationFull, durationRemaining);
    }

    @Override
    public void onRemove(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        super.onRemove(livingEntity, potency, durationFull, durationRemaining);

        removeAttributesModifiers(livingEntity, potency, durationFull, durationRemaining);
    }

    @Override
    protected void tick(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        applyAttributesModifiers(livingEntity, potency, durationFull, durationRemaining); // Application function removes, then applies.
    }

    private void applyAttributesModifiers(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        AttributeModifierManager attributeModifierManager = livingEntity.getAttributeManager();
        attributePotencyFactors.forEach((attributePotencyFactor) ->
        {
            ModifiableAttributeInstance modifiableAttributeInstance = attributeModifierManager.createInstanceIfAbsent(attributePotencyFactor.attribute);
            if (modifiableAttributeInstance != null)
            {
                modifiableAttributeInstance.removeModifier(uuid);
                AttributeModifier attributeModifier = new AttributeModifier(uuid,
                        this::getTranslationKey,
                        attributePotencyFactor.amount.get(this, livingEntity, potency, durationFull, durationRemaining),
                        attributePotencyFactor.operation);
                modifiableAttributeInstance.applyPersistentModifier(attributeModifier);

                Herblore.LOGGER.debug("Applied modifier: " + attributeModifier.toString());
            }
        });
    }

    private void removeAttributesModifiers(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        AttributeModifierManager attributeModifierManager = livingEntity.getAttributeManager();
        attributePotencyFactors.forEach((attributePotencyFactor) ->
        {
            ModifiableAttributeInstance modifiableAttributeInstance = attributeModifierManager.createInstanceIfAbsent(attributePotencyFactor.attribute);
            if (modifiableAttributeInstance != null)
            {
                modifiableAttributeInstance.removeModifier(uuid);
            }
        });
    }

    protected static class AttributePotencyFactor
    {
        protected interface ModifierAmount { float get(AttributeFlaskEffect flaskAttributeEffect, LivingEntity livingEntity, int potency, int durationFull, int durationRemaining); }

        private final Attribute attribute;
        private final AttributeModifier.Operation operation;
        private final ModifierAmount amount;

        public AttributePotencyFactor(Attribute attribute, AttributeModifier.Operation operation, ModifierAmount amount)
        {
            this.attribute = attribute;
            this.operation = operation;
            this.amount = amount;
        }
    }
}

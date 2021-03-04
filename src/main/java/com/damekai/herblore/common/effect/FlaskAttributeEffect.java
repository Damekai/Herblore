package com.damekai.herblore.common.effect;

import com.damekai.herblore.common.Herblore;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;

import java.util.UUID;
import java.util.function.Supplier;

public abstract class FlaskAttributeEffect extends FlaskEffect
{
    private final UUID uuid;
    private final int attributesUpdateFrequency; // After this many ticks, the attribute will be "updated" (removed and reapplied, with fresh attribute modification amount calculation).
    private final ImmutableList<AttributePotencyFactor> attributePotencyFactors;

    protected FlaskAttributeEffect(String translationName, UUID uuid, int attributesUpdateFrequency, AttributePotencyFactor... attributePotencyFactors)
    {
        super(translationName);

        this.uuid = uuid;
        this.attributesUpdateFrequency = attributesUpdateFrequency;
        this.attributePotencyFactors = ImmutableList.copyOf(attributePotencyFactors);
    }

    @Override
    public void onApply(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        super.onApply(livingEntity, potency, durationFull, durationRemaining);

        applyAttributesModifiers(livingEntity, potency, durationFull, durationRemaining);
    }

    @Override
    public void onTick(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        super.onTick(livingEntity, potency, durationFull, durationRemaining);

        if (durationRemaining % attributesUpdateFrequency == 0)
        {
            applyAttributesModifiers(livingEntity, potency, durationFull, durationRemaining); // Application function removes, then applies.
        }
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
        protected interface ModifierAmount { float get(FlaskAttributeEffect flaskAttributeEffect, LivingEntity livingEntity, int potency, int durationFull, int durationRemaining); }

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

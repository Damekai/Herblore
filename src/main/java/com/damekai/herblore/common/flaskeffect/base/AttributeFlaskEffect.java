package com.damekai.herblore.common.flaskeffect.base;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flask.FlaskInstance;
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
    protected void apply(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        applyAttributesModifiers(flaskInstance, livingEntity);
    }

    @Override
    protected void expire(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        removeAttributesModifiers(flaskInstance, livingEntity);
    }

    @Override
    protected void remove(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        removeAttributesModifiers(flaskInstance, livingEntity);
    }

    @Override
    protected void tick(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        applyAttributesModifiers(flaskInstance, livingEntity); // Application function removes, then applies.
    }

    private void applyAttributesModifiers(FlaskInstance flaskInstance, LivingEntity livingEntity)
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
                        attributePotencyFactor.amount.get(this, flaskInstance, livingEntity),
                        attributePotencyFactor.operation);
                modifiableAttributeInstance.applyPersistentModifier(attributeModifier);

                Herblore.LOGGER.debug("Applied modifier: " + attributeModifier.toString());
            }
        });
    }

    private void removeAttributesModifiers(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        AttributeModifierManager attributeModifierManager = livingEntity.getAttributeManager();
        attributePotencyFactors.forEach((attributePotencyFactor) ->
        {
            ModifiableAttributeInstance modifiableAttributeInstance = attributeModifierManager.createInstanceIfAbsent(attributePotencyFactor.attribute);
            if (modifiableAttributeInstance != null)
            {
                modifiableAttributeInstance.removeModifier(uuid);
                Herblore.LOGGER.debug("Removed modifier: uuid=" + uuid.toString());
            }
        });
    }

    protected static class AttributePotencyFactor
    {
        public interface ModifierAmount { float get(AttributeFlaskEffect attributeFlaskEffect, FlaskInstance flaskInstance, LivingEntity livingEntity); }

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

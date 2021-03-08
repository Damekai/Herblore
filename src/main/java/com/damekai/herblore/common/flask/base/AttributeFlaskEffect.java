package com.damekai.herblore.common.flask.base;

import com.damekai.herblore.common.Herblore;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;

import java.util.UUID;

public abstract class AttributeFlaskEffect extends FlaskEffect implements FlaskEffect.IApplicable, FlaskEffect.ITickable, FlaskEffect.IExpirable
{
    private final UUID uuid;
    private final int updateFrequency;
    private final ImmutableList<AttributePotencyFactor> attributePotencyFactors;

    protected AttributeFlaskEffect(FlaskEffect.Properties properties, UUID uuid, int updateFrequency, AttributePotencyFactor... attributePotencyFactors)
    {
        super(properties);

        this.uuid = uuid;
        this.updateFrequency = updateFrequency;
        this.attributePotencyFactors = ImmutableList.copyOf(attributePotencyFactors);
    }

    @Override
    public void onApply(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        applyAttributesModifiers(flaskEffectInstance, livingEntity);
    }

    @Override
    public void onTick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        if (flaskEffectInstance.getDurationRemaining() % updateFrequency != 0)
        {
            return;
        }

        applyAttributesModifiers(flaskEffectInstance, livingEntity); // Application function removes, then applies.
    }


    @Override
    public void onExpire(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        removeAttributesModifiers(flaskEffectInstance, livingEntity);
    }

    private void applyAttributesModifiers(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
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
                        attributePotencyFactor.amount.get(this, flaskEffectInstance, livingEntity),
                        attributePotencyFactor.operation);
                modifiableAttributeInstance.applyPersistentModifier(attributeModifier);

                Herblore.LOGGER.debug("Applied modifier: " + attributeModifier.toString());
            }
        });
    }

    private void removeAttributesModifiers(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
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
        public interface ModifierAmount { float get(AttributeFlaskEffect attributeFlaskEffect, FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity); }

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

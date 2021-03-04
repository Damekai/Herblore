package com.damekai.herblore.common.capability;

import net.minecraft.entity.LivingEntity;

public interface IToxicityHandler
{
    void addToxicity(LivingEntity livingEntity, int amount);

    void removeToxicity(LivingEntity livingEntity, int amount);

    void setToxicity(LivingEntity livingEntity, int amount);

    int getToxicity();

    void clearToxicity(LivingEntity livingEntity);
}

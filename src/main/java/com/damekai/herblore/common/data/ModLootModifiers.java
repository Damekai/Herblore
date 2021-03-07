package com.damekai.herblore.common.data;

import com.damekai.herblore.common.Herblore;
import com.google.gson.JsonObject;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModLootModifiers
{
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Herblore.MOD_ID);

    public static final RegistryObject<GlobalLootModifierSerializer<ReagentLootModifier>> REAGENT_LOOT_MODIFIER = LOOT_MODIFIERS.register("reagent_loot_modifier", ReagentLootModifierSerializer::new);
}

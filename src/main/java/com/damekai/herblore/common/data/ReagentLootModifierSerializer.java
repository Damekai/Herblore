package com.damekai.herblore.common.data;

import com.google.gson.JsonObject;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

public class ReagentLootModifierSerializer extends GlobalLootModifierSerializer<ReagentLootModifier>
{
    @Override
    public ReagentLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition)
    {
        return new ReagentLootModifier(ailootcondition);
    }

    @Override
    public JsonObject write(ReagentLootModifier instance)
    {
        return makeConditions(new ILootCondition[0]);
    }
}

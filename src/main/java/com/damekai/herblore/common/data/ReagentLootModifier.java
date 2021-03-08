package com.damekai.herblore.common.data;

import com.damekai.herblore.common.item.ItemReagent;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.*;

public class ReagentLootModifier extends LootModifier
{
    protected ReagentLootModifier(ILootCondition[] conditions)
    {
        super(conditions);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
    {
        // TODO: DEBUG THIS!!! Then, go through the full process. Need to delete old MilledReagent recipes, update tooltip code for ItemReagent, etc. Then do the whole averaging thing. Read Discord messages from Silk if lost on LootTableModifiers.

        Queue<ItemStack> generatedDrops = new LinkedList<>(generatedLoot);
        List<ItemStack> modifiedDrops = new ArrayList<>();

        while (!generatedDrops.isEmpty())
        {
            ItemStack drop = generatedDrops.remove();

            if (drop.getItem() instanceof ItemReagent)
            {
                ItemReagent reagent = (ItemReagent) drop.getItem();

                for (int i = 0; i < drop.getCount(); i++)
                {
                    modifiedDrops.add(reagent.getInstanceWithRandomPotency());
                }
            }
            else
            {
                modifiedDrops.add(drop);
            }
        }

        return modifiedDrops;
    }
}
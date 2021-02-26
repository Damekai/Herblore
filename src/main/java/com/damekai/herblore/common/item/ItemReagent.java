package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.CapabilityHerbloreEffectHandler;
import com.damekai.herblore.common.capability.HerbloreEffectHandler;
import com.damekai.herblore.common.effect.HerbloreEffect;
import com.damekai.herblore.common.effect.HerbloreEffectInstance;
import com.damekai.herblore.common.effect.ModHerbloreEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

import java.util.Map;
import java.util.Random;

public class ItemReagent extends Item
{
    private final Map<RegistryObject<HerbloreEffect>, Integer> herbloreEffectPoints; // TODO: Make this its own class?
    private final int totalHerbloreEffectPoints;

    public ItemReagent(Map<RegistryObject<HerbloreEffect>, Integer> herbloreEffectPoints)
    {
        super(ModItems.defaultItemProperties().maxStackSize(16));

        this.herbloreEffectPoints = herbloreEffectPoints;
        int totalPoints = 0;
        for (Integer i : herbloreEffectPoints.values())
        {
            totalPoints += i;
        }
        this.totalHerbloreEffectPoints = totalPoints;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        if (world.isRemote) return ActionResult.resultPass(player.getHeldItem(hand));

        Herblore.LOGGER.debug("Used reagent.");

        HerbloreEffectHandler herbloreEffectHandler = player.getCapability(CapabilityHerbloreEffectHandler.HERBLORE_EFFECT_HANDLER_CAPABILITY).orElse(null);
        if (herbloreEffectHandler != null)
        {
            herbloreEffectHandler.applyHerbloreEffects(player, new HerbloreEffectInstance(getRandomHerbloreEffectPoint(), 0, 40));
        }

        return ActionResult.resultSuccess(player.getHeldItem(hand));
    }

    public Map<RegistryObject<HerbloreEffect>, Integer> getHerbloreEffectPoints() { return herbloreEffectPoints; }

    public HerbloreEffect getRandomHerbloreEffectPoint()
    {
        int roll = Herblore.RANDOM.nextInt(totalHerbloreEffectPoints);

        int current = 0;
        for (RegistryObject<HerbloreEffect> herbloreEffect : herbloreEffectPoints.keySet())
        {
            current += herbloreEffectPoints.get(herbloreEffect);
            if (current > roll)
            {
                return herbloreEffect.get();
            }
        }

        return null; // Only ever called if there are no Herblore effect points in this reagent.
    }
}

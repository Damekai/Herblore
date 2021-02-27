package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.CapabilityFlaskHandler;
import com.damekai.herblore.common.capability.FlaskHandler;
import com.damekai.herblore.common.effect.FlaskEffect;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.FlaskInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

import java.util.Map;

public class ItemReagent extends Item
{
    private final Map<RegistryObject<Flask>, Integer> flaskPoints; // TODO: Make this its own class?
    private final int totalFlaskPoints;

    public ItemReagent(Map<RegistryObject<Flask>, Integer> flaskPoints)
    {
        super(ModItems.defaultItemProperties().maxStackSize(16));

        this.flaskPoints = flaskPoints;
        int totalPoints = 0;
        for (Integer i : flaskPoints.values())
        {
            totalPoints += i;
        }
        this.totalFlaskPoints = totalPoints;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        if (world.isRemote) return ActionResult.resultPass(player.getHeldItem(hand));

        Herblore.LOGGER.debug("Used reagent.");

        FlaskHandler flaskHandler = player.getCapability(CapabilityFlaskHandler.FLASK_HANDLER_CAPABILITY).orElse(null);
        if (flaskHandler != null)
        {
            flaskHandler.applyFlasks(player, new FlaskInstance(getRandomFlask(), 0, 40));
        }

        return ActionResult.resultSuccess(player.getHeldItem(hand));
    }

    public Map<RegistryObject<Flask>, Integer> getFlaskPoints()
    {
        return flaskPoints;
    }

    /**
     * Returns a random Flask attributed to this Reagent. Flasks with more points in this Reagent are more likely
     * to be selected.
     * @return a weighted-random Flask.
     */
    public Flask getRandomFlask()
    {
        int roll = Herblore.RANDOM.nextInt(totalFlaskPoints);

        int current = 0;
        for (RegistryObject<Flask> flask : flaskPoints.keySet())
        {
            current += flaskPoints.get(flask);
            if (current > roll)
            {
                return flask.get();
            }
        }

        return null; // Only ever called if there are no Flask points in this reagent.
    }
}

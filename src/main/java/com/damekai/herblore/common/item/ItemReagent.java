package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.CapabilityFlaskHandler;
import com.damekai.herblore.common.capability.FlaskHandler;
import com.damekai.herblore.common.effect.FlaskEffect;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.util.WeightedSet;
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
    private final WeightedSet<RegistryObject<Flask>> flaskWeights;

    public ItemReagent(WeightedSet<RegistryObject<Flask>> flaskWeights)
    {
        super(ModItems.defaultItemProperties());
        this.flaskWeights = flaskWeights;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        if (world.isRemote) return ActionResult.resultPass(player.getHeldItem(hand));

        Herblore.LOGGER.debug("Used reagent.");

        FlaskHandler flaskHandler = player.getCapability(CapabilityFlaskHandler.FLASK_HANDLER_CAPABILITY).orElse(null);
        if (flaskHandler != null)
        {
            flaskHandler.applyFlasks(player, new FlaskInstance(flaskWeights.getWeightedRandomEntry().get(), 0, 40));
        }

        return ActionResult.resultSuccess(player.getHeldItem(hand));
    }

    public WeightedSet<RegistryObject<Flask>> getFlaskWeights()
    {
        return flaskWeights;
    }
}

package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.CapabilityFlaskHandler;
import com.damekai.herblore.common.capability.FlaskHandler;
import com.damekai.herblore.common.capability.HerbloreKnowledge;
import com.damekai.herblore.common.effect.FlaskEffect;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.util.FlaskHelper;
import com.damekai.herblore.common.util.WeightedSet;
import com.google.common.collect.ImmutableList;
import com.sun.org.apache.xpath.internal.operations.String;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        FlaskHandler flaskHandler = FlaskHandler.getFlaskHandlerOf(player);
        if (flaskHandler != null)
        {
            RegistryObject<Flask> flaskSupplier = flaskWeights.getWeightedRandomEntry();
            Flask flask = flaskSupplier.get();
            flaskHandler.applyFlasks(player, new FlaskInstance(flask, flaskWeights.getWeight(flaskSupplier), 100));

            if (!world.isRemote)
            {
                HerbloreKnowledge herbloreKnowledge = HerbloreKnowledge.getHerbloreKnowledgeOf(player);
                if (herbloreKnowledge != null)
                {
                    herbloreKnowledge.setFlaskKnown(player, this, flask);
                }
            }
        }

        return ActionResult.func_233538_a_(player.getHeldItem(hand), world.isRemote);
    }

    public WeightedSet<RegistryObject<Flask>> getFlaskWeights()
    {
        return flaskWeights;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        super.addInformation(stack, world, tooltip, flag);

        HerbloreKnowledge herbloreKnowledge = HerbloreKnowledge.getHerbloreKnowledgeOf(Minecraft.getInstance().player);
        if (herbloreKnowledge != null)
        {
            ImmutableList<Flask> knownFlasks = herbloreKnowledge.getKnownFlasks(this);
            for (RegistryObject<Flask> flaskSupplier : flaskWeights.getElements())
            {
                Flask flask = flaskSupplier.get();
                if (knownFlasks != null && knownFlasks.contains(flask))
                {
                    tooltip.add(new TranslationTextComponent(flask.getTranslationKey())
                            .appendString(" " + flaskWeights.getWeight(flaskSupplier))
                            .mergeStyle(TextFormatting.GREEN));
                }
                else
                {
                    tooltip.add(new StringTextComponent("???")
                            .mergeStyle(TextFormatting.GRAY));
                }
            }
        }
    }
}

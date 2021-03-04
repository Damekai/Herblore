package com.damekai.herblore.common.item;

import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.capability.herbloreknowledge.HerbloreKnowledge;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.util.WeightedSet;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

public class ItemReagent extends Item
{
    private final WeightedSet<RegistryObject<Flask>> flaskWeights;

    public ItemReagent(WeightedSet<RegistryObject<Flask>> flaskWeights)
    {
        super(ModItems.defaultItemProperties());
        this.flaskWeights = flaskWeights;
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 40;
    }

    @Override
    public UseAction getUseAction(ItemStack stack)
    {
        return UseAction.EAT;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        player.setActiveHand(hand);
        return ActionResult.resultSuccess(player.getHeldItem(hand));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity livingEntity)
    {
        if (!(livingEntity instanceof PlayerEntity))
        {
            return stack;
        }
        PlayerEntity player = (PlayerEntity) livingEntity;

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

        stack.shrink(1);
        return stack;
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
                            .mergeStyle(TextFormatting.GRAY)
                            .mergeStyle(TextFormatting.ITALIC));
                }
            }
        }
    }
}

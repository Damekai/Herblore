package com.damekai.herblore.common.item;

import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.capability.herbloreknowledge.HerbloreKnowledge;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.util.ProbabilitySet;
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
import java.util.Random;

public class ItemReagent extends Item
{
    private static final Random RANDOM = new Random();

    private final ProbabilitySet<Integer> potencyProbabilities;
    private final List<RegistryObject<FlaskEffect>> flaskEffects;

    public ItemReagent(ProbabilitySet<Integer> potencyProbabilities, List<RegistryObject<FlaskEffect>> flaskEffects)
    {
        super(ModItems.defaultItemProperties());

        this.potencyProbabilities = potencyProbabilities;
        this.flaskEffects = flaskEffects;
    }

    @Override
    public ItemStack getDefaultInstance()
    {
        ItemStack stack = super.getDefaultInstance();
        stack.getOrCreateTag().putInt("potency", 10);
        return stack;
    }

    public ItemStack getInstanceWithRandomPotency()
    {
        ItemStack result = new ItemStack(this);
        result.getOrCreateTag().putInt("potency", potencyProbabilities.roll());
        return result;
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
            RegistryObject<FlaskEffect> flaskEffectSupplier = flaskEffects.get(RANDOM.nextInt(flaskEffects.size()));
            FlaskEffect flaskEffect = flaskEffectSupplier.get();
            flaskHandler.applyFlaskEffectInstance(new FlaskEffectInstance(flaskEffect, stack.getOrCreateTag().getInt("potency"), 100), player);

            if (!world.isRemote)
            {
                HerbloreKnowledge herbloreKnowledge = HerbloreKnowledge.getHerbloreKnowledgeOf(player);
                if (herbloreKnowledge != null)
                {
                    herbloreKnowledge.setFlaskEffectKnown(player, this, flaskEffect);
                }
            }
        }

        stack.shrink(1);
        return stack;
    }

    public List<RegistryObject<FlaskEffect>> getFlaskEffects()
    {
        return flaskEffects;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        super.addInformation(stack, world, tooltip, flag);

        HerbloreKnowledge herbloreKnowledge = HerbloreKnowledge.getHerbloreKnowledgeOf(Minecraft.getInstance().player);
        if (herbloreKnowledge != null)
        {
            tooltip.add(new TranslationTextComponent("Potency").appendString(" " + stack.getOrCreateTag().getInt("potency")).mergeStyle(TextFormatting.BLUE));

            ImmutableList<FlaskEffect> knownFlaskEffects = herbloreKnowledge.getKnownFlaskEffects(this);
            flaskEffects.forEach((flaskEffectSupplier) ->
            {
                FlaskEffect flaskEffect = flaskEffectSupplier.get();
                if (knownFlaskEffects != null && knownFlaskEffects.contains(flaskEffect))
                {
                    tooltip.add(new TranslationTextComponent(flaskEffect.getTranslationKey()).mergeStyle(TextFormatting.GREEN));
                }
                else
                {
                    tooltip.add(new StringTextComponent("???").mergeStyle(TextFormatting.GRAY).mergeStyle(TextFormatting.ITALIC));
                }
            });
        }
    }
}

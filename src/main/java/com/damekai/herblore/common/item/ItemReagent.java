package com.damekai.herblore.common.item;

import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.capability.herbloreknowledge.HerbloreKnowledge;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ItemReagent extends Item
{
    private final int potency;
    private final Supplier<FlaskEffect> flaskEffect;

    public ItemReagent(int potency, Supplier<FlaskEffect> flaskEffect)
    {
        super(ModItems.defaultItemProperties());

        this.potency = potency;
        this.flaskEffect = flaskEffect;
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 40;
    }

    @Nonnull
    @Override
    public UseAction getUseAction(ItemStack stack)
    {
        return UseAction.EAT;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        player.setActiveHand(hand);
        return ActionResult.resultSuccess(player.getHeldItem(hand));
    }

    @Nonnull
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
            flaskHandler.applyFlaskEffectInstance(new FlaskEffectInstance(flaskEffect.get(), potency, 200), player);

            if (!world.isRemote)
            {
                HerbloreKnowledge herbloreKnowledge = HerbloreKnowledge.getHerbloreKnowledgeOf(player);
                if (herbloreKnowledge != null)
                {
                    herbloreKnowledge.setReagentKnown(player, this);
                }
            }
        }

        stack.shrink(1);
        return stack;
    }

    public int getPotency()
    {
        return potency;
    }

    public Supplier<FlaskEffect> getFlaskEffect()
    {
        return flaskEffect;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        super.addInformation(stack, world, tooltip, flag);

        HerbloreKnowledge herbloreKnowledge = HerbloreKnowledge.getHerbloreKnowledgeOf(Minecraft.getInstance().player);
        if (herbloreKnowledge != null)
        {
            tooltip.add(new TranslationTextComponent("Potency").appendString(" " + potency).mergeStyle(TextFormatting.BLUE));

            if (herbloreKnowledge.isReagentKnown(this))
            {
                tooltip.add(new TranslationTextComponent(flaskEffect.get().getTranslationKey()).mergeStyle(TextFormatting.GREEN));
            }
            else
            {
                tooltip.add(new StringTextComponent("???").mergeStyle(TextFormatting.GRAY).mergeStyle(TextFormatting.ITALIC));
            }
        }
    }
}

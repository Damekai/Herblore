package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.ModRegistries;
import com.damekai.herblore.common.capability.continuousdrinkhandler.ContinuousDrinkHandler;
import com.damekai.herblore.common.capability.herbloreeffecthandler.HerbloreEffectHandler;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.util.FlaskHelper;
import com.damekai.herblore.common.util.IContinuousDrinkItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.thread.EffectiveSide;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFlask extends Item implements IContinuousDrinkItem
{
    public static int INITIAL_FLASK_SIPS = 200;

    public ItemFlask()
    {
        super(ModItems.defaultItemProperties().maxStackSize(1));
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack)
    {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (nbt.contains("flask"))
        {

            Flask flask = ModRegistries.FLASKS.getValue(new ResourceLocation(stack.getOrCreateTag().getString("flask")));
            if (flask == null)
            {
                return new TranslationTextComponent("ruined_flask"); // TODO: Make this a bit more... robust.
            }
            return new TranslationTextComponent(flask.getTranslationKey());
        }
        return super.getDisplayName(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 16;
    }

    @Override
    public int getMaxDrinkTime(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("flask_sips");
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity livingEntity, int timeLeft)
    {
        ContinuousDrinkHandler continuousDrinkHandler = ContinuousDrinkHandler.getContinuousDrinkHandlerOf(livingEntity);
        if (continuousDrinkHandler == null)
        {
            return;
        }

        int drinkTime = continuousDrinkHandler.finishDrink();

        if (world.isRemote)
        {
            return;
        }

        stack.getOrCreateTag().putInt("flask_sips", stack.getOrCreateTag().getInt("flask_sips") - drinkTime);

        HerbloreEffectHandler herbloreEffectHandler = HerbloreEffectHandler.getHerbloreEffectHandlerOf(livingEntity);
        if (herbloreEffectHandler != null)
        {
            Flask flask = ModRegistries.FLASKS.getValue(new ResourceLocation(stack.getOrCreateTag().getString("flask")));
            if (flask != null)
            {
                HerbloreEffectInstance herbloreEffectInstance = flask.getHerbloreEffectInstance();
                herbloreEffectInstance.setDuration(Math.round(herbloreEffectInstance.getDurationFull() * (float) drinkTime / INITIAL_FLASK_SIPS));
                herbloreEffectHandler.applyHerbloreEffectInstance(herbloreEffectInstance, livingEntity);
            }
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack)
    {
        return UseAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        player.setActiveHand(hand);
        return ActionResult.func_233538_a_(player.getHeldItem(hand), world.isRemote);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity livingEntity)
    {
        ContinuousDrinkHandler continuousDrinkHandler = ContinuousDrinkHandler.getContinuousDrinkHandlerOf(livingEntity);
        if (continuousDrinkHandler == null)
        {
            return stack;
        }

        int drinkTime = continuousDrinkHandler.finishDrink();

        Herblore.LOGGER.debug(drinkTime);

        if (!world.isRemote)
        {
            HerbloreEffectHandler herbloreEffectHandler = HerbloreEffectHandler.getHerbloreEffectHandlerOf(livingEntity);
            if (herbloreEffectHandler != null)
            {
                Flask flask = ModRegistries.FLASKS.getValue(new ResourceLocation(stack.getOrCreateTag().getString("flask")));
                if (flask != null)
                {
                    HerbloreEffectInstance herbloreEffectInstance = flask.getHerbloreEffectInstance();
                    herbloreEffectInstance.setDuration(Math.round(herbloreEffectInstance.getDurationFull() * (float) drinkTime / INITIAL_FLASK_SIPS));
                    herbloreEffectHandler.applyHerbloreEffectInstance(herbloreEffectInstance, livingEntity);
                }
            }
        }

        return new ItemStack(ModItems.EMPTY_FLASK::get);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        super.addInformation(stack, world, tooltip, flag);
        FlaskHelper.addFlaskTooltip(stack, tooltip);
    }
}

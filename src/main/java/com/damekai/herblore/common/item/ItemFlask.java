package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.util.FlaskHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFlask extends Item
{
    public ItemFlask()
    {
        super(ModItems.defaultItemProperties().maxStackSize(1));
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack)
    {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (nbt.contains("flask_instance"))
        {
            Flask flask = FlaskInstance.read(nbt.getCompound("flask_instance")).getFlask();
            return new TranslationTextComponent("flaskof." + flask.getTranslationKey());
        }
        return super.getDisplayName(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 40;
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
        return ActionResult.resultSuccess(player.getHeldItem(hand));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity livingEntity)
    {
        CompoundNBT nbt = stack.getOrCreateTag();

        if (nbt.contains("flask_instance"))
        {

            Herblore.LOGGER.debug(nbt.toString());

            FlaskHandler flaskHandler = FlaskHandler.getFlaskHandlerOf(livingEntity);
            if (flaskHandler != null) {
                CompoundNBT flaskInstanceTag = nbt.getCompound("flask_instance");
                flaskHandler.applyFlask(FlaskInstance.read(flaskInstanceTag), livingEntity);
            }

            if (nbt.contains("flask_doses"))
            {
                int doses = nbt.getInt("flask_doses");
                if (doses == 1)
                {
                    return new ItemStack(ModItems.EMPTY_FLASK.get());
                }
                else
                {
                    nbt.putInt("flask_doses", doses - 1);
                }
            }

            return stack;
        }
        return stack;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        super.addInformation(stack, world, tooltip, flag);
        FlaskHelper.addFlaskTooltip(stack, tooltip);
    }
}

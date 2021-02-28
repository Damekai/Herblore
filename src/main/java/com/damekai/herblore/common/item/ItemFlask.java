package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.CapabilityFlaskHandler;
import com.damekai.herblore.common.capability.FlaskHandler;
import com.damekai.herblore.common.effect.FlaskEffect;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.util.FlaskHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        super(ModItems.defaultItemProperties());
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
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        if (world.isRemote)
        {
            return ActionResult.resultPass(player.getHeldItem(hand));
        }

        CompoundNBT nbt = player.getHeldItem(hand).getOrCreateTag();

        if (nbt.contains("flask_instance"))
        {
            Herblore.LOGGER.debug(nbt.toString());

            FlaskHandler flaskHandler = player.getCapability(CapabilityFlaskHandler.FLASK_HANDLER_CAPABILITY).orElse(null);
            if (flaskHandler != null)
            {
                CompoundNBT flaskInstanceTag = nbt.getCompound("flask_instance");
                flaskHandler.applyFlasks(player, FlaskInstance.read(flaskInstanceTag));
            }

            return ActionResult.resultSuccess(player.getHeldItem(hand));
        }

        return ActionResult.resultPass(player.getHeldItem(hand));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        super.addInformation(stack, world, tooltip, flag);
        FlaskHelper.addFlaskTooltip(stack, tooltip);
    }
}

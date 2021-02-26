package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.CapabilityHerbloreEffectHandler;
import com.damekai.herblore.common.capability.HerbloreEffectHandler;
import com.damekai.herblore.common.effect.HerbloreEffect;
import com.damekai.herblore.common.effect.HerbloreEffectInstance;
import com.damekai.herblore.common.effect.ModHerbloreEffects;
import com.damekai.herblore.common.util.FlaskHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFlask extends Item
{
    public ItemFlask()
    {
        super(ModItems.defaultItemProperties());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        if (world.isRemote)
        {
            return ActionResult.resultPass(player.getHeldItem(hand));
        }

        CompoundNBT nbt = player.getHeldItem(hand).getOrCreateTag();

        if (nbt.contains("flask_effects", Constants.NBT.TAG_LIST))
        {
            Herblore.LOGGER.debug(nbt.toString());

            HerbloreEffectHandler herbloreEffectHandler = player.getCapability(CapabilityHerbloreEffectHandler.HERBLORE_EFFECT_HANDLER_CAPABILITY).orElse(null);
            if (herbloreEffectHandler != null)
            {
                ListNBT nbtList = nbt.getList("flask_effects", Constants.NBT.TAG_COMPOUND);

                for (INBT tag : nbtList)
                {
                    herbloreEffectHandler.applyHerbloreEffects(player, HerbloreEffectInstance.read((CompoundNBT) tag));
                }
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

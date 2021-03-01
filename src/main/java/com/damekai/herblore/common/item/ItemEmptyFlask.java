package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemEmptyFlask extends Item
{
    public ItemEmptyFlask(Properties properties)
    {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getHeldItem(hand);

        BlockRayTraceResult blockRayTraceResult = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (blockRayTraceResult.getType() == RayTraceResult.Type.BLOCK && world.getBlockState(blockRayTraceResult.getPos()).getFluidState().getFluid() == Fluids.WATER)
        {
            world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);

            ItemStack productStack = new ItemStack(ModItems.FLASK_OF_WATER::get);
            stack.shrink(1);

            if (stack.isEmpty())
            {
                stack = productStack;
            }
            else
            {
                player.addItemStackToInventory(productStack);
            }

            return ActionResult.func_233538_a_(stack, world.isRemote);
        }

        return ActionResult.resultPass(stack);
    }
}

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
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getItemInHand(hand);

        BlockRayTraceResult blockRayTraceResult = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (blockRayTraceResult.getType() == RayTraceResult.Type.BLOCK && world.getBlockState(blockRayTraceResult.getBlockPos()).getFluidState().getType() == Fluids.WATER)
        {
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);

            ItemStack productStack = new ItemStack(ModItems.FLASK_OF_WATER::get);
            stack.shrink(1);

            if (stack.isEmpty())
            {
                stack = productStack;
            }
            else
            {
                player.addItem(productStack);
            }

            return ActionResult.sidedSuccess(stack, world.isClientSide);
        }

        return ActionResult.pass(stack);
    }
}

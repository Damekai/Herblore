package com.damekai.herblore.common.item;

import com.damekai.herblore.common.util.WeightedSet;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

public class ItemGrimyHerb extends Item
{
    private final WeightedSet<RegistryObject<Item>> outputs;

    public ItemGrimyHerb(WeightedSet<RegistryObject<Item>> outputs)
    {
        super(ModItems.defaultItemProperties());

        this.outputs = outputs;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getHeldItem(hand);

        BlockRayTraceResult blockRayTraceResult = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (blockRayTraceResult.getType() == RayTraceResult.Type.BLOCK && world.getBlockState(blockRayTraceResult.getPos()).getFluidState().getFluid() == Fluids.WATER)
        {
            world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_VILLAGER_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);

            ItemStack productStack = new ItemStack(outputs.getWeightedRandomEntry()::get);
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

    public WeightedSet<RegistryObject<Item>> getOutputs()
    {
        return outputs;
    }
}

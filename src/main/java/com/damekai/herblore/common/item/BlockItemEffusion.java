package com.damekai.herblore.common.item;

import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.util.EffusionHelper;
import com.damekai.herblore.effusion.base.EffusionInstance;
import com.damekai.herblore.effusion.ModEffusions;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockItemEffusion extends BlockItem
{
    public BlockItemEffusion()
    {
        super(ModBlocks.EFFUSION.get(), ModItems.defaultItemProperties());
    }

    @Override
    public ITextComponent getName(ItemStack stack)
    {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (nbt.contains("BlockEntityTag"))
        {
            CompoundNBT blockEntityTag = nbt.getCompound("BlockEntityTag");
            if (blockEntityTag.contains("effusion"))
            {
                EffusionInstance effusionInstance = EffusionInstance.read(blockEntityTag.getCompound("effusion"));
                return new TranslationTextComponent(effusionInstance.getEffusion().getTranslationKey());
            }
        }
        return new TranslationTextComponent("effusion.herblore.ruined_effusion");
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand)
    {
        // TODO: This is here for debugging. Eventually, this override can be removed.

        EffusionHelper.writeEffusionTag(new EffusionInstance(ModEffusions.VERDURE, 400), playerEntity.getItemInHand(hand));

        return super.use(world, playerEntity, hand);
    }

    /**
     * This override exists so that the liquid color of the Effusion is rendered. By default, TileEntity is only deserialized from the BlockItem
     * on the server side, and not the client side. However, the client side performs the color handling.
     *
     * Syncing from server to client takes too long; block color handling is finished by the time it happens. Fortunately, deserialization via
     * TileEntity::load occurs before the color handling does.
     *
     * On the server side, this override behaves identically to the superclass definition.
     *
     * On the client side, this override loads the BlockEntityTag NBT data from the BlockItem onto the TileEntity in a way that is pretty much
     * identical to the way it is done on the server side (see the base function for more information).
     */
    @Override
    protected boolean updateCustomBlockEntityTag(@Nonnull BlockPos blockPos, World world, PlayerEntity playerEntity, @Nonnull ItemStack stack, @Nonnull BlockState blockState)
    {
        if (!world.isClientSide)
        {
            return super.updateCustomBlockEntityTag(blockPos, world, playerEntity, stack, blockState);
        }
        else
        {
            // Mimic the code run on the server side in super.updateCustomBlockEntityTag to load the tag onto the client's version of the tile.

            CompoundNBT nbt = stack.getTagElement("BlockEntityTag");
            if (nbt != null)
            {
                TileEntity tileEntity = world.getBlockEntity(blockPos);
                if (tileEntity != null)
                {
                    CompoundNBT saved = tileEntity.save(new CompoundNBT());
                    CompoundNBT copy = saved.copy();
                    saved.merge(nbt);
                    saved.putInt("x", blockPos.getX());
                    saved.putInt("y", blockPos.getY());
                    saved.putInt("z", blockPos.getZ());
                    if (!saved.equals(copy))
                    {
                        tileEntity.load(world.getBlockState(blockPos), saved);
                        tileEntity.setChanged();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> lores, ITooltipFlag flag)
    {
        super.appendHoverText(stack, world, lores, flag);
        CompoundNBT nbt = stack.getOrCreateTag();
        if (nbt.contains("BlockEntityTag"))
        {
            CompoundNBT blockEntityTag = nbt.getCompound("BlockEntityTag");
            if (blockEntityTag.contains("effusion"))
            {
                EffusionInstance effusionInstance = EffusionInstance.read(blockEntityTag.getCompound("effusion"));
                lores.add(new TranslationTextComponent("text.herblore.duration_remaining")
                        .append(new StringTextComponent(": " + StringUtils.formatTickDuration(effusionInstance.getDurationRemaining())))
                        .withStyle(TextFormatting.BLUE));
            }
        }
    }
}

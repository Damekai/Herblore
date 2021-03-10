package com.damekai.herblore.common.block.tile;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.BlockAthanor;
import com.damekai.herblore.common.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

public class TileAthanor extends TileEntity implements ITickableTileEntity
{
    /**
     * There are 4 stages (0-3) for the Athanor
     * 0 - Not burning
     * 1 - Burning
     * 2 - Burning with vial on top
     * 3 - Burning with bubbling vial on top
     */
    private int stage;

    private int currentProgressTicks;

    private ItemStack flaskStack;

    public TileAthanor()
    {
        super(ModTiles.ATHANOR.get());

        stage = 0;
        currentProgressTicks = 0;
        flaskStack = ItemStack.EMPTY;
    }

    @Override
    public void tick()
    {
        if (stage == 2 || stage == 3)
        {
            currentProgressTicks += 1;
            if (isFlaskFinished())
            {
                if (flaskStack.getItem() != ModItems.FLASK.get())
                {
                    setStage(3);

                    // Copy NBT list of effects from crude flask to completed flask.
                    CompoundNBT flaskEffect = flaskStack.getOrCreateTag().getCompound("flask_effect_instance").copy();
                    int flaskColor = flaskStack.getOrCreateTag().getInt("flask_effect_color");
                    flaskStack = new ItemStack(ModItems.FLASK.get(), 1);
                    flaskStack.getOrCreateTag().put("flask_effect_instance", flaskEffect);
                    flaskStack.getOrCreateTag().putInt("flask_effect_color", flaskColor);
                    flaskStack.getOrCreateTag().putInt("flask_doses", 4);
                }
            }
        }
    }

    public ActionResultType attemptUse(BlockState blockState, PlayerEntity player, ItemStack stack)
    {
        if (world == null)
        {
            return ActionResultType.FAIL;
        }

        switch (stage)
        {
            case 0:
                if (stack.getItem() == Items.FLINT_AND_STEEL)
                {
                    setStage(1);
                    if (player != null)
                    {
                        stack.damageItem(1, player, (p) -> p.sendBreakAnimation(p.getActiveHand()));
                    }
                    world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.AMBIENT, 1f, 1f);
                    return ActionResultType.SUCCESS;
                }
                break;

            case 1:
                if (stack.getItem() == ModItems.CRUDE_FLASK.get())
                {
                    setStage(2);
                    flaskStack = stack.copy();
                    stack.setCount(stack.getCount() - 1);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.AMBIENT, 1f, 1f);
                    return ActionResultType.SUCCESS;
                }
                break;

            case 2: case 3:

                setStage(1);
                player.addItemStackToInventory(flaskStack);
                flaskStack = ItemStack.EMPTY;
                currentProgressTicks = 0;
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.AMBIENT, 1f, 1f);
                return ActionResultType.SUCCESS;

            default:
                break;
        }

        return ActionResultType.CONSUME;
    }

    public int getStage()
    {
        return stage;
    }

    private boolean isFlaskFinished()
    {
        return currentProgressTicks >= 300; // TODO: Dynamic value, perhaps based on the crude flask?
    }

    private void setStage(int newStage)
    {
        if (world != null)
        {
            stage = newStage;
            world.setBlockState(pos, world.getBlockState(pos).with(BlockAthanor.STAGE, stage));
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt)
    {
        super.read(state, nbt);

        stage = nbt.getInt("stage");
        flaskStack = ItemStack.read(nbt.getCompound("flask_stack"));
        currentProgressTicks = nbt.getInt("progress_ticks");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound.putInt("stage", stage);
        compound.put("flask_stack", flaskStack.serializeNBT());
        compound.putInt("progress_ticks", currentProgressTicks);

        return super.write(compound);
    }
}

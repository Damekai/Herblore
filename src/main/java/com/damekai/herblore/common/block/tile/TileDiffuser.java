package com.damekai.herblore.common.block.tile;

import com.damekai.herblore.common.item.effusion.base.ItemEffusion;
import com.damekai.herblore.common.util.DiffuserInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileDiffuser extends TileEntity implements ITickableTileEntity, IInventoryChangedListener
{
    private final DiffuserInventory diffuserInventory;

    public TileDiffuser()
    {
        super(ModTiles.DIFFUSER.get());

        diffuserInventory = new DiffuserInventory();
    }

    public DiffuserInventory getDiffuserInventory()
    {
        return diffuserInventory;
    }

    @Override
    public void tick()
    {
        ItemStack stack = diffuserInventory.getItem(0);
        if (stack.getItem() instanceof ItemEffusion)
        {
            stack.setDamageValue(stack.getDamageValue() + 1);

            ItemEffusion effusion = (ItemEffusion) stack.getItem();
            effusion.tick(stack, level, worldPosition);

            if (stack.getDamageValue() == stack.getMaxDamage())
            {
                stack.shrink(1);
            }
        }
    }

    @Override
    public void containerChanged(IInventory inventory)
    {
        setChanged();
    }
}

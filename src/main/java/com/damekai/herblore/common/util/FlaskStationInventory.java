package com.damekai.herblore.common.util;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.tile.TileFlaskStation;
import com.damekai.herblore.common.item.ItemReagent;
import com.damekai.herblore.common.item.ModItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class FlaskStationInventory extends Inventory
{
    private final TileFlaskStation flaskStationTile;

    public static final int FLASK_SLOT = 0;

    public FlaskStationInventory(TileFlaskStation flaskStationTile)
    {
        super(17);

        this.flaskStationTile = flaskStationTile;
    }

    public boolean isValidPuzzleSolution()
    {
        // Only need to check the first and last slot of each section.
        for (int firstSlotOfSection = 1; firstSlotOfSection <= 13; firstSlotOfSection += 4)
        {
            // Verify all slots are non-empty.
            for (int slot = firstSlotOfSection; slot < firstSlotOfSection + 4; slot++)
            {
                if (getStackInSlot(slot) == ItemStack.EMPTY)
                {
                    return false;
                }
            }

            ItemReagent topLeft = (ItemReagent) getStackInSlot(firstSlotOfSection).getItem();
            ItemReagent topRight = (ItemReagent) getStackInSlot(firstSlotOfSection + 1).getItem();
            ItemReagent bottomLeft = (ItemReagent) getStackInSlot(firstSlotOfSection + 2).getItem();
            ItemReagent bottomRight = (ItemReagent) getStackInSlot(firstSlotOfSection + 3).getItem();

            // Verify top left.
            if (topLeft.getRightColor() != topRight.getLeftColor() || topLeft.getDownColor() != bottomLeft.getUpColor())
            {
                return false;
            }

            // Verify bottom right.
            if (bottomRight.getLeftColor() != bottomLeft.getRightColor() || bottomRight.getUpColor() != topRight.getDownColor())
            {
                return false;
            }
        }

        return true;
    }
}

package com.damekai.herblore.common.util;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.tile.TileFlaskStation;
import com.damekai.herblore.common.item.ItemReagent;
import com.google.common.collect.ImmutableMap;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;


public class FlaskStationInventory extends Inventory
{
    public static final ImmutableMap<Integer, SlotPositionalProperties> SLOT_POSITIONAL_PROPERTIES =
            new ImmutableMap.Builder<Integer, SlotPositionalProperties>()
                    .put(1, new SlotPositionalProperties(null, 3, null, 2))
                    .put(2, new SlotPositionalProperties(null, 4, 1, null))
                    .put(3, new SlotPositionalProperties(1, null, null, 4))
                    .put(4, new SlotPositionalProperties(2, null, 3, null))
                    .put(5, new SlotPositionalProperties(null, 7, null, 6))
                    .put(6, new SlotPositionalProperties(null, 8, 5, null))
                    .put(7, new SlotPositionalProperties(5, null, null, 8))
                    .put(8, new SlotPositionalProperties(6, null, 7, null))
                    .put(9, new SlotPositionalProperties(null, 11, null, 10))
                    .put(10, new SlotPositionalProperties(null, 12, 9, 11))
                    .put(11, new SlotPositionalProperties(null, 13, 10, null))
                    .put(12, new SlotPositionalProperties(9, 15, null, 13))
                    .put(13, new SlotPositionalProperties(10, 16, 12, 14))
                    .put(14, new SlotPositionalProperties(11, 17, 13, null))
                    .put(15, new SlotPositionalProperties(12, null, null, 16))
                    .put(16, new SlotPositionalProperties(13, null, 15, 17))
                    .put(17, new SlotPositionalProperties(14, null, 16, null))
                    .build();

    private final TileFlaskStation flaskStationTile;

    public FlaskStationInventory(TileFlaskStation flaskStationTile)
    {
        super(18);

        this.flaskStationTile = flaskStationTile;
    }

    public boolean isValidPuzzleSolution()
    {
        // Verify that all slots have reagents.
        for (int slot = 1; slot < 18; slot++)
        {
            ItemStack stack = getStackInSlot(slot);

            if (!(stack.getItem() instanceof ItemReagent))
            {
                return false;
            }
        }

        for (int slot = 1; slot < 18; slot++)
        {
            ItemReagent reagent = (ItemReagent) getStackInSlot(slot).getItem();

            SlotPositionalProperties slotPositionalProperties = SLOT_POSITIONAL_PROPERTIES.get(slot);

            if (slotPositionalProperties.hasUp())
            {
                ItemReagent other = (ItemReagent) getStackInSlot(slotPositionalProperties.getUp()).getItem();

                if (reagent.getUpColor() != other.getDownColor())
                {
                    return false;
                }
            }
            if (slotPositionalProperties.hasDown())
            {
                ItemReagent other = (ItemReagent) getStackInSlot(slotPositionalProperties.getDown()).getItem();

                if (reagent.getDownColor() != other.getUpColor())
                {
                    return false;
                }
            }
            if (slotPositionalProperties.hasLeft())
            {
                ItemReagent other = (ItemReagent) getStackInSlot(slotPositionalProperties.getLeft()).getItem();

                if (reagent.getLeftColor() != other.getRightColor())
                {
                    return false;
                }
            }
            if (slotPositionalProperties.hasRight())
            {
                ItemReagent other = (ItemReagent) getStackInSlot(slotPositionalProperties.getRight()).getItem();

                if (reagent.getRightColor() != other.getLeftColor())
                {
                    return false;
                }
            }
        }

        return true;
    }

    public static class SlotPositionalProperties
    {
        private final Integer up;
        private final Integer down;
        private final Integer left;
        private final Integer right;

        public SlotPositionalProperties(Integer up, Integer down, Integer left, Integer right)
        {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }

        public boolean hasUp()
        {
            return up != null;
        }

        public Integer getUp()
        {
            return up;
        }

        public boolean hasDown()
        {
            return down != null;
        }

        public Integer getDown()
        {
            return down;
        }

        public boolean hasLeft()
        {
            return left != null;
        }

        public Integer getLeft()
        {
            return left;
        }

        public boolean hasRight()
        {
            return right != null;
        }

        public Integer getRight()
        {
            return right;
        }
    }
}

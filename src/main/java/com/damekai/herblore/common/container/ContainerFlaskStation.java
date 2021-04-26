package com.damekai.herblore.common.container;

import com.damekai.herblore.common.block.tile.TileFlaskStation;
import com.damekai.herblore.common.util.FlaskStationInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerFlaskStation extends Container
{
    private final TileFlaskStation flaskStationTile;
    private final FlaskStationInventory flaskStationInventory;

    public ContainerFlaskStation(int id, World world, BlockPos pos, PlayerInventory playerInventory)
    {
        super(ModContainers.FLASK_STATION.get(), id);

        flaskStationTile = (TileFlaskStation) world.getBlockEntity(pos);
        flaskStationInventory = flaskStationTile.getFlaskStationInventory();

        setupFlaskStationInventory();
        setupPlayerInventory(playerInventory);
    }

    public TileFlaskStation getFlaskStationTile()
    {
        return flaskStationTile;
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity)
    {
        return true;
    }

    private void setupFlaskStationInventory()
    {
        // Flask slot.
        this.addSlot(new Slot(flaskStationInventory, 0, 120, 130));

        // Top left section slots (1 thru 4).
        this.addSlot(new Slot(flaskStationInventory, 1, 40, 32));
        this.addSlot(new Slot(flaskStationInventory, 2, 62, 32));
        this.addSlot(new Slot(flaskStationInventory, 3, 40, 54));
        this.addSlot(new Slot(flaskStationInventory, 4, 62, 54));

        // Bottom left section slots (5 thru 8).
        this.addSlot(new Slot(flaskStationInventory, 5, 40, 92));
        this.addSlot(new Slot(flaskStationInventory, 6, 62, 92));
        this.addSlot(new Slot(flaskStationInventory, 7, 40, 114));
        this.addSlot(new Slot(flaskStationInventory, 8, 62, 114));

        // Right section slots (9 - 17)
        this.addSlot(new Slot(flaskStationInventory, 9, 170, 50));
        this.addSlot(new Slot(flaskStationInventory, 10, 192, 50));
        this.addSlot(new Slot(flaskStationInventory, 11, 214, 50));
        this.addSlot(new Slot(flaskStationInventory, 12, 170, 72));
        this.addSlot(new Slot(flaskStationInventory, 13, 192, 72));
        this.addSlot(new Slot(flaskStationInventory, 14, 214, 72));
        this.addSlot(new Slot(flaskStationInventory, 15, 170, 94));
        this.addSlot(new Slot(flaskStationInventory, 16, 192, 94));
        this.addSlot(new Slot(flaskStationInventory, 17, 214, 94));
    }

    private void setupPlayerInventory(PlayerInventory playerInventory)
    {
        // Set up player inventory.
        for(int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 48 + j * 18, i * 18 + 161));
            }
        }

        // Set up player hotbar.
        for(int i = 0; i < 9; ++i)
        {
            this.addSlot(new Slot(playerInventory, i, 48 + i * 18, 219));
        }
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index)
    {
        return ItemStack.EMPTY;
    }
}

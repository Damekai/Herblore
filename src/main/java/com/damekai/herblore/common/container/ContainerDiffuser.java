package com.damekai.herblore.common.container;

import com.damekai.herblore.common.block.tile.TileDiffuser;
import com.damekai.herblore.common.util.DiffuserInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerDiffuser extends Container
{
    private final DiffuserInventory diffuserInventory;

    public ContainerDiffuser(int id, World world, BlockPos blockPos, PlayerInventory playerInventory)
    {
        super(ModContainers.DIFFUSER.get(), id);

        diffuserInventory = ((TileDiffuser) world.getBlockEntity(blockPos)).getDiffuserInventory();

        setupDiffuserInventory();
        setupPlayerInventory(playerInventory);
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity)
    {
        return true;
    }

    private void setupDiffuserInventory()
    {
        this.addSlot(new Slot(diffuserInventory, 0, 120, 130));
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

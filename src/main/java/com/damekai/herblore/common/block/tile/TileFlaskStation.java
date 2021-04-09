package com.damekai.herblore.common.block.tile;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.container.ContainerFlaskStation;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.recipe.FlaskRecipe;
import com.damekai.herblore.common.util.FlaskStationInventory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class TileFlaskStation extends TileEntity implements ITickableTileEntity, INamedContainerProvider, IInventoryChangedListener
{
    public static final int COOK_TIME = 100;

    private final FlaskStationInventory flaskStationInventory;

    private int elapsedCookTime;

    public TileFlaskStation()
    {
        super(ModTiles.FLASK_STATION.get());

        flaskStationInventory = new FlaskStationInventory(this);

        elapsedCookTime = 0;
    }

    @Override
    public void tick()
    {
        IRecipe<FlaskStationInventory> recipe = this.world.getRecipeManager().getRecipe(FlaskRecipe.FLASK_RECIPE, flaskStationInventory, this.world).orElse(null);
        if (recipe != null)
        {
            elapsedCookTime++;
            if (elapsedCookTime == COOK_TIME)
            {
                flaskStationInventory.setInventorySlotContents(0, recipe.getCraftingResult(flaskStationInventory));
                for (int i = 1; i < 18; i++)
                {
                    flaskStationInventory.getStackInSlot(i).shrink(1);
                }
                elapsedCookTime = 0;
            }
        }
        else
        {
            elapsedCookTime = 0;
        }
    }

    public int getElapsedCookTime()
    {
        return elapsedCookTime;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent("tile_flask_station");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity)
    {
        return new ContainerFlaskStation(id, world, pos, playerInventory);
    }

    @Override
    public void onInventoryChanged(IInventory invBasic)
    {
        markDirty();
    }

    public FlaskStationInventory getFlaskStationInventory()
    {
        return flaskStationInventory;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt)
    {
        super.read(state, nbt);
        flaskStationInventory.read(nbt.getList("flask_station_inventory", Constants.NBT.TAG_COMPOUND));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound.put("flask_station_inventory", flaskStationInventory.write());
        return super.write(compound);
    }
}

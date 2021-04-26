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

public class TileFlaskStation extends TileEntity implements ITickableTileEntity, IInventoryChangedListener
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
        if (level == null)
        {
            return;
        }

        IRecipe<FlaskStationInventory> recipe = this.level.getRecipeManager().getRecipeFor(FlaskRecipe.FLASK_RECIPE, flaskStationInventory, this.level).orElse(null);
        if (recipe != null)
        {
            elapsedCookTime++;
            if (elapsedCookTime == COOK_TIME)
            {
                flaskStationInventory.setItem(0, recipe.getResultItem());
                for (int i = 1; i < 18; i++)
                {
                    flaskStationInventory.getItem(i).shrink(1);
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
    public void containerChanged(IInventory inventory)
    {
        setChanged();
    }

    public FlaskStationInventory getFlaskStationInventory()
    {
        return flaskStationInventory;
    }


    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {
        super.deserializeNBT(nbt);
        flaskStationInventory.fromTag(nbt.getList("flask_station_inventory", Constants.NBT.TAG_COMPOUND));
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        CompoundNBT nbt = super.serializeNBT();
        nbt.put("flask_station_inventory", flaskStationInventory.createTag());
        return nbt;
    }
}

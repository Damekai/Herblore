package com.damekai.herblore.common.block.tile;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTiles
{
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Herblore.MOD_ID);

    public static final RegistryObject<TileEntityType<TileAthanor>> ATHANOR = TILES.register(
            "athanor", () -> TileEntityType.Builder.create(TileAthanor::new, ModBlocks.ATHANOR.get()).build(null));

}

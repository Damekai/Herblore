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

    public static final RegistryObject<TileEntityType<TileFlaskStation>> FLASK_STATION = TILES.register(
            "flask_station", () -> TileEntityType.Builder.of(TileFlaskStation::new, ModBlocks.FLASK_STATION.get()).build(null));

    public static final RegistryObject<TileEntityType<TileDiffuser>> DIFFUSER = TILES.register(
            "diffuser", () -> TileEntityType.Builder.of(TileDiffuser::new, ModBlocks.DIFFUSER.get()).build(null));
}

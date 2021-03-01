package com.damekai.herblore.common.block;

import com.damekai.herblore.common.Herblore;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Herblore.MOD_ID);

    public static final RegistryObject<Block> ATHANOR = BLOCKS.register("athanor", BlockAthanor::new);

    public static final RegistryObject<Block> PERENNIAL_PATCH_DEBUG = BLOCKS.register("perennial_patch_debug", BlockPerennialPatch::new);
}

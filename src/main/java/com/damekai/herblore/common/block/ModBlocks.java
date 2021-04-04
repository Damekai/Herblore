package com.damekai.herblore.common.block;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.ItemReagentSeeds;
import com.damekai.herblore.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Herblore.MOD_ID);

    public static final RegistryObject<Block> PERENNIAL_PATCH = BLOCKS.register("perennial_patch", BlockPerennialPatch::new);

    public static final RegistryObject<BlockPerennialCrop> WINDY_LICHEN_CROP = BLOCKS.register("windy_lichen_crop", () -> new BlockPerennialCrop(ModItems.WINDY_LICHEN_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> SUNSPECKLE_CROP = BLOCKS.register("sunspeckle_crop", () -> new BlockPerennialCrop(ModItems.SUNSPECKLE_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> MOONSPECKLE_CROP = BLOCKS.register("moonspeckle_crop", () -> new BlockPerennialCrop(ModItems.MOONSPECKLE_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> STONESTEM_CROP = BLOCKS.register("stonestem_crop", () -> new BlockPerennialCrop(ModItems.STONESTEM_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> WILLOW_WORT_CROP = BLOCKS.register("willow_wort_crop", () -> new BlockPerennialCrop(ModItems.WILLOW_WORT_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> RUMBLEROOT_CROP = BLOCKS.register("rumbleroot_crop", () -> new BlockPerennialCrop(ModItems.RUMBLEROOT_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> PHANTOM_FROND_CROP = BLOCKS.register("phantom_frond_crop", () -> new BlockPerennialCrop(ModItems.PHANTOM_FROND_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> BREEZEBLOOM_CROP = BLOCKS.register("breezebloom_crop", () -> new BlockPerennialCrop(ModItems.BREEZEBLOOM_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> DESERTS_THIRST_CROP = BLOCKS.register("deserts_thirst_crop", () -> new BlockPerennialCrop(ModItems.DESERTS_THIRST_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> THUNDERSTAR_CROP = BLOCKS.register("thunderstar_crop", () -> new BlockPerennialCrop(ModItems.THUNDERSTAR_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> SUNSTRIDERS_SIN_CROP = BLOCKS.register("sunstriders_sin_crop", () -> new BlockPerennialCrop(ModItems.SUNSTRIDERS_SIN_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> SLAKEMOSS_CROP = BLOCKS.register("slakemoss_crop", () -> new BlockPerennialCrop(ModItems.SLAKEMOSS_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> SLAG_RIND_CROP = BLOCKS.register("slag_rind_crop", () -> new BlockPerennialCrop(ModItems.SLAG_RIND_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> VENGERVINE_CROP = BLOCKS.register("vengervine_crop", () -> new BlockPerennialCrop(ModItems.VENGERVINE_SEEDS));
    public static final RegistryObject<BlockPerennialCrop> SKYGLOM_CROP = BLOCKS.register("skyglom_crop", () -> new BlockPerennialCrop(ModItems.SKYGLOM_SEEDS));
}

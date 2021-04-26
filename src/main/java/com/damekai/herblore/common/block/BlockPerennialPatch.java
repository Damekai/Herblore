package com.damekai.herblore.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockPerennialPatch extends Block
{
    public BlockPerennialPatch()
    {
        super(AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP));
    }
}

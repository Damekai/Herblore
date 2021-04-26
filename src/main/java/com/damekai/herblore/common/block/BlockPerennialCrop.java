package com.damekai.herblore.common.block;

import com.damekai.herblore.common.item.ItemReagentSeeds;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IItemProvider;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class BlockPerennialCrop extends CropsBlock
{
    private final Supplier<ItemReagentSeeds> reagentSeeds;

    public BlockPerennialCrop(Supplier<ItemReagentSeeds> reagentSeeds)
    {
        super(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));

        this.reagentSeeds = reagentSeeds;
    }

    @Nonnull
    @Override
    protected IItemProvider getBaseSeedId()
    {
        return reagentSeeds.get();
    }
}

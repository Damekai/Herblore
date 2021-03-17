package com.damekai.herblore.common.item;

import com.damekai.herblore.common.block.BlockPerennialCrop;
import net.minecraft.item.BlockNamedItem;

public class ItemReagentSeeds extends BlockNamedItem
{
    public ItemReagentSeeds(BlockPerennialCrop crop)
    {
        super(crop, ModItems.defaultItemProperties());
    }
}

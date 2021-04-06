package com.damekai.herblore.common.item;

import net.minecraft.item.Item;

import java.awt.*;

public class ItemReagent extends Item
{
    private final Color up;
    private final Color down;
    private final Color left;
    private final Color right;

    public ItemReagent(Color up, Color down, Color left, Color right)
    {
        super(ModItems.defaultItemProperties());

        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public Color getUpColor()
    {
        return up;
    }

    public Color getDownColor()
    {
        return down;
    }

    public Color getLeftColor()
    {
        return left;
    }

    public Color getRightColor()
    {
        return right;
    }
}

package com.damekai.herblore.common.item;

import com.damekai.herblore.common.util.FlaskHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import javax.swing.text.AttributeSet;
import java.awt.*;
import java.awt.Color;
import java.util.List;

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

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        super.addInformation(stack, world, tooltip, flag);

        tooltip.add((new TranslationTextComponent("Up").mergeStyle(up == ModItems.RED ? TextFormatting.RED : TextFormatting.BLUE))
                .append(new TranslationTextComponent("/").mergeStyle(TextFormatting.WHITE))
                .append(new TranslationTextComponent("Down").mergeStyle(down == ModItems.RED ? TextFormatting.RED : TextFormatting.BLUE))
                .append(new TranslationTextComponent("/").mergeStyle(TextFormatting.WHITE))
                .append(new TranslationTextComponent("Left").mergeStyle(left == ModItems.RED ? TextFormatting.RED : TextFormatting.BLUE))
                .append(new TranslationTextComponent("/").mergeStyle(TextFormatting.WHITE))
                .append(new TranslationTextComponent("Right").mergeStyle(right == ModItems.RED ? TextFormatting.RED : TextFormatting.BLUE)));
    }
}

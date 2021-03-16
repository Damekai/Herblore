package com.damekai.herblore.common.item;

import com.damekai.herblore.common.flask.perk.base.FlaskPerk;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ItemCatalyst extends Item
{
    private final Supplier<FlaskPerk> flaskPerk;

    public ItemCatalyst(Properties properties, Supplier<FlaskPerk> flaskPerk)
    {
        super(properties);

        this.flaskPerk = flaskPerk;
    }

    public FlaskPerk getFlaskPerk()
    {
        return flaskPerk.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent(flaskPerk.get().getTranslationKey()).mergeStyle(TextFormatting.ITALIC).mergeStyle(TextFormatting.GRAY));
    }
}

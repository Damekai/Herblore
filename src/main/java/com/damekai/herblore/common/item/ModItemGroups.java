package com.damekai.herblore.common.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class ModItemGroups
{
    public static final ItemGroup HERBLORE = new ModItemGroup("herblore", () -> new ItemStack(ModItems.EMPTY_FLASK.get()));

    public static class ModItemGroup extends ItemGroup
    {
        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(String name, Supplier<ItemStack> iconSupplier)
        {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack makeIcon()
        {
            return iconSupplier.get();
        }
    }
}

package com.damekai.herblore.common.screen;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.tile.TileFlaskStation;
import com.damekai.herblore.common.container.ContainerFlaskStation;
import com.damekai.herblore.common.item.ItemReagent;
import com.damekai.herblore.common.util.FlaskStationInventory;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.awt.*;

public class ScreenFlaskStation extends ContainerScreen<ContainerFlaskStation>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Herblore.MOD_ID, "textures/gui/flask_station.png");

    public ScreenFlaskStation(ContainerFlaskStation container, PlayerInventory playerInventory, ITextComponent title)
    {
        super(container, playerInventory, title);
        this.imageWidth = 256;
        this.imageHeight = 240;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;

        // Add reagent colors.
        for (int i = 1; i < 18; i++)
        {
            Slot slot = menu.getSlot(i);
            ItemStack stack = slot.getItem();

            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemReagent)
            {
                ItemReagent reagent = (ItemReagent) stack.getItem();

                // Up
                if (FlaskStationInventory.SLOT_POSITIONAL_PROPERTIES.get(i).hasUp())
                {
                    AbstractGui.fill(matrixStack, relX + slot.x - 1, relY + slot.y - 1, relX + slot.x + 17, relY + slot.y - 3, reagent.getUpColor().getRGB());
                }

                // Down
                if (FlaskStationInventory.SLOT_POSITIONAL_PROPERTIES.get(i).hasDown())
                {
                    AbstractGui.fill(matrixStack, relX + slot.x - 1, relY + slot.y + 17, relX + slot.x + 17, relY + slot.y + 19, reagent.getDownColor().getRGB());
                }

                // Left
                if (FlaskStationInventory.SLOT_POSITIONAL_PROPERTIES.get(i).hasLeft())
                {
                    AbstractGui.fill(matrixStack, relX + slot.x - 3, relY + slot.y - 1, relX + slot.x - 1, relY + slot.y + 17, reagent.getLeftColor().getRGB());
                }

                // Right
                if (FlaskStationInventory.SLOT_POSITIONAL_PROPERTIES.get(i).hasRight())
                {
                    AbstractGui.fill(matrixStack, relX + slot.x + 17, relY + slot.y - 1, relX + slot.x + 19, relY + slot.y + 17, reagent.getRightColor().getRGB());
                }
            }
        }

        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y)
    {
        this.getMinecraft().getTextureManager().bind(TEXTURE);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;

        // Progress bar background.
        AbstractGui.fill(matrixStack, relX + 104, relY + 90, relX + 151, relY + 151, new Color(9145227).getRGB());

        // Progress bar based on cook time elapsed.
        int elapsedCookTime = menu.getFlaskStationTile().getElapsedCookTime();
        if (elapsedCookTime > 0)
        {
            AbstractGui.fill(matrixStack, relX + 104, relY + (151 - Math.round(61 * (elapsedCookTime / (float) TileFlaskStation.COOK_TIME))), relX + 151, relY + 151, Color.WHITE.getRGB());
        }

        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }



    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y)
    {
        this.font.draw(matrixStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
    }
}

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
        this.xSize = 256;
        this.ySize = 240;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;

        // Add reagent colors.
        for (int i = 1; i < 18; i++)
        {
            Slot slot = container.getSlot(i);
            ItemStack stack = slot.getStack();

            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemReagent)
            {
                ItemReagent reagent = (ItemReagent) stack.getItem();

                // Up
                if (FlaskStationInventory.SLOT_POSITIONAL_PROPERTIES.get(i).hasUp())
                {
                    AbstractGui.fill(matrixStack, relX + slot.xPos - 1, relY + slot.yPos - 1, relX + slot.xPos + 17, relY + slot.yPos - 3, reagent.getUpColor().getRGB());
                }

                // Down
                if (FlaskStationInventory.SLOT_POSITIONAL_PROPERTIES.get(i).hasDown())
                {
                    AbstractGui.fill(matrixStack, relX + slot.xPos - 1, relY + slot.yPos + 17, relX + slot.xPos + 17, relY + slot.yPos + 19, reagent.getDownColor().getRGB());
                }

                // Left
                if (FlaskStationInventory.SLOT_POSITIONAL_PROPERTIES.get(i).hasLeft())
                {
                    AbstractGui.fill(matrixStack, relX + slot.xPos - 3, relY + slot.yPos - 1, relX + slot.xPos - 1, relY + slot.yPos + 17, reagent.getLeftColor().getRGB());
                }

                // Right
                if (FlaskStationInventory.SLOT_POSITIONAL_PROPERTIES.get(i).hasRight())
                {
                    AbstractGui.fill(matrixStack, relX + slot.xPos + 17, relY + slot.yPos - 1, relX + slot.xPos + 19, relY + slot.yPos + 17, reagent.getRightColor().getRGB());
                }
            }
        }

        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y)
    {
        this.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;

        // Progress bar background.
        AbstractGui.fill(matrixStack, relX + 104, relY + 90, relX + 151, relY + 151, new Color(9145227).getRGB());

        // Progress bar based on cook time elapsed.
        int elapsedCookTime = container.getFlaskStationTile().getElapsedCookTime();
        if (elapsedCookTime > 0)
        {
            AbstractGui.fill(matrixStack, relX + 104, relY + (151 - Math.round(61 * (elapsedCookTime / (float) TileFlaskStation.COOK_TIME))), relX + 151, relY + 151, Color.WHITE.getRGB());
        }

        this.blit(matrixStack, relX, relY, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y)
    {
        this.font.func_243248_b(matrixStack, this.title, (float)this.titleX, (float)this.titleY, 4210752);
    }
}

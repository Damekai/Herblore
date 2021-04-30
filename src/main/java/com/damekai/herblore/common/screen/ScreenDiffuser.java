package com.damekai.herblore.common.screen;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.container.ContainerDiffuser;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ScreenDiffuser extends ContainerScreen<ContainerDiffuser>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Herblore.MOD_ID, "textures/gui/diffuser.png");

    public ScreenDiffuser(ContainerDiffuser container, PlayerInventory playerInventory, ITextComponent title)
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
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y)
    {
        this.getMinecraft().getTextureManager().bind(TEXTURE);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;

        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y)
    {
        this.font.draw(matrixStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
    }
}

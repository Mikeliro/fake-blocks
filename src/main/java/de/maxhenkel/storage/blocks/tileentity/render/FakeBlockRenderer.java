package de.maxhenkel.storage.blocks.tileentity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.maxhenkel.storage.blocks.ModBlocks;
import de.maxhenkel.storage.blocks.tileentity.FakeBlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ModelManager;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class FakeBlockRenderer extends TileEntityRenderer<FakeBlockTileEntity> {

    private static final ModelResourceLocation MODEL = new ModelResourceLocation("fixed", "");

    private Minecraft minecraft;

    public FakeBlockRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
        this.minecraft = Minecraft.getInstance();
    }

    @Override
    public void render(FakeBlockTileEntity target, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Block block = target.getBlock();
        BlockState state = ModBlocks.FAKE_BLOCK.getDefaultState();
        if (block != null) {
            BlockState s = block.getDefaultState();
            if (s.getRenderType() == BlockRenderType.MODEL) {
                state = s;
            }
        }

        matrixStackIn.push();
        BlockRendererDispatcher dispatcher = minecraft.getBlockRendererDispatcher();

        dispatcher.getBlockModelRenderer().renderModel(matrixStackIn.getLast(), bufferIn.getBuffer(Atlases.getSolidBlockType()), state, dispatcher.getModelForState(state), 1F, 1F, 1F, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        matrixStackIn.pop();
    }


}
package cavebiomes.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class MagmaCrustRenderer implements ISimpleBlockRenderingHandler{




	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		System.out.println("Called");
		renderOreBlockAsItem(Blocks.lava, block, metadata, 1F, renderer);
		//this is where the block gets an item renderer
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
		Block block, int modelId, RenderBlocks renderer) {

		renderer.renderBlockUsingTexture(Blocks.stone, x, y, z, Blocks.lava.getIcon(1, 0));

		renderer.renderStandardBlock(block, x, y, z);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return RenderRegisterer.MagmaCrustRenderType;
	}


    public void renderOreBlockAsItem(Block stone, Block overlay,int p_147800_2_, float p_147800_3_, RenderBlocks renderer) {
        //logger.info("rendering");
        Tessellator tessellator = Tessellator.instance;
        boolean flag = stone == Blocks.grass;

        if (stone == Blocks.dispenser || stone == Blocks.dropper || stone == Blocks.furnace)
        {
            p_147800_2_ = 3;
        }

        int j;
        float f1;
        float f2;
        float f3;

        if (renderer.useInventoryTint)
        {
            j = stone.getRenderColor(p_147800_2_);

            if (flag)
            {
                j = 16777215;
            }

            f1 = (j >> 16 & 255) / 255.0F;
            f2 = (j >> 8 & 255) / 255.0F;
            f3 = (j & 255) / 255.0F;
            GL11.glColor4f(f1 * p_147800_3_, f2 * p_147800_3_, f3 * p_147800_3_, 1.0F);
        }

        j = stone.getRenderType();
        renderer.setRenderBoundsFromBlock(stone);
        int k;
        {
            if (j == 16)
            {
                p_147800_2_ = 1;
            }

            stone.setBlockBoundsForItemRender();
            renderer.setRenderBoundsFromBlock(stone);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
            renderer.renderFaceYNeg(stone, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(stone, 0, p_147800_2_));
            renderer.renderFaceYNeg(stone, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(overlay, 0, p_147800_2_));
            tessellator.draw();

            if (flag && renderer.useInventoryTint)
            {
                k = stone.getRenderColor(p_147800_2_);
                f2 = (k >> 16 & 255) / 255.0F;
                f3 = (k >> 8 & 255) / 255.0F;
                float f4 = (k & 255) / 255.0F;
                GL11.glColor4f(f2 * p_147800_3_, f3 * p_147800_3_, f4 * p_147800_3_, 1.0F);
            }

            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            renderer.renderFaceYPos(stone, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(stone, 1, p_147800_2_));
            renderer.renderFaceYPos(overlay, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(overlay, 1, p_147800_2_));
            tessellator.draw();

            if (flag && renderer.useInventoryTint)
            {
                GL11.glColor4f(p_147800_3_, p_147800_3_, p_147800_3_, 1.0F);
            }

            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            renderer.renderFaceZNeg(stone, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(stone, 2, p_147800_2_));
            renderer.renderFaceZNeg(stone, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(overlay, 2, p_147800_2_));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            renderer.renderFaceZPos(stone, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(stone, 3, p_147800_2_));
            renderer.renderFaceZPos(stone, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(overlay, 3, p_147800_2_));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
            renderer.renderFaceXNeg(stone, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(stone, 4, p_147800_2_));
            renderer.renderFaceXNeg(stone, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(overlay, 4, p_147800_2_));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            renderer.renderFaceXPos(stone, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(stone, 5, p_147800_2_));
            renderer.renderFaceXPos(stone, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(overlay, 5, p_147800_2_));
            tessellator.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
    }

}

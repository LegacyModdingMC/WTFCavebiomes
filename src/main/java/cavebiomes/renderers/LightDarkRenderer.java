package cavebiomes.renderers;

import cavebiomes.utilities.ILightDarkBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class LightDarkRenderer implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {

		ILightDarkBlock lightblock = (ILightDarkBlock) block;
		IIcon iicon = lightblock.getUnlitIcon();

		if (lightblock.shouldBeLit(world, x, y, z) && world.getBlockMetadata(x,y,z) > 7){
			iicon = lightblock.getLitIcon();
		}
		else {
			iicon = lightblock.getUnlitIcon();
		}

		if (lightblock.isPlant()){
			renderCrossedSquares(renderer, iicon, block, x, y, z);
			return true;
		}


		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return RenderRegisterer.LightDarkRenderType;
	}
	/**
	 * Renders any block requiring crossed squares such as reeds, flowers, and mushrooms
	 */
	public boolean renderCrossedSquares(RenderBlocks renderer, IIcon iicon, Block p_147746_1_, int p_147746_2_, int p_147746_3_, int p_147746_4_)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(p_147746_1_.getMixedBrightnessForBlock(renderer.blockAccess, p_147746_2_, p_147746_3_, p_147746_4_));
		int l = p_147746_1_.colorMultiplier(renderer.blockAccess, p_147746_2_, p_147746_3_, p_147746_4_);
		float f = (l >> 16 & 255) / 255.0F;
		float f1 = (l >> 8 & 255) / 255.0F;
		float f2 = (l & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable)
		{
			float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
			float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
			float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
			f = f3;
			f1 = f4;
			f2 = f5;
		}

		tessellator.setColorOpaque_F(f, f1, f2);
		double d1 = p_147746_2_;
		double d2 = p_147746_3_;
		double d0 = p_147746_4_;
		//long i1;


		//IIcon iicon = this.getBlockIconFromSideAndMetadata(p_147746_1_, 0, this.blockAccess.getBlockMetadata(p_147746_2_, p_147746_3_, p_147746_4_));
		renderer.drawCrossedSquares(iicon, d1, d2, d0, 1.0F);
		return true;
	}
}

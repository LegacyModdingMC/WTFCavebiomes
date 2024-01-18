package cavebiomes.blocks.customplants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;

public class PlantsCavePlants extends BlockBush{

	 public PlantsCavePlants() {
		super();

	}
	private static final String[][] field_149860_M = new String[][] {{"flower_dandelion"}, {"flower_rose", "flower_blue_orchid", "flower_allium", "flower_houstonia", "flower_tulip_red", "flower_tulip_orange", "flower_tulip_white", "flower_tulip_pink", "flower_oxeye_daisy"}};

    @Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{

			this.blockIcon =iconRegister.registerIcon("minecraft:flower_blue_orchid");

	}






    @Override
	protected boolean canPlaceBlockOn(Block p_149854_1_)
	    {
	        return true;
	    }
	 @Override
	public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
	    {
	        return  true;
	    }

}

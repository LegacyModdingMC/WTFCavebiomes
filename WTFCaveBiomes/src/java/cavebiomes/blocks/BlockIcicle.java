package cavebiomes.blocks;

import java.util.Random;

import wtfcore.blocks.IAlphaMaskedBlock;
import wtfcore.proxy.ClientProxy;
import wtfcore.utilities.BlockSets;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cavebiomes.CaveBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockIcicle extends BlockPackedIce implements IAlphaMaskedBlock{

	public static Block IcicleSmall;
	public static Block IcicleLargeBase;
	public static Block IcicleLargeTip;

	public BlockIcicle()
	{
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);
		if (this == IcicleSmall){
			setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 1F, 0.8F);
		}
		else if (this == IcicleLargeBase){
			setBlockBounds(0.2F, 0F, 0.2F, .8F, 1F, .8F);
		}
		else {
			setBlockBounds(0.3F, 0.4F, 0.3F, 0.7F, 1F, 0.7F);
		}

	}

	public static void register(){
		String name = "icicle_small";
		IcicleSmall = new BlockIcicle().setBlockName(name);
		GameRegistry.registerBlock(IcicleSmall, name);
		ClientProxy.registerBlockOverlay(name, "minecraft:ice", name, CaveBiomes.alphaMaskDomain, true);

		name = "icicle_base";
		IcicleLargeBase = new BlockIcicle().setBlockName(name);
		GameRegistry.registerBlock(IcicleLargeBase, name);
		ClientProxy.registerBlockOverlay(name, "minecraft:ice", name, CaveBiomes.alphaMaskDomain, true);

		name = "icicle_tip";
		IcicleLargeTip = new BlockIcicle().setBlockName(name);
		GameRegistry.registerBlock(IcicleLargeTip, name);
		ClientProxy.registerBlockOverlay(name, "minecraft:ice", name, CaveBiomes.alphaMaskDomain, true);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		world.scheduleBlockUpdate(x, y, z, this, 1);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		this.checkAndDropBlock(world, x, y, z);
	}

	protected void checkAndDropBlock(World world, int x, int y, int z)
	{
		if (!this.canBlockStay(world, x, y, z))
		{
			world.setBlockToAir(x, y, z);
		}
	}

    @Override
	protected boolean canSilkHarvest()
    {
    	return true;
    }

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if (BlockSets.meltBlocks.contains(world.getBlock(x+1, y, z))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x-1, y, z))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x, y, z+1))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x+1, y, z-1))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x, y+1, z))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x, y-1, z))){return false;}
		if (world.isAirBlock(x, y+1, z)){return false;}
		return true;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)  {
	      return null;
	    }

	  	@Override
		public boolean isOpaqueCube() {
	        return false;
	    }

	    @Override
		public boolean renderAsNormalBlock() {
	        return false;
	    }

	    @Override
		public int getRenderType() {
	        return 1;
	    }

	    @Override
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister iconRegister)
		{
			this.blockIcon = iconRegister.registerIcon(CaveBiomes.modid + ":" + this.getUnlocalizedName().substring(5));
		}


	    @Override
		public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	    {
	        return null;
	    }
}

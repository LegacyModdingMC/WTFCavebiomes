package cavebiomes.blocks;

import java.util.Random;

import cavebiomes.CaveBiomes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import wtfcore.api.BlockSets;

public class IcePatch extends BlockPackedIce{
	public IcePatch()
	{
		super();
		this.slipperiness = 0.98F;
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
		this.setStepSound(soundTypeGlass);
		this.setTickRandomly(false);
		this.setLightOpacity(0);

	}
	
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		world.scheduleBlockUpdate(x, y, z, this, 20);
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

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        Block block = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_);
        return block == this ? false : super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
    }

	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if (!world.getBlock(x, y-1, z).renderAsNormalBlock()){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x+1, y, z))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x-1, y, z))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x, y, z+1))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x, y, z-1))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x, y+1, z))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x+1, y, z+1))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x-1, y, z-1))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x+1, y, z-1))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x-1, y, z+1))){return false;}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return Blocks.ice.getIcon(p_149691_1_, p_149691_2_);
	}

    @Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
    	return true;
    }

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return null;
	}
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		byte b0 = 0;
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox(p_149668_2_ + this.minX, p_149668_3_ + this.minY, p_149668_4_ + this.minZ, p_149668_2_ + this.maxX, p_149668_3_ + b0 * f, p_149668_4_ + this.maxZ);
	}
	
	

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * Sets the block's bounds for rendering it as an item
	 */
		/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	{
		return this.canBlockStay(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_);
	}

	@Override
	public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}




}

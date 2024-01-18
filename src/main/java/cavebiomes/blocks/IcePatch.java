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
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import wtfcore.api.BlockSets;

public class IcePatch extends BlockPackedIce {
	public IcePatch() {
		super();
		slipperiness = 0.98F;
		setCreativeTab(CaveBiomes.tabCaveDecorations);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
		setStepSound(soundTypeGlass);
		setTickRandomly(false);
		setLightOpacity(0);

	}

    public boolean renderAsNormalBlock()
    {
        return false;
    }

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		world.scheduleBlockUpdate(x, y, z, this, 20);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		checkAndDropBlock(world, x, y, z);
	}

	protected void checkAndDropBlock(World world, int x, int y, int z) {
		if(!canBlockStay(world, x, y, z)) {
			world.setBlockToAir(x, y, z);
		}
	}

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        Block block = blockAccess.getBlock(x, y, z);
        return block == this ? false : super.shouldSideBeRendered(blockAccess, x, y, z, side);
    }

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		byte b0 = 0;
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + b0 * f, z + maxZ);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	/**
	 * Checks to see if it is valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return this.canBlockStay(world, x, y, z);
	}

	@Override
	public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
		return true;
	}

}

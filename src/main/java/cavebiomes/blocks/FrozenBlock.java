package cavebiomes.blocks;

import java.util.List;
import java.util.Random;
import cavebiomes.proxy.CBClientProxy;
import cavebiomes.renderers.RenderRegisterer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;
import wtfcore.api.BlockSets;

public class FrozenBlock extends BlockPackedIce {
	public Block outterBlock;
	public Block innerBlock;

	protected String[] textureNames;
	protected String[] parentLocations;

	protected FrozenBlock(Block outter, Block inner, String[] parentNames, String domain) {
		super();

		outterBlock = outter;
		innerBlock = inner;
		setStepSound(outter.stepSound);
		setBlockBounds((float)outter.getBlockBoundsMinX(), (float)outter.getBlockBoundsMinY(), (float)outter.getBlockBoundsMinZ(), (float)outter.getBlockBoundsMaxX(), (float)outter.getBlockBoundsMaxY(), (float)outter.getBlockBoundsMaxZ());
		loadTextureStrings(parentNames, domain);
	}

	public void loadTextureStrings(String[] stoneNames, String domain){
		String[] tempTextureNames = new String [stoneNames.length];
		String[] tempParentLocations = new String [stoneNames.length];

		for (int loop = 0; loop < stoneNames.length; loop++){
			tempTextureNames[loop] = "frozen_"+stoneNames[loop];
			tempParentLocations[loop] = domain+":"+stoneNames[loop];
		}

		this.textureNames = stoneNames;
		this.parentLocations = tempParentLocations;

	}

	@Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float subX, float subY, float subZ, int meta)  {
        return innerBlock.onBlockPlaced(world, x, y, z, side, subX, subY, subZ, meta);
    }
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
		innerBlock.onBlockPlacedBy(world, x, y, z, placer, itemIn);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < textureNames.length; ++i) {
			list.add(new ItemStack(item, 1, i));
		}
	}

    @Override
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
    	if (world.getBlock(x, y, z) instanceof FrozenBlock){return false;}
        return side == 0 && this.minY > 0.0D ? true : (side == 1 && this.maxY < 1.0D ? true : (side == 2 && this.minZ > 0.0D ? true : (side == 3 && this.maxZ < 1.0D ? true : (side == 4 && this.minX > 0.0D ? true : (side == 5 && this.maxX < 1.0D ? true : !world.getBlock(x, y, z).isOpaqueCube())))));
    }

    @Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    	return true;
    }

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return outterBlock.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return innerBlock.getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return outterBlock.getIcon(side, world.getBlockMetadata(x, y, z));
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
	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		return RenderRegisterer.FrozenBlockRenderType;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		world.scheduleBlockUpdate(x, y, z, this, 20);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if(!this.canBlockStay(world, x, y, z)) {
			world.setBlock(x, y, z, innerBlock);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		if (BlockSets.meltBlocks.contains(world.getBlock(x, y-1, z))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x+1, y, z))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x-1, y, z))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x, y, z+1))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x+1, y, z-1))){return false;}
		if (BlockSets.meltBlocks.contains(world.getBlock(x, y+1, z))){return false;}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass(){
		return 1;
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return outterBlock.getItemDropped(metadata, random, fortune);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int meta) {
		outterBlock.breakBlock(world, x, y, z, p_149749_5_, meta);
	}

	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
		return ForgeHooks.blockStrength(outterBlock, player, world, x, y, z);
	}

	@Override
	public void dropXpOnBlockBreak(World world, int p_149657_2_, int p_149657_3_, int p_149657_4_, int p_149657_5_){
		outterBlock.dropXpOnBlockBreak(world, p_149657_2_, p_149657_3_, p_149657_4_, p_149657_5_);
	}
	@Override
	public float getExplosionResistance(Entity p_149638_1_) {
		return outterBlock.getExplosionResistance(p_149638_1_);
	}

    @Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        if (!EnchantmentHelper.getSilkTouchModifier(player)){
        	world.setBlock(x, y, z, innerBlock, world.getBlockMetadata(x,y,z),3);
        } else {
        	world.setBlockToAir(x, y, z);
        }
    	return true;
    }

	@Override
	public int damageDropped(int metadata){
		return outterBlock.damageDropped(metadata);
	}

	@Override
	public boolean canRenderInPass(int pass) {
		//Set the static var in the client proxy
		CBClientProxy.renderPass = pass;
		//the block can render in both passes, so return true always
		return true;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return outterBlock.getBlockHardness(world, x, y, z);
	}

	@Override
	public String getHarvestTool(int metadata) {
		return outterBlock.getHarvestTool(metadata);
	}

	@Override
	public int getHarvestLevel(int metadata) {
		return outterBlock.getHarvestLevel(metadata);
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return Blocks.fire.getFlammability(outterBlock);
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return outterBlock.getFlammability(world, x, y, z, face) > 0;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return outterBlock.getFireSpreadSpeed(world, x, y, z, face);
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta) {
		return outterBlock.canHarvestBlock(player, meta);
	}

}

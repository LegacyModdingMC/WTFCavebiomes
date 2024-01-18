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
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;
import wtfcore.api.BlockSets;

public class FrozenBlock extends BlockPackedIce// implements AlphaMaskedBlock
{
	public Block outterBlock;
	public Block innerBlock;
	private IIcon[] textures;

	protected String[] textureNames;
	protected String[] parentLocations;

	protected FrozenBlock(Block outter, Block inner, String[] parentNames, String domain) {
		super();

		this.outterBlock = outter;
		this.innerBlock = inner;
		this.setStepSound(outter.stepSound);
		this.setBlockBounds((float)outter.getBlockBoundsMinX(), (float)outter.getBlockBoundsMinY(), (float)outter.getBlockBoundsMinZ(), (float)outter.getBlockBoundsMaxX(), (float)outter.getBlockBoundsMaxY(), (float)outter.getBlockBoundsMaxZ());
		this.loadTextureStrings(parentNames, domain);
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
/*
	@Override
	public void registerBlockIcons(IIconRegister iconRegister){

		textures = new IIcon[16];

		for (int loop = 0; loop < textureNames.length; loop++){
			textures[loop] = iconRegister.registerIcon(CaveBiomes.modid+":"+textureNames[loop]);
			ClientProxy.registerDerivativeBlockOverlay(textureNames[loop], parentLocations[loop], "ice", "minecraft");
		}
	}
	*/
	


	@Override
    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
    {
        return innerBlock.onBlockPlaced(p_149660_1_, p_149660_2_, p_149660_3_, p_149660_4_, p_149660_5_, p_149660_6_, p_149660_7_, p_149660_8_, p_149660_9_);
    }
	
	@Override
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		innerBlock.onBlockPlacedBy(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_, p_149689_6_);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < 8; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

    @Override
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
    	if (world.getBlock(x, y, z) instanceof FrozenBlock){return false;}
        return side == 0 && this.minY > 0.0D ? true : (side == 1 && this.maxY < 1.0D ? true : (side == 2 && this.minZ > 0.0D ? true : (side == 3 && this.maxZ < 1.0D ? true : (side == 4 && this.minX > 0.0D ? true : (side == 5 && this.maxX < 1.0D ? true : !world.getBlock(x, y, z).isOpaqueCube())))));
    }

    @Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
    	return true;
    }
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return outterBlock.getCollisionBoundingBoxFromPool(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return outterBlock.getIcon(p_149691_1_, p_149691_2_);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		return outterBlock.getIcon(side, world.getBlockMetadata(x, y, z));
	}

	@Override
	public boolean isOpaqueCube(){return false;}
	@Override
	public boolean renderAsNormalBlock(){return false;}
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		return RenderRegisterer.FrozenBlockRenderType;
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
			world.setBlock(x, y, z, innerBlock);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
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
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
	{
		outterBlock.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	}
	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer p_149737_1_, World p_149737_2_, int p_149737_3_, int p_149737_4_, int p_149737_5_)
	{
		return ForgeHooks.blockStrength(outterBlock, p_149737_1_, p_149737_2_, p_149737_3_, p_149737_4_, p_149737_5_);
	}
	@Override
	public void dropXpOnBlockBreak(World p_149657_1_, int p_149657_2_, int p_149657_3_, int p_149657_4_, int p_149657_5_){
		outterBlock.dropXpOnBlockBreak(p_149657_1_, p_149657_2_, p_149657_3_, p_149657_4_, p_149657_5_);
	}
	@Override
	public float getExplosionResistance(Entity p_149638_1_)
	{
		return outterBlock.getExplosionResistance(p_149638_1_);
	}


    @Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
    {
        if (!EnchantmentHelper.getSilkTouchModifier(player)){
        	world.setBlock(x, y, z, innerBlock, world.getBlockMetadata(x,y,z),3);
        }
        else{
        	world.setBlockToAir(x, y, z);
        }
    	return true;
    }



	@Override
	public int damageDropped(int metadata){
		return outterBlock.damageDropped(metadata);
	}
	@Override
	public boolean canRenderInPass(int pass)
	{
		//Set the static var in the client proxy
		CBClientProxy.renderPass = pass;
		//the block can render in both passes, so return true always
		return true;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		return outterBlock.getBlockHardness(world, x, y, z);
	}

	@Override
	public String getHarvestTool(int metadata)
	{
		return outterBlock.getHarvestTool(metadata);
	}
	@Override
	public int getHarvestLevel(int metadata)
	{
		return outterBlock.getHarvestLevel(metadata);
	}
	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return Blocks.fire.getFlammability(outterBlock);
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return outterBlock.getFlammability(world, x, y, z, face) > 0;
	}
	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return outterBlock.getFireSpreadSpeed(world, x, y, z, face);
	}
	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		return outterBlock.canHarvestBlock(player, meta);
	}
}

package cavebiomes.blocks;
import java.util.List;
import wtfcore.blocks.ChildBlockCarryMetadata;
import wtfcore.items.ItemMetadataSubblock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import texturegeneratorlib.TextureGeneratorLib;
import cavebiomes.CaveBiomes;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpeleothems extends ChildBlockCarryMetadata
{
	public  Block parentBlock;
	public static  Block blockToRegister;
	protected int speleothemType;
	private IIcon[] textures;


	protected String[] textureNames;
	protected String[] parentLocations;
	protected String[] maskType;


	public static final int stalactitesmall =0;
	public static final int stalactitelargebase = 1;
	public static final int stalactitelargetip =2;
	public static final int largecolumn = 3;
	public static final int stalagmitesmall = 4;
	public static final int stalagmitelargebase= 5;
	public static final int stalagmitelargetip = 6;

	public static String[] formationList ={"stalactite_small", "stalactite_base", "stalactite_tip", "column", "stalagmite_small", "stalagmite_base", "stalagmite_tip"};

	protected BlockSpeleothems(Block block,int type, String[] stoneNames, String domain) {
		super(block);
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);
		this.parentBlock = block;
		this.speleothemType = type;
		this.loadTextureStrings(stoneNames, domain);
	}

	public static void registerSpeleothemSet(Block block, String unlocalisedName, String[] stoneNames, String domain){

		BlockSpeleothems[] blockArray = new BlockSpeleothems[8];
		FrozenBlock[] frozenblockArray = new FrozenBlock[8];

		for (int formationTypeLoop = 0; formationTypeLoop < formationList.length; formationTypeLoop++){

			String name = unlocalisedName+"_"+formationList[formationTypeLoop];
			blockToRegister = new BlockSpeleothems(block, formationTypeLoop, stoneNames, domain).setBlockName(name);
			GameRegistry.registerBlock(blockToRegister, ItemMetadataSubblock.class, name);


			String[] frozenstoneNames = new String[stoneNames.length];
			for (int loop = 0; loop < stoneNames.length; loop++){
				frozenstoneNames[loop] = stoneNames[loop]+"_"+formationList[formationTypeLoop];
			}

			Block frozenBlockToRegister = new FrozenBlock(Blocks.ice, blockToRegister, frozenstoneNames, TextureGeneratorLib.overlayDomain).setBlockName("frozen_"+name);
			GameRegistry.registerBlock(frozenBlockToRegister, ItemMetadataSubblock.class, "frozen_"+name);

			frozenblockArray[formationTypeLoop] = (FrozenBlock) frozenBlockToRegister;
			blockArray[formationTypeLoop] = (BlockSpeleothems) blockToRegister;
		}

		if (block == Blocks.redstone_block){
			CaveBlocks.speleothemMap.put(Blocks.redstone_ore, blockArray);
			CaveBlocks.frozenspeleothemMap.put(Blocks.redstone_ore, frozenblockArray);
		}
		CaveBlocks.speleothemMap.put(block, blockArray);
		CaveBlocks.frozenspeleothemMap.put(block, frozenblockArray);

	}

	public void loadTextureStrings(String[] stoneNames, String domain){
		String[] tempTextureNames = new String [stoneNames.length];
		String[] tempParentLocations = new String [stoneNames.length];

		for (int loop = 0; loop < stoneNames.length; loop++){
			tempTextureNames[loop] = stoneNames[loop]+"_"+formationList[speleothemType];
			tempParentLocations[loop] = domain+":"+stoneNames[loop];
		}

		this.textureNames = tempTextureNames;
		this.parentLocations = tempParentLocations;
		this.maskType = BlockSpeleothems.formationList;

	}

    @Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
    	return true;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < textureNames.length; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public boolean isOpaqueCube(){return false;}
	@Override
	public boolean renderAsNormalBlock(){return false;}

	@Override
	public int getRenderType(){return 1;}

		@Override
	public void registerBlockIcons(IIconRegister iconRegister){

		textures = new IIcon[16];

		for (int loop = 0; loop < textureNames.length; loop++){
			textures[loop] = iconRegister.registerIcon(CaveBiomes.modid+":"+textureNames[loop]);
			TextureGeneratorLib.registerBlockOverlay(this, textureNames[loop], parentLocations[loop], maskType[speleothemType], CaveBiomes.alphaMaskDomain, true);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return textures[meta];
	}


	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)	    {
		return null;
	}


	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
	{
		return this.getIcon(p_149673_5_, p_149673_1_.getBlockMetadata(p_149673_2_, p_149673_3_, p_149673_4_));
	}

	public int getSpeleothemType(){
		return this.speleothemType;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
		switch (getSpeleothemType()){

		case stalactitesmall :
			setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 1F, 0.8F);
			break;
		case stalactitelargebase :
			setBlockBounds(0.2F, 0F, 0.2F, .8F, 1F, .8F);
			break;
		case stalactitelargetip :
			setBlockBounds(0.3F, 0.4F, 0.3F, 0.7F, 1F, 0.7F);
			break;
		case largecolumn :
			setBlockBounds(0.3F, 0F, 0.3F, 0.7F, 1F, 0.7F);
			break;
		case stalagmitesmall :
			setBlockBounds(0.2F, 0F, 0.2F, 0.8F, 0.8F, 0.8F);
			break;
		case stalagmitelargebase :
			setBlockBounds(0.2F, 0F, 0.2F, .8F, 1F, .8F);
			break;
		case stalagmitelargetip :
			setBlockBounds(0.3F, 0F, 0.3F, 0.7F, 0.7F, 0.7F);
			break;

		}


	}





}

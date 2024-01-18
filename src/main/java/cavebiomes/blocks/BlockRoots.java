package cavebiomes.blocks;

import java.util.List;
import wtfcore.blocks.ChildBlockCarryMetadata;
import wtfcore.items.ItemMetadataSubblock;
import cavebiomes.CaveBiomes;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

public class BlockRoots extends ChildBlockCarryMetadata {



	private IIcon[] textures;
	protected String[] textureNames = {"roots"};
	protected String[] parentLocations = {"minecraft:log_oak"};
	protected String maskType = "roots";

	protected BlockRoots(Block block) {
		super(block);
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);
	}

	public static void registerRoots(){

		String name = "roots";
		CaveBlocks.Roots =  new BlockRoots(Blocks.log).setBlockName(name);
		GameRegistry.registerBlock(CaveBlocks.Roots, ItemMetadataSubblock.class, name);


		String names[] = {"frozen_roots"};
		CaveBlocks.frozenRoots = new FrozenBlock(Blocks.ice, CaveBlocks.Roots, names, TextureGeneratorLib.overlayDomain).setBlockName(names[0]);
		GameRegistry.registerBlock(CaveBlocks.frozenRoots, ItemMetadataSubblock.class, names[0]);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister){

		textures = new IIcon[16];

		for (int loop = 0; loop < textureNames.length; loop++){
			textures[loop] = iconRegister.registerIcon(CaveBiomes.modid+":"+textureNames[loop]);
			TextureGeneratorLib.registerBlockOverlay(this, textureNames[loop], parentLocations[loop], maskType, CaveBiomes.alphaMaskDomain, true);
		}
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
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
    	return true;
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



}

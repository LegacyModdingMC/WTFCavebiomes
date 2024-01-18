
package cavebiomes.blocks;

import java.util.List;
import java.util.Random;
import wtfcore.api.BlockInfo;
import wtfcore.api.BlockSets;
import wtfcore.api.BlockSets.Modifier;
import wtfcore.blocks.ChildBlockCarryMetadata;
import wtfcore.items.ItemMetadataSubblock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cavebiomes.CaveBiomes;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.renderers.RenderRegisterer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import texturegeneratorlib.TextureGeneratorLib;

public class BlockMagmaCrust extends ChildBlockCarryMetadata {

		public ResourceLocation resourceDomain;
	public ResourceLocation textureLocation;


	private IIcon[] textures;
	protected String[] textureNames;
	protected String[] parentLocations;

	public BlockMagmaCrust(Block block, String[] stoneNames, String domain)
	{
		super(block);
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);
		this.loadTextureStrings(stoneNames, domain);
		if (WTFCaveBiomesConfig.lavacrustGlow){
			this.setLightLevel(0.2F);// THIS HAS TO BE KEPT AS A FLOAT
		}
	}
	
	public void loadTextureStrings(String[] stoneNames, String domain){
		textureNames = new String [stoneNames.length];
		parentLocations = new String [stoneNames.length];

		for (int loop = 0; loop < stoneNames.length; loop++){
			textureNames[loop] = stoneNames[loop]+"_lavacrust";
			parentLocations[loop] = domain+":"+stoneNames[loop];
		}
	}

	public static Block registerMagmaCrust(Block block, String unlocalisedName, String[] stoneNames, String domain){
		unlocalisedName = unlocalisedName+"_lavacrust";
		Block blockToRegister = new BlockMagmaCrust(block, stoneNames, domain).setBlockName(unlocalisedName);
		GameRegistry.registerBlock(blockToRegister, ItemMetadataSubblock.class, unlocalisedName);
		
		BlockSets.blockTransformer.put(new BlockInfo(block, 0, Modifier.stoneMagmaCrust), blockToRegister);
		BlockSets.meltBlocks.add(blockToRegister);
				
		return blockToRegister;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister){
		textures = new IIcon[16];
		for (int loop = 0; loop < textureNames.length; loop++){
			textures[loop] = iconRegister.registerIcon(CaveBiomes.modid +":"+ textureNames[loop]);
			TextureGeneratorLib.registerBlockOverlay(this, textureNames[loop], parentLocations[loop], "lavacrust",  CaveBiomes.alphaMaskDomain, true);
		}
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
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return textures[meta];
	}

    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        return getIcon(side, world.getBlockMetadata(x, y, z));
    }

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock(){return false;}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		return RenderRegisterer.MagmaCrustRenderType;
	}
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		world.setBlock(x, y, z, Blocks.flowing_lava, 0, 3);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (!WTFCaveBiomesConfig.lavacrustAnimations){return;}
		//int l;

        double d5;
        double d6;
        double d7;

        if (random.nextInt(30) == 0 && world.isAirBlock(x, y-1, z) && WTFCaveBiomesConfig.enableDrippingBlocks)
        {
            d5 = x + random.nextFloat();
            d6 = y - 0.05D;
            d7 = z + random.nextFloat();

            world.spawnParticle("dripLava", d5, d6, d7, 0.0D, 0.0D, 0.0D);

        }


        if (random.nextInt(15) == 0 && world.isAirBlock(x,  y+1,  z))
        {
            d5 = x + random.nextFloat();
            d6 = y + this.maxY;
            d7 = z + random.nextFloat();
            world.spawnParticle("smoke", d5, d6, d7, 0.0D, 0.0D, 0.0D);
            world.playSound(d5, d6, d7, "liquid.lavapop", 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
        }

        if (random.nextInt(200) == 0)
        {
            world.playSound(x, y, z, "liquid.lava", 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
        }

    }
}

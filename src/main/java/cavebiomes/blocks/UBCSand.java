package cavebiomes.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import wtfcore.api.BlockInfo;
import wtfcore.api.BlockSets;
import wtfcore.items.ItemMetadataSubblock;
import wtfcore.utilities.UBCblocks;
import cavebiomes.CaveBiomes;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.blocks.replacers.UBCSandReplacer;
import cavebiomes.blocks.replacers.UBCSandstoneReplacer;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import exterminatorJeff.undergroundBiomes.api.NamedVanillaItem;
import exterminatorJeff.undergroundBiomes.api.UBIDs;
import exterminatorJeff.undergroundBiomes.common.UndergroundBiomes;
import exterminatorJeff.undergroundBiomes.common.item.ItemFossilPiece;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class UBCSand extends BlockGravel{
	private IIcon[] textures;
	public static Block sedimentarySand;
	public static String[] SedimentarySandTextures = new String[8];

	public UBCSand() {
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);
		this.blockHardness = 0.5F;
		this.setStepSound(soundTypeGravel);

	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	public static void register(){

		if (Loader.isModLoaded("UndergroundBiomes")){
	
			sedimentarySand =  new UBCSand().setBlockName("sedimentarySand");
			GameRegistry.registerBlock(sedimentarySand, ItemMetadataSubblock.class, "sedimentarySand");
			if (WTFCaveBiomesConfig.ReplaceVanillaSand){
				new UBCSandReplacer(Blocks.sand);
	            new UBCSandstoneReplacer(Blocks.sandstone);
			}
			
			BlockSets.blockTransformer.put(new BlockInfo(UBCblocks.SedimentaryStone, 0, BlockSets.Modifier.cobblestone), UBCSand.sedimentarySand);
			OreDictionary.registerOre("sand", sedimentarySand);
			
			//recipes
			for (int loop = 0; loop < 8; loop++){
				GameRegistry.addShapelessRecipe(new ItemStack(UBCblocks.SedimentaryStone, 4, loop), new Object[] {new ItemStack(UBCSand.sedimentarySand, 1, loop), new ItemStack(UBCSand.sedimentarySand, 1, loop), new ItemStack(UBCSand.sedimentarySand, 1, loop), new ItemStack(UBCSand.sedimentarySand, 1, loop)});
			}
			
			//StainedGlass
			//item size meta
			GameRegistry.addSmelting(new ItemStack(UBCSand.sedimentarySand, 1, 0), new ItemStack(Blocks.stained_glass, 1, 9), 0F);
			GameRegistry.addSmelting(new ItemStack(UBCSand.sedimentarySand, 1, 1), new ItemStack(Blocks.stained_glass, 1, 0), 0F);
			GameRegistry.addSmelting(new ItemStack(UBCSand.sedimentarySand, 1, 2), new ItemStack(Blocks.stained_glass, 1, 8), 0F);
			GameRegistry.addSmelting(new ItemStack(UBCSand.sedimentarySand, 1, 3), new ItemStack(Blocks.stained_glass, 1, 1), 0F);
			GameRegistry.addSmelting(new ItemStack(UBCSand.sedimentarySand, 1, 4), new ItemStack(Blocks.stained_glass, 1, 15), 0F);
			GameRegistry.addSmelting(new ItemStack(UBCSand.sedimentarySand, 1, 5), new ItemStack(Blocks.stained_glass, 1, 4), 0F);
			GameRegistry.addSmelting(new ItemStack(UBCSand.sedimentarySand, 1, 6), new ItemStack(Blocks.stained_glass, 1, 7), 0F);
			GameRegistry.addSmelting(new ItemStack(UBCSand.sedimentarySand, 1, 7), new ItemStack(Blocks.stained_glass, 1, 12), 0F);
			
			
			GameRegistry.addSmelting(new ItemStack(Blocks.sand, 1, 1), new ItemStack(Blocks.stained_glass, 1, 14), 0F);
			
		}
	}

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune){
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        int count = 1;
        ItemStack stack = itemDropped(metadata, world.rand, fortune, y);

        if ((fortune != 0) && (UndergroundBiomes.fortuneAffected.contains(stack.getItem().getUnlocalizedName())))
        {
            // Fortune III gives up to 4 items
            int j = world.rand.nextInt(fortune + 2);
            count = (j < 1) ? 1 : j;
        }
        for(int i = 0; i < count; i++){
            ret.add(stack);
        }
        return ret;
    }
	
    public ItemStack itemDropped(int metadata, Random random, int fortune, int y)
    {
        // Rare drops
        if ((metadata < 8) && (random.nextInt(64) <= fortune))
        {
            // Shale drops clay
            if (metadata == 2)
            {
                return new ItemStack(NamedVanillaItem.clay.cachedItem(), 1, 0);
            }
            // Limestone, chalk, siltstone, lignite and dolomite drop fossil pieces
            if (metadata == 0 || metadata == 1 || metadata == 3 || metadata == 4 || metadata == 5)
            {
                return new ItemStack(UBIDs.fossilPieceName.cachedItem(), 1, random.nextInt(ItemFossilPiece.TYPES));
            }
            // Chert drops flint item
            if (metadata == 7)
            {
                return new ItemStack(NamedVanillaItem.flint.cachedItem(), 1, 0);
            }
        }
        if ((metadata & 7) == 4) return new ItemStack(UBIDs.ligniteCoalName.cachedItem(), 1, 0);
        return new ItemStack(this, 1, metadata & 7);
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
	public void registerBlockIcons(IIconRegister iconRegister){

		textures = new IIcon[16];

		for (int metaloop=0; metaloop < 8; metaloop++){
			this.textures[metaloop]= iconRegister.registerIcon(CaveBiomes.modid + ":" + UBCblocks.SedimentaryStoneList[metaloop]+"_sand");
			SedimentarySandTextures[metaloop] =  UBCblocks.SedimentaryStoneList[metaloop]+"_sand";
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return textures[meta];
	}
}

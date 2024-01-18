package cavebiomes.blocks;

import java.util.List;
import java.util.Random;

import wtfcore.api.BlockInfo;
import wtfcore.api.BlockSets;
import wtfcore.blocks.ChildBlockCarryMetadata;
import wtfcore.items.ItemMetadataSubblock;
import cavebiomes.CaveBiomes;
import cavebiomes.WTFCaveBiomesConfig;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DrippingBlock extends ChildBlockCarryMetadata{

	boolean isWater;

	int dripFreq;
	private int numsubblocks;


	protected DrippingBlock(Block block, int subblocks, BlockSets.Modifier drip, int freq) {
		super(block);
		this.dripFreq = freq;
		if (drip == BlockSets.Modifier.waterDrippingStone || drip == BlockSets.Modifier.WaterRainStone){
			this.isWater=true;
			}
		else {
			isWater = false;
		}
		this.setTickRandomly(WTFCaveBiomesConfig.enableDrippingBlocks);
		this.numsubblocks=subblocks;
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);
	}

	public static void registerDrippingStoneSet(Block block, String name, int subBlocks, boolean water, boolean lava){
		if (!name.endsWith("stone")){
			name=name+"_stone";
		}
		if (water){
			registerDrippingBlock(block, subBlocks, BlockSets.Modifier.waterDrippingStone, 1, "dripping_water_"+name);
			registerDrippingBlock(block, subBlocks, BlockSets.Modifier.WaterRainStone, 20, "raining_water_"+name);
		}
		if (lava){
			registerDrippingBlock(block, subBlocks, BlockSets.Modifier.lavaDrippinStone, 1, "dripping_lava_"+name);
			registerDrippingBlock(block, subBlocks, BlockSets.Modifier.LavaRainStone, 20, "raining_lava_"+name);	
		}
	}
	
	public static void registerDrippingBlock(Block block, int numSubBlocks, BlockSets.Modifier fluid, int frequency, String name){

		Block blockToRegister = new DrippingBlock(block, numSubBlocks, fluid, frequency ).setBlockName(name);
		GameRegistry.registerBlock(blockToRegister, ItemMetadataSubblock.class, name);
		for (int loop = 0; loop < 8; loop ++){
			BlockSets.blockTransformer.put(new BlockInfo(block, loop, fluid), blockToRegister);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list){
		for (int i = 0; i < numsubblocks+1; ++i){
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	    public void randomDisplayTick(World world, int x, int y, int z, Random random)
	    {
	        //int l;

	        double d5;
	        double d6;
	        double d7;

	        if (random.nextInt(20) < dripFreq && world.getBlock(x, y-1, z).isOpaqueCube() && WTFCaveBiomesConfig.enableDrippingBlocks)
	        {
	            d5 = x + random.nextFloat();
	            d6 = y - 0.05D;
	            d7 = z + random.nextFloat();

	            if (isWater)
	            {
	                world.spawnParticle("dripWater", d5, d6, d7, 0.0D, 0.0D, 0.0D);
	            }
	            else
	            {
	                world.spawnParticle("dripLava", d5, d6, d7, 0.0D, 0.0D, 0.0D);
	            }
	        }
	    }
    @Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
    	return true;
    }
}

package cavebiomes.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import texturegeneratorlib.TextureGeneratorLib;
import wtfcore.InterModBlocks;
import wtfcore.items.ItemMetadataSubblock;


public class RedstoneSpeleothem extends BlockSpeleothems {

	private boolean isLit;
	private static BlockSpeleothems[] unlitRedstoneSpeleothems;
	private static BlockSpeleothems[] litRedstoneSpeleothems;


	protected RedstoneSpeleothem(Block block, int type, String[] stoneNames, String domain) {
		super(block, type, stoneNames, domain);
		if (this.parentBlock == Blocks.lit_redstone_ore){
			this.setLightLevel(0.67F);
			this.isLit = true;
		}
	}


	public static void registerSpeleothemSet(Block block, String unlocalisedName, String[] stoneNames, String domain){

		BlockSpeleothems[] blockArray = new BlockSpeleothems[8];
		FrozenBlock[] frozenblockArray = new FrozenBlock[8];

		for (int formationTypeLoop = 0; formationTypeLoop < formationList.length; formationTypeLoop++){

			String name;
			if (block == Blocks.lit_redstone_ore){
				name ="lit_redstone"+"_"+formationList[formationTypeLoop];
			}
			else {
				name = "redstone"+"_"+formationList[formationTypeLoop];
			}
			blockToRegister = new RedstoneSpeleothem(block, formationTypeLoop, stoneNames, domain).setBlockName(name);
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


		CaveBlocks.speleothemMap.put(Blocks.redstone_ore, blockArray);
		CaveBlocks.frozenspeleothemMap.put(Blocks.redstone_ore, frozenblockArray);

		if (block == Blocks.redstone_ore){
			unlitRedstoneSpeleothems = blockArray;
			InterModBlocks.unlitRedstoneSpeleothems = blockArray;
		}
		else if (block == Blocks.lit_redstone_ore){
			litRedstoneSpeleothems = blockArray;
		}

	}

	  @Override
		public int tickRate(World p_149738_1_)
	    {
	        return 30;
	    }
	    @Override
		public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_)
	    {
	        this.func_150185_e(p_149699_1_, p_149699_2_, p_149699_3_, p_149699_4_);
	        //super.onBlockClicked(p_149699_1_, p_149699_2_, p_149699_3_, p_149699_4_, p_149699_5_);
	    }
	    /*
	    public void onEntityWalking(World p_149724_1_, int p_149724_2_, int p_149724_3_, int p_149724_4_, Entity p_149724_5_)
	    {
	        this.func_150185_e(p_149724_1_, p_149724_2_, p_149724_3_, p_149724_4_);
	        super.onEntityWalking(p_149724_1_, p_149724_2_, p_149724_3_, p_149724_4_, p_149724_5_);
	    }
	    */
	    @Override
		public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	    {
	        this.func_150185_e(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
	        return super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
	    }

	    private void func_150185_e(World world, int x, int y, int z)
	    {
	        this.func_150186_m(world, x, y, z);

	        if (!isLit)
	        {
	            Block blockToSet = litRedstoneSpeleothems[this.speleothemType];
	        	world.setBlock(x, y, z, blockToSet, world.getBlockMetadata(x,y,z), 3);
	        }
	    }
	    @Override
		public void updateTick(World world, int x, int y, int z, Random random)
	    {
	        if (!world.isRemote && isLit)
	        {
	        	Block blockToSet = unlitRedstoneSpeleothems[this.speleothemType];
	        	world.setBlock(x, y, z, blockToSet);
	        }
	    }



	    private void func_150186_m(World world, int p_150186_2_, int p_150186_3_, int p_150186_4_)
	    {
	        Random random = world.rand;
	        double d0 = 0.0625D;

	        for (int l = 0; l < 6; ++l)
	        {
	            double d1 = p_150186_2_ + random.nextFloat();
	            double d2 = p_150186_3_ + random.nextFloat();
	            double d3 = p_150186_4_ + random.nextFloat();

	            if (l == 0 && !world.getBlock(p_150186_2_, p_150186_3_ + 1, p_150186_4_).isOpaqueCube())
	            {
	                d2 = p_150186_3_ + 1 + d0;
	            }

	            if (l == 1 && !world.getBlock(p_150186_2_, p_150186_3_ - 1, p_150186_4_).isOpaqueCube())
	            {
	                d2 = p_150186_3_ + 0 - d0;
	            }

	            if (l == 2 && !world.getBlock(p_150186_2_, p_150186_3_, p_150186_4_ + 1).isOpaqueCube())
	            {
	                d3 = p_150186_4_ + 1 + d0;
	            }

	            if (l == 3 && !world.getBlock(p_150186_2_, p_150186_3_, p_150186_4_ - 1).isOpaqueCube())
	            {
	                d3 = p_150186_4_ + 0 - d0;
	            }

	            if (l == 4 && !world.getBlock(p_150186_2_ + 1, p_150186_3_, p_150186_4_).isOpaqueCube())
	            {
	                d1 = p_150186_2_ + 1 + d0;
	            }

	            if (l == 5 && !world.getBlock(p_150186_2_ - 1, p_150186_3_, p_150186_4_).isOpaqueCube())
	            {
	                d1 = p_150186_2_ + 0 - d0;
	            }

	            if (d1 < p_150186_2_ || d1 > p_150186_2_ + 1 || d2 < 0.0D || d2 > p_150186_3_ + 1 || d3 < p_150186_4_ || d3 > p_150186_4_ + 1)
	            {
	                world.spawnParticle("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
	            }
	        }
	    }

}

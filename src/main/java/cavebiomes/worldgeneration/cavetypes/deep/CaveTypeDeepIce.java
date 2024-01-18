package cavebiomes.worldgeneration.cavetypes.deep;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import wtfcore.api.BlockSets;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;

public class CaveTypeDeepIce extends CaveType{


	
		public CaveTypeDeepIce(int cavedepth, DungeonSet coldSet) {
		super("DeepIce", cavedepth, coldSet);
	}

		@Override
		public void generateCeilingAddons(World world, Random random, int x, int y, int z)
		{
			gen.genStalactite(world, x, y, z, depth, Blocks.ice);
		}

		@Override
		public void generateFloor(World world, Random random, int x, int y, int z)
		{
			if (world.getBlock(x, y, z)==Blocks.lava){world.setBlock(x,y,z, Blocks.obsidian);}

			int height = 2*MathHelper.abs_int((MathHelper.abs_int(x/2+z)/2 % 10) -5)-3;
			
			if (height < -1){
				gen.replaceBlock(world, x, y, z, Blocks.obsidian, 0);
				gen.freezeBlock(world, x, y, z);

			}
			else{
				if (random.nextInt(4)==0){
					gen.setStoneAddon(world, x, y, z, BlockSets.Modifier.cobblestone);
					gen.freezeBlock(world, z, y+1, z);
				}
				else if (shouldGenFloorAddon(random)){
					gen.genStalagmite(world, x, y, z, depth, Blocks.ice);
				}
			}



		}




}

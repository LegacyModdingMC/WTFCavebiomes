package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import wtfcore.api.BlockSets;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;
import cavebiomes.blocks.CaveBlocks;

public class CaveTypeForest extends CaveType{

	public CaveTypeForest(int cavedepth, DungeonSet forestSet) {
		super("Forest", cavedepth, forestSet);
	}

	BlockSets.Modifier[] blockarray = {null, null, null, BlockSets.Modifier.MossyStone, BlockSets.Modifier.mossy_cobblestone};
	
	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y, z, depth);
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		int height = MathHelper.abs_int((MathHelper.abs_int(x) % 10) -5)+MathHelper.abs_int((MathHelper.abs_int(z) % 10) -5)+ (random.nextInt(3)+3);

		if (height >= 10)
		{
			if(random.nextBoolean()){
				gen.replaceBlock(world, x, y, z, CaveBlocks.MossyDirt, 0);
			}
			else{
				gen.replaceBlock(world, x, y, z, Blocks.dirt, 0);
			}
		}
		else{

			gen.transformBlock(world, x, y, z, blockarray[random.nextInt(blockarray.length)]);
			if (shouldGenFloorAddon(random)){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}
		
	}
}





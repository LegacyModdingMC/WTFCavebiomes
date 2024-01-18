package cavebiomes.worldgeneration.cavetypes.deep;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import wtfcore.api.BlockSets;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;

public class CaveTypeWetDeep extends CaveType{

	
	public CaveTypeWetDeep(int cavedepth, DungeonSet wetSet) {
		super("WetDeep", cavedepth, wetSet);
	}

	BlockSets.Modifier[] blockArray = {null, null, BlockSets.Modifier.cobblestone};

	@Override
	public void generateCeiling(World world, Random random, int x, int y, int z)
	{
		if (random.nextInt(4)==0){
			gen.transformBlock(world, x, y, z, BlockSets.Modifier.waterDrippingStone);
		}
	}

	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y, z, depth);
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		if (gen.IsBlockSurrounded(world, x, y, z)){
			if (random.nextInt(8) == 0){
				gen.transformBlock(world, x, y, z, BlockSets.Modifier.cobblestone);
			}
			else {
				gen.setFluid(world, x, y, z, Blocks.water, blockArray[random.nextInt(blockArray.length)]);
			}
		}
	}


}

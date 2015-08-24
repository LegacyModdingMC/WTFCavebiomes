package cavebiomes.worldgeneration.cavetypes.deep;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import wtfcore.utilities.BlockSets;
import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.DungeonSet;
import cavebiomes.worldgeneration.DungeonType;

public class CaveTypeVolcanic extends CaveType{

	public CaveTypeVolcanic(String name, int cavedepth,	DungeonSet volcanicSet) {
		super(name, cavedepth, volcanicSet);
	}

	BlockSets.Modifier[] blockarray = {BlockSets.Modifier.stoneMagmaCrust, BlockSets.Modifier.cobblestone};

	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y, z, depth);
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		int height = 2*MathHelper.abs_int((MathHelper.abs_int(x/2+z)/2 % 10) -5)-3;

		if (height < -1){
			if (IsBlockSurrounded(world, x, y, z)){
				gen.setFluid(world, x, y, z, Blocks.lava);
			}
		}
		else{
			if(random.nextInt(4)==0){
				gen.setStoneAddon(world, x, y, z, blockarray[random.nextInt(blockarray.length)]);
			}
			else if (random.nextBoolean()){
				gen.transformBlock(world, x, y, z, blockarray[random.nextInt(blockarray.length)]);
			}
			else if (shouldGenFloorAddon()){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}
	}

}

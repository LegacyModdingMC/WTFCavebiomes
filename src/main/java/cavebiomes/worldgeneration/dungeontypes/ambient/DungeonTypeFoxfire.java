package cavebiomes.worldgeneration.dungeontypes.ambient;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import cavebiomes.api.DungeonType;
import cavebiomes.blocks.customplants.Foxfire;

public class DungeonTypeFoxfire extends DungeonType {
	public DungeonTypeFoxfire() {
		super("Foxfire");
	}

	int xMin = -7;
	int xMax = 7;
	int yMin = -5;
	int yMax = -5;
	int zMin = -7;
	int zMax = 7;



	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		if (rand.nextInt(10) < 4){
			gen.setBlockWithoutNotify(world, x, y+2, z, Blocks.log, 1);
			gen.setBlockWithoutNotify(world, x, y+1, z, Blocks.log, 1);
			gen.setBlockWithoutNotify(world, x, y, z, Blocks.log, 1);
			gen.setBlockWithoutNotify(world, x, y-1, z, Blocks.log, 1);
			gen.setBlockWithoutNotify(world, x, y-2, z, Foxfire.hangingFoxfire, 0);
		}
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		if (rand.nextInt(10) < 4){
			gen.setBlockWithoutNotify(world, x, y-2, z, Blocks.log, 1);
			gen.setBlockWithoutNotify(world, x, y-1, z, Blocks.log, 1);
			gen.setBlockWithoutNotify(world, x, y, z, Blocks.log, 1);
			gen.setBlockWithoutNotify(world, x, y+1, z, Blocks.log, 1);
			gen.setBlockWithoutNotify(world, x, y+2, z, Foxfire.blockFoxfire, 0);
		}
	}


}
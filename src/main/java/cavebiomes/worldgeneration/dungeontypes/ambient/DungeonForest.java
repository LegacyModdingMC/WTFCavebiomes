package cavebiomes.worldgeneration.dungeontypes.ambient;

import java.util.Random;

import cavebiomes.api.DungeonType;
import cavebiomes.blocks.CaveBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DungeonForest extends DungeonType {
	public DungeonForest() {
		super("Forest");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){

	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.grass, 0);

		int spawn = rand.nextInt(10);
		if (spawn < 6){gen.setBlockWithoutNotify(world, x, y+1, z, Blocks.tallgrass, 1);}
		else if(spawn > 7){gen.setBlockWithoutNotify(world, x, y+1, z,CaveBlocks.CaveOrchid, 0);}
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){

	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		for (int yloop = ceiling+2; yloop > y; --yloop){
			gen.setBlockWithoutNotify(world, x, yloop, z, Blocks.log, 0);
		}
		gen.setBlockWithoutNotify(world, x, y, z,  CaveBlocks.GlowstoneStalactite, 0);
	}



}
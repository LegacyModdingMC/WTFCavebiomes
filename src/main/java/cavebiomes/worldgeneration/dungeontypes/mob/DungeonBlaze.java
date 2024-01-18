package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import wtfcore.api.BlockSets;
import cavebiomes.api.DungeonType;

public class DungeonBlaze extends DungeonType{

	public DungeonBlaze() {
		super("ClassicBlaze");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.transformBlock(world, x, y, z, BlockSets.Modifier.LavaRainStone);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x,  y,  z, Blocks.nether_brick, 0);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x,  y,  z, Blocks.nether_brick, 0);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		gen.spawnRareVanillaSpawner(world, x, floor+1, z, "Blaze");
	}
}

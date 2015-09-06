package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import cavebiomes.worldgeneration.dungeontypes.DungeonType;

public class DungeonClassicSkeleton extends DungeonType{

	public DungeonClassicSkeleton() {
		super("ClassicSkeleton");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,0);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,0);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		gen.spawnVanillaSpawner(world, x, floor+1, z, "Skeleton");
	}


}

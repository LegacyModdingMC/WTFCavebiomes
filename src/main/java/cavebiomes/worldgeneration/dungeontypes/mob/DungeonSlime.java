package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import cavebiomes.api.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DungeonSlime extends DungeonType{

	public DungeonSlime() {
		super("Slime");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		world.setBlock( x, y, z, Blocks.stonebrick, 2, 0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){

		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick, 2);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick, 2);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		gen.spawnRareVanillaSpawner(world, x, floor+1, z, "Slime");
	}


}

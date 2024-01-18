package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import cavebiomes.api.DungeonType;
import cavebiomes.entities.Entities;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DungeonTypeSkeletonKnight extends DungeonType{

	public DungeonTypeSkeletonKnight() {
		super("SkeletonKnight");
	}
	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,0);
		if (random.nextInt(5) == 0 && spawncounter < 4){
			world.setBlock(x, y+1, z, Entities.CustomMobTypes.SkeletonKnight.getSpawner());
			spawncounter++;
		}
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,0);
	}

	
}

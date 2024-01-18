package cavebiomes.worldgeneration.dungeontypes.ambient;

import java.util.Random;

import cavebiomes.api.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DungeonCaveOasis extends DungeonType{

	public DungeonCaveOasis() {
		super("Oasis");
	}
	
	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.replaceBlock(world, x, y, z, Blocks.sandstone, 0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.replaceBlock(world, x, y, z, Blocks.grass, 0);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){

		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone, 0);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{

		while (world.isAirBlock(x,  y,  z)){y--;}
		for(int i = y-2;  i >floor-2; i--){
			gen.setBlockWithoutNotify(world, x+1, i, z, Blocks.sandstone, 0);
			gen.setBlockWithoutNotify(world, x-1, i, z, Blocks.sandstone, 0);
			gen.setBlockWithoutNotify(world, x, i, z+1, Blocks.sandstone, 0);
			gen.setBlockWithoutNotify(world, x, i, z-1, Blocks.sandstone, 0);
			gen.setFluid(world, x, i, z, Blocks.water);
			gen.replaceBlock(world, x, i-1, z, Blocks.sandstone,0);

		}
		gen.setFluid(world, x, y+1, z, Blocks.water);

	}

}

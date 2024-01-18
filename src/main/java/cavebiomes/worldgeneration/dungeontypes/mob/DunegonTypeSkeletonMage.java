package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import cavebiomes.api.DungeonType;
import cavebiomes.entities.Entities;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DunegonTypeSkeletonMage extends DungeonType{

	public DunegonTypeSkeletonMage() {
		super("SkeletonMage");
	}
	
	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x,  y,  z, Blocks.obsidian, 0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x,  y,  z, Blocks.obsidian, 0);

	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x,  y,  z, Blocks.obsidian, 0);
	}

	@Override
	public boolean generateWallStripe(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x,  y,  z, Blocks.bookshelf, 0);
		return true;
	}
	
	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		gen.setBlockWithoutNotify(world, x, floor+1, z, Blocks.enchanting_table, 0);
		world.setBlock(x+1, floor+1, z, Entities.CustomMobTypes.SkeletonMage.getSpawner());
		world.setBlock(x-1, floor+1, z, Entities.CustomMobTypes.SkeletonMage.getSpawner());
	}
}

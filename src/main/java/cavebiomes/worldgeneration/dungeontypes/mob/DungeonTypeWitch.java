package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.api.DungeonType;
import cavebiomes.entities.Entities;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DungeonTypeWitch extends DungeonType{

	public DungeonTypeWitch() {
		super("Witch");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.planks,0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.planks,0);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.planks,0);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		gen.setBlockWithoutNotify(world, x, y+1, z, Blocks.brewing_stand, 0);
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			gen.setBlockWithoutNotify(world, x, y+2, z, Entities.CustomMobTypes.Witch.getSpawner(), 0);
		}
	}
	
	@Override
	public boolean canSpawnHere(World world, int x, int y, int z, int ceiling, int floor) {
		return y>56;
	}
	
}

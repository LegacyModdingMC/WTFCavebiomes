package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.api.DungeonType;
import cavebiomes.entities.Entities;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DungeonTypeDerangedGolem extends DungeonType {

	public DungeonTypeDerangedGolem() {
		super("DerangedGolem");
		this.yClearance = 5;
	}
	
	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,2);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,2);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick, 1);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		
		
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			gen.setBlockWithoutNotify(world, x, y, z, Entities.CustomMobTypes.DerangedGolem.getSpawner(), 0);
		}
	}
}

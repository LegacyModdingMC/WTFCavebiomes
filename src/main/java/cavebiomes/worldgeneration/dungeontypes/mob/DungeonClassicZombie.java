package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import cavebiomes.api.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class DungeonClassicZombie extends DungeonType{


	public DungeonClassicZombie() {
		super("ClassicZombie");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.replaceBlock(world, x, y, z, Blocks.mossy_cobblestone, 0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.replaceBlock(world, x, y, z, Blocks.mossy_cobblestone, 0);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.replaceBlock(world, x, y, z, Blocks.mossy_cobblestone, 0);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		if (BiomeDictionary.isBiomeOfType(biome, Type.SNOWY)){
			gen.spawnVanillaSpawner(world, x, floor+1, z, "Zombie");
		}
		else {
			gen.spawnVanillaSpawner(world, x, floor+1, z, "Zombie");

		}
	}
}
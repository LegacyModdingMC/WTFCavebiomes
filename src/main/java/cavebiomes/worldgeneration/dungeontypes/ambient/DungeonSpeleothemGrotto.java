package cavebiomes.worldgeneration.dungeontypes.ambient;

import java.util.Random;

import cavebiomes.api.DungeonType;
import net.minecraft.world.World;

public class DungeonSpeleothemGrotto extends DungeonType{

	public DungeonSpeleothemGrotto() {
		super("SpeleothemGrotto");

	}

	@Override
	public void generateCeiling(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y-1, z, 3, modifier, random.nextInt(4));
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		gen.genStalagmite(world, x, y+1, z, modifier, random.nextInt(4));
	}


}

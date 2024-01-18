package cavebiomes.worldgeneration.dungeontypes.ambient;

import java.util.Random;

import net.minecraft.world.World;
import wtfcore.api.BlockSets;
import cavebiomes.api.DungeonType;
import cavebiomes.blocks.customplants.CinderShroom;

public class DungeonTypeCinderShroom extends DungeonType {
	public DungeonTypeCinderShroom() {
		super("Condershroom");
	}

	int xMin = -7;
	int xMax = 7;
	int yMin = -5;
	int yMax = -5;
	int zMin = -7;
	int zMax = 7;



	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.transformBlock(world, x, y, z, BlockSets.Modifier.stoneMagmaCrust);
	}
@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.transformBlock(world, x, y, z, BlockSets.Modifier.stoneMagmaCrust);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		if (rand.nextInt(10) < 4){
			gen.transformBlock(world, x, y, z, BlockSets.Modifier.stoneMagmaCrust);
			gen.setBlockWithoutNotify(world, x, y+2, z, CinderShroom.cinderShroom, 0);
		}
	}

}

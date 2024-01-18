package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import cavebiomes.api.DungeonType;
import cavebiomes.entities.Entities;

public class DungeonTypeMummysTomb extends DungeonType{

	public DungeonTypeMummysTomb() {
		super("MummiesTomb");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone,0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone,0);

		if (random.nextBoolean() && random.nextBoolean()){
			world.setBlock(x, y+2, z, Entities.CustomMobTypes.Mummy.getSpawner());
		}
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone, 0);
	}

	@Override
	public boolean generateWallStripe(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone, 1);
		return true;
	}



}

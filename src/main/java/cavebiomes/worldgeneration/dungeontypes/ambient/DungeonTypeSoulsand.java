package cavebiomes.worldgeneration.dungeontypes.ambient;

import java.util.Random;

import cavebiomes.api.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DungeonTypeSoulsand extends DungeonType{

	public DungeonTypeSoulsand() {
		super("SoulSand");
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){	
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.soul_sand, 0);
		if (rand.nextInt(5)==0){
			gen.setBlockWithoutNotify(world, x, y, z, Blocks.nether_wart, 0);
		}
	}


}

package cavebiomes.worldgeneration.dungeontypes.ambient;

import java.util.Random;

import cavebiomes.api.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DungeonTypeNetherPortal extends DungeonType{

	public DungeonTypeNetherPortal() {
		super("NetherPortal");
		this.yClearance = 7;
		this.zClearance = 8;
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.netherrack, 0);

	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		gen.setBlockWithoutNotify(world, x, floor+1, z, Blocks.obsidian, 0);
		gen.setBlockWithoutNotify(world, x+1, floor+1, z, Blocks.obsidian, 0);
		gen.setBlockWithoutNotify(world, x-1, floor+1, z, Blocks.obsidian, 0);
		for (int loop = 1; loop < 7;loop++){
			gen.setBlockWithoutNotify(world, x+2, floor+loop, z, Blocks.obsidian, 0);
			gen.setBlockWithoutNotify(world, x-2, floor+loop, z, Blocks.obsidian, 0);
		}
		gen.setBlockWithoutNotify(world, x,   floor+6, z, Blocks.obsidian, 0);
		gen.setBlockWithoutNotify(world, x+1, floor+6, z, Blocks.obsidian, 0);
		gen.setBlockWithoutNotify(world, x-1, floor+6, z, Blocks.obsidian, 0);
		gen.setBlockWithoutNotify(world, x-1, floor+6, z, Blocks.obsidian, 0);
		for (int xloop = -1; xloop < 2; xloop++){
			for (int yloop = 2; yloop <6; yloop++){
				gen.setBlockWithoutNotify(world, x+xloop, floor+yloop, z, Blocks.portal, 0);
			}
		}
	}
}

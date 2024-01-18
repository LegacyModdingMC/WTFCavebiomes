package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;

import net.minecraft.world.World;
import wtfcore.api.BlockSets;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;

public class CaveTypeIceMountain extends CaveType{

	public CaveTypeIceMountain(int cavedepth, DungeonSet coldSet) {
		super("IceMountain", cavedepth, coldSet);
	}

	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		if (random.nextBoolean()){
			gen.genStalactite(world, x, y, z, depth);
		}
		else {
			gen.genIcicle(world, x, y, z);
		}
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{

		if (random.nextInt(4) < 1){
			gen.transformBlock(world, x, y, z, BlockSets.Modifier.cobblestone);
		}
		if (random.nextInt(8) < 1){
			gen.setStoneAddon(world, x, y, z, BlockSets.Modifier.cobblestone);
			gen.freezeBlock(world, x, y+1, z);
		}
		else if (shouldGenFloorAddon(random)){
			gen.genStalagmite(world, x, y, z, depth);
		}
		else {
			gen.freezeBlock(world, x, y, z);
		}
	}



}

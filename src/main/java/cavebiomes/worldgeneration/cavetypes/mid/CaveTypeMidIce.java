package cavebiomes.worldgeneration.cavetypes.mid;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;

public class CaveTypeMidIce extends CaveType{

	public CaveTypeMidIce(int cavedepth, DungeonSet coldSet) {
		super("IceMid", cavedepth, coldSet);

	}

	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		if (random.nextBoolean()){
			gen.genIcicle(world, x, y, z);
		}
		else {
			gen.genStalactite(world, x, y, z, depth, Blocks.ice);
		}
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		if (shouldGenFloorAddon(random) && random.nextBoolean() == true){
			gen.genStalagmite(world, x, y, z, depth, Blocks.ice);
		}
		else {
			gen.freezeBlock(world, x, y, z);
		}
	}



}

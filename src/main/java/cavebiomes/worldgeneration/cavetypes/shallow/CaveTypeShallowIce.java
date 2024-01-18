package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;

import net.minecraft.world.World;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;
import cavebiomes.blocks.BlockIcicle;

public class CaveTypeShallowIce extends CaveType{

	public CaveTypeShallowIce(int cavedepth, DungeonSet coldSet) {
		super("IceShallow", cavedepth, coldSet);
	}


	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		if (!world.isAirBlock(x, y+1, z))
		{
			if (random.nextInt(3) == 2){
				world.setBlock(x, y, z, BlockIcicle.IcicleLargeBase);
				world.setBlock(x, y-1, z, BlockIcicle.IcicleLargeTip);
			}
			else{
				world.setBlock(x, y, z, BlockIcicle.IcicleSmall);
			}
		}
	}


	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		gen.freezeBlock(world, x, y, z);
	}




}

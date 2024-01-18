package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;

public class CaveTypeConifer extends CaveType{

	
	
	public CaveTypeConifer(int cavedepth, DungeonSet forestSet) {
		super("Coniferous", cavedepth, forestSet);
	}

	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y, z, depth);
	}


	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{

		int height = MathHelper.abs_int((MathHelper.abs_int(x) % 10) -5)+MathHelper.abs_int((MathHelper.abs_int(z) % 10) -5)+ (random.nextInt(3)-7);
		if (height < 0 ){
			if (shouldGenFloorAddon(random)){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}
		else {
			gen.replaceBlock(world, x, y, z, Blocks.dirt, 2);

			if (shouldGenFloorAddon(random)){
				if (MathHelper.abs_int(x+y)%100 < 4){
				gen.setBlockWithoutNotify(world, x, y+1, z, Blocks.brown_mushroom, 0);
				}
				else {
					gen.setBlockWithoutNotify(world, x, y+1, z, Blocks.red_mushroom, 0);
				}
			}
		}
	}


}

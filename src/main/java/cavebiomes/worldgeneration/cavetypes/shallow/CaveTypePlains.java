package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;

public class CaveTypePlains extends CaveType{

	public CaveTypePlains(int cavedepth, DungeonSet defaultSet) {
		super("Plains", cavedepth, defaultSet);
	}


	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y, z, depth);
	}


	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		int height = 2*MathHelper.abs_int((MathHelper.abs_int(x/2+z) % 10) -5) + (random.nextInt(3)-6);

		if (height < -1 ){
			if (gen.IsBlockSurrounded(world, x, y, z)){
				gen.setFluid(world, x, y, z, Blocks.water);
			}
		}

		else {
			if (shouldGenFloorAddon(random)){
				gen.genStalagmite(world, x, y, z, depth);
			}
			else if (random.nextInt(5) >3){
				gen.replaceBlock(world, x, y, z, Blocks.dirt, 0);
			}
		}
	}




}

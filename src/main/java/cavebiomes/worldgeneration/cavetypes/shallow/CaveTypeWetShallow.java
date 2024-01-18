package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import wtfcore.api.BlockSets;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;

public class CaveTypeWetShallow extends CaveType {
	
	public CaveTypeWetShallow(int cavedepth, DungeonSet wetSet) {
		super("WetShallow", cavedepth, wetSet);
	}

	@Override
	public void generateCeiling(World world, Random random, int x, int y, int z)
	{
		if (random.nextInt(4)==0){
			gen.transformBlock(world, x, y, z, BlockSets.Modifier.waterDrippingStone);
		}
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

		if (height < -1){
			if (gen.IsBlockSurrounded(world, x, y, z)){
				gen.setFluid(world, x, y, z, Blocks.water, null);
			}

		}

		else {
			if (shouldGenFloorAddon(random)){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}
	}
}

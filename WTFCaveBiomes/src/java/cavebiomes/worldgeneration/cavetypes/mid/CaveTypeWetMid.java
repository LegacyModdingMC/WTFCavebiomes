package cavebiomes.worldgeneration.cavetypes.mid;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import wtfcore.utilities.BlockSets;
import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.DungeonSet;
import cavebiomes.worldgeneration.DungeonType;

public class CaveTypeWetMid  extends CaveType{

	public CaveTypeWetMid(String name, int cavedepth, DungeonSet wetSet) {
		super(name, cavedepth, wetSet);
	}
	BlockSets.Modifier[] blockArray = {null, null, BlockSets.Modifier.cobblestone};

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
		int height = MathHelper.abs_int((MathHelper.abs_int(x/2) % 10) -5)+MathHelper.abs_int((MathHelper.abs_int(z) % 10) -5)+ (random.nextInt(3)+2);

		if (height < 9 ){

			if (IsBlockSurrounded(world, x, y, z)){
				if (random.nextInt(8) == 0){
					gen.transformBlock(world, x, y, z, BlockSets.Modifier.cobblestone);
				}
				else {
					gen.setFluid(world, x, y, z, Blocks.water, blockArray[random.nextInt(blockArray.length)]);
				}
			}
		}
		else {
			if (shouldGenFloorAddon()){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}
	}
}

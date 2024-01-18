package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;
import net.minecraft.world.World;
import wtfcore.api.BlockSets;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;

public class CaveTypeMountain extends CaveType{

	public CaveTypeMountain(int cavedepth,	DungeonSet defaultSet) {
		super( "Mountain", cavedepth, defaultSet);
	}

	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y, z, depth);
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		if (random.nextInt(4) < 1){
			gen.transformBlock(world, x, y, z, BlockSets.Modifier.cobblestone);
		}
		if (random.nextInt(8) < 1){
			gen.setStoneAddon(world, x, y, z, BlockSets.Modifier.cobblestone);
		}
		else {
			if (shouldGenFloorAddon(random)){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}
	}



}
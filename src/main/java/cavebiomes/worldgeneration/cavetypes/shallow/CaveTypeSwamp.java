package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import wtfcore.api.BlockSets;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;

public class CaveTypeSwamp extends CaveType {

	
	public CaveTypeSwamp(int cavedepth, DungeonSet swampSet) {
		super("Swamp", cavedepth, swampSet);
	}


	@Override
	public void generateCeiling(World world, Random random, int x, int y, int z)
	{
		if (random.nextInt(4)==0){
			gen.transformBlock(world, x, y, z, BlockSets.Modifier.lavaDrippinStone);
		}
	}


	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		if (random.nextBoolean()){
			if (gen.GenVines(world, x, y, z)){//vines generated
			}
			else {
				gen.genStalactite(world, x, y, z, depth);
			}
		}
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		int height = 6+MathHelper.abs_int((MathHelper.abs_int(x-z) % 10) -5) + (random.nextInt(3)-1);

		BlockSets.Modifier[] swampBlock = {BlockSets.Modifier.mossy_cobblestone, BlockSets.Modifier.cobblestone, BlockSets.Modifier.MossyStone};
		BlockSets.Modifier addonblock = swampBlock[random.nextInt(swampBlock.length)];

		if (height < 9 ){
			if (gen.IsBlockSurrounded(world, x, y, z)){
				gen.setFluid(world, x, y, z, Blocks.water, addonblock);
				if (random.nextInt(8)==0 && Blocks.waterlily.canBlockStay(world,  x,  y+1,  z)){
					gen.setBlockWithoutNotify(world, x, y+1, z, Blocks.waterlily, 0);
				}
			}
		}
		else{
			if (shouldGenFloorAddon(random) && addonblock == null){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}
	}
}

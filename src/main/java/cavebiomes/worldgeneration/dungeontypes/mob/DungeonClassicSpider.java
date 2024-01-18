package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import cavebiomes.api.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import wtfcore.api.BlockSets;

public class DungeonClassicSpider extends DungeonType{

	public DungeonClassicSpider() {
		super("ClassicSpider");
	}

	BlockSets.Modifier[] swampBlock = {BlockSets.Modifier.mossy_cobblestone, BlockSets.Modifier.cobblestone, BlockSets.Modifier.MossyStone};

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){

		BlockSets.Modifier addonblock = swampBlock[random.nextInt(swampBlock.length)];
		gen.transformBlock(world, x, y, z, addonblock);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.cobblestone, 0);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		gen.spawnVanillaSpawner(world, x, floor+1, z, "Spider");
	}


}


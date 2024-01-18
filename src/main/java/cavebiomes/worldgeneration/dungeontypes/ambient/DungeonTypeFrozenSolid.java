package cavebiomes.worldgeneration.dungeontypes.ambient;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import cavebiomes.api.DungeonType;
import cavebiomes.blocks.CaveBlocks;

public class DungeonTypeFrozenSolid extends DungeonType{

	public DungeonTypeFrozenSolid() {
		super("FrozenSolid");
	}

	@Override
	public void generateFill(World world, Random random, int x, int y, int z)
	{
		if (world.isAirBlock(x, y, z) || world.getBlock(x,y,z)==CaveBlocks.IcePatch){
			gen.setBlockWithoutNotify(world, x, y, z, Blocks.packed_ice, 0);
		}
	}


}

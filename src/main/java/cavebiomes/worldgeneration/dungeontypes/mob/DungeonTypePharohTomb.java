package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.api.DungeonType;
import cavebiomes.entities.Entities;
import cpw.mods.fml.common.Loader;
import ganymedes01.etfuturum.ModBlocks;

public class DungeonTypePharohTomb extends DungeonType{

	public DungeonTypePharohTomb() {
		super("Pharoh'sTomb");
		if (Loader.isModLoaded("etfuturum")){
			this.sandstone = ModBlocks.red_sandstone;
		}
		else {
			this.sandstone = Blocks.sandstone;
		}
	}
	
	Block sandstone;

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, sandstone, 0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, sandstone, 0);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, sandstone, 1);
	}

	@Override
	public boolean generateWallStripe(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.gold_block, 0);
		return true;
	}
	
	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			if (WTFCaveBiomesConfig.EnableMobSpawners){
				while (world.isAirBlock(x,  y-1,  z)){y--;}
				world.setBlock(x, y, z, Entities.CustomMobTypes.Pharoh.getSpawner());
				
			}



		}
	}


}

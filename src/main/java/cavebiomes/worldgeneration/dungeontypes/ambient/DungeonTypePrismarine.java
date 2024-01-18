package cavebiomes.worldgeneration.dungeontypes.ambient;

import java.util.Random;

import cavebiomes.api.DungeonType;
import cpw.mods.fml.common.Loader;
import ganymedes01.etfuturum.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DungeonTypePrismarine extends DungeonType {

	public DungeonTypePrismarine() {
		super("Primsarine");
		if (Loader.isModLoaded("etfuturum")){
			this.prismarine = ModBlocks.prismarine;
			this.lantern = ModBlocks.sea_lantern;
		}
		else {
			this.prismarine = Blocks.lapis_block;
			this.lantern = Blocks.glowstone;
		}
	}
	
	Block prismarine;
	Block lantern;

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, prismarine, 0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, prismarine, 0);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, prismarine, 1);
	}

	@Override
	public boolean generateWallStripe(World world, Random rand, int x, int y, int z){
		
		gen.setBlockWithoutNotify(world, x, y, z, lantern, 0);
		return true;
	}
	@Override
	public boolean canSpawnHere(World world, int x, int y, int z, int ceiling, int floor) {
		return y < 36;
	}
		
}

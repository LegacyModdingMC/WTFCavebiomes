package cavebiomes.worldgeneration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.api.CaveType;
import cavebiomes.utilities.gencores.GenCoreProvider;
import cavebiomes.utilities.gencores.VanillaGen;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import wtfcore.api.BlockSets;
import wtfcore.utilities.CavePosition;
import wtfcore.utilities.SurfacePos;
import wtfcore.worldgen.OverworldScanner;
import wtfcore.worldgen.WorldGenListener;

public class CaveBiomesChunkScanner extends OverworldScanner{

	public VanillaGen gen = GenCoreProvider.getGenCore();
	
	@Override
	public void generate(World world, Random rand, int chunkX, int chunkZ)
	{		
		ThreadLocalRandom localRand = ThreadLocalRandom.current();
		
		ArrayList<CavePosition> cavepositions = new ArrayList<CavePosition>();
		ArrayList<CavePosition> dungeonposition = new ArrayList<CavePosition>();
		ArrayList<SurfacePos> surfacepositions = new ArrayList();

		Chunk chunk = world.getChunkFromBlockCoords(chunkX, chunkZ);

		//WTFCore.log.info("Scanning chunk");
		int lastY = 70;

		int surfaceaverage = 0;

		for (int xloop = 0; xloop < 16; xloop++)
		{
			int x = chunkX + xloop;
			for (int zloop = 0; zloop < 16; zloop++){

				int z = chunkZ + zloop;	
				int y = scanForSurface(chunk, x, lastY, z);
				surfacepositions.add(new SurfacePos(x, y, z));
				lastY = y;
				surfaceaverage += y;

				int ceiling = -1;
				boolean wasAir = false;

				while (y > 10){

					if (isAirAndCheck(chunk, x, y, z)){

						if (!wasAir && ceiling == -1){
							ceiling = y + 1;
						}
						wasAir = true;
					}
					else{

						if (ceiling != -1){
							cavepositions.add(new CavePosition(x, ceiling, y, z));

							dungeonposition.add(new CavePosition(x, ceiling, y, z));

							ceiling = -1;
							y--;
						}
						wasAir = false;
					}
					y--;

				}
			}
		}

		surfaceaverage /= 256;
		//I set this up for some reason, and now I'm not sure what it was, so I'm disabling it to try and prevent dungeon generation above the surface average
		//if (surfaceaverage < 64){ surfaceaverage = 64;}

		//Additional Generators called herer- such as WTFOres
		if (WorldGenListener.generator != null){
			WorldGenListener.generator.generate(world, surfaceaverage, chunkX, chunkZ, localRand, cavepositions);
		}

		int deepmax = surfaceaverage/3;
		int midmax = surfaceaverage*2/3;

		//iterators for CaveBiomes cave generation
		CaveType shallowtype = CaveTypeRegister.GetShallowCaveType(world, chunkX, chunkZ);
		CaveType midtype = CaveTypeRegister.GetMidCaveType(world, chunkX, chunkZ);
		CaveType deeptype = CaveTypeRegister.GetDeepCaveType(world, chunkX, chunkZ);


		//WTFCore.log.info("Dungeon iterating");
		CavePosition position;
		if (WTFCaveBiomesConfig.generateCaveSubtypes){
			Iterator<CavePosition> dungeoniterator = dungeonposition.iterator();
			while (dungeoniterator.hasNext()) {
				position = dungeoniterator.next();
				if (position.floor < surfaceaverage-5){
					if (position.floor < deepmax){
						if (localRand.nextInt(deeptype.DungeonWeight) == 1){
							CaveGen.generateDungeon(deeptype, world, localRand, position.x, position.z, position.ceiling, position.floor);
						}
					}
					else if (position.floor < midmax ){
						if (localRand.nextInt(midtype.DungeonWeight) == 1){
							CaveGen.generateDungeon(midtype, world, localRand, position.x, position.z, position.ceiling, position.floor);
						}
					}
					else {
						if (localRand.nextInt(shallowtype.DungeonWeight) == 1){
							CaveGen.generateDungeon(shallowtype, world, localRand, position.x,  position.z, position.ceiling, position.floor);
						}
					}
				}
			}
		}
		//WTFCore.log.info("CaveType iterating");
		Iterator<CavePosition> caveiterator= cavepositions.iterator();
		while (caveiterator.hasNext()) {
			position = caveiterator.next();


			if (position.floor < deepmax){

				CaveGen.generateCaveType(deeptype, world, localRand, position.x, position.floor, position.ceiling, position.z);
			}
			else if (position.floor < midmax){
				CaveGen.generateCaveType(midtype, world, localRand, position.x, position.floor, position.ceiling, position.z);
			}
			else {
				CaveGen.generateCaveType(shallowtype, world, localRand, position.x, position.floor, position.ceiling, position.z);
			}
		}

		if (WorldGenListener.treehandler != null) {
			WorldGenListener.treehandler.generateTrees(world, surfacepositions, chunkX, chunkZ);
		}

	}
	
	@Override
	public boolean isAirAndCheck(Chunk chunk, int x, int y, int z){
		Block block = chunk.getBlock(x & 15, y, z & 15);
		
		if (BlockSets.genReplace.containsKey(block)){
			gen.replaceBlockDuringGen(chunk, block, x, y, z);
			//WTFCore.log.info("Replaced");
			return false;
		}
		return block.isAir(chunk.worldObj, x, y, z);
	}
	
	@Override
	public boolean isSurfaceAndCheck(Chunk chunk, int x, int y, int z){
		Block block = chunk.getBlock(x&15, y, z&15);
		if (BlockSets.genReplace.containsKey(block)){
			//System.out.println("genReplace contained " + block.getLocalizedName());
			gen.replaceBlockDuringGen(chunk, block, x, y, z);
			
		}
		return BlockSets.surfaceBlocks.contains(block);
	}
	
	
}

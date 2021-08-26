package cavebiomes.worldgeneration;

import cavebiomes.utilities.BiomeAndHeight;
import cavebiomes.worldgeneration.CaveTypeRegister;
import cavebiomes.worldgeneration.cavetypes.deep.CaveTypeVolcanic;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGeneration implements IWorldGenerator {

   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {}

   private void generateSurface(World world, Random random, int x, int z) {}

   private void addOreSpawn(Block block, World world, Random random, int x, int z, int maxveinsize, int chancetospawn, int miny, int maxy) {
      for(int i = 0; i < chancetospawn; ++i) {
         if(CaveTypeRegister.getCaveType(new BiomeAndHeight(world.getBiomeGenForCoords(x, z), 2)) instanceof CaveTypeVolcanic) {
            int posX = x + random.nextInt(16);
            int posY = miny + random.nextInt(maxy - miny);
            int posZ = z + random.nextInt(16);
            (new WorldGenMinable(block, maxveinsize)).generate(world, random, posX, posY, posZ);
         }
      }

   }
}

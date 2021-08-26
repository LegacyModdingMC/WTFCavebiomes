package cavebiomes.worldgeneration;

import cavebiomes.EventListener;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.utilities.gencores.VanillaGen;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import wtfcore.WTFCore;
import wtfcore.utilities.DungeonBlockPosition;

public class DungeonType {

   int xmin = -5;
   int xmax = 5;
   int ymin = -5;
   int ymax = 5;
   int zmin = -5;
   int zmax = 5;
   float radius = 6.0F;
   protected Random random = new Random();
   protected Block modifier;
   public String name;
   protected static VanillaGen gen;


   public DungeonType(String name) {
      this.name = name;
      gen = VanillaGen.getGenMethods();
   }

   public void SpawnDungeon(World world, Random rand, int x, int y, int z, int ceiling, int floor) {
      if(BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(x, z), Type.SNOWY)) {
         this.modifier = Blocks.ice;
      } else {
         this.modifier = null;
      }

      if(WTFCaveBiomesConfig.logDungeons) {
         EventListener.thePlayer.addChatMessage(new ChatComponentText("Spawning " + this.name + " @ " + x + " " + y + " " + z));
         WTFCore.log.info("Spawning " + this.name + " @ " + x + " " + y + " " + z);
      }

      HashSet hashset = new HashSet();
      HashSet air = new HashSet();

      int i;
      for(int iterator = this.xmin; iterator < this.xmax + 1; ++iterator) {
         for(int chunkposition = this.ymin; chunkposition < this.ymax + 1; ++chunkposition) {
            for(i = this.zmin; i < this.zmax + 1; ++i) {
               if(iterator == this.xmin || iterator == this.xmax || chunkposition == this.ymin || chunkposition == this.ymax || i == this.zmin || i == this.zmax) {
                  double j = (double)(0 + iterator);
                  double d4 = (double)(0 + chunkposition);
                  double d5 = (double)(0 + i);
                  double vectorLength = Math.sqrt(j * j + d4 * d4 + d5 * d5);
                  j /= vectorLength;
                  d4 /= vectorLength;
                  d5 /= vectorLength;
                  double currentX = (double)x + 0.5D;
                  double currentY = (double)y + 0.5D;
                  double currentZ = (double)z + 0.5D;
                  float vectorStr = this.radius;
                  Boolean cont = Boolean.valueOf(true);

                  for(float f2 = 0.3F; vectorStr > 0.0F && cont.booleanValue(); vectorStr -= f2 * 0.75F) {
                     int i1 = MathHelper.floor_double(currentX);
                     int j1 = MathHelper.floor_double(currentY);
                     int k1 = MathHelper.floor_double(currentZ);
                     if(!world.isAirBlock(i1, j1, k1)) {
                        hashset.add(new DungeonBlockPosition(i1, j1, k1, false));
                        cont = Boolean.valueOf(false);
                     } else {
                        air.add(new DungeonBlockPosition(i1, j1, k1, false));
                     }

                     vectorStr -= 0.3F;
                     currentX += j * (double)f2;
                     currentY += d4 * (double)f2;
                     currentZ += d5 * (double)f2;
                  }
               }
            }
         }
      }

      Iterator var33 = hashset.iterator();

      int k;
      DungeonBlockPosition var34;
      int var35;
      while(var33.hasNext()) {
         var34 = (DungeonBlockPosition)var33.next();
         i = var34.chunkPosX;
         var35 = var34.chunkPosY;
         k = var34.chunkPosZ;
         if(var35 < y && !world.getBlock(i, var35 + 1, k).renderAsNormalBlock()) {
            this.generateFloor(world, rand, i, var35, k);
         } else if(var35 > y && !world.getBlock(i, var35 - 1, k).renderAsNormalBlock()) {
            this.generateCeiling(world, rand, i, var35, k);
         } else {
            this.generateWalls(world, rand, i, var35, k);
         }
      }

      this.generateCenter(world, rand, x, y, z, ceiling, floor);
      var33 = air.iterator();

      while(var33.hasNext()) {
         var34 = (DungeonBlockPosition)var33.next();
         i = var34.chunkPosX;
         var35 = var34.chunkPosY;
         k = var34.chunkPosZ;
         this.generateFill(world, rand, i, var35, k);
      }

   }

   public void generateCeiling(World world, Random rand, int x, int y, int z) {}

   public void generateFloor(World world, Random rand, int x, int y, int z) {}

   public void generateWalls(World world, Random rand, int x, int y, int z) {}

   public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor) {}

   public void generateFill(World world, Random rand, int x, int y, int z) {}
}

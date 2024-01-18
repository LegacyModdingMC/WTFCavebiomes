package cavebiomes.entities;

import java.util.Random;

import cavebiomes.entities.skeleton.SkeletonMage;
import cavebiomes.items.ArmorRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class SpawnHandler {

	static Random random = new Random();

	public static void replaceEntity(Entity oldentity, Entity newentity){
		newentity.setLocationAndAngles(oldentity.posX, oldentity.posY, oldentity.posZ, oldentity.rotationYaw, oldentity.rotationPitch);
		oldentity.worldObj.spawnEntityInWorld(newentity);
		oldentity.setDead();
	}

	public static boolean zombieSpawn(LivingSpawnEvent event){
		EntityZombie oldentity = (EntityZombie) event.entityLiving;
		if (oldentity.getCanSpawnHere()){
			BiomeGenBase biome = event.world.getBiomeGenForCoords(MathHelper.floor_float(event.x), MathHelper.floor_float(event.z));
			Block baseBlock = event.world.getBlock(MathHelper.floor_float(event.x), MathHelper.floor_float(event.y)-1, MathHelper.floor_float(event.z));

			if (baseBlock.hashCode() == Blocks.rail.hashCode()){ //zombie miner
				oldentity.setCurrentItemOrArmor(0, new ItemStack(Items.stone_pickaxe));
				oldentity.setCurrentItemOrArmor(4, new ItemStack(Items.iron_helmet));
				return false;
			}
			if (BiomeDictionary.isBiomeOfType(biome, Type.SANDY) && !event.world.canBlockSeeTheSky(MathHelper.ceiling_float_int(event.x), MathHelper.ceiling_float_int(event.y), MathHelper.ceiling_float_int(event.z))){
				replaceEntity(oldentity, Entities.CustomMobTypes.Mummy.getMob(event.world));
				return true;
			}
			//else if (BiomeDictionary.isBiomeOfType(biome, Type.SNOWY)){
			//	replaceEntity(event.entity, new ZombieFrozen(event.world));
			//}
		}
		return false;
	}

	public static boolean skeletonSpawn(LivingSpawnEvent event){
		EntitySkeleton oldentity = (EntitySkeleton) event.entityLiving;

		if (oldentity.getCanSpawnHere()){
			//BiomeGenBase biome = event.world.getBiomeGenForCoords(MathHelper.floor_float(event.x), MathHelper.floor_float(event.z));
			Block baseBlock = event.world.getBlock(MathHelper.floor_float(event.x), MathHelper.floor_float(event.y)-1, MathHelper.floor_float(event.z));

			if (baseBlock.hashCode() == Blocks.stonebrick.hashCode()){
				if(random.nextInt(5) == 0){
					replaceEntity(oldentity, new SkeletonMage(event.world));
					return true;
				}
				else if (random.nextInt(3) == 0){//skeleton knight
					replaceEntity(oldentity, Entities.CustomMobTypes.SkeletonKnight.getMob(event.world));
					return true;
				}
			}
			else if (baseBlock.hashCode() == Blocks.rail.hashCode()){ //skeleton miner
				oldentity.setCurrentItemOrArmor(0, new ItemStack(Items.stone_pickaxe));
				oldentity.setCurrentItemOrArmor(4, new ItemStack(Items.leather_helmet));
				return false;
			}

			if (event.y < 40){
				if(random.nextInt(500) == 0){
					replaceEntity(oldentity, new SkeletonMage(event.world));
					return true;
				}
				else if (random.nextInt(250) == 0){
					replaceEntity(oldentity, Entities.CustomMobTypes.SkeletonKnight.getMob(event.world));
					return true;
				}
			}

			//if (BiomeDictionary.isBiomeOfType(biome, Type.SNOWY) && event.y <40){
			//	replaceEntity(event.entity, new SkeletonIce(event.world));
			//}
			//else if (event.y < 32 && random.nextBoolean()){
			//	replaceEntity(event.entity, new SkeletonLava(event.world));
			//}
		}
		return false;
	}


}

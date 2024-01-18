package cavebiomes.entities;

import cavebiomes.CaveBiomes;
import cavebiomes.entities.skeleton.SkeletonMage;
import cavebiomes.items.ArmorRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class Entities {

	static int counter = 1;
	public static Entities entities = new Entities();

	public static void RegisterEntityList()
	{	
		GameRegistry.registerTileEntity(SpawnerBlockEntity.class, "CustomMobSpawner");

		registerEntity(SkeletonMage.class, "SkeletonMage");		

		//this should iterate through CustomMobTypes, registering a spawner for each, which can then be retrieved from the type itself
		for (CustomMobTypes type : CustomMobTypes.values()){
			GameRegistry.registerBlock(type.getSpawner(), type.toString()+"_spawner");
		}
	}

	public static void registerEntity(Class entityClass, String name)
	{
		int entityId = counter;
		counter++;
		EntityRegistry.registerModEntity(entityClass, name, entityId, CaveBiomes.instance, 64, 1, true);
	}


	public interface ICustomMob{
		public EntityLiving getMob(World world);
	}

	public enum CustomMobTypes{
		Mummy(entities.new CustomMummy()),
		Pharoh(entities.new CustomPharoh()),
		SkeletonKnight(entities.new CustomSkeletonKnight()),
		SkeletonMage(entities.new CustomSkeletonMage()),
		DerangedGolem(entities.new CustomDerangedGolem()),
		Witch(entities.new CustomWitch());


		private final ICustomMob custommob;
		CustomMobTypes(ICustomMob customMob){
			this.custommob = customMob;
		}

		public EntityLiving getMob(World world){
			return custommob.getMob(world);
		}
		public Block getSpawner(){
			return new SpawnerBlock(this).setBlockName(this.toString()+"_spawner");
		}
	}

	public class CustomMummy implements ICustomMob{

		@Override
		public EntityLiving getMob(World world) {
			EntityLiving mob = new EntityZombie(world);
			mob.setCurrentItemOrArmor(4, new ItemStack(ArmorRegistry.mummyHelmet));
			mob.setCurrentItemOrArmor(3, new ItemStack(ArmorRegistry.mummyChestplate));
			mob.setCurrentItemOrArmor(2, new ItemStack(ArmorRegistry.mummyLeggings));
			mob.setCurrentItemOrArmor(1, new ItemStack(ArmorRegistry.mummyBoots));
			mob.setEquipmentDropChance(4, 0F);
			mob.setEquipmentDropChance(3, 0F);
			mob.setEquipmentDropChance(2, 0F);
			mob.setEquipmentDropChance(1, 0F);
			return mob;
		}
	}
	public class CustomPharoh implements ICustomMob{

		@Override
		public EntityLiving getMob(World world) {
			EntityLiving mob = new EntityZombie(world);
			mob.setCurrentItemOrArmor(4, new ItemStack(ArmorRegistry.pharaohHelmet));
			mob.setCurrentItemOrArmor(3, new ItemStack(ArmorRegistry.pharaohChestplate));
			mob.setCurrentItemOrArmor(2, new ItemStack(ArmorRegistry.pharaohLeggings));
			mob.setCurrentItemOrArmor(1, new ItemStack(ArmorRegistry.pharaohBoots));
			mob.setEquipmentDropChance(4, 0F);
			mob.setEquipmentDropChance(3, 0F);
			mob.setEquipmentDropChance(2, 0F);
			mob.setEquipmentDropChance(1, 0F);
			return mob;
		}
	}

	public class CustomSkeletonKnight implements ICustomMob{
		@Override
		public EntityLiving getMob(World world) {
			EntityLiving mob = new EntitySkeleton(world);
			mob.tasks.addTask(5, new EntityAIMoveTowardsRestriction((EntityCreature) mob, 1.0D));
			mob.setCurrentItemOrArmor(3, new ItemStack(Items.chainmail_chestplate));
			mob.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
			return mob;
		}
	}
	public class CustomSkeletonMage implements ICustomMob{
		@Override
		public EntityLiving getMob(World world) {
			EntityLiving mob = new SkeletonMage(world);
			return mob;
		}
	}

	public class CustomDerangedGolem implements ICustomMob{
		@Override
		public EntityLiving getMob(World world) {
			EntityIronGolem mob = new EntityIronGolem(world);
			mob.tasks.taskEntries.clear();
			mob.targetTasks.taskEntries.clear();
			mob.getNavigator().setAvoidsWater(true);
			mob.tasks.addTask(2, new EntityAIAttackOnCollide(mob, EntityPlayer.class, 1.0D, false));
			mob.tasks.addTask(5, new EntityAIMoveTowardsRestriction(mob, 1.0D));
			//this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
			mob.tasks.addTask(8, new EntityAIWatchClosest(mob, EntityPlayer.class, 8.0F));
			mob.tasks.addTask(8, new EntityAILookIdle(mob));
			mob.targetTasks.addTask(1, new EntityAIHurtByTarget(mob, true));
			mob.targetTasks.addTask(2, new EntityAINearestAttackableTarget(mob, EntityPlayer.class, 0, true));
			mob.getNavigator().setCanSwim(false);
			return mob;
		}

	}
	public class CustomWitch implements ICustomMob{
		@Override
		public EntityLiving getMob(World world) {
			EntityLiving mob = new EntityWitch(world);
			return mob;
		}
	}




}

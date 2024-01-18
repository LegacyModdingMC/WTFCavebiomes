package cavebiomes;

import java.util.Random;

import cavebiomes.blocks.UBCSand;
import cavebiomes.entities.SpawnHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import wtfcore.utilities.UBCblocks;


public class EventListener {


	Random random = new Random();
	public static EntityPlayer thePlayer;

	@SubscribeEvent
	public void getPlayer (PlayerEvent.PlayerLoggedInEvent event){
		if (WTFCaveBiomesConfig.logDungeons){
			thePlayer = event.player;
		}
	}

	@SubscribeEvent
	public void SpawnReplacer (LivingSpawnEvent.CheckSpawn event)
	{
		if (!event.world.isRemote){
			if (WTFCaveBiomesConfig.customMobs){
				if (event.entityLiving instanceof EntityZombie && SpawnHandler.zombieSpawn(event)){
					event.setResult(Result.DENY);
				}
				else if (event.entityLiving instanceof EntitySkeleton && SpawnHandler.skeletonSpawn(event)){
					event.setResult(Result.DENY);
				}
			}
		}
	}

	@SubscribeEvent
	public void yourPlayerHarvestEvent(HarvestDropsEvent event){
		if (WTFCaveBiomesConfig.enableUBCSand && Loader.isModLoaded("UndergroundBiomes") && event.block == UBCblocks.SedimentaryStone && event.blockMetadata != 4 && event.blockMetadata != 12)
		{
			event.drops.clear();
			int metadata = event.blockMetadata;
			if (metadata > 7){ metadata = metadata - 8;}
			event.drops.add(new ItemStack(UBCSand.sedimentarySand, 1, metadata));
		}
	}
	


}

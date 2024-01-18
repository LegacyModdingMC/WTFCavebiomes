package cavebiomes.entities;


import cavebiomes.CaveBiomes;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import wtfcore.WTFCore;


public class SpawnerBlockEntity extends TileEntity{

	
	int activatingRangeFromPlayer = 16;

	public SpawnerBlockEntity() {

	}


	public boolean anyPlayerInRange()
	{
		return this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, activatingRangeFromPlayer) != null;
	}

	@Override
	public void updateEntity()
	{
		if(this.anyPlayerInRange()){
			if (!this.worldObj.isRemote)
			{
			//WTFCore.log.info("Spawning");
				spawnEntity();
				worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
			}
		}
	}

	public void spawnEntity()
	{
		SpawnerBlock block = (SpawnerBlock) this.worldObj.getBlock(xCoord, yCoord, zCoord);
		EntityLiving entityToSpawn= block.mobType.getMob(worldObj);
		entityToSpawn.setLocationAndAngles(this.xCoord+0.5, this.yCoord, this.zCoord+0.5, 0F, 0.0F);
		worldObj.spawnEntityInWorld(entityToSpawn);
		entityToSpawn.playLivingSound();
	}


}

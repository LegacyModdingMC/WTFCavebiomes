package cavebiomes.entities.skeleton;

import cavebiomes.items.ArmorRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SkeletonMage extends CustomSkeleton implements IRangedAttackMob{

	public SkeletonMage(World p_i1741_1_) {
		super(p_i1741_1_);
		texture = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
		type = SkeletonType.MAGE;
		this.tasks.addTask(4, new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F));
		this.setCurrentItemOrArmor(0, new ItemStack(Item.getItemFromBlock(Blocks.torch)));
		//this.setCurrentItemOrArmor(4, new ItemStack(Item.getItemFromBlock(Blocks.pumpkin)));
	}


    @Override
	protected boolean canDespawn()
    {
        return false;
    }

	@Override
	public void setCombatTask()
	{
		//OVERRIDE
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float p_70785_2_)
	{
		double d0 = entity.posX - this.posX;
		double d1 = entity.boundingBox.minY + entity.height / 2.0F - (this.posY + this.height / 2.0F);
		double d2 = entity.posZ - this.posZ;

		float f1 = MathHelper.sqrt_float(p_70785_2_) * 0.5F;
		this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
		this.swingItem();
		for (int i = 0; i < 1; ++i)
		{
			EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * f1, d1, d2 + this.rand.nextGaussian() * f1);
			entitysmallfireball.setLocationAndAngles(this.posX, this.posY + this.getEyeHeight(), this.posZ, this.rotationYaw, this.rotationPitch);
			entitysmallfireball.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
			entitysmallfireball.posY -= 0.10000000149011612D;
			entitysmallfireball.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
			this.worldObj.spawnEntityInWorld(entitysmallfireball);
		}

	}





}

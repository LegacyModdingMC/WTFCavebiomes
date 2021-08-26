package cavebiomes.entities;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CustomWolf extends EntityWolf {

   public CustomWolf(World p_i1696_1_) {
      super(p_i1696_1_);
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      this.setTamed(false);
      this.setAngry(true);
   }

   public void setAngry(boolean p_70916_1_) {
      byte b0 = this.dataWatcher.getWatchableObjectByte(16);
      if(p_70916_1_) {
         this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 2)));
      }

   }

   public boolean interact(EntityPlayer p_70085_1_) {
      return false;
   }
}

package cavebiomes.entities.skeleton;

import cavebiomes.entities.skeleton.CustomSkeleton;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SkeletonLava extends CustomSkeleton {

   public SkeletonLava(World p_i1741_1_) {
      super(p_i1741_1_);
      this.texture = new ResourceLocation("cavebiomes:textures/entity/lava_skeleton.png");
      this.type = CustomSkeleton.SkeletonType.LAVA;
   }

   protected void addRandomArmor() {
      ItemStack itemstack = new ItemStack(Items.bow);
      itemstack.addEnchantment(Enchantment.flame, 1);
      this.setCurrentItemOrArmor(0, itemstack);
   }
}

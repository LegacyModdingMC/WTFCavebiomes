package cavebiomes.entities.skeleton;

import cavebiomes.entities.skeleton.CustomSkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SkeletonIce extends CustomSkeleton {

   public SkeletonIce(World p_i1741_1_) {
      super(p_i1741_1_);
      this.texture = new ResourceLocation("cavebiomes:textures/entity/ice_skeleton.png");
      this.type = CustomSkeleton.SkeletonType.ICE;
   }
}

package cavebiomes.entities.skeleton;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CustomSkeleton extends EntitySkeleton{

	public CustomSkeleton(World p_i1741_1_) {
		super(p_i1741_1_);
	}


	public enum SkeletonType {ICE, LAVA, KNIGHT, MAGE}
	public ResourceLocation texture;
	public SkeletonType type;











}

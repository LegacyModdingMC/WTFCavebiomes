package cavebiomes.proxy;

import net.minecraft.client.model.ModelWolf;
import cavebiomes.entities.skeleton.SkeletonMage;
import cavebiomes.renderers.RenderCustomSkeleton;
import cavebiomes.renderers.RenderCustomWolf;
import cavebiomes.renderers.RenderCustomZombie;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CBClientProxy extends CommonProxy {

	public static int renderPass;
	
	@Override
	public void registerRenderers() {

	
		RenderingRegistry.registerEntityRenderingHandler(SkeletonMage.class, new RenderCustomSkeleton());

	
	}
}
package cavebiomes.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class RenderCustomWolf extends RenderWolf{

	public RenderCustomWolf(ModelBase p_i1269_1_, ModelBase p_i1269_2_,
			float p_i1269_3_) {
		super(p_i1269_1_, p_i1269_2_, p_i1269_3_);

	}

	private static final ResourceLocation hellhound = new ResourceLocation("cavebiomes:textures/entity/hellhound.png");
	//private static final ResourceLocation anrgyWolfTextures = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

	@Override
	  protected ResourceLocation getEntityTexture(EntityWolf entity)
	    {
		return hellhound;

	    }


}

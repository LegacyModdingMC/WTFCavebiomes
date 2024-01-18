package cavebiomes.renderers;



import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderCustomZombie extends RenderZombie{


	private static final ResourceLocation mummyTexture = new ResourceLocation("cavebiomes:textures/entity/mummy.png");
	private static final ResourceLocation frozenTexture = new ResourceLocation("textures/entity/zombie/zombie.png");
	private static final ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");

	@Override
	  protected ResourceLocation getEntityTexture(EntityZombie entity)
	    {

	        return zombieTextures;
	    }


}

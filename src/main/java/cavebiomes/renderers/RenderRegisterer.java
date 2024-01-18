package cavebiomes.renderers;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderRegisterer {


	public static int MagmaCrustRenderType;
	public static int FrozenBlockRenderType;;
	public static int LightDarkRenderType;


	public static void RegisterCustomRenderers() {
		MagmaCrustRenderType = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new cavebiomes.renderers.MagmaCrustRenderer());

		FrozenBlockRenderType = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new cavebiomes.renderers.FrozenBlockRenderer());

		LightDarkRenderType = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new cavebiomes.renderers.LightDarkRenderer());

	}
	
}

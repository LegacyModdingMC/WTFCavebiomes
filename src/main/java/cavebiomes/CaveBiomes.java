package cavebiomes;

import java.util.Iterator;

import cavebiomes.api.APICaveBiomes;
import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonType;
import cavebiomes.blocks.CaveBlocks;
import cavebiomes.entities.Entities;
import cavebiomes.items.ArmorRegistry;
import cavebiomes.proxy.CommonProxy;
import cavebiomes.renderers.RenderRegisterer;
import cavebiomes.utilities.gencores.GenCoreProvider;
import cavebiomes.worldgeneration.CaveBiomesChunkScanner;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeRegister;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import wtfcore.InterModBlocks;
import wtfcore.WTFCore;
import wtfcore.WTFCoreConfig;
import wtfcore.worldgen.WorldGenListener;



@Mod(modid = CaveBiomes.modid, name = "WhiskyTangoFox's Cave Biomes", version = "1.6", dependencies = "after:UndergroundBiomes;required-after:WTFCore@[1.7,);required-after:TextureGeneratorLib")


public class CaveBiomes {
	public static  final String modid = WTFCore.CaveBiomes;

	@Instance(modid)
	public static CaveBiomes instance;

	@SidedProxy(clientSide="cavebiomes.proxy.CBClientProxy", serverSide="cavebiomes.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static String alphaMaskDomain = "cavebiomes:textures/blocks/alphamasks/";
	public static String overlayDomain =   "cavebiomes:textures/blocks/overlays/";

	public static CreativeTabs tabCaveDecorations = new CreativeTabs("CaveDecorations")
	{

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(CaveBlocks.GlowstoneStalactite);
		}

	};

	@EventHandler
	public void PreInit(FMLPreInitializationEvent preEvent)
	{
		APICaveBiomes.CAVEBIOMESAPI = new CaveBiomesAPI();
		WTFCaveBiomesConfig.customConfig();
		Entities.RegisterEntityList();
		proxy.registerRenderers();
		RenderRegisterer.RegisterCustomRenderers();
		ArmorRegistry.registerArmorTypes();
	}
	@EventHandler public void load(FMLInitializationEvent event)
	{

		CaveBlocks.BlockRegister();
		MinecraftForge.EVENT_BUS.register(new EventListener());
		DungeonTypeRegister.AddDungeonTypes();
		//recipes

	}
	@EventHandler
	public void PostInit(FMLPostInitializationEvent postEvent){

		CaveType.gen = GenCoreProvider.getGenCore();
		DungeonType.gen = GenCoreProvider.getGenCore();
		InterModBlocks.gen = GenCoreProvider.getGenCore();
		
		//add a config option to allow users to place a the thing in another dimension
		Iterator<Integer> iterator = WTFCoreConfig.overworlds.iterator();
		while (iterator.hasNext()){
			int dimensionID = iterator.next();
			WorldGenListener.GetScanner.put(dimensionID, new CaveBiomesChunkScanner());
			WTFCore.log.info("Adding CaveBiomes Overworld scanner for dimension " + dimensionID);
		}
		//Once I do a nether decorator, it goes here
	}




}

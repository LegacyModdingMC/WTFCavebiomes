package cavebiomes.worldgeneration.dungeontypes;

import cavebiomes.api.DungeonSet;
import cavebiomes.api.DungeonType;
import cavebiomes.worldgeneration.dungeontypes.ambient.DungeonCaveOasis;
import cavebiomes.worldgeneration.dungeontypes.ambient.DungeonForest;
import cavebiomes.worldgeneration.dungeontypes.ambient.DungeonSpeleothemGrotto;
import cavebiomes.worldgeneration.dungeontypes.ambient.DungeonTypeCaveIn;
import cavebiomes.worldgeneration.dungeontypes.ambient.DungeonTypeCinderShroom;
import cavebiomes.worldgeneration.dungeontypes.ambient.DungeonTypeFoxfire;
import cavebiomes.worldgeneration.dungeontypes.ambient.DungeonTypeFrozenSolid;
import cavebiomes.worldgeneration.dungeontypes.ambient.DungeonTypeNetherPortal;
import cavebiomes.worldgeneration.dungeontypes.ambient.DungeonTypePrismarine;
import cavebiomes.worldgeneration.dungeontypes.ambient.DungeonTypeRain;
import cavebiomes.worldgeneration.dungeontypes.ambient.DungeonTypeSoulsand;
import cavebiomes.worldgeneration.dungeontypes.mob.DunegonTypeSkeletonMage;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonBlaze;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonClassicSkeleton;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonClassicSpider;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonClassicZombie;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonMagmaSlime;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonPigman;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonSlime;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonTypeDerangedGolem;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonTypeMummysTomb;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonTypePharohTomb;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonTypeSkeletonKnight;
import cavebiomes.worldgeneration.dungeontypes.mob.DungeonTypeWitch;

public class DungeonTypeRegister {
	
	public static DungeonType CaveIn = new DungeonTypeCaveIn();
	public static DungeonType Grotto = new DungeonSpeleothemGrotto();
	private static DungeonType Spider = new DungeonClassicSpider();
	private static DungeonType Skeleton = new DungeonClassicSkeleton();
	private static DungeonType Zombie = new DungeonClassicZombie();
	private static DungeonType Forest = new DungeonForest();
	private static DungeonType Foxfire = new DungeonTypeFoxfire();
	private static DungeonType Frozen = new DungeonTypeFrozenSolid();
	private static DungeonType Rainstone = new DungeonTypeRain();
	private static DungeonType Slime = new DungeonSlime();
	private static DungeonType MagmaSlime = new DungeonMagmaSlime();
	private static DungeonType Cindershroom = new DungeonTypeCinderShroom();
	private static DungeonType Oasis = new DungeonCaveOasis();
	private static DungeonType Mummy = new DungeonTypeMummysTomb();
	private static DungeonType Pharoh = new DungeonTypePharohTomb();
	private static DungeonType Pigman = new DungeonPigman();
	private static DungeonType Blaze = new DungeonBlaze();
	private static DungeonType Golem = new DungeonTypeDerangedGolem();
	private static DungeonType Soulsand = new DungeonTypeSoulsand();
	private static DungeonType SkeletonKnight = new DungeonTypeSkeletonKnight();
	private static DungeonType SkeletonMage = new DunegonTypeSkeletonMage();
	private static DungeonType Prismarine = new DungeonTypePrismarine();
	private static DungeonType Witch = new DungeonTypeWitch();
	private static DungeonType NetherPortal = new DungeonTypeNetherPortal();
	//
	private static DungeonType[] defaultlist= {CaveIn, Grotto, Skeleton, Zombie};
	private static DungeonType[] forestlist = {Forest, Foxfire, Spider}; // Witch
	private static DungeonType[] coldlist = {Frozen};
	private static DungeonType[] wetlist = {Rainstone, Slime, Prismarine};
	private static DungeonType[] volcaniclist = {MagmaSlime, Cindershroom};
	private static DungeonType[] desertlist = {Mummy, Pharoh, Oasis, Soulsand};
	private static DungeonType[] netherlist = {Pigman, Blaze, SkeletonKnight, SkeletonMage, NetherPortal};
	private static DungeonType[] mountainlist = {Golem};
	
	
	public static DungeonSet defaultSet = new DungeonSet();
	public static DungeonSet forestSet = new DungeonSet();
	public static DungeonSet wetSet = new DungeonSet();
	public static DungeonSet swampSet = new DungeonSet();
	public static DungeonSet desertSet = new DungeonSet();
	public static DungeonSet jungleSet = new DungeonSet();
	public static DungeonSet coldSet = new DungeonSet();
	public static DungeonSet volcanicSet = new DungeonSet();
	public static DungeonSet mountainSet = new DungeonSet();
	public static DungeonSet icemountainSet = new DungeonSet();
	
	public static void AddDungeonTypes(){
		defaultSet.addDungeons(defaultlist);
		forestSet.addDungeons(defaultlist, forestlist);
		coldSet.addDungeons(defaultlist, coldlist);
		wetSet.addDungeons(defaultlist, wetlist);
		swampSet.addDungeons(defaultlist, wetlist, forestlist);
		desertSet.addDungeons(defaultlist, desertlist);
		jungleSet.addDungeons(defaultlist,volcaniclist);
		volcanicSet.addDungeons(defaultlist, netherlist);
		mountainSet.addDungeons(defaultlist, mountainlist);
		icemountainSet.addDungeons(defaultlist, coldlist, mountainlist);
		
	}
	
	
	
}

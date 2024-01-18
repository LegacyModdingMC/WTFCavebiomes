package cavebiomes.api;

import java.util.HashMap;
import java.util.Random;

public class DungeonSet {

	private HashMap<Integer, DungeonType> dungeonMap = new HashMap<Integer, DungeonType>();
	private int listSize = 0;
	
	
	public void addDungeons(DungeonType[] list1){
		for (int loop=0;loop<list1.length;loop++){
			addDungeon(list1[loop]);
		}
	}
	public void addDungeons(DungeonType[] list1, DungeonType[] list2){
		for (int loop=0;loop<list1.length;loop++){
			addDungeon(list1[loop]);
		}
		for (int loop=0;loop<list2.length;loop++){
			addDungeon(list2[loop]);
		}
	}
	public void addDungeons(DungeonType[] list1, DungeonType[] list2, DungeonType[] list3){
		for (int loop=0;loop<list1.length;loop++){
			addDungeon(list1[loop]);
		}
		for (int loop=0;loop<list2.length;loop++){
			addDungeon(list2[loop]);
		}
		for (int loop=0;loop<list3.length;loop++){
			addDungeon(list3[loop]);
		}
	}
	
	
	public void addDungeon(DungeonType newdungeon){
		dungeonMap.put(listSize, newdungeon);
		//WTFCore.log.info("Adding dungeon " + newdungeon.name);
		listSize++;
	}
	
	public DungeonType getRandomDungeon(Random random){
		return dungeonMap.get(random.nextInt(listSize));
	}
	
}

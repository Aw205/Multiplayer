package com.mygdx.game.entity;

import java.util.HashMap;
import java.util.Map;

public class EntityManager {
	
	static public Map<String,Entity> entities = new HashMap<String,Entity>();
	
	public EntityManager() {
		
		
	}

	public static void addEntity(Entity e) {
		
		entities.put(e.ID, e);
		
	}
	
	public static Entity getEntity(String ID) {
		
		return entities.get(ID);
	}
}

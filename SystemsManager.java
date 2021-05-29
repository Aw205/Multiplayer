package com.mygdx.game.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mygdx.game.Multiplayer;
import com.mygdx.game.entity.Entity;

public class SystemsManager {
	
	public static List<GameSystem> systems = new ArrayList<GameSystem>();
	
	private static Set<Entity> toDelete = new HashSet<Entity>();
	
	public SystemsManager() {
		
		systems.add(new InputSystem());
		systems.add(new AnimationSystem());
		systems.add(new AttackSystem());
		systems.add(new MovementSystem());
		systems.add(new AISystem());
		systems.add(new CollisionSystem());
		systems.add(new RenderSystem(Multiplayer.sb));
		
		
		
	}
	
	public void updateSystems() {
			
		for(GameSystem s: systems) {	
			s.update();
		}
		
		deleteEntities();
	}
	
	
	public static void notifySystems(Entity e) {		
		for(GameSystem s: systems) {		
			s.addEntity(e);
		}	
	}
	
	public static void removeFromSystems(Entity e) {
		
		toDelete.add(e);
	}
	
	public void deleteEntities() {
		

		for (GameSystem s : systems) {
			for (Entity e : toDelete) {
				s.removeEntity(e);
			}
		}
		toDelete.clear();
	}
}

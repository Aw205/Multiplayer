package com.mygdx.game.system;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.FloatingText;
import com.mygdx.game.GameScreen;
import com.mygdx.game.component.HitboxComponent;
import com.mygdx.game.component.PositionComponent;
import com.mygdx.game.component.ProjectileComponent;
import com.mygdx.game.entity.Entity;

public class CollisionSystem extends GameSystem{
	
	private List<Entity> activeIntervals = new ArrayList<Entity>();
	private List<Marker> markers = new ArrayList<Marker>();

	private class Marker{
		
		public Marker(Entity e, float x,boolean isStart) {
			
			this.e=e;
			this.x=x;
			this.isStart=isStart;
		}
	
		Entity e;
		float x;
		boolean isStart;
		
	}

	@Override
	public void update() {
		
		
		updateMarkers();
		sortMarkers();

		for (int i = 0; i < markers.size(); i++) {
			if (markers.get(i).isStart) {
				activeIntervals.add(markers.get(i).e);
				detectCollisions();
			} else {
				activeIntervals.remove(markers.get(i).e);
			}
		}
		activeIntervals.clear();
		
	}
	
	private void detectCollisions() {
		
		Entity last = activeIntervals.get(activeIntervals.size()-1);
		
		for(int i=0;i<activeIntervals.size()-1;i++) {
			
			if(activeIntervals.get(i).getComponent(HitboxComponent.class).hitbox.overlaps
					(last.getComponent(HitboxComponent.class).hitbox)) {
				
				processCollision(activeIntervals.get(i),last);
			}
		}
	}
	
	private void processCollision(Entity e1, Entity e2) {
		
		
		  PositionComponent pc = e1.getComponent(PositionComponent.class);
		  GameScreen.textRenderer.stage.addActor(new FloatingText("25",new
		  Vector3(pc.position.x,pc.position.y,0)));
		 
		if(e1.getComponent(ProjectileComponent.class)!=null) {
			SystemsManager.removeFromSystems(e1);
		}
		if(e2.getComponent(ProjectileComponent.class)!=null) {
			SystemsManager.removeFromSystems(e2);
		}
	}
	
	
	private void sortMarkers() { //sort along x-axis
		
		for (int j = 1; j < markers.size(); j++) {
	        Marker current = markers.get(j);
	        int i = j-1;
	        while ((i > -1) && (markers.get(i).x>current.x)) {
	            markers.set(i+1, markers.get(i));
	            i--;
	        }
	        markers.set(i+1, current);
	    }
		
	}
	
	private void updateMarkers() {
		for(Marker m : markers) {
			HitboxComponent hb = m.e.getComponent(HitboxComponent.class);
			m.x= (m.isStart) ? hb.hitbox.x : hb.hitbox.x+hb.hitbox.width;
		}
	}
	
	@Override
	public boolean addEntity(Entity e) {
		if(e.getComponent(HitboxComponent.class)!=null) {
			entities.add(e);
			HitboxComponent hb = e.getComponent(HitboxComponent.class);
			markers.add(new Marker(e,hb.hitbox.x,true));
			markers.add(new Marker(e,hb.hitbox.x+hb.hitbox.width,false));
			return true;
		}
		return false;
	}
	
	@Override
	protected void removeEntity(Entity e) {
		this.entities.remove(e);
		for(int i=0;i<markers.size();i++) {
			if(markers.get(i).e==e) {
				markers.remove(i);
				i--;
			}
		}
		
		
		
	}
}

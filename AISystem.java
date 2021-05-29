package com.mygdx.game.system;


import java.util.List;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Directions.Direction;
import com.mygdx.game.component.AIComponent;
import com.mygdx.game.component.HitboxComponent;
import com.mygdx.game.component.PositionComponent;
import com.mygdx.game.entity.Entity;

public class AISystem extends GameSystem {

	@Override
	public void update() {

		for(Entity e : entities) {
			updateMovement(e);
		}
	}

	public void updateMovement(Entity e) {
		
		PositionComponent pc =e.getComponent(PositionComponent.class);
		AIComponent ai= e.getComponent(AIComponent.class);
		HitboxComponent hc = e.getComponent(HitboxComponent.class);
		
		Vector2 start=ai.start;
		List<Vector2> path = ai.path;
		List<Direction> pathDirections=ai.pathDirections;

		if (start.dst(path.get(ai.target)) > 0.1f) {
		
			start.lerp(path.get(ai.target), 0.3f);

		} else if ((ai.target + ai.direction) > -1 && (ai.target + ai.direction) < path.size()) {
			start.set(path.get(ai.target));
			ai.target += ai.direction;
			Direction d = (ai.direction == 1) ? pathDirections.get(ai.target - ai.direction)
					: pathDirections.get(ai.target - ai.direction).opposite();
			pc.direction=d;
		} else {
			start.set(path.get(ai.target));
			ai.direction = -ai.direction;
			ai.target += ai.direction;
		}
		hc.hitbox.setPosition(start.x + 0.5f, start.y + 0.5f);
	}

	

	@Override
	public boolean addEntity(Entity e) {
		if (e.getComponent(AIComponent.class) != null) {
			this.entities.add(e);
			return true;
		}
		return false;
	}
	
	@Override
	protected void removeEntity(Entity e) {
		this.entities.remove(e);
		
	}

}

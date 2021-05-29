package com.mygdx.game.system;


import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Board;
import com.mygdx.game.Directions.Direction;
import com.mygdx.game.GameScreen;
import com.mygdx.game.component.AIComponent;
import com.mygdx.game.component.HitboxComponent;
import com.mygdx.game.component.InputComponent;
import com.mygdx.game.component.PositionComponent;
import com.mygdx.game.component.RotationComponent;
import com.mygdx.game.component.StatsComponent;
import com.mygdx.game.component.VelocityComponent;
import com.mygdx.game.component.WeaponComponent;
import com.mygdx.game.entity.Entity;

public class MovementSystem extends GameSystem{
	
	public MovementSystem() {
		
	}
	
	
	public void update() {
		
		for(Entity e : entities) {
			
			if(e.getComponent(WeaponComponent.class)!=null)
			{
				updateWeapon(e);
			}
			else if(e.getComponent(RotationComponent.class)!=null) {
				updateProjectile(e);
			}
			else {
				updateInputMovement(e);
			}	
		}
	}
	
	private void updateProjectile(Entity e) {
		
		PositionComponent pos = e.getComponent(PositionComponent.class);
		HitboxComponent hc = e.getComponent(HitboxComponent.class);
		RotationComponent rc = e.getComponent(RotationComponent.class);
		
		//StatsComponent stats = (StatsComponent) e.getComponent(StatsComponent.class);
		
		if (pos.position.dst2(pos.spawnPosition) > 80) {
			SystemsManager.removeFromSystems(e);
			return;
		}
		pos.position.add(pos.velocity);
		
		float height=(float) Math.sin(Math.toRadians(rc.angle));
		float width=(float) Math.cos(Math.toRadians(rc.angle));
		hc.hitbox.setPosition(pos.position.x+2f*width,pos.position.y+2f*height);
		
		
	}
	
	
	private void updateWeapon(Entity e) {
		
		InputComponent in = e.getComponent(InputComponent.class);	
		PositionComponent pos =  e.getComponent(PositionComponent.class);
		RotationComponent rot =  e.getComponent(RotationComponent.class);
		
		Vector3 mousePos=GameScreen.cam.unproject(in.mousePosition);
		Vector2 mouseLoc = new Vector2(mousePos.x, mousePos.y);
		PositionComponent playerPosition =  Board.p.getComponent(PositionComponent.class);
		Vector2 velocity = mouseLoc.sub(playerPosition.position);
		
		pos.position=playerPosition.position;
		rot.angle=velocity.angleDeg();
		rot.offsetY=(float) Math.sin(Math.toRadians(rot.angle));
		rot.offsetX=(float) Math.cos(Math.toRadians(rot.angle));
	
	}
	
	
	private void updateInputMovement(Entity e) {

		PositionComponent pos = e.getComponent(PositionComponent.class);
		VelocityComponent vel = e.getComponent(VelocityComponent.class);

		for (int i = 0; i < InputSystem.downKeys.size(); i++) {

			float x = pos.position.x;
			float y = pos.position.y;

			if (getDirection(InputSystem.downKeys.get(i)) != null) {
				pos.position.mulAdd(VelocityComponent.directions.get(getDirection(InputSystem.downKeys.get(i))),
						Gdx.graphics.getDeltaTime() * vel.speed);
			}

			if (!isValid((int) pos.position.x, (int) pos.position.y)) {
				pos.position.set(x, y);

			}
		}
	}
	
	 public Direction getDirection(int keyCode) {
	        switch(keyCode) {
	            case Keys.A: return Direction.LEFT;
	            case Keys.D: return Direction.RIGHT;
	            case Keys.S: return Direction.DOWN;
	            case Keys.W: return Direction.UP;
	        }
			return null;
	    }
	
	private boolean isValid(int x, int y) {	
	
		if(Board.nodeArray[x][y].isBlocked) {
			return false;
		}
		return true;
	}

	@Override
	public boolean addEntity(Entity e) {
		if (e.getComponent(PositionComponent.class) != null && e.getComponent(AIComponent.class)==null) {
			entities.add(e);
			return true;
		}
		return false;
	}


	@Override
	protected void removeEntity(Entity e) {
		this.entities.remove(e);
		
	}
	
}

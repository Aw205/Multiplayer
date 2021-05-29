package com.mygdx.game.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.Directions.Direction;
import com.mygdx.game.component.AIComponent;
import com.mygdx.game.component.AnimationComponent;
import com.mygdx.game.component.PositionComponent;
import com.mygdx.game.component.RotationComponent;
import com.mygdx.game.component.WeaponComponent;
import com.mygdx.game.entity.Entity;

public class AnimationSystem extends GameSystem {


	public AnimationSystem() {

	}

	public void update() {

		for (Entity e : entities) {
			
			if(e.getComponent(AIComponent.class)!=null) {
				updateEnemy(e);
			}

			else if(e.getComponent(WeaponComponent.class)!=null || e.getComponent(RotationComponent.class)!=null) {
				//animateWeapon(e);
			}
			else {
				updateAnimation(e);
			}
		}

	}
	
	private void updateAnimation(Entity e) {

		AnimationComponent ac = e.getComponent(AnimationComponent.class);
		
		
		
		if (Gdx.input.isKeyPressed(Keys.D)) {
			ac.currentAnimation = ac.walkRight;
			ac.stateTime += Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			ac.currentAnimation = ac.walkLeft;
			ac.stateTime += Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Keys.W)) {
			ac.currentAnimation = ac.walkForward;
			ac.stateTime += Gdx.graphics.getDeltaTime();
		}
		else if (Gdx.input.isKeyPressed(Keys.S)) {
			ac.currentAnimation = ac.walkBack;
			ac.stateTime += Gdx.graphics.getDeltaTime();
		}
		else {
			ac.stateTime =0; 
		}

	}

	private void animateWeapon(Entity e) {
		//AnimationComponent ac = (AnimationComponent) e.getComponent(AnimationComponent.class);
		//ac.currentAnimation=ac.bowAnimation;
		
	}
	
	private void updateEnemy(Entity e) {
		
		AnimationComponent ac = e.getComponent(AnimationComponent.class);
		PositionComponent pc = e.getComponent(PositionComponent.class);
		
		ac.stateTime += Gdx.graphics.getDeltaTime();
		if (pc.direction==Direction.RIGHT) {
			ac.currentAnimation = ac.animations.get("moveRight");
		} else if (pc.direction==Direction.LEFT) {
			ac.currentAnimation = ac.animations.get("moveLeft");
		} else if (pc.direction==Direction.UP) {
			ac.currentAnimation = ac.animations.get("moveUp");
		}
		else if (pc.direction==Direction.DOWN) {
			ac.currentAnimation = ac.animations.get("moveDown");
		}
	}

	@Override
	public boolean addEntity(Entity e) {
		if (e.getComponent(AnimationComponent.class) != null ) {
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

package com.mygdx.game.system;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.component.AIComponent;
import com.mygdx.game.component.AnimationComponent;
import com.mygdx.game.component.PositionComponent;
import com.mygdx.game.component.RotationComponent;
import com.mygdx.game.component.WeaponComponent;
import com.mygdx.game.entity.Entity;

public class RenderSystem extends GameSystem {
	
	private SpriteBatch sb;
	
	public RenderSystem(SpriteBatch sb) {		
		this.sb=sb;
	}
	
	public void update() {

		for (Entity e : entities) {
			drawEntity(e);
		}
	}
	
	private void drawEntity(Entity e) {

		PositionComponent pc = e.getComponent(PositionComponent.class);
		AnimationComponent ac =  e.getComponent(AnimationComponent.class);

		TextureRegion currentFrame = ac.currentAnimation.getKeyFrame(ac.stateTime, true);
		
		if (e.getComponent(WeaponComponent.class) != null) {

			RotationComponent rot = e.getComponent(RotationComponent.class);
			sb.draw(currentFrame, pc.position.x - 0.5f + rot.offsetX, pc.position.y - 0.25f + rot.offsetY, 1, 1, 2, 2,
					1, 1, rot.angle + 45 + 180);
		} 
		else if(e.getComponent(RotationComponent.class)!=null) {
			RotationComponent rot =  e.getComponent(RotationComponent.class);
			sb.draw(currentFrame, pc.position.x + 0.5f, pc.position.y + 0.5f, 0, 0, 2, 2, 1, 1, rot.angle-45);
		}
		else if(e.getComponent(AIComponent.class)!=null) {
			AIComponent ai =  e.getComponent(AIComponent.class);
			sb.draw(currentFrame, ai.start.x - 0.5f, ai.start.y - 0.25f, 2, 2);
			
		}
		else {
			sb.draw(currentFrame, pc.position.x - 0.5f, pc.position.y - 0.25f, 2, 2);
		}
	}

	@Override
	public boolean addEntity(Entity e) {
		if (e.getComponent(PositionComponent.class) != null && e.getComponent(AnimationComponent.class) != null) {
			entities.add(e);
			return true;
		}
		return false;
	}
	
	public synchronized boolean addEntityy(Entity e) {
		if (e.getComponent(PositionComponent.class) != null && e.getComponent(AnimationComponent.class) != null) {
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

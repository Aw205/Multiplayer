package com.mygdx.game.system;


import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.WeaponLoader;
import com.mygdx.game.component.AttackComponent;
import com.mygdx.game.component.InputComponent;
import com.mygdx.game.component.PositionComponent;
import com.mygdx.game.component.RotationComponent;
import com.mygdx.game.component.StatsComponent;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Projectile;

public class AttackSystem extends GameSystem{
	

	@Override
	public void update() {
		
		for(Entity e : entities) {
			
			InputComponent ic = e.getComponent(InputComponent.class);
			if(ic.keyPressed==Keys.F) {
				
				InputSystem.keyPressed=0;
				
				PositionComponent pos =  e.getComponent(PositionComponent.class);
				RotationComponent rot =  e.getComponent(RotationComponent.class);
				
				Projectile p= new Projectile(WeaponLoader.projectiles.get(e));
				
				
				PositionComponent projectilePos = p.getComponent(PositionComponent.class);
				RotationComponent projectileRot =  p.getComponent(RotationComponent.class);
	
				
				projectilePos.position.set(pos.position);
				projectilePos.spawnPosition.set(pos.position);
				projectileRot.angle=rot.angle;
				projectilePos.velocity.setAngleDeg(rot.angle);
				
				p.addComponent(e.getComponent(StatsComponent.class));
				
				SystemsManager.notifySystems(p);
			}
			
		}
	}

	@Override
	public boolean addEntity(Entity e) {
		if(e.getComponent(AttackComponent.class)!=null) {
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

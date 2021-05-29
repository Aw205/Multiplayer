package com.mygdx.game.entity;

import com.mygdx.game.AssetsManager;
import com.mygdx.game.component.AnimationComponent;
import com.mygdx.game.component.HitboxComponent;
import com.mygdx.game.component.PositionComponent;
import com.mygdx.game.component.ProjectileComponent;
import com.mygdx.game.component.RotationComponent;

public class Projectile extends Entity implements Cloneable{
	
	
	public Projectile(AssetsManager am) {
		
		this.components.add(new PositionComponent());
		this.components.add(new RotationComponent());
		this.components.add(new HitboxComponent());
		this.components.add(new ProjectileComponent());
		this.components.add(new AnimationComponent(am));
		
	}
	
	public Projectile(Projectile toClone) {
		
		this.components.add(new PositionComponent());
		this.components.add(new RotationComponent());
		this.components.add(new HitboxComponent());
		this.components.add(new ProjectileComponent());
		this.components.add(((AnimationComponent) toClone.getComponent(AnimationComponent.class)));
	}
}

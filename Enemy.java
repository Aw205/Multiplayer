package com.mygdx.game.entity;

import com.mygdx.game.AssetsManager;
import com.mygdx.game.component.AIComponent;
import com.mygdx.game.component.AnimationComponent;
import com.mygdx.game.component.HitboxComponent;
import com.mygdx.game.component.PositionComponent;

public class Enemy extends Entity{

	public Enemy(AssetsManager am) {
		this.addComponent(new PositionComponent());
		this.addComponent(new AnimationComponent(am));
		this.addComponent(new AIComponent());	
		this.addComponent(new HitboxComponent());
	}
}

package com.mygdx.game.entity;

import com.mygdx.game.AssetsManager;
import com.mygdx.game.component.AnimationComponent;
import com.mygdx.game.component.AttackComponent;
import com.mygdx.game.component.InputComponent;
import com.mygdx.game.component.PositionComponent;
import com.mygdx.game.component.RotationComponent;
import com.mygdx.game.component.StatsComponent;
import com.mygdx.game.component.WeaponComponent;


public class Weapon extends Entity implements Cloneable {
	
	public Weapon(AssetsManager am) {
		
		this.components.add(new WeaponComponent());
		this.components.add(new AnimationComponent(am));
		this.components.add(new RotationComponent());
		this.components.add(new PositionComponent());
		this.components.add(new InputComponent());
		this.components.add(new StatsComponent());
		this.components.add(new AttackComponent());
		//SystemsManager.notifySystems(this);
		
	}
	
	@Override
	public Object clone()throws CloneNotSupportedException{  
		return (Weapon)super.clone();  
	   }
}



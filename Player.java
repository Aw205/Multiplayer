package com.mygdx.game.entity;
import com.mygdx.game.AssetsManager;
import com.mygdx.game.WeaponLoader;
import com.mygdx.game.component.AnimationComponent;
import com.mygdx.game.component.HitboxComponent;
import com.mygdx.game.component.InputComponent;
import com.mygdx.game.component.PositionComponent;
import com.mygdx.game.component.VelocityComponent;
import com.mygdx.game.system.SystemsManager;

public class Player extends Entity{
	
	public Weapon bow;
	private AssetsManager am;
	
	public Player(AssetsManager am) {
		
		this.am=am;		
		this.components.add(new InputComponent());
		this.components.add(new PositionComponent());
		this.components.add(new VelocityComponent());
		this.components.add(new AnimationComponent(am));
		//this.components.add(new HitboxComponent());
		//SystemsManager.notifySystems(this);
		
		bow = WeaponLoader.weapons.get("LightBow1");
		SystemsManager.notifySystems(bow);
	}
}

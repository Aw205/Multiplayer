package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Loadout extends Table{
	
	Image left = new Image(new Texture(Gdx.files.internal("leftWeaponSlot.png")));
	Image mid = new Image(new Texture(Gdx.files.internal("midWeaponSlot.png")));
	Image right = new Image(new Texture(Gdx.files.internal("rightWeaponSlot.png")));
	

	public Loadout() {
		
		this.add(left);
		this.add(mid);
		this.add(right);
		
	}
}

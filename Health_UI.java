package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public class Health_UI extends HorizontalGroup {
	
	Image knobBefore = new Image(new Texture(Gdx.files.internal("healthPortrait.png")));
	Image redMid = new Image(new Texture(Gdx.files.internal("redMid.png")));
	Image emptyBar = new Image(new Texture(Gdx.files.internal("emptyHealth.png")));
	VerticalGroup bar = new VerticalGroup();
	public Health_UI() {

		this.addActor(knobBefore);
		this.addActor(bar);
		createBar();
	}
	
	
	
	private void createBar() {
		createHealthBar(5,Direction.DOWN);
		createHealthBar(0,Direction.UP);
		createHealthBar(5,Direction.UP);
	}
	
	private void createHealthBar(int pad,Direction d) {
		HealthBar health = new HealthBar(pad,d);
		bar.addActor(health);
		
	}
}

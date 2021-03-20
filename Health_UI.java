package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;

public class Health_UI extends HorizontalGroup {
	
	Image knobBefore = new Image(new Texture(Gdx.files.internal("healthPortrait.png")));
	Image redMid = new Image(new Texture(Gdx.files.internal("redMid.png")));
	Table bar = new Table();
	public Health_UI() {
		
		createBar();
		
		this.addActor(knobBefore);
		this.addActor(bar);
	}
	
	
	
	private void createBar() {
		
		createHealthBar();
		bar.row().padTop(15);
		createHealthBar();
		bar.row().padTop(15);
		createHealthBar();
	}
	
	private void createHealthBar() {
		ProgressBarStyle s = new ProgressBarStyle();
		s.background=redMid.getDrawable();
		//s.knobBefore= knobBefore.getDrawable();
		HealthBar health = new HealthBar(0,100,1,false,s);
		bar.add(health);
	}
}

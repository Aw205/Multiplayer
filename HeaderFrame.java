package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class HeaderFrame extends HorizontalGroup{
	
	Image headerLeft = new Image(new Texture(Gdx.files.internal("big_Frame_left.png")));
	Image headerMid = new Image(new Texture(Gdx.files.internal("big_Frame_mid.png")));
	Image headerRight = new Image(new Texture(Gdx.files.internal("big_Frame_right.png")));
	
	public HeaderFrame() {
		
		//this.setDebug(true);
		this.addActor(headerLeft);
		this.addActor(headerMid);
		this.addActor(headerMid);
		this.addActor(headerRight);
	}

}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class HealthBar extends HorizontalGroup {
	
	int max=25;
	
	public HealthBar(int pad,Direction d) {
		if(d==Direction.UP) {
			this.padTop(pad);
		}
		else {
			this.padBottom(pad);
		}		
		for(int i=0;i<max;i++) {
			Image emptyBar = new Image(new Texture(Gdx.files.internal("redMid.png")));
			this.addActor(emptyBar);
			
		}
		
	}

}

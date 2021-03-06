package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class InventorySlot extends Image {
	
	public boolean hasItem=false;
	static Image slot = new Image(new Texture(Gdx.files.internal("slotTopLeft.png")));
	
	
	public InventorySlot() {
		
		this.setDrawable(slot.getDrawable());
	
		this.addListener(new ClickListener() {
			@Override
			public boolean isPressed() {
				System.out.println("here");
				return true;
			}
			 
		});
	}
}

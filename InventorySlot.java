package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class InventorySlot extends Container<InventoryItem> {
	
	public boolean hasItem=false;
	Image slot = new Image(new Texture(Gdx.files.internal("slotTopLeft.png")));
	
	public InventorySlot() {
		//this.setDebug(true);
		this.setBackground(slot.getDrawable());
		this.setActor(new InventoryItem());
	}
}

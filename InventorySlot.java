package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class InventorySlot extends Container<InventoryItem> {
	
	public boolean hasItem=false;
	Image slot;
	
	public InventorySlot(AssetsManager am) {
		
		slot = new Image(am.manager.get("slotTopLeft.png",Texture.class));
		this.setBackground(slot.getDrawable());
		this.setActor(new InventoryItem(am));
	}
}

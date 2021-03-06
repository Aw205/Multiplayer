package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Inventory extends Table {
	
	static Image frame = new Image(new Texture(Gdx.files.internal("windowFrame.png")));
	static Image slot = new Image(new Texture(Gdx.files.internal("slotTopLeft.png")));
	Table inventoryFrame = new Table();

	public Inventory() {
	
		this.setTouchable(Touchable.enabled);
		this.initSlots();
		this.setBackground(frame.getDrawable());
		this.add(inventoryFrame);
		this.pack();
	
	}
	
	public void initSlots() {
		inventoryFrame.setDebug(true);
		inventoryFrame.setTouchable(Touchable.enabled);
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				InventorySlot s = new InventorySlot();
				s.setBounds(100, 100, 10, 10);
				s.setDebug(true);
				inventoryFrame.add(s);
			}
			inventoryFrame.row();
		}
	}
}

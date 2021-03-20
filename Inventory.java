package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Inventory extends Table {
	
	 Image frame = new Image(new Texture(Gdx.files.internal("windowFrame.png")));
	 

	public Inventory() {
		
		//this.setDebug(true);
		this.setBackground(frame.getDrawable());
		this.initSlots();
		this.pack();
	}
	
	public void initSlots() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				InventorySlot s = new InventorySlot();
				this.addActor(s);
				Cell<InventorySlot> current = this.add(s);
				if(i==0) {
					current.padTop(30);
				}
				if(i==2) {
					current.padBottom(30);
				}
				if(j==0) {
					current.padLeft(10);
				}
				if(j==2) {
					current.padRight(10);
				}
			}
			if(i!=2) {
				this.row();
			}
		}
	}
}

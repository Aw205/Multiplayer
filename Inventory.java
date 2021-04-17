package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Inventory extends Table {
	
	 private AssetsManager am;
	 Image frame;
	 
	 private final int ROWS=4;
	 private final int COLS=4;
	 
	 
	public Inventory(AssetsManager am) {
		this.am=am;
		this.frame= new Image(am.manager.get("windowFrame.png", Texture.class));
		this.setBackground(frame.getDrawable());
		this.initSlots();
		this.pack();
	}
	
	public void initSlots() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				InventorySlot s = new InventorySlot(am);
				this.addActor(s);
				Cell<InventorySlot> current = this.add(s);
				if(i==0) {
					current.padTop(15);
				}
				else if(i==ROWS-1) {
					current.padBottom(15);
				}
				if(j==0) {
					current.padLeft(20);
				}
				else if (j==COLS-1) {
					current.padRight(20);
				}
			}
			if(i!=ROWS-1) {
				this.row();
			}
		}
	}
}

package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Loadout extends HorizontalGroup {
	
	private AssetsManager am;
	private final int NUM_SLOTS=3;

	public Loadout(AssetsManager am) {
		//this.setDebug(true);
		this.am=am;
		createSlots();
		
	}
	
	private void createSlots() {

		for (int i = 0; i < NUM_SLOTS; i++) {
			InventorySlot s;
			if (i == 0) {
				s = new InventorySlot(am, new Image(am.manager.get("leftWeaponSlot.png", Texture.class)));
			} else if (i == NUM_SLOTS - 1) {
				s = new InventorySlot(am, new Image(am.manager.get("rightWeaponSlot.png", Texture.class)));
			} else {
				s = new InventorySlot(am, new Image(am.manager.get("midWeaponSlot.png", Texture.class)));
			}
			this.addActor(s);
		}
	}
}

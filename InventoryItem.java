package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class InventoryItem extends Image {
	
	Image sword;
	
	public InventoryItem(AssetsManager am) {
		sword = new Image(am.manager.get("sampleSword.png",Texture.class));
		this.setDrawable(sword.getDrawable());
		this.addListener(new DragListener() {
		    public void drag(InputEvent event, float x, float y, int pointer) {
		    	InventoryItem.this.getParent().setZIndex(8);
		        InventoryItem.this.moveBy(x - InventoryItem.this.getWidth() / 2, y - InventoryItem.this.getHeight() / 2);
		    }
		    
		    public void dragStop(InputEvent event, float x, float y, int pointer) {
		    	Actor a = InventoryItem.this.hit(x, y, true);
		    	InventoryItem.this.setPosition(a.getOriginX(), a.getOriginY());
		    }
		});
	}
}

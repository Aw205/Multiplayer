package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class InventorySlot extends Stack {
	
	public boolean hasItem=false;
	static Image slot = new Image(new Texture(Gdx.files.internal("slotTopLeft.png")));
	Image sword = new Image(new Texture(Gdx.files.internal("sampleSword.png")));
	
	
	
	public InventorySlot() {
		
		this.add(slot);
		this.add(new InventoryItem());
	
		this.addListener(new ClickListener() {
			 @Override
			    public void clicked(InputEvent event, float x, float y) {
			       //System.out.println("here");
			    }
			 @Override
			 public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				 System.out.println("entered cell");
			 }
		});
	}
}

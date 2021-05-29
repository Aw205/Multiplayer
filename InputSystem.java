package com.mygdx.game.system;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameScreen;
import com.mygdx.game.component.InputComponent;
import com.mygdx.game.entity.Entity;

public class InputSystem extends GameSystem implements InputProcessor{
	
	
	public static List<Integer> downKeys = new ArrayList<Integer>();
	public static int keyPressed=0;
	
	public InputSystem() {
		GameScreen.multiplexer.addProcessor(this);
	}
	
	@Override
	public void update() {
		
		Vector3 mousePosition = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
		
		for(Entity e: entities) {
			InputComponent ic = e.getComponent(InputComponent.class);
			ic.keyPressed=keyPressed;	
			ic.mousePosition.set(mousePosition);
		}
	}

	@Override
	public boolean addEntity(Entity e) {
		if (e.getComponent(InputComponent.class) != null ) {
			entities.add(e);
			return true;
		}
		return false;
	}
	
	@Override
	protected void removeEntity(Entity e) {
		this.entities.remove(e);
		
	}

	@Override
	public boolean keyDown(int keycode) {
		downKeys.add(keycode);
		 keyPressed=keycode;
         return true;		
	}

	@Override
	public boolean keyUp(int keycode) {
		downKeys.remove(Integer.valueOf(keycode)); 
		keyPressed=0;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		
		return false;
	}

}

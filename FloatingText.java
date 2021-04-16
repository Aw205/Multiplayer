package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FloatingText extends Actor {

	private final String text;
	private final long animationDuration = 1000;
	private BitmapFont font;
	private long animationStart;
	public boolean animated=false;
	private float deltaY=2;
	static FreeTypeFontGenerator generator;
	static FreeTypeFontParameter parameter;
	private Vector3 position;
	
	
	static {
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal("upheavtt.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size=22;
		
		//generator.dispose();
		
	}

	public FloatingText(String text,Vector3 position) {
		this.text = text;
		this.position=position;
		convertToScreenSpace();
		font=generator.generateFont(parameter); 		
	}
	
	public void animate() {
	    animated = true;
	    animationStart = System.currentTimeMillis();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		if(animated) {
			if(animationStart + animationDuration < System.currentTimeMillis()) {
				font.dispose();
				this.remove();
				return;
			}
		}
		else {
			animate();
		}
		
		 float elapsed = System.currentTimeMillis() - animationStart;
		 font.setColor(getColor().r, 0, 0, parentAlpha * (1 - elapsed / animationDuration));	
	     font.draw(batch, text, position.x,position.y+200*elapsed/1000);
	}
	
	private void convertToScreenSpace() {
		
		 position=GameScreen.cam.project(position);
		 position.y= Gdx.graphics.getHeight()-position.y;
		 position=GameScreen.textRenderer.stage.getCamera().unproject(position);
	}
}

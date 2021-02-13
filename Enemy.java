package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy {
	
	private Animation<TextureRegion> currentAnimation=AnimationStyles.logBack;
	private float stateTime=0f;
	//Rectangle hitBox = new Rectangle(70,70,2,2);
	
	public Enemy() {
		
	}
	
	public void drawEnemy(SpriteBatch sb,float delta) {
		stateTime+=delta;
		TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);
		sb.draw(currentFrame, 60, 60, 2, 2);
		
	}

}

package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Weapon {
	
	
	private  Animation<TextureRegion> bowAnimation;
	public Animation<TextureRegion> currentAnimation;
	private float stateTime=0;
	
	private Vector2 position;
	private float angle;
	
	
	public Weapon(AssetsManager am) {
		
		createAnimations(am);
	}
	
	public void createAnimations(AssetsManager am) {
		
		Texture bowSheet= am.manager.get("LightBow_1.png",Texture.class);
		TextureRegion[][] tmp = TextureRegion.split(bowSheet,52,52);		
		bowAnimation = new Animation<TextureRegion>(0.1f, tmp[0]);
	}
	
	public void draw(SpriteBatch sb, float delta) {
	
		TextureRegion currentFrame = bowAnimation.getKeyFrame(stateTime, true);
		
		float offsetY=(float) Math.sin(Math.toRadians(angle));
		float offsetX=(float) Math.cos(Math.toRadians(angle));
		
		sb.draw(currentFrame,position.x-0.5f+offsetX, position.y-0.25f+offsetY,1,1,2,2,1,1, angle+45+180);
		
		}
		
	public void updatePosition(Vector2 position, float angle) {		
		this.position=position;
		this.angle=angle;
	}
}

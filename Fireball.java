package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Fireball {
	
	private static Texture fireMoving;
	private static Texture fireDespawn;
	public Vector2 spawn;
	public Vector2 position;
	public Vector2 velocity;
	static Animation<TextureRegion> moving;
	static Animation<TextureRegion> despawn;
	Animation<TextureRegion> currentAnimation;
	float stateTime = 0f;
	private float speed=0.3f;
	float angle=0;
	private int range=25;
	public boolean isAlive=true;
	
	
	static {
		
		fireMoving = new Texture(Gdx.files.internal("fireMoveSheet.png"));
		fireDespawn = new Texture(Gdx.files.internal("fireDieSheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(fireMoving, 220, 124);
		TextureRegion[][] tmp2 = TextureRegion.split(fireDespawn, 120, 172);
		moving = new Animation<TextureRegion>(0.08f, tmp[0]);
		despawn = new Animation<TextureRegion>(0.08f, tmp2[0]);
	}
	
	
	public Fireball(Vector2 spawnPos,Vector2 velocity) {
		
		currentAnimation=moving;
		spawn=new Vector2(spawnPos.x,spawnPos.y);
		position= new Vector2(spawnPos.x,spawnPos.y);
		this.velocity=velocity;
		this.velocity.scl(speed);
		this.angle=velocity.angleDeg();
	}

	public void draw(SpriteBatch sb, float delta) {
		
		TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);
		if(isAlive) {
			sb.draw(currentFrame, position.x, position.y, 0, 0, 
					1.77f, 1, 1, 1, angle+180);
			updatePosition();
		}
		else {
			sb.draw(currentFrame, position.x, position.y, 0, 0, 
					1, 1.43f, 1, 1, angle+180);
		}
		stateTime+=delta;
	}
	
	private void updatePosition() {
		
		if(position.dst2(spawn)>range) {
			currentAnimation=despawn;
			isAlive=false;
			stateTime=0;
			return;
		}		
			position.add(velocity);
	}
}

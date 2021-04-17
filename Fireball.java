package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Fireball {
	
	private static Texture fireMoving;
	private static Texture fireDespawn;
	private static Animation<TextureRegion> moving;
	private static Animation<TextureRegion> despawn;
	public Animation<TextureRegion> currentAnimation;
	
	private Vector2 spawnPosition;
	private Vector2 position;
	private Vector2 velocity;
	
	public float stateTime = 0f;
	private float speed=0.3f;
	private float angle=0;
	private int range=25;
	
	public boolean isAlive=true;
	private Rectangle hitbox= new Rectangle();
	
	static {
		fireMoving = new Texture(Gdx.files.internal("fireMoveSheet.png"));
		fireDespawn = new Texture(Gdx.files.internal("fireDieSheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(fireMoving, 220, 124);
		TextureRegion[][] tmp2 = TextureRegion.split(fireDespawn, 120, 172);
		moving = new Animation<TextureRegion>(0.08f, tmp[0]);
		despawn = new Animation<TextureRegion>(0.08f, tmp2[0]);
	}
	
	
	public Fireball(Vector2 spawnPosition,Vector2 velocity) {
		
		this.currentAnimation=moving;
		this.spawnPosition=spawnPosition;
		this.position=spawnPosition.cpy();
		this.velocity=velocity;
		this.velocity.scl(speed);
		this.angle=velocity.angleDeg();
	}

	public void draw(SpriteBatch sb, float delta) {
		
		TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);
		if(isAlive) {
			sb.draw(currentFrame, position.x+0.5f, position.y+0.5f, 0, 0,1.77f, 1, 1, 1, angle+180);
			updatePosition();
		}
		else {
			sb.draw(currentFrame, position.x, position.y, 0, 0, 1,1.43f, 1, 1, angle+180);
		}
		stateTime+=delta;
	}
	
	private void updatePosition() {
		
		if(isAlive && position.dst2(spawnPosition)>range ) {
			currentAnimation=despawn;
			isAlive=false;
			stateTime=0;
			GameScreen.textRenderer.stage.addActor(new FloatingText("25",new Vector3(position.x,position.y,0)));
			return;
		}		
			position.add(velocity);
			//hitbox.setPosition(position);
	}
}

package com.mygdx.game;
import java.nio.charset.Charset;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

enum Direction {
	RIGHT, LEFT, UP, DOWN
}

public class Player {

	Vector2 position = new Vector2(50, 50);
	Direction direction = Direction.UP;
	AnimationStyles as = new AnimationStyles();
	private Animation<TextureRegion> currentAnimation=AnimationStyles.walkForward;
	String id;
	private boolean isAttacking = false;

	public Player() {
		this.id = generateID();
	}

	private String generateID() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		return new String(array, Charset.forName("UTF-8"));
	}

	public void drawPlayer(SpriteBatch sb) {
		
		TextureRegion currentFrame = currentAnimation.getKeyFrame(AnimationStyles.stateTime, true);
		sb.draw(currentFrame, position.x, position.y,2,2);
	}
	
	public void handleInput() {
		
		if(isAttacking) {
			AnimationStyles.stateTime+=Gdx.graphics.getDeltaTime();
			if(currentAnimation.isAnimationFinished(AnimationStyles.stateTime)) {
				 isAttacking=false;
				 AnimationStyles.stateTime=0;
			}
		}
		 if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			 direction=Direction.RIGHT;
			 position.x+=0.25;
			 currentAnimation=AnimationStyles.walkRight;
			 AnimationStyles.stateTime+=Gdx.graphics.getDeltaTime();
		 }
		 else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			 direction=Direction.LEFT;
			 position.x-=0.25;
			 currentAnimation=AnimationStyles.walkLeft;	
			 AnimationStyles.stateTime+=Gdx.graphics.getDeltaTime();
		 }
		 else if (Gdx.input.isKeyPressed(Keys.UP)) {
			 direction=Direction.UP;
			 position.y+=0.25;
			 currentAnimation=AnimationStyles.walkForward; 
			 AnimationStyles.stateTime+=Gdx.graphics.getDeltaTime();
			 }
		 else if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			 direction=Direction.DOWN;
			 position.y-=0.25;
			 currentAnimation=AnimationStyles.walkBack;	
			 AnimationStyles.stateTime+=Gdx.graphics.getDeltaTime();
		 }
		 else if(Gdx.input.isKeyPressed(Keys.Q)) {
			 isAttacking=true;
			 
			 switch(direction) {
				 case RIGHT:
					 currentAnimation=AnimationStyles.attackRight;
					 break;
				 case LEFT:
					 currentAnimation=AnimationStyles.attackLeft;
					 break;
				 case UP:
					 currentAnimation=AnimationStyles.attackForward;
					 break;
				 case DOWN:
					 currentAnimation=AnimationStyles.attackBack;
					 break;
			 }
			 AnimationStyles.stateTime+=Gdx.graphics.getDeltaTime();
		 }
	}
}

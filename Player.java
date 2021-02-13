package com.mygdx.game;
import java.nio.charset.Charset;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;


enum Direction {
	RIGHT, LEFT, UP, DOWN
}

public class Player {

	Vector2 position;
	Vector2 target;
	Direction direction = Direction.UP;
	//Rectangle hitBox = new Rectangle(50,50,1,1);
	AnimationStyles as = new AnimationStyles();
	private Animation<TextureRegion> currentAnimation=AnimationStyles.walkForward;
	String id;
	private boolean isAttacking = false;
	//Label damageLabel = new Label("777", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

	public Player() {
		this.id = generateID();
		//damageLabel.setFontScale(0.08f);
	}

	private String generateID() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		return new String(array, Charset.forName("UTF-8"));
	}

	public void drawPlayer(SpriteBatch sb) {

		TextureRegion currentFrame = currentAnimation.getKeyFrame(AnimationStyles.stateTime, true);
		sb.draw(currentFrame, position.x-0.5f, position.y-0.25f, 2, 2);
	}

	public void handleInput(float delta) {

		updateAttack(delta);
		updateMovement(delta);
		
		/*
		 * if(hitBox.overlaps(Board.e.hitBox)) {
		 * damageLabel.setPosition(Board.e.hitBox.x, Board.e.hitBox.y); }
		 */
	}
	
	private void updateMovement(float delta) {
		
		if(position.dst(target)>0.1f) {
			position.lerp(target, 0.3f);
			AnimationStyles.stateTime+=delta;
			return;
		}
		else if(!isAttacking){	
			AnimationStyles.stateTime=0;
			position.set(target);
		}
		 if (Gdx.input.isKeyPressed(Keys.RIGHT)) {	
			 direction=Direction.RIGHT;
				
				  if(isValid((int) position.x+1,(int)position.y)) {
					  target.x=position.x+1;
				  }
			 currentAnimation=AnimationStyles.walkRight;
		
		 }
		 else if (Gdx.input.isKeyPressed(Keys.LEFT)) {		
			 direction=Direction.LEFT;
			 if(isValid((int) position.x-1,(int)position.y)) {
				  target.x=position.x-1;
			  }
			 currentAnimation=AnimationStyles.walkLeft;
		 }

		 else if (Gdx.input.isKeyPressed(Keys.UP)) {
			 direction=Direction.UP;
			 if(isValid((int) position.x,(int)position.y+1)) {
				  target.y=position.y+1;
			  }
			 currentAnimation=AnimationStyles.walkForward;
		
		 }

		 else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			 direction=Direction.DOWN;
			 if(isValid((int) position.x,(int)position.y-1)) {
				  target.y=position.y-1;
			  }
			 currentAnimation=AnimationStyles.walkBack;
		 }
	}
	
	private void updateAttack(float delta) {
		if (isAttacking) {
			AnimationStyles.stateTime += delta;
			if (currentAnimation.isAnimationFinished(AnimationStyles.stateTime)) {
				isAttacking = false;
				AnimationStyles.stateTime = 0;
			}
		}

		else if (Gdx.input.isKeyPressed(Keys.Q)) {
			isAttacking = true;
			currentAnimation = AnimationStyles.meleeAnimation.get(direction);
			AnimationStyles.stateTime += delta;
		}
	}
	
	private boolean isValid(int x, int y) {
		
		for(TiledMapTileLayer t :Board.mapLayers) {	
			if(t.getCell(x, y)!=null) {
				boolean isBlocked =t.getCell(x, y).getTile().getProperties().get("isBlocked", Boolean.class);
				if(isBlocked) {
					return false;
				}		
			}
		}
		return true;
	}
}

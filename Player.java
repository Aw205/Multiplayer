package com.mygdx.game;
import java.nio.charset.Charset;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

enum Direction {
	RIGHT, LEFT, UP, DOWN;
	 public Direction opposite() {
        switch(this) {
            case RIGHT: return Direction.LEFT;
            case LEFT: return Direction.RIGHT;
            case UP: return Direction.DOWN;
            case DOWN: return Direction.UP;
            default: throw new IllegalStateException("This should never happen: " + this + " has no opposite.");
        }
    }
}

public class Player {

	Vector2 position;
	Vector2 target;
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
		sb.draw(currentFrame, position.x-0.5f, position.y-0.25f, 2, 2);
	}

	public void handleInput(float delta) {
		updateAttack(delta);
		updateMovement(delta);
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
		
		if (Gdx.input.isKeyPressed(Keys.D)) {	
			 direction=Direction.RIGHT;
				  if(isValid((int) position.x+1,(int)position.y)) {
					  target.x=position.x+1;
				  }
			 currentAnimation=AnimationStyles.walkRight;
		
		 }
		 else if (Gdx.input.isKeyPressed(Keys.A)) {		
			 direction=Direction.LEFT;
			 if(isValid((int) position.x-1,(int)position.y)) {
				  target.x=position.x-1;
			  }
			 currentAnimation=AnimationStyles.walkLeft;
		 }
		 else if (Gdx.input.isKeyPressed(Keys.W)) {
			 direction=Direction.UP;
			 if(isValid((int) position.x,(int)position.y+1)) {
				  target.y=position.y+1;
			  }
			 currentAnimation=AnimationStyles.walkForward;
		 }

		 else if (Gdx.input.isKeyPressed(Keys.S)) {
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
		else if (Gdx.input.isKeyPressed(Keys.F)) {
			
			Vector3 mousePosition = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
			GameScreen.cam.unproject(mousePosition);
			Vector2 mouseLoc = new Vector2(mousePosition.x, mousePosition.y);
			Vector2 velocity = mouseLoc.sub(position).nor();
			
			Board.projectiles.add(new Fireball(position,velocity));
			isAttacking = true;
		}
	}
	
	private boolean isValid(int x, int y) {			
		if(Board.nodeArray[x][y].isBlocked) {	
			return false;
		}
		return true;
	}
}

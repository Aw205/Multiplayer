package com.mygdx.game;
import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationStyles {
	
	static Map<Direction,Animation<TextureRegion>> meleeAnimation = new HashMap<Direction,Animation<TextureRegion>>();
	static Map<Direction,Animation<TextureRegion>> logAnimation = new HashMap<Direction,Animation<TextureRegion>>();

	static Animation<TextureRegion> walkBack;
	static Animation<TextureRegion> walkForward;
	static Animation<TextureRegion> walkLeft;
	static Animation<TextureRegion> walkRight;
	
	private Animation<TextureRegion> attackBack;
	private Animation<TextureRegion> attackForward;
	private Animation<TextureRegion> attackLeft;
	private Animation<TextureRegion> attackRight;
	
	static Animation<TextureRegion> logBack;
	static Animation<TextureRegion> logForward;
	static Animation<TextureRegion> logLeft;
	static Animation<TextureRegion> logRight;

	static float stateTime = 0f;

	public AnimationStyles(AssetsManager am) {
		
		Texture walkSheet = am.manager.get("PlayerAnimations/charWalk.png", Texture.class);
		Texture attackSheet = am.manager.get("PlayerAnimations/charAttack.png", Texture.class);
		Texture logSheet = am.manager.get("EnemyAnimations/logWalk.png", Texture.class);

	
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, 32, 32);
		TextureRegion[][] atk = TextureRegion.split(attackSheet, 32, 32);
		TextureRegion[][] log = TextureRegion.split(logSheet, 32, 32);

		walkBack = new Animation<TextureRegion>(0.08f, tmp[0]);
		walkRight = new Animation<TextureRegion>(0.08f, tmp[1]);
		walkForward = new Animation<TextureRegion>(0.08f, tmp[2]);
		walkLeft = new Animation<TextureRegion>(0.08f, tmp[3]);

		attackBack = new Animation<TextureRegion>(0.1f, atk[0]);
		attackForward = new Animation<TextureRegion>(0.1f, atk[1]);
		attackRight = new Animation<TextureRegion>(0.1f, atk[2]);
		attackLeft = new Animation<TextureRegion>(0.1f, atk[3]);
		
		logBack = new Animation<TextureRegion>(0.1f, log[0]);
		logForward = new Animation<TextureRegion>(0.1f, log[1]);
		logRight = new Animation<TextureRegion>(0.1f, log[2]);
		logLeft = new Animation<TextureRegion>(0.1f, log[3]);
		
		meleeAnimation.put(Direction.DOWN, attackBack);
		meleeAnimation.put(Direction.UP, attackForward);
		meleeAnimation.put(Direction.RIGHT, attackRight);
		meleeAnimation.put(Direction.LEFT, attackLeft);
		
		logAnimation.put(Direction.DOWN, logBack);
		logAnimation.put(Direction.UP, logForward);
		logAnimation.put(Direction.RIGHT, logRight);
		logAnimation.put(Direction.LEFT, logLeft);
	}

}

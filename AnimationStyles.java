package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationStyles {

	private Texture walkSheet;
	private Texture attackSheet;

	static Animation<TextureRegion> walkBack;
	static Animation<TextureRegion> walkForward;
	static Animation<TextureRegion> walkLeft;
	static Animation<TextureRegion> walkRight;
	static Animation<TextureRegion> attackBack;
	static Animation<TextureRegion> attackForward;
	static Animation<TextureRegion> attackLeft;
	static Animation<TextureRegion> attackRight;

	static float stateTime = 0f;

	public AnimationStyles() {

		walkSheet = new Texture(Gdx.files.internal("charWalkEven.png"));
		attackSheet = new Texture(Gdx.files.internal("charAttack.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, 32, 32);
		TextureRegion[][] atk = TextureRegion.split(attackSheet, 32, 32);

		walkBack = new Animation<TextureRegion>(0.1f, tmp[0]);
		walkRight = new Animation<TextureRegion>(0.1f, tmp[1]);
		walkForward = new Animation<TextureRegion>(0.1f, tmp[2]);
		walkLeft = new Animation<TextureRegion>(0.1f, tmp[3]);

		attackBack = new Animation<TextureRegion>(0.1f, atk[0]);
		attackForward = new Animation<TextureRegion>(0.1f, atk[1]);
		attackRight = new Animation<TextureRegion>(0.1f, atk[2]);
		attackLeft = new Animation<TextureRegion>(0.1f, atk[3]);
	}

}

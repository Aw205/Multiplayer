package com.mygdx.game.component;
import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.AssetsManager;

public class AnimationComponent extends Component {

	public float stateTime = 0f;

	Texture walkSheet;
	TextureRegion[][] tmp;
	public Animation walkBack;
	public Animation walkRight;
	public Animation walkForward;
	public Animation walkLeft;

	public Animation<TextureRegion> currentAnimation;
	public Map<String,Animation<TextureRegion>> animations = new HashMap<String,Animation<TextureRegion>>();

	public AnimationComponent(AssetsManager am) {

		walkSheet = am.manager.get("PlayerAnimations/charWalk.png", Texture.class);
		tmp = TextureRegion.split(walkSheet, 32, 32);
		walkBack = new Animation<TextureRegion>(0.08f, tmp[0]);
		walkRight = new Animation<TextureRegion>(0.08f, tmp[1]);
		walkForward = new Animation<TextureRegion>(0.08f, tmp[2]);
		walkLeft = new Animation<TextureRegion>(0.08f, tmp[3]);
		
		currentAnimation = walkBack;

	}
}

package com.mygdx.game.component;

import com.badlogic.gdx.math.Rectangle;

public class HitboxComponent extends Component {
	
	public Rectangle hitbox= new Rectangle();

	
	public HitboxComponent() {
		
		hitbox.width=1f;
		hitbox.height=1f;	
	}
	
	

}

package com.mygdx.game.component;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Directions.Direction;

public class VelocityComponent extends Component{
	
	public static Map<Direction,Vector2> directions = new HashMap<Direction,Vector2>();
	
	static {		
		directions.put(Direction.DOWN, new Vector2(0,-1));
		directions.put(Direction.UP, new Vector2(0,1));
		directions.put(Direction.RIGHT, new Vector2(1,0));
		directions.put(Direction.LEFT, new Vector2(-1,0));		
	}
	
	public int speed=5;

}

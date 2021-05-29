package com.mygdx.game.component;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Directions.Direction;


public class PositionComponent extends Component{
	
	
	public Vector2 position= new Vector2(0,0);
	public Vector2 spawnPosition= new Vector2(0,0);
	public Vector2 target= new Vector2(0,0);
	public Vector2 velocity= new Vector2(0.3f,0);
	public Direction direction = Direction.UP;

}

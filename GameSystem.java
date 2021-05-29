package com.mygdx.game.system;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.entity.Entity;

public abstract class GameSystem {
	
	public List<Entity> entities = new ArrayList<Entity>();
	
	public abstract void update();
	public abstract boolean addEntity(Entity e);
	protected abstract void removeEntity(Entity e);

}

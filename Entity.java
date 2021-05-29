package com.mygdx.game.entity;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mygdx.game.component.Component;

public abstract class Entity {
	
	public String ID;
	protected List<Component> components = new ArrayList<Component>();
	
	
	public Entity() {
		
		this.ID=generateID();
		
	}
	
	public <T extends Component> T getComponent(Class<T> type) {
		for (Component c : components) {
			if (c.getClass() == type) {
				return (T) c;
			}
		}
		return null;
	}
	
	public void addComponent(Component c) {
		
		this.components.add(c);
	}

	private String generateID() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		return new String(array, Charset.forName("UTF-8"));
	}

}

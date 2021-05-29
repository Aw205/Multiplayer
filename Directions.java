package com.mygdx.game;

public class Directions {
	
	public enum Direction {
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
}

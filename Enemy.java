package com.mygdx.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import javax.swing.text.Position;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Board.Node;

public class Enemy {
		
	private Animation<TextureRegion> currentAnimation=AnimationStyles.logBack;
	private float stateTime=0f;
	private List<Vector2> path= new ArrayList<Vector2>();
	private List<Direction> pathDirections = new ArrayList<Direction>();
	public Vector2 start;
	public Vector2 end;
	private int target=0;
	private int direction=1;
	private float currentTime=0f;
	
	public Rectangle hitbox;
	public int health=100;

	public Enemy() {
		
		hitbox = new Rectangle();
		hitbox.setSize(1,1);
	}
	
	public void draw(SpriteBatch sb) {
		
		TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);
		sb.draw(currentFrame, start.x, start.y, 2, 2);
	}
	
	public void updateMovement(float delta) {
		
		currentTime+=delta;
		
		if(start.dst(path.get(target))>0.1f) {
			start.lerp(path.get(target), currentTime/2);
			stateTime+=delta;
		}
		else if((target+direction)>-1 && (target+direction)<path.size()){
			currentTime=0;
			start.set(path.get(target));
			stateTime=0;
			target+=direction;	
			Direction d = (direction==1)? pathDirections.get(target-direction) : pathDirections.get(target-direction).opposite();
			currentAnimation=AnimationStyles.logAnimation.get(d);
		}
		else {
			currentTime=0;
			start.set(path.get(target));
			direction=-direction;
			target+=direction;
		}
		hitbox.setPosition(start.x+0.5f, start.y+0.5f);
	}
	
	private int calcHeuristic(Node n) {
		int dx = (int) Math.abs(n.position.x-end.x);
		int dy = (int) Math.abs(n.position.y-end.y);			    
		return (dx + dy);
	}
	
	public void calculatePath() {
		
		Node current = Board.nodeArray[(int) start.x][(int) start.y];
		Node goal= Board.nodeArray[(int) end.x][(int) end.y];
		
		Queue<Node >open = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {		
				if(n1.rank==n2.rank) return 0;		
				return (n1.rank>n2.rank) ? 1:-1;
			}
		});
		
		Set<Node> closed = new HashSet<Node>();
		open.add(current);
		
		while(!open.element().position.epsilonEquals(goal.position)) {
			current = open.poll();
			closed.add(current);
			for(Node n : getValidAdjacents((int)current.position.x,(int)current.position.y)) {
				int cost = current.g+1;
				if(open.contains(n) && cost<n.g) {
					open.remove(n);
				}
				else if(closed.contains(n) && cost<n.g) {
					closed.remove(n);
				}
				else if(!open.contains(n)&& !closed.contains(n)) {
					n.g=cost;
					n.rank=calcHeuristic(n)+n.g;
					open.add(n);
					n.parent=current;
				}
			}
		}
		current=open.element();
		while(current!=null) {
			
			path.add(current.position);
			if (current.parent != null) {
				Vector2 diff = current.parent.position.cpy().sub(current.position);
				if (diff.x == 1) {
					pathDirections.add(Direction.LEFT);				
				} else if (diff.y == 1) {
					pathDirections.add(Direction.DOWN);				
				} else if (diff.x == -1) {
					pathDirections.add(Direction.RIGHT);				
				} else if (diff.y == -1) {
					pathDirections.add(Direction.UP);
				}
			  }			 
			current=current.parent;
		}		
		Collections.reverse(path);
		Collections.reverse(pathDirections);
	}
	
	private List<Node> getValidAdjacents(int x, int y) {
		int[][] dp = {{0,1},{0,-1},{1,0},{-1,0}};
		List<Node> adjacents = new ArrayList<Node>();
		
		for(int[] d :dp) {
			if (x+d[0]<100 && x+d[0]>-1 && y+d[1]<100 && y+d[1]>-1 && !Board.nodeArray[x + d[0]][y+d[1]].isBlocked) {
				adjacents.add(Board.nodeArray[x + d[0]][y+d[1]]);
			}
		}
		return adjacents;
	}
}

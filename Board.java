package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Board {
	
	static class Node {
		boolean isBlocked = false;
		Vector2 position;
		Node parent;
		int g = 0;
		int rank = -1;

		public Node(Vector2 position) {
			this.position = position;
		}
	}
	
	private AssetsManager am;

	static Map<String, Player> players = new HashMap<String, Player>();
	static List<Arrow> projectiles = new ArrayList<Arrow>();
	static List<Enemy> enemies = new ArrayList<Enemy>();
	static Player p;
	private TiledMapRenderer renderer;
	private TiledMap map;
	private MapLayer objectLayer;
	static TiledMapTileLayer[] mapLayers = new TiledMapTileLayer[2];
	static Node[][] nodeArray=new Node[100][100];
	
	public Board(AssetsManager am) {
		this.am=am;
		 map=am.manager.get("ForestMap/forestMap.tmx", TiledMap.class);
		
		mapLayers[0]=(TiledMapTileLayer) map.getLayers().get(0);
		mapLayers[1]=(TiledMapTileLayer) map.getLayers().get(1);
		objectLayer = map.getLayers().get("Object Layer 1");
		
		initPlayer();
		
		renderer = new OrthogonalTiledMapRenderer(map, 1/16f);
		renderer.setView(GameScreen.cam);
		createNodes();
		initEnemy();
	}
	

	public void drawBoard() {

		Multiplayer.sb.setProjectionMatrix(GameScreen.cam.combined);
		renderer.setView(GameScreen.cam);
		renderer.render();

		Multiplayer.sb.begin();

		drawEnemies();
		for (Player p : players.values()) {
			p.drawPlayer(Multiplayer.sb);
		}
		drawProjectiles(Gdx.graphics.getDeltaTime());
		
		Multiplayer.sb.end();
	}
	
	private void drawProjectiles(float deltaTime) {
		//projectiles.removeIf(e-> (!e.isAlive && e.currentAnimation.isAnimationFinished(e.stateTime)));
		projectiles.removeIf(e-> (!e.isAlive));
		for(Arrow fb: projectiles) {
			fb.draw(Multiplayer.sb, deltaTime);
		}
	}
	
	private void drawEnemies() {	
		for(Enemy e: enemies) {
			e.updateMovement(Gdx.graphics.getDeltaTime());
			e.draw(Multiplayer.sb);
		}
	}
	
	private void initPlayer() {
		p = new Player(am);
		players.put(p.id, p);
		for (MapObject t : objectLayer.getObjects()) {
			RectangleMapObject rect = (RectangleMapObject) t;
			if (t.getName().equals("Player Spawn Point")) {
				p.position = new Vector2(rect.getRectangle().x / 16, rect.getRectangle().y / 16);
				p.target = new Vector2(p.position.x,p.position.y);
				return;
			}
		}
	}
	
	private void initEnemy() {
		
		for (MapObject t : objectLayer.getObjects()) {
			RectangleMapObject rect = (RectangleMapObject) t;
			if (t.getName().equals("Enemy Spawn Point")) {
				Enemy e = new Enemy();
				enemies.add(e);
				e.start = new Vector2(rect.getRectangle().x / 16, rect.getRectangle().y / 16);
				e.end = new Vector2(e.start.x,e.start.y+4);
				e.calculatePath();
			}
		}	
	
	}
	
	private void createNodes() {
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				nodeArray[i][j]= new Node(new Vector2(i,j));
				for(TiledMapTileLayer t :Board.mapLayers) {	
					if(t.getCell(i, j) != null) {
						boolean isBlocked =t.getCell(i,j).getTile().getProperties().get("isBlocked", Boolean.class);
						if(isBlocked) {
							nodeArray[i][j].isBlocked=true;	
							break;
						}	
					}					
				}
			}
		}
	}
}

package com.mygdx.game;


import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.component.AIComponent;
import com.mygdx.game.component.PositionComponent;
import com.mygdx.game.entity.Enemy;
import com.mygdx.game.entity.Player;
import com.mygdx.game.system.SystemsManager;

public class Board {
	
	public static class Node {
		public boolean isBlocked = false;
		public Vector2 position;
		public Node parent;
		public int g = 0;
		public int rank = -1;

		public Node(Vector2 position) {
			this.position = position;
		}
	}
	
	private AssetsManager am;
	
	public static Player p;
	private TiledMapRenderer renderer;
	private TiledMap map;
	private MapLayer objectLayer;
	static TiledMapTileLayer[] mapLayers = new TiledMapTileLayer[2];
	public static Node[][] nodeArray=new Node[100][100];
	
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
	}
	
	private void initPlayer() {
		p = new Player(am);
		SystemsManager.notifySystems(p);
		for (MapObject t : objectLayer.getObjects()) {
			RectangleMapObject rect = (RectangleMapObject) t;
			if (t.getName().equals("Player Spawn Point")) {
				PositionComponent pos = (PositionComponent) p.getComponent(PositionComponent.class);
				pos.position = new Vector2(rect.getRectangle().x / 16, rect.getRectangle().y / 16);
				pos.target = new Vector2(pos.position.x,pos.position.y);
				return;
			}
		}
	}
	
	private void initEnemy() {
		
		for (MapObject t : objectLayer.getObjects()) {
			RectangleMapObject rect = (RectangleMapObject) t;
			if (t.getName().equals("Enemy Spawn Point")) {
				Enemy e= WeaponLoader.enemies.get("log");
				AIComponent ai = e.getComponent(AIComponent.class);
				ai.start = new Vector2(rect.getRectangle().x / 16, rect.getRectangle().y / 16);
				ai.end = new Vector2(ai.start.x,ai.start.y+4);
				ai.calculatePath();
				SystemsManager.notifySystems(e);
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

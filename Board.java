package com.mygdx.game;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Board {

	static Map<String, Player> players = new HashMap<String, Player>();
	static Player p;
	private TiledMapRenderer renderer;
	private TiledMap map;
	private MapLayer objectLayer;
	static TiledMapTileLayer[] mapLayers = new TiledMapTileLayer[2];
	 Enemy e;
	
	public Board() {
		
		map = new TmxMapLoader().load("ForestMap/forestMap.tmx");
		mapLayers[0]=(TiledMapTileLayer) map.getLayers().get(0);
		mapLayers[1]=(TiledMapTileLayer) map.getLayers().get(1);
		objectLayer = map.getLayers().get("Object Layer 1");
		
		initPlayer();
		
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
		renderer.setView(GameScreen.cam);
		
		e= new Enemy();
	}

	public void drawBoard() {

		Multiplayer.sb.setProjectionMatrix(GameScreen.cam.combined);
		renderer.setView(GameScreen.cam);
		renderer.render();

		Multiplayer.sb.begin();
		
		e.drawEnemy(Multiplayer.sb,Gdx.graphics.getDeltaTime());
		//p.damageLabel.draw(Multiplayer.sb, 0.9f);
		for (Player p : players.values()) {
			p.drawPlayer(Multiplayer.sb);
		}
		Multiplayer.sb.end();
	}
	
	private void initPlayer() {
		p = new Player();
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
}

package com.mygdx.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Board {

	static Map<String, Player> players = new HashMap<String, Player>();
	static Player p;
	private TiledMapRenderer renderer;
	private TiledMap map;
	static SpriteBatch sb = new SpriteBatch();

	public Board() {
		p=new Player();
		players.put(p.id, p);
		map = new TmxMapLoader().load("ForestMap/forestMap.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
		renderer.setView(Multiplayer.cam);
	}

	public void drawBoard() {

		sb.setProjectionMatrix(Multiplayer.cam.combined);
		renderer.setView(Multiplayer.cam);
		renderer.render();

		sb.begin();
		for (Player p : players.values()) {
			p.drawPlayer(sb);
		}
		sb.end();
	}
}

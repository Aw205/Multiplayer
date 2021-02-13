package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;

public class GameScreen implements Screen {

	static OrthographicCamera cam = new OrthographicCamera();
	final Multiplayer game;
	Board board;
	Hud hud;
	private final float VIEWPORT_WIDTH = 20;
	private final float VIEWPORT_HEIGHT = 20;

	public GameScreen(Multiplayer game) {
		this.game = game;
		// Client c = new Client();
		// c.start();
		float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
		cam.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT * aspectRatio);
		cam.update();
		board = new Board();
		hud = new Hud(game.sb);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {

		Board.p.handleInput(delta);
		
		cam.position.set(Board.p.position.x, Board.p.position.y, 0);
		cam.update();
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		board.drawBoard();
		
		/*
		 * game.sb.flush();
		 * Multiplayer.sr.begin(); Multiplayer.sr.setProjectionMatrix(cam.combined);
		 * Board.p.hitBox.setPosition(Board.p.position);
		 * Multiplayer.sr.rect(Board.p.hitBox.getX(), Board.p.hitBox.getY(),
		 * Board.p.hitBox.getWidth(), Board.p.hitBox.getHeight());
		 * Multiplayer.sr.rect(Board.e.hitBox.getX(), Board.e.hitBox.getY(),
		 * Board.e.hitBox.getWidth(), Board.e.hitBox.getHeight());
		 * Multiplayer.sr.end(); Multiplayer.sr.flush();
		 */
		
		game.sb.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		game.sb.dispose();
		hud.stage.dispose();
	}

}

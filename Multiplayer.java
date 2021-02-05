package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Multiplayer extends ApplicationAdapter {

	static OrthographicCamera cam = new OrthographicCamera();
	Board board;
	Hud hud;
	private final float VIEWPORT_WIDTH = 20;
	private final float VIEWPORT_HEIGHT = 20;

	@Override
	public void create() {

		float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
		cam.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT * aspectRatio);
		cam.update();
		board = new Board();
		hud = new Hud(Board.sb);
	}

	@Override
	public void render() {

		Board.p.handleInput();
		cam.position.set(Board.p.position.x, Board.p.position.y, 0);
		cam.update();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		board.drawBoard();

		Board.sb.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	@Override
	public void dispose() {
		Board.sb.dispose();
		hud.stage.dispose();
	}
}

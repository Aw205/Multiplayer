package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen {

	static OrthographicCamera cam = new OrthographicCamera();
	static InputMultiplexer multiplexer = new InputMultiplexer();
	final Multiplayer game;
	Board board;
	HUD hud;
	static TextRenderer textRenderer = new TextRenderer();
	private final float VIEWPORT_WIDTH = 20;
	private final float VIEWPORT_HEIGHT = 20;
	private AssetsManager am;

	public GameScreen(Multiplayer game,AssetsManager am) {
		this.game = game;
		this.am=am;
		// Client c = new Client();
		// c.start();
		float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
		cam.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT * aspectRatio);
		cam.update();
		board = new Board(am);
		hud = new HUD(game.sb,am);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {

		Board.p.handleInput(delta);
		updateCameraPosition();
		
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		board.drawBoard();
		
		//game.sb.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		textRenderer.stage.draw();
				
		
		  Multiplayer.sr.setProjectionMatrix(GameScreen.cam.combined);
		  Multiplayer.sr.begin(ShapeType.Filled); for(Arrow e: Board.projectiles) {
		  Multiplayer.sr.rect(e.hitbox.x, e.hitbox.y, e.hitbox.width, e.hitbox.height); }
		 
		  Multiplayer.sr.end();
		 
	}
	
	
	private void updateCameraPosition() {
		
		Vector3 playerPosition = new Vector3(Board.p.position.x,Board.p.position.y,0);
		
		if(cam.position.dst(playerPosition)>0.1f) {
			cam.position.lerp(playerPosition, 0.1f);
		}
		else {
			cam.position.set(playerPosition);
		}
		cam.position.x = MathUtils.clamp(cam.position.x, VIEWPORT_WIDTH / 2f, 100 - VIEWPORT_WIDTH / 2f);
		cam.position.y = MathUtils.clamp(cam.position.y, VIEWPORT_HEIGHT / 2f, 100 - VIEWPORT_HEIGHT / 2f);
		cam.update();
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

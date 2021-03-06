package com.mygdx.game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Hud{

	public Stage stage;
	private FitViewport stageViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
	private Table table = new Table();
	
	static Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	private ProgressBar healthBar = new ProgressBar(0, 10, 1, false, skin);
	private Image lvlDisplay = new Image(new Texture(Gdx.files.internal("exp_lvl_HUD.png")));
	
	Inventory in;
	
	Label t = new Label("hello",skin);
	
	
	public Hud(SpriteBatch sb) {
		
		stage = new Stage(stageViewport, sb);
		stage.addActor(table);
		addListener();
		setUpTable();
		lvlDisplay.setScale(2, 2);
		GameScreen.multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(GameScreen.multiplexer);
	}

	private void setUpTable() {
		table.setFillParent(true);
		//table.add(lvlDisplay).expand().top().left().padTop(50);
		createInventory();
	//	table.row();
		//table.add(healthBar);
	}
	
	
	private void addListener() {
			stage.addListener(new InputListener() {	
			@Override
			public boolean keyDown(InputEvent event, int keycode){
				if(keycode==Input.Keys.I) {
					in.setVisible(!in.isVisible());
					return true;
				}
				return false;
			}
		});	
	}
	
	private void createInventory() {
		
		in = new Inventory();
		//in.setDebug(true);
		in.setVisible(true);	
		in.setPosition(Gdx.graphics.getWidth()/2 - in.getWidth()/2, Gdx.graphics.getHeight()/2 - in.getHeight()/2);
		//table.setDebug(true);
		table.addActor(in);
	}
}

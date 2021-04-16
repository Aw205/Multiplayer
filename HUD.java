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
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HUD{

	public Stage stage;
	private FitViewport stageViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	private Table table = new Table();
	private AssetsManager am;
	Inventory in;
	HeaderFrame header;
	
	
	public HUD(SpriteBatch sb,AssetsManager am) {
		this.am=am;
		
		stage = new Stage(stageViewport, sb);
		stage.addActor(table);
		addListener();
		setUpTable();
		GameScreen.multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(GameScreen.multiplexer);
	}

	private void setUpTable() {
		table.setFillParent(true);
		createInventory();
		//createLoadout();
		createHealthUI();
	}
	
	
	private void addListener() {
			stage.addListener(new InputListener() {	
			@Override
			public boolean keyDown(InputEvent event, int keycode){
				if(keycode==Input.Keys.I) {
					in.setVisible(!in.isVisible());
					header.setVisible(!header.isVisible());
					return true;
				}
				return false;
			}
		});	
	}
	
	private void createInventory() {
		header = new HeaderFrame();
		in = new Inventory(am);
		table.add(header);
		table.row();
		table.add(in);
		table.row().expandX();
	}
	
	private void createLoadout() {
		Loadout loadout = new Loadout();
		loadout.setVisible(true);
		table.add(loadout).left();
	}
	
	private void createHealthUI() {
		Health_UI h = new Health_UI();
		table.bottom();
		table.add(h);
	}
}

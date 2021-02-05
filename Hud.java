package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Hud {

	public Stage stage;
	private FitViewport stageViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
	private Table table = new Table();
	
	private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	private ProgressBar healthBar = new ProgressBar(0, 10, 1, false, skin);
	private Image lvlDisplay = new Image(new Texture(Gdx.files.internal("exp_lvl_HUD.png")));

	public Hud(SpriteBatch sb) {

		stage = new Stage(stageViewport, sb);
		stage.addActor(table);
		setUpTable();
		lvlDisplay.setScale(2, 2);
	}

	private void setUpTable() {
		table.setFillParent(true);
		table.setDebug(true);
		table.add(lvlDisplay).expand().top().left().padTop(50);
		table.row();
		table.add(healthBar);
	}
}

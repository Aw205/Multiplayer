package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetsManager {
	
	public AssetManager manager = new AssetManager();
	
	public AssetsManager() {
	
		loadTextures();
		loadMap();
		loadFonts();
	}
	
	private void loadFonts() {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		
		FreeTypeFontLoaderParameter upheavtt = new FreeTypeFontLoaderParameter();
		upheavtt.fontFileName="upheavtt.ttf";
		upheavtt.fontParameters.size=22;
		manager.load("upheavtt.ttf", BitmapFont.class, upheavtt);
		
	}
	
	
	private void loadMap() {
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		manager.load("ForestMap/forestMap.tmx", TiledMap.class);
	}
	
	
	private void loadTextures() {
			
		manager.load("EnemyAnimations/logWalk.png", Texture.class);
		manager.load("PlayerAnimations/charAttack.png", Texture.class);
		manager.load("PlayerAnimations/charWalk.png", Texture.class);
		
		manager.load("big_Frame_left.png", Texture.class);
		manager.load("big_Frame_mid.png", Texture.class);
		manager.load("big_Frame_right.png", Texture.class);
		
		manager.load("emptyHealth.png", Texture.class);
		manager.load("redMid.png", Texture.class);
		manager.load("healthPortrait.png", Texture.class);
		
		manager.load("fireDieSheet.png", Texture.class);
		manager.load("fireMoveSheet.png", Texture.class);
		
		manager.load("leftWeaponSlot.png", Texture.class);
		manager.load("midWeaponSlot.png", Texture.class);
		manager.load("rightWeaponSlot.png", Texture.class);
		
		manager.load("slotTopLeft.png", Texture.class);
		manager.load("windowFrame.png", Texture.class);
		
		manager.load("sampleSword.png", Texture.class);
		manager.load("Weapons/LightBow_1.png", Texture.class);
		manager.load("Weapons/LightBow_2.png", Texture.class);
		manager.load("Weapons/Light_Arrow_2.png", Texture.class);
	}

}

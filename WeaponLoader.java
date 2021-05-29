package com.mygdx.game;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.component.AnimationComponent;
import com.mygdx.game.component.StatsComponent;
import com.mygdx.game.entity.Enemy;
import com.mygdx.game.entity.Projectile;
import com.mygdx.game.entity.Weapon;

public class WeaponLoader {
	
	public static Map<String,Weapon> weapons = new HashMap<String,Weapon>();
	public static Map<Weapon,Projectile> projectiles = new HashMap<Weapon,Projectile>();
	public static Map<String,Enemy> enemies = new HashMap<String,Enemy>();
	
	public WeaponLoader() {

	}
	
	public static void load(AssetsManager am) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader("Weapons.json"));
			JSONArray array = (JSONArray) obj.get("weapons");

			for (int i = 0; i < array.size(); i++) {

				JSONObject item = (JSONObject) array.get(i);

				Weapon w = new Weapon(am);

				AnimationComponent ac = (AnimationComponent) w.getComponent(AnimationComponent.class);
				StatsComponent sc = (StatsComponent) w.getComponent(StatsComponent.class);

				int frameWidth = ((Long) item.get("frame_width")).intValue();
				int frameHeight = ((Long) item.get("frame_height")).intValue();
				String imagePath = (String) item.get("image_path");

				createAnimations(am, ac, imagePath, frameWidth, frameHeight,null);

				sc.damage = ((Long) item.get("damage")).intValue();
				sc.range = ((Long) item.get("range")).intValue();

				weapons.put((String) item.get("name"), w);
			}
			
			JSONArray projArray = (JSONArray) obj.get("projectiles");
			
			for (int i = 0; i < projArray.size(); i++) {

				JSONObject item = (JSONObject) projArray.get(i);

				Projectile p = new Projectile(am);

				AnimationComponent ac = (AnimationComponent) p.getComponent(AnimationComponent.class);
				int frameWidth = ((Long) item.get("frame_width")).intValue();
				int frameHeight = ((Long) item.get("frame_height")).intValue();
				String imagePath = (String) item.get("image_path");
				
				createAnimations(am, ac, imagePath, frameWidth, frameHeight,null);
				
				String parent = (String) item.get("parent");

				projectiles.put(weapons.get(parent), p);
				
				
				
				loadEnemies(am,obj);
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadEnemies(AssetsManager am, JSONObject obj) {
		
		JSONArray enemyArray = (JSONArray) obj.get("enemies");
		
		for (int i = 0; i < enemyArray.size(); i++) {

			JSONObject item = (JSONObject) enemyArray.get(i);
			Enemy e = new Enemy(am);

			AnimationComponent ac = (AnimationComponent) e.getComponent(AnimationComponent.class);
			int frameWidth = ((Long) item.get("frame_width")).intValue();
			int frameHeight = ((Long) item.get("frame_height")).intValue();
			String imagePath = (String) item.get("image_path");
			String name = (String) item.get("name");
			
			JSONArray animations = (JSONArray) item.get("animations");

			createAnimations(am, ac, imagePath, frameWidth, frameHeight,animations);
			enemies.put(name, e);
		}
		
	}
	
	private static void createAnimations(AssetsManager am, AnimationComponent ac, String imagePath, int w, int h,JSONArray anim) {
		
		Texture spriteSheet = am.manager.get(imagePath, Texture.class);
		TextureRegion[][] frames = TextureRegion.split(spriteSheet, w, h);
		
		if (anim != null) {
			for (int i = 0; i < anim.size(); i++) {
				JSONObject animation = (JSONObject) anim.get(i);
				int index = ((Long) animation.get("index")).intValue();
				ac.animations.put((String) animation.get("name"), new Animation<TextureRegion>(0.1f, frames[index]));
			}
		}
		ac.currentAnimation = new Animation<TextureRegion>(0.1f, frames[0]);
	}
	
}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * Created by Funki on 10.03.2017.
 */
public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Background background;
	private Hero hero;
	private final int ASTEROIDS_COUNT = 50;
	private Asteroid[] asteroids;
	private final int BULLETS_COUNT = 200;
	public static Bullet[] bullets;
	private Texture textureBullet;
	private BitmapFont fnt;

	public void create() {
		batch = new SpriteBatch();
		background = new Background();
		hero = new Hero();
		asteroids = new Asteroid[ASTEROIDS_COUNT];
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i] = new Asteroid();
		}
		bullets = new Bullet[BULLETS_COUNT];
		for (int i = 0; i < bullets.length; i++) {
			bullets[i] = new Bullet();
		}
		textureBullet = new Texture("bullet32.png");
		fnt = new BitmapFont();
	}

	public void render() {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		if (hero.isAlive()) {
			hero.render(batch);
			fnt.draw(batch, "SCORE: " + hero.getScore(), 50, 680);
			fnt.draw(batch, "HP: " + hero.getHp() + " / " + hero.getMaxHP(), 50, 660);
		} else {
			fnt.draw(batch, "PRESS SPACE TO RESTART", 500, 680);
		}
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i].render(batch);
		}
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i].isActive()) {
				batch.draw(textureBullet, bullets[i].getPosition().x - 16, bullets[i].getPosition().y - 16);
			}
		}
		batch.end();
	}

	public void startNewGame() {
		hero = new Hero();
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i] = new Asteroid();
		}
		for (int i = 0; i < bullets.length; i++) {
			bullets[i] = new Bullet();
		}
	}

	public void update() {
		background.update();
		if (hero.isAlive())
			hero.update();
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i].update();
		}
		for (int i = 0; i < asteroids.length; i++) {
			if (hero.isAlive() && hero.getRect().overlaps(asteroids[i].getRect())) {
				asteroids[i].takeDamage(1);
				hero.takeDamage(1);
			}
		}
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i].isActive()) {
				bullets[i].update();
				for (int j = 0; j < asteroids.length; j++) {
					if (asteroids[j].getRect().contains(bullets[i].getPosition())) {
						bullets[i].destroy();
						if (asteroids[j].takeDamage(1)) {
							bullets[i].getOwner().addScore(asteroids[j].getMaxHp() * 100);
						}
					}
				}
			}
		}
		if (!hero.isAlive() && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			startNewGame();
		}
	}

	public void dispose() {
		batch.dispose();
	}
}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Funki on 10.03.2017.
 */
public class Hero {
    private Vector2 position;
    private float speed;
    private Texture texture;
    private int fireRate;
    private int fireCounter;
    private Rectangle rect;
    private int score;
    private int maxHP;
    private int hp;
    private boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public int getScore() {
        return score;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHp() {
        return hp;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Hero() {
        texture = new Texture("ship80x60.tga");
        speed = 10.0f;
        position = new Vector2(100, 330);
        fireCounter = 0;
        fireRate = 6;
        rect = new Rectangle(position.x, position.y, 80, 60);
        score = 0;
        maxHP = 50;
        hp = maxHP;
        alive = true;
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            alive = false;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed;
            if (position.y > 720) position.y = -60;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed;
            if (position.y < -60) position.y = 720;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed;
            if (position.x < 0) position.x = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed;
            if (position.x > 1200) position.x = 1200;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            fireCounter++;
            if (fireCounter >= fireRate) {
                fireCounter = 0;
                fire();
            }
        }
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() < position.x + 40) position.x -= speed;
            if (Gdx.input.getX() > position.x + 40) position.x += speed;
            if (Gdx.graphics.getHeight() - Gdx.input.getY() < position.y + 30) position.y -= speed;
            if (Gdx.graphics.getHeight() - Gdx.input.getY() > position.y + 30) position.y += speed;
        }
        rect.x = position.x;
        rect.y = position.y;
    }

    public void fire() {
        for (int i = 0; i < MyGdxGame.bullets.length; i++) {
            if (!MyGdxGame.bullets[i].isActive()) {
                MyGdxGame.bullets[i].setup(this, position.x + 40, position.y + 20);
                break;
            }
        }
    }
}

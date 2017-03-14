package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Funki on 10.03.2017.
 */
public class Asteroid {
    private Vector2 position;
    private float speed;
    private static Texture texture;
    private Rectangle rect;
    private float angle;
    private int hp;
    private int maxHp;

    public Rectangle getRect() {
        return rect;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public Asteroid() {
        position = new Vector2(1280 + (float) Math.random() * 1280, (float) Math.random() * 720);
        speed = 4.0f + (float) Math.random() * 4.0f;
        rect = new Rectangle(position.x, position.y, 64, 64);
        angle = (float) Math.random() * 360;
        maxHp = 2 + (int) (Math.random() * 8);
        hp = maxHp;
        if (texture == null) {
            texture = new Texture("asteroid64.png");
        }
    }

    public void render(SpriteBatch batch) {
        float sc = 0.5f + hp * 0.1f;
        batch.draw(texture, position.x, position.y, 32, 32, 64, 64, sc, sc, angle, 0, 0, 64, 64, false, false);
    }

    public boolean takeDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            recreate();
            return true;
        }
        return false;
    }

    public void recreate() {
        position.x = 1280 + (float) Math.random() * 1280;
        position.y = (float) Math.random() * 720;
        speed = 4.0f + (float) Math.random() * 4.0f;
        angle = (float) Math.random() * 360;
        maxHp = 2 + (int) (Math.random() * 8);
        hp = maxHp;
    }

    public void update() {
        position.x -= speed;
        angle += speed / 2;
        if (position.x < -64) {
            recreate();
        }
        rect.x = position.x;
        rect.y = position.y;
    }
}

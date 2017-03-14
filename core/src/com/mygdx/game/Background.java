package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Funki on 10.03.2017.
 */
public class Background {
    class Star {
        private Vector2 position;
        private float speed;

        public Star() {
            position = new Vector2((float) Math.random() * 1280, (float) Math.random() * 720);
            speed = 2.0f + (float)Math.random() * 6.0f;
        }

        public void update() {
            position.x -= speed;
            if (position.x < -20) {
                position.x = 1280;
                position.y = (float) Math.random() * 720;
                speed = 2.0f + (float)Math.random() * 6.0f;
            }
        }
    }

    private Texture texture;
    private Texture textureStar;
    private final int STARS_COUNT = 300;
    private Star[] stars;

    public Background() {
        texture = new Texture("bg.png");
        textureStar = new Texture("star12.tga");
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
        for (int i = 0; i < stars.length; i++) {
            batch.draw(textureStar, stars[i].position.x, stars[i].position.y);
        }
    }

    public void update() {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update();
        }
    }
}

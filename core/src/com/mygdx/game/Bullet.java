package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Funki on 10.03.2017.
 */
public class Bullet {
    private Vector2 position;
    private float speed;
    private boolean active;
    private Hero owner;

    public Hero getOwner() {
        return owner;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isActive() {
        return active;
    }

    public Bullet() {
        position = new Vector2(0, 0);
        speed = 16.0f;
        active = false;
    }

    public void destroy() {
        active = false;
    }

    public void setup(Hero owner, float x, float y) {
        this.owner = owner;
        position.x = x;
        position.y = y;
        active = true;
    }

    public void update() {
        position.x += speed;
        if (position.x > 1280) {
            destroy();
        }
    }
}

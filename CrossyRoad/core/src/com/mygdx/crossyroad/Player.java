package com.mygdx.crossyroad;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
    private int w, h;
    private int x, y;
    private char plane;
    private Color col;
    private Texture texture;

    public Player(int x, int y, int w, int h, Texture texture) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.texture = texture;
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(col);
        sr.rect(x, y, w, h);
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
            this.x = x;
        }

    public int getY() {
        return y;
    }

    public void setY(int y) {
            this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public char getPlane() {
        return plane;
    }

    public void setPlane(char plane) {
        this.plane = plane;
    }




}

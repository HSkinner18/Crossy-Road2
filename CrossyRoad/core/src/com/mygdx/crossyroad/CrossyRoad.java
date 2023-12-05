package com.mygdx.crossyroad;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Random;

public class CrossyRoad extends ApplicationAdapter {
	ShapeRenderer sr;
	int upperbound = 700;
	int upperbound2 = 700;
	int blue = 255;
	int red = 255;
	int green = 255;
	Random rand = new Random();
	Color col;

	Player player;
	Cars[] cars = new Cars[100];
	Texture Chicken;
	Texture Car;

	@Override
	public void create() {


		Chicken = new Texture("Chicken.png.png");
		Car = new Texture("Car.png.png");


		sr = new ShapeRenderer();
		player = new Player(Gdx.graphics.getWidth() / 2, 10, 10, 10, Chicken);

		for(int i = 0; i < cars.length; i++){
			int y = rand.nextInt(upperbound);
			int x = rand.nextInt(upperbound2);

			if(y < 100){
				y = y + 100;
			}
			int w = 20;
			int h = 10;
			Cars c = new Cars(x, y, w, h, Car);
			cars[i] = c;
		}
	}

	@Override
	public void render() {

		playerMovement(5);
		carMovement();
		collision();
		endCondition();
		super.render();

		ScreenUtils.clear(256, 256, 256, 1);
		sr.begin(ShapeRenderer.ShapeType.Filled);
		player.draw(sr);

		for(int i = 0; i < cars.length; i++) {
			cars[i].draw(sr);
		}
		sr.end();
	}

	public void playerMovement(int d) {
		if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.getY() < 690) {
			player.setY(player.getY() + d);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.getY() < 690) {
			player.setY(player.getY() - d);
		}
	}

	public void carMovement() {
		for (int i = 0; i < cars.length; i++) {
			float delta = Gdx.graphics.getDeltaTime();
			float carSpeed = 200; // Set your desired speed

			cars[i].setX((int) (cars[i].getX() + carSpeed * delta));

			// Check if the right edge of the car is past the screen width
			if (cars[i].getX() + cars[i].getW() > Gdx.graphics.getWidth()) {
				// Reset the car to the left side of the screen
				cars[i].setX(-cars[i].getW());
			}
		}
	}


	public void collision() {
		for (int i = 0; i < cars.length; i++) {
			float playerRightX = player.getX() + player.getW();
			float carRightX = cars[i].getX() + cars[i].getW();

			if (player.getX() < carRightX && playerRightX > cars[i].getX()) {
				if (player.getY() < cars[i].getY() + cars[i].getH() && player.getY() + player.getH() > cars[i].getY()) {
					player.setY(10); // Reset player position on collision
				}
			}
		}
	}


	public void endCondition() {
		if (player.getY() == Gdx.graphics.getHeight() - 20) {
			player.setY(10);
		}
	}


	@Override
	public void dispose() {

	}
}
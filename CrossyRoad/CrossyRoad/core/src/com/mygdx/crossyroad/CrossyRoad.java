package com.mygdx.crossyroad;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Random;

public class CrossyRoad extends ApplicationAdapter {
	ShapeRenderer sr;
	SpriteBatch batch;
	SpriteBatch batch2;
	int upperbound = 700;
	int upperbound2 = 700;
	int blue = 255;
	int red = 255;
	int green = 255;
	boolean end = false;
	Random rand = new Random();
	Color col;
	int Score = 0;
	public BitmapFont font;

	Player player;
	Cars[] cars = new Cars[100];
	Texture Chicken;
	Texture Car;

	@Override
	public void create() {
		batch = new SpriteBatch();
		batch2 = new SpriteBatch();
		font = new BitmapFont();


		Chicken = new Texture("Chicken.png");
		Car = new Texture("Car.png");


		sr = new ShapeRenderer();
		player = new Player(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()/2, 10, 10, Color.BLACK);

		for(int i = 0; i < cars.length; i++){
			int y = rand.nextInt(upperbound);
			int x = rand.nextInt(upperbound2);

			if(y < 375 && y > 325){
				y = y + 100;
			}
			int w = 20;
			int h = 10;
			Cars c = new Cars(x, y, w, h, Color.BLACK);
			cars[i] = c;
		}
	}

	@Override
	public void render() {

		playerMovement(5);
		carMovement();
		collision();
		carRespawn();

		super.render();

		ScreenUtils.clear(0, 0, 0, 1);

		sr.begin(ShapeRenderer.ShapeType.Filled);
		batch.begin();
		font.draw(batch, Score+"", 0, 650);

		for(int i = 0; i < cars.length; i++) {
			batch.draw(Chicken, player.getX(), player.getY());
			batch.draw(Car, cars[i].getX(), cars[i].getY());
		}

		if(end == true){
			ScreenUtils.clear(0, 0, 0, 1);
			font.draw(batch, "you lose" + "\n" + "Score = " +Score, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		}
		batch.end();
		sr.end();
	}

	public void playerMovement(int d) {
		for (int i = 0; i < cars.length; i++) {
			if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.getY() < 690) {
				cars[i].setY(cars[i].getY() - d);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.getY() < 690) {
				cars[i].setY(cars[i].getY() + d);
			}
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
						end = true;
					}
				}
			}
		}
	public void carRespawn(){
		for (int i = 0; i < cars.length; i++){
			if(cars[i].getY() < 0){
				cars[i].setY(cars[i].getY() + 700);
				Score++;
			}
		}
	}





	@Override
	public void dispose() {

	}
}
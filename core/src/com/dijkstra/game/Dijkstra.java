package com.dijkstra.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Dijkstra extends ApplicationAdapter {
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public ShapeRenderer shapeRenderer;
	public static Spot start;
	public static Spot end;
	public Vector2 lineStart;
	public Vector2 lineEnd;

	private Spot[][] grid; // Bi-dimensional array of Spot objects where the program is based
	public static final int ROWS = 50; // Number of rows
	public static final int WIDTH = 600; // Screen width

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		lineStart = new Vector2();
		lineEnd = new Vector2();
		camera.setToOrtho(false, 600, 600);
		shapeRenderer = new ShapeRenderer();
		makeGrid();
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 255, 255, 0); // Color white
		batch.begin();
		drawSpots();
		drawGrid();
		batch.end();

		// Mouse button left to draw spots
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			Vector3 aux = getMousePosition(touchPos);

			int row = (int) aux.x;
			int col = (int) aux.y;

			Spot spot = grid[row][col];

			System.out.println("row: " + row + " col: " + col);
			if(start == null)
			{
				start = spot;
				spot.setColor(Color.BLUE);
			}
			else if(end == null && !spot.equals(start))
			{
				end = spot;
				spot.setColor(Color.YELLOW);
			}
			else if(!spot.equals(start) && !spot.equals(end))
			{
				spot.setColor(Color.BLACK);
				spot.setBarrier(true);
			}

		}

		// Mouse button right to erase spots
		if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
		{
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			Vector3 aux = getMousePosition(touchPos);

			int row = (int) aux.x;
			int col = (int) aux.y;

			Spot spot = grid[row][col];

			if(spot.equals(start))
			{
				start = null;
				spot.setColor(Color.WHITE);
			}
			else if(spot.equals(end))
			{
				end = null;
				spot.setColor(Color.WHITE);
			}
			else
			{
				spot.setColor(Color.WHITE);
				spot.setBarrier(false);
			}
		}

		// Start Dijkstra's Algorithm
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && start != null && end != null)
		{
			for(Spot[] row: grid)
				for(Spot spot: row)
					spot.updateNeighbors(grid);

			Algorithm algorithm = new Algorithm();
			algorithm.dijkstraAlgorithm(start, end);
		}

		// Restart the grid
		if(Gdx.input.isKeyPressed(Input.Keys.R))
		{
			start = null;
			end = null;
			makeGrid();
			drawSpots();
		}

		// Quit the app
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
		{
			Gdx.app.exit();
			System.exit(0);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
	}

	/*
	* No param
	* Initialize the grid array with Spot object instances
	* Where i and j represent the row and col position
	* The x and y are stored in a Vector2 object.
	* */
	private void makeGrid()
	{
		int gap = WIDTH / ROWS; // Space between spots
		Spot[][] aux = new Spot[ROWS][WIDTH];
		for(int i = 0; i < ROWS; i++)
			for (int j = 0; j < WIDTH; j++)
				aux[i][j] = new Spot(i, j, gap, ROWS);
		setGrid(aux);
	}

	/*
	* No param
	* Draws the grid iterating between two for loops
	* Calls drawLine function in each iteration.
	* */
	private void drawGrid()
	{
		int gap = WIDTH / ROWS; //Space between Spots
		for(int i = 0; i < ROWS; i++)
		{
			drawLine(shapeRenderer, lineStart.set(0, i * gap), lineEnd.set(WIDTH, i * gap),1 ,Color.GRAY);
			for(int j = 0; j < ROWS; j++)
			{
				drawLine(shapeRenderer, lineStart.set(j * gap, 0), lineEnd.set(j * gap, WIDTH), 1, Color.GRAY);
			}
		}
	}

	/*
	* No param
	* Draws the spots iterating through the grid
	* Calls the drawSpot method in each iteration
	* */
	private void drawSpots()
	{
		for(int i = 0; i < ROWS; i++)
			for(int j = 0; j < WIDTH; j++)
				grid[i][j].drawSpot(shapeRenderer);
	}

	/*
	* Param: ShapeRenderer, gdx.Vector2, gdx.Vector2, int lineWitdh, gdx.Color
	* Uses gdx.ShapeRenderer (Setting the ShapeType.Line) to draw the lines from Vector2 start to Vector2 end
	* Width and the color are specified in the parameters
	* */
	private static void drawLine(ShapeRenderer shapeRenderer,Vector2 start, Vector2 end, int lineWidth, Color color)
	{
		Gdx.gl.glLineWidth(lineWidth);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(color);
		shapeRenderer.line(start, end);
		shapeRenderer.end();

		Gdx.gl.glLineWidth(1);
	}


	/*
	* Param: Vector3
	* Returns: Vector3
	* This method gets the gdx.Vector3 where the mouse got clicked
	* Cast's the position x and y from the vector to int and divides them between the space between Spots
	* In order to get the position in the form of row and col.
	* */
	public Vector3 getMousePosition(Vector3 pos)
	{
		int gap = WIDTH / ROWS;
		int x = (int) pos.x;
		int y =(int) pos.y;

		int row = x / gap;
		int col = y / gap;

		pos.set(row, col, 0);

		return pos;
	}

	public void setGrid(Spot[][] grid) {
		this.grid = grid;
	}

}

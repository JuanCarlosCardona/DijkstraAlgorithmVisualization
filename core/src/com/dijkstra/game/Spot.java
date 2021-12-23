package com.dijkstra.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Objects;

public class Spot
{
    private final int row;
    private final int col;
    private Vector2 pos;
    private Color color;
    private int spotWidth;
    private int totalRows;
    private final LinkedList<Node> neighbors = new LinkedList<>();
    private boolean isBarrier;

    public Spot(int row, int col, int spotWidth, int totalRows)
    {
        this.row = row;
        this.col = col;
        this.setSpotWidth(spotWidth);
        this.setPos(new Vector2(row * spotWidth, col * spotWidth)); // Set x and y position in the grid
        this.setTotalRows(totalRows);
        this.setColor(Color.WHITE);
    }

    /*
    * Param: ShapeRenderer
    * Set the ShapeType to rect type
    * Draws the rect in the x and y position of the spot
    * The width is specified in the parameterized constructor
    * */
    public void drawSpot(ShapeRenderer shapeRenderer)
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.getColor());
        shapeRenderer.rect(this.getPos().x, this.getPos().y, this.getSpotWidth(), this.getSpotWidth());
        shapeRenderer.end();
    }

    /*
    * Param: Spot[][]
    * If the neighbor of the current Spot is not a barrier
    * Adds a new Node object in the adjacency list
    * */
    public void updateNeighbors(Spot[][] grid)
    {
        if(this.row < this.totalRows - 1 && !grid[this.row + 1][col].isBarrier) // Down
        {
            this.neighbors.add(new Node(grid[this.row + 1][col]));
        }

        if(this.row > 0 && !grid[this.row - 1][this.col].isBarrier) // Up
        {
            this.neighbors.add(new Node(grid[this.row - 1 ][col]));
        }


        if(this.col < this.totalRows - 1 && !grid[this.row][this.col + 1].isBarrier) // Right
        {
            this.neighbors.add(new Node(grid[this.row][this.col + 1]));
        }

        if(this.col > 0 && !grid[this.row][this.col - 1].isBarrier) //Left
        {
            this.neighbors.add(new Node(grid[this.row][this.col - 1]));
        }
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSpotWidth() {
        return spotWidth;
    }

    public void setSpotWidth(int spotWidth) {
        this.spotWidth = spotWidth;
    }


    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public LinkedList<Node> getNeighbors() {return this.neighbors;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spot spot = (Spot) o;
        return getPos().equals(spot.getPos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPos());
    }

    public void setBarrier(boolean barrier) {
        isBarrier = barrier;
    }

}

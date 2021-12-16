package com.dijkstra.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Spot
{
    private Vector2 pos;
    private Color color;
    private int spotWidth;
    private int totalRows;

    public Spot(int row, int col, int spotWidth, int totalRows)
    {
        this.setSpotWidth(spotWidth);
        this.setPos(new Vector2(row * spotWidth, col * spotWidth));
        this.setTotalRows(totalRows);
        this.setColor(Color.WHITE);
    }

    public void drawSpot(ShapeRenderer shapeRenderer)
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.getColor());
        shapeRenderer.rect(this.getPos().x, this.getPos().y, this.getSpotWidth(), this.getSpotWidth());
        shapeRenderer.end();
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

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }


}

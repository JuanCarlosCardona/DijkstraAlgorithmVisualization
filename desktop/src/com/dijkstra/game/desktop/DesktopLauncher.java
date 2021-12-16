package com.dijkstra.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dijkstra.game.Dijkstra;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Dijkstra Pathfinding Algorithm";
		config.width = 600;
		config.height = 600;
		new LwjglApplication(new Dijkstra(), config);
	}
}

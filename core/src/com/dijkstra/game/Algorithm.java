package com.dijkstra.game;

import com.badlogic.gdx.graphics.Color;

import java.util.*;

public class Algorithm
{
    private final int V = Dijkstra.ROWS * Dijkstra.WIDTH;
    private final int[] dist = new int[V];
    private final Set<Spot> visited = new HashSet<>();
    private final PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>();
    private final List<List<Node>> adjacencyList = new LinkedList<>();


    public void dijkstraAlgorithm(Spot start, Spot end)
    {
        nodePriorityQueue.add(new Node(start, 0, null));
        dist[0] = 0;
        visited.add(start);
        int count = 0;

        while(!nodePriorityQueue.isEmpty())
        {
            Node current = nodePriorityQueue.poll();
            visited.remove(current.item);

            if(current.item.equals(end))
                return;

            for(Node neighbor : current.item.getNeighbors())
            {
                int temp_dist = count + 1;

                if(temp_dist < neighbor.cost)
                {
                    neighbor.cameFrom = current;
                    neighbor.cost = temp_dist;

                    if(!visited.contains(neighbor.item))
                    {
                        count++;
                        nodePriorityQueue.add(neighbor);
                        neighbor.item.setColor(Color.GREEN);
                    }
                }

            }

            if(!current.item.equals(start))
                current.item.setColor(Color.ROYAL);
        }

    }

}

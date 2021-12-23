package com.dijkstra.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;
import java.util.*;


public class Algorithm
{
    private final Set<Spot> visited = new HashSet<>(); // Stores visited Nodes
    private final PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>(); // Store nodes in a Queue data structure


    /*
    * Param: Spot, Spot
    * Makes the Dijkstra's Algorithm with a PriorityQueue
    * Begin from the start Spot and checks every possible path to get to the end
    * Once the end Spot is reached, draws the shortestPath
    * */
    public void dijkstraAlgorithm(Spot start, Spot end)
    {
        nodePriorityQueue.add(new Node(start, 0, null)); // Add the start Node
        visited.add(start); // Start Node is visited
        int count = 0;

        while(!nodePriorityQueue.isEmpty())
        {
            final Node current = nodePriorityQueue.poll(); // Get the head of the Queue
            visited.remove(current.item); // Node visited

            /*
            * If the end is reached calls the method to draw the shortest path
            * Timer.schedule is used in order to make a better visual experience the pathfinding
            * */
            if(current.item.equals(end))
            {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        drawShortestPath(visited, current);
                    }
                },0.6f);
                return;
            }

            //Iterate through the current Node neighbors
            for(final Node neighbor : current.item.getNeighbors())
            {
                int temp_dist = current.cost + 1;

                // Select shortest path if true
                if(temp_dist < neighbor.cost)
                {
                    neighbor.cameFrom = current; // Connect between Nodes

                    // If true the neighbor Node is added to the data structures and changes the current color
                    if(!visited.contains(neighbor.item))
                    {
                        count++; // Increase count
                        neighbor.cost = count; // Set the cost from the neighbor to the current count
                        nodePriorityQueue.add(neighbor);
                       // neighbor.item.setColor(Color.GREEN);

                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                neighbor.item.setColor(Color.GREEN);
                            }
                        },0.5f);

                        visited.add(neighbor.item); //Neighbor visited
                    }

                }

            }
            // If is already visited change color to gdx.Color.SKY
            if(current.item != (start))
            {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        current.item.setColor(Color.SKY);
                    }
                },0.5f);
            }
        }
    }

    /*
    * Param: Set<Spot>, Node
    * Draws the end spot and its predecessors
    * Changes the color of each node to draw the path
    * And every Node is removed from the set
    * */
    private void drawShortestPath(Set<Spot> visited, Node current)
    {
        while (current != null)
        {
            visited.remove(current.item);
            current.item.setColor(Color.PURPLE);
            current = current.cameFrom;
        }
    }

}

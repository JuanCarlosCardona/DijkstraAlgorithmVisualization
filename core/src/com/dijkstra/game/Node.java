package com.dijkstra.game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node implements Comparable<Node>
{
    public Spot item;
    public int cost = Integer.MAX_VALUE;
    public Node cameFrom;

    public Node(){
        this.item = null;
        this.cameFrom = null;
    }

    public Node(Spot item, int cost)
    {
        this.item = item;
        this.cost = cost;
    }

    public Node(Spot item, int cost, Node cameFrom)
    {
        this.item = item;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }


}

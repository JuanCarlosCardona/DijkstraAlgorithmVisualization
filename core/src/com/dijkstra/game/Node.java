package com.dijkstra.game;

public class Node implements Comparable<Node>
{
    public Spot item;
    public int cost;
    public Node cameFrom;

    public Node(){
        this.item = null;
        this.cost = Integer.MAX_VALUE;
        this.cameFrom = null;
    }

    public Node(Spot item)
    {
        this.item = item;
        this.cost = Integer.MAX_VALUE;
        this.cameFrom = null;
    }

    public Node(Spot item, int cost, Node cameFrom)
    {
        this.item = item;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    //Compare in ascending order
    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }

}

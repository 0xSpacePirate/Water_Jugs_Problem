package com.company.search;

public class Node {
    public State state;
    public Node parent;
    public Action action;
    public int depth;

    public Node(State state, Node parent, Action action, int depth) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.depth = depth;
    }

    public double getCost() {
        return this.parent == null ? 0.0D : this.action.cost + this.parent.getCost();
    }
}

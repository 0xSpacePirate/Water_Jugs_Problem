package com.company.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public abstract class SearchProblem {
    public int nodeVisited;
    protected State startState;

    public SearchProblem(State start) {
        this.startState = start;
        this.nodeVisited = 0;
    }

    public Path search() {
        Set<State> visitedState = new HashSet();
        List<Node> fringe = new LinkedList();
        fringe.add(new Node(this.startState, (Node) null, (Action) null, 0));
        ++this.nodeVisited;

        while (!fringe.isEmpty()) {
            Node node = (Node) fringe.remove(0);
            if (this.isGoal(node.state)) {
                return this.constructPath(node);
            }

            if (!visitedState.contains(node.state)) {
                List<ActionStatePair> childrenNodes = node.state.successor();
                visitedState.add(node.state);
                this.addChildrenNodes(fringe, node, childrenNodes);
            }
        }

        return null;
    }

    protected void addChildrenNodes(List<Node> fringe, Node parentNode, List<ActionStatePair> children) {
        Object[] childrenArray = children.toArray();

        for (int i = 0; i < childrenArray.length; ++i) {
            ActionStatePair actionState = (ActionStatePair) childrenArray[i];
            Action action = actionState.action;
            State childState = actionState.state;
            int newDepth = parentNode.depth + 1;
            Node childNode = new Node(childState, parentNode, action, newDepth);
            this.addChild(fringe, childNode);
            ++this.nodeVisited;
        }

    }

    protected void addChild(List<Node> fringe, Node childNode) {
        fringe.add(childNode);
    }

    protected Path constructPath(Node node) {
        if (node == null) {
            return null;
        } else {
            Path result = new Path();

            for (result.cost = node.getCost(); node.parent != null; node = node.parent) {
                result.add(0, new ActionStatePair(node.action, node.state));
            }

            result.head = node.state;
            return result;
        }
    }

    public abstract boolean isGoal(State var1);
}


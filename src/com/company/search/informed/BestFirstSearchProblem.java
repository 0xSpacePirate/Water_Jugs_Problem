package com.company.search.informed;

import com.company.search.*;

import java.util.*;

public abstract class BestFirstSearchProblem extends SearchProblem {
    public State goalState;

    public BestFirstSearchProblem(State start, State goal) {
        super(start);
        this.goalState = goal;
    }

    public Path search() {
        Map<State, Node> visitedNodes = new HashMap<>();
        List<Node> fringe = new LinkedList<>();
        Node rootNode = new Node(this.startState, null, null, 0);
        final int FIXED_NUMBER_OF_NODES = 1000, NO_REMAINDER = 0, FIRST_ELEMENT = 0;
        final String NODES_VISITED_MESSAGE = "No. of nodes explored: ";
        fringe.add(rootNode);
        visitedNodes.put(rootNode.state, rootNode);
        ++this.nodeVisited;
        if (this.nodeVisited % FIXED_NUMBER_OF_NODES == NO_REMAINDER) {
            System.out.println(NODES_VISITED_MESSAGE + this.nodeVisited);
        }

        while(!fringe.isEmpty()) {
            Node node = fringe.remove(FIRST_ELEMENT);
            if (this.isGoal(node.state)) {
                return this.constructPath(node);
            }

            Object[] childrenNodes = node.state.successor().toArray();

            Arrays.stream(childrenNodes).forEach(childrenNode -> {
                ++this.nodeVisited;
                if (this.nodeVisited % FIXED_NUMBER_OF_NODES == NO_REMAINDER) {
                    System.out.println(NODES_VISITED_MESSAGE + this.nodeVisited);
                }
                ActionStatePair child = (ActionStatePair) childrenNode;
                Action action = child.action;
                State nextState = child.state;
                Node lastSeenNode = visitedNodes.get(nextState);
                if (lastSeenNode == null) {
                    Node childNode = new Node(nextState, node, action, node.depth + 1);
                    this.addChild(fringe, childNode);
                    visitedNodes.put(nextState, childNode);
                } else if (lastSeenNode.getCost() > action.cost + node.getCost()) {
                    lastSeenNode.parent = node;
                    lastSeenNode.depth = node.depth + 1;
                    lastSeenNode.action = action;
                }
            });
        }

        return null;
    }

    protected void addChild(List<Node> fringe, Node childNode) {
        for(int i = 0; i < fringe.size(); ++i) {
            if (this.evaluation(childNode) < this.evaluation(fringe.get(i))) {
                fringe.add(i, childNode);
                return;
            }
        }
        fringe.add(childNode);
    }

    public abstract double evaluation(Node var1);
}


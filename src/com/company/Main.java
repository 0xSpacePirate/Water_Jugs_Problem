package com.company;

import com.company.search.Path;

public class Main {

    public static void main(String[] args) {

        final String SEARCH_MESSAGE = "Searching....", NO_SOLUTION_MESSAGE = "No solution";
        final String NODES_VISITED_MESSAGE = "Nodes visited: ", COST_MESSAGE = "Total Cost: ";
        final String NEW_LINE_DELIMITER = "\n";

        final int JUG_A_INITIAL_VALUE = 0, JUG_B_INITIAL_VALUE = 0;
        final int JUG_A_MIN_VALUE = 0, JUG_B_MIN_VALUE =0;
        final int JUG_A_MAX_VALUE = 5, JUG_B_MAX_VALUE = 3;
        final int JUG_A_GOAL = 4, JUG_B_GOAL = 0;

        JugState initial = new JugState(JUG_A_INITIAL_VALUE,JUG_B_INITIAL_VALUE,JUG_A_MIN_VALUE,JUG_B_MIN_VALUE,JUG_A_MAX_VALUE,JUG_B_MAX_VALUE);
        JugState goalState = new JugState(JUG_A_GOAL,JUG_B_GOAL,JUG_A_MIN_VALUE,JUG_B_MIN_VALUE,JUG_A_MAX_VALUE,JUG_B_MAX_VALUE);

        //JugState initial = new JugState(0, 0); // set initial state of jugs;
        //JugState goalState = new JugState(4,0); // set goal state of jugs;

        JugProblem problem = new JugProblem(initial, goalState);

        System.out.println(SEARCH_MESSAGE);
        Path path = problem.search();
        if (path == null) System.out.println(NO_SOLUTION_MESSAGE);
        else {
            path.print();
            System.out.println(NODES_VISITED_MESSAGE + problem.nodeVisited);
            System.out.println(COST_MESSAGE + path.cost + NEW_LINE_DELIMITER);
        }

    }

}

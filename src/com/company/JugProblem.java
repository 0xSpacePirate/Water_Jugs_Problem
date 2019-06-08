package com.company;


import com.company.search.Node;
import com.company.search.State;
import com.company.search.informed.BestFirstSearchProblem;

import static com.company.JugState.*;

public class JugProblem extends BestFirstSearchProblem {

    private State goalState;

    /**
     * @param start initial state of the Jugs
     * @param goal  goal state of Jugs
     **/
    JugProblem(State start, State goal) {
        super(start, goal);
        this.goalState = goal;
    }

    /**
     * @param node using the current node to
     * @return the heuristic evaluation taking into account the cost
     **/
    @Override
    public double evaluation(Node node) {
        return node.getCost() + this.heuristic(node.state);
    }

    /**
     * @return checks to see whether the goal is reached
     **/
    @Override
    public boolean isGoal(State goal) {
        return goal.equals(this.goalState);
    }

    /**
     * @param state determines to which state a priority should be given;
     **/
    private double heuristic(State state) {
        JugState jugCurrent = (JugState) state, jugGoal = (JugState) this.goalState;

        final int INITIAL_TOTAL_VALUE = jugCurrent.getJugACurrentValue() + jugCurrent.getJugBCurrentValue();
        final int GOAL_TOTAL_VALUE = jugGoal.getJugACurrentValue() + jugGoal.getJugBCurrentValue();

        if(INITIAL_TOTAL_VALUE > GOAL_TOTAL_VALUE) return Math.abs(INITIAL_TOTAL_VALUE - GOAL_TOTAL_VALUE) * EMPTY_JUG_IN_THE_SINK_COST;
        if(INITIAL_TOTAL_VALUE == GOAL_TOTAL_VALUE) return Math.abs(INITIAL_TOTAL_VALUE - GOAL_TOTAL_VALUE);

        return Math.abs(INITIAL_TOTAL_VALUE - GOAL_TOTAL_VALUE) * FILLING_SINGLE_LITRE_FROM_TAP_COST;
    }
}

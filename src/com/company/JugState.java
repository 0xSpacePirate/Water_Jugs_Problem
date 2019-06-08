package com.company;

import com.company.search.ActionStatePair;
import com.company.search.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JugState implements State {

    private int jugACurrentValue, jugBCurrentValue;

    //Cost per action below, according to the requirements;
    final static double POURING_ONE_JUG_TO_ANOTHER_COST = 1.0, FILLING_SINGLE_LITRE_FROM_TAP_COST = 2.0, EMPTY_JUG_IN_THE_SINK_COST = 10.0;

    static int JUG_A_MAX = 5, JUG_B_MAX = 3; /* Default max values for the Jugs!*/
    static int JUG_A_MIN = 0, JUG_B_MIN = 0; /* Default min values for the Jugs!*/

    /**
     * The values of the variables below correspond to
     *
     * @param sentences the indexes of the array;
     * @param FILL_JUG_A_FROM_TAP_VALUE equals corresponding sentence in the array
     * @param FILL_JUG_B_FROM_TAP_VALUE equals corresponding sentence in the array
     * @param FILL_JUG_A_FROM_JUG_B_VALUE equals corresponding sentence in the array
     * @param FILL_JUG_B_FROM_JUG_A_VALUE equals corresponding sentence in the array
     * @param EMPTY_JUG_A_VALUE equals corresponding sentence in the array
     * @param EMPTY_JUG_B_VALUE equals corresponding sentence in the array
     **/
    final static int FILL_JUG_A_FROM_TAP_VALUE = 0, FILL_JUG_B_FROM_TAP_VALUE = 1;
    final static int FILL_JUG_A_FROM_JUG_B_VALUE = 2, FILL_JUG_B_FROM_JUG_A_VALUE = 3;
    final static int EMPTY_JUG_B_VALUE = 5, EMPTY_JUG_A_VALUE = 4;

    /**
     * set all fields,
     *
     * @param sentences an array containing the sentences for each possible move,
     * makes the code more compact using an array filled with the appropriate sentences required
     **/
    static String[] sentences = {"Fill jug A from the tap. Cost: ",
            "Fill jug B from the tap. Cost: ",
            "Fill jug A from jug B. Cost: ",
            "Fill jug B from jug A. Cost: ",
            "Empty jug A into the sink. Cost: ",
            "Empty jug B into the sink. Cost: "};

    /**
     * set all fields,
     * @param jugA new initial or goal state for Jug A;
     * @param jugB new initial or goal state for Jug B;
     **/
    public JugState(int jugA, int jugB) {
        this.jugACurrentValue = jugA;
        this.jugBCurrentValue = jugB;
    }

    /**
     * set all fields,
     * @param jugA                new initial or goal state for Jug A;
     * @param jugB                new initial or goal state for Jug B;
     * @param JUG_A_NEW_MIN_VALUE new minimum value for Jug A;
     * @param JUG_B_NEW_MIN_VALUE new minimum value for Jug B;
     * @param JUG_A_NEW_MAX_VALUE new maximum value for Jug A;
     * @param JUG_B_NEW_MAX_VALUE new maximum value for Jug B;
     **/
    JugState(int jugA, int jugB, int JUG_A_NEW_MIN_VALUE, int JUG_B_NEW_MIN_VALUE, int JUG_A_NEW_MAX_VALUE, int JUG_B_NEW_MAX_VALUE) {
        this.jugACurrentValue = jugA;
        this.jugBCurrentValue = jugB;
        JUG_A_MIN = JUG_A_NEW_MIN_VALUE;
        JUG_B_MIN = JUG_B_NEW_MIN_VALUE;
        JUG_A_MAX = JUG_A_NEW_MAX_VALUE;
        JUG_B_MAX = JUG_B_NEW_MAX_VALUE;
    }

    /**
     * A method that passes a list full of each possible next move for the algorithm
     **/
    @Override
    public List<ActionStatePair> successor() {
        List<ActionStatePair> result = new ArrayList<>();

        fillJugBWithJugA(result);
        fillJugAWithJugB(result);
        fillJugAFromTap(result);
        fillJugBFromTap(result);
        emptyJugA(result);
        emptyJugB(result);

        return result;
    }

    /**
     * A method that checks if Jug A is not empty and Jug B is not full, fill Jug B with what is inside of Jug A;
     * @param result list of possible moves;
     **/
    private void fillJugBWithJugA(List<ActionStatePair> result){
        if (this.jugACurrentValue > JUG_A_MIN && this.jugBCurrentValue < JUG_B_MAX) {
            JugAction action = new JugAction(jugACurrentValue, jugBCurrentValue, Step.FILL_JUG_B_FROM_JUG_A); //create JugAction object
            action.cost = POURING_ONE_JUG_TO_ANOTHER_COST;                                  //update cost of the action
            JugState nextState = this.applyAction(action);                            //apply jugAction to find next state
            ActionStatePair actionStatePair = new ActionStatePair(action, nextState);    //create jugAction-state pair
            result.add(actionStatePair);
        }
    }

    /**
     * A method that checks if Jug B is not empty and Jug A is not full, fill Jug A with what is inside of Jug B;
     * @param result list of possible moves;
     **/
    private void fillJugAWithJugB(List<ActionStatePair> result){
        if (this.jugBCurrentValue > JUG_B_MIN && this.jugACurrentValue < JUG_A_MAX) {
            JugAction action = new JugAction(jugACurrentValue, jugBCurrentValue, Step.FILL_JUG_A_FROM_JUG_B); //create JugAction object
            action.cost = POURING_ONE_JUG_TO_ANOTHER_COST;                                  //update cost of the action
            JugState nextState = this.applyAction(action);                            //apply jugAction to find next state
            ActionStatePair actionStatePair = new ActionStatePair(action, nextState);    //create jugAction-state pair
            result.add(actionStatePair);
        }
    }

    /**
     * A method that checks if Jug A is less than the maximum, fill it from the tap;
     * @param result list of possible moves;
     **/
    private void fillJugAFromTap(List<ActionStatePair> result){
        //
        if (this.jugACurrentValue < JUG_A_MAX) {
            JugAction action = new JugAction(jugACurrentValue, jugBCurrentValue, Step.FILL_JUG_A_FROM_TAP);  //create JugAction object
            action.cost = (JUG_A_MAX - action.jugACurrentValue) * FILLING_SINGLE_LITRE_FROM_TAP_COST;  //update cost of the action
            JugState nextState = this.applyAction(action);                            //apply jugAction to find next state
            ActionStatePair actionStatePair = new ActionStatePair(action, nextState);    //create jugAction-state pair
            result.add(actionStatePair);
        }

    }

    /**
     * A method that checks if Jug B is less than the maximum, fill it from the tap;
     * @param result list of possible moves;
     **/
    private void fillJugBFromTap(List<ActionStatePair> result){
        if (this.jugBCurrentValue < JUG_B_MAX) {
            JugAction action = new JugAction(jugACurrentValue, jugBCurrentValue, Step.FILL_JUG_B_FROM_TAP); //create JugAction object
            action.cost = (JUG_B_MAX - action.jugBCurrentValue) * FILLING_SINGLE_LITRE_FROM_TAP_COST;  //update cost of the action
            JugState nextState = this.applyAction(action);                            //apply jugAction to find next state
            ActionStatePair actionStatePair = new ActionStatePair(action, nextState);    //create jugAction-state pair
            result.add(actionStatePair);
        }
    }

    /**
     * A method that checks if Jug A is not empty, empty it;;
     * @param result list of possible moves;
     **/
    private void emptyJugA(List<ActionStatePair> result){
        if (this.jugACurrentValue > JUG_A_MIN) {
            JugAction action = new JugAction(jugACurrentValue, jugBCurrentValue, Step.EMPTY_JUG_A); //create JugAction object
            action.cost = (action.jugACurrentValue * EMPTY_JUG_IN_THE_SINK_COST);            //update cost of the action
            JugState nextState = this.applyAction(action);                            //apply jugAction to find next state
            ActionStatePair actionStatePair = new ActionStatePair(action, nextState);    //create jugAction-state pair
            result.add(actionStatePair);
        }
    }

    /**
     * A method that checks if Jug B is not empty, empty it;
     * @param result list of possible moves;
     **/
    private void emptyJugB(List<ActionStatePair> result){
        if (this.jugBCurrentValue > JUG_B_MIN) {
            JugAction action = new JugAction(jugACurrentValue, jugBCurrentValue, Step.EMPTY_JUG_B); //create JugAction object
            action.cost = (action.jugBCurrentValue * EMPTY_JUG_IN_THE_SINK_COST);             //update cost of the action
            JugState nextState = this.applyAction(action);                            //apply jugAction to find next state
            ActionStatePair actionStatePair = new ActionStatePair(action, nextState);    //create jugAction-state pair
            result.add(actionStatePair);
        }
    }


    @Override
    public boolean equals(Object state) {
        if (!(state instanceof JugState)) return false;
        JugState jugState = (JugState) state;
        return this.jugACurrentValue == jugState.jugACurrentValue
                && this.jugBCurrentValue == jugState.jugBCurrentValue;
        // Checking to see whether the jugs are ACTUALLY the same.
    }

    @Override
    public int hashCode() {
        return Objects.hash(jugACurrentValue, jugBCurrentValue);
    }


    /**
     * @param jugAction takes a state and
     *                  estimates the values depending on what the case is
     *                  AND MUST NOT change current values of the Jugs,
     *                  thus use temporary variables
     **/
    public JugState applyAction(JugAction jugAction) {
        int difference;
        int jugA = this.jugACurrentValue, jugB = this.jugBCurrentValue;

        switch (jugAction.step) {
            case FILL_JUG_A_FROM_JUG_B:
                if ((jugA + jugB) > JUG_A_MAX) {
                    difference = (jugA + jugB) - JUG_A_MAX;  /* get excess amount */
                    jugA = JUG_A_MAX;
                    jugB = difference;
                } else {
                    jugA += jugB;
                    jugB = JUG_B_MIN; /* When a person pours into jugA, the value of jugB becomes the minimum for jugB not 0;*/
                }
                break;

            case FILL_JUG_B_FROM_JUG_A:
                if ((jugA + jugB) > JUG_B_MAX) {
                    difference = (jugA + jugB) - JUG_B_MAX; /* get excess amount */
                    jugB = JUG_B_MAX;
                    jugA = difference;
                } else {
                    jugB += jugA;
                    jugA = JUG_A_MIN; /* When a person pours into jugB, the value of jugA becomes the minimum for jugA not 0;*/
                }
                break;

            case FILL_JUG_A_FROM_TAP:
                jugA = JUG_A_MAX;
                break;

            case FILL_JUG_B_FROM_TAP:
                jugB = JUG_B_MAX;
                break;

            case EMPTY_JUG_A:
                jugA = JUG_A_MIN;  /* When a person empties jugA, the value of jugA becomes the minimum for jugA not 0;*/
                break;

            case EMPTY_JUG_B:
                jugB = JUG_B_MIN;  /* When a person empties jugB, the value of jugB becomes the minimum for jugB not 0;*/
                break;
        }
        return new JugState(jugA, jugB);
    }

    int getJugACurrentValue() {
        return jugACurrentValue;
    }

    public void setJugACurrentValue(int jugACurrentValue) {
        this.jugACurrentValue = jugACurrentValue;
    }

    int getJugBCurrentValue() {
        return jugBCurrentValue;
    }

    public void setJugBCurrentValue(int jugBCurrentValue) {
        this.jugBCurrentValue = jugBCurrentValue;
    }

    public String toString() {
        final String JUG_A_OUTPUT = "\nJug A: ", JUG_B_OUTPUT = "\nJug B: ";
        final String JUG_A_MAX_MIN_OUTPUT = " (max " + JUG_A_MAX + ", min " + JUG_A_MIN + ")";
        final String JUG_B_MAX_MIN_OUTPUT = " (max " + JUG_B_MAX + ", min " + JUG_B_MIN + ")";
        final String LINE_DELIMITER = "\n= = = = = = = = = = = = = = = = = = = =";
        return JUG_A_OUTPUT + this.jugACurrentValue + JUG_A_MAX_MIN_OUTPUT +
                JUG_B_OUTPUT + this.jugBCurrentValue + JUG_B_MAX_MIN_OUTPUT + LINE_DELIMITER;
    }

}

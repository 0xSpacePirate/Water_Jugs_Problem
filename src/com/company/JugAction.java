package com.company;

import com.company.search.Action;
import static com.company.JugState.*;

public class JugAction extends Action {
    Step step;
    int jugACurrentValue, jugBCurrentValue;

    public JugAction(int jugA, int jugB, Step step) {
        this.jugACurrentValue = jugA;
        this.jugBCurrentValue = jugB;
        this.step = step;
    }

    @Override
    public String toString() {
        String output = "";
        final String INCORRECT_ACTION_SENTENCE = "\nIncorrect action!", RETURN_STATEMENT_ONE = "\n\nFrom JUG_A = ";
        final String RETURN_STATEMENT_TWO = ", JUG_B = ", RETURN_STATEMENT_THREE = "\nTo \t JUG_A = ", RETURN_STATEMENT_FOUR = ", JUG_B = ";
        int difference, jugA = this.jugACurrentValue, jugB = this.jugBCurrentValue;
        int total = jugA + jugB;
        switch (this.step) {
            case FILL_JUG_A_FROM_JUG_B:
                output += sentences[FILL_JUG_A_FROM_JUG_B_VALUE] + POURING_ONE_JUG_TO_ANOTHER_COST;
                if (total > JUG_A_MAX) {
                    difference = total - JUG_A_MAX;  /* get excess amount */
                    jugA = JUG_A_MAX;
                    jugB = difference;
                } else {
                    jugA += jugB;
                    jugB = JUG_B_MIN; /* When a person pours into jugA, the value of jugB becomes the minimum for jugB not 0;*/
                }
                break;

            case FILL_JUG_B_FROM_JUG_A:
                output += sentences[FILL_JUG_B_FROM_JUG_A_VALUE] + POURING_ONE_JUG_TO_ANOTHER_COST;
                if (total > JUG_B_MAX) {
                    difference = total - JUG_B_MAX;  /* get excess amount */
                    jugB = JUG_B_MAX;
                    jugA = difference;
                } else {
                    jugB += jugA;
                    jugA = JUG_A_MIN; /* When a person pours into jugB, the value of jugA becomes the minimum for jugA not 0;*/
                }
                break;

            case FILL_JUG_A_FROM_TAP:
                difference = JUG_A_MAX - this.jugACurrentValue; // get the number of empty litres;
                output += sentences[FILL_JUG_A_FROM_TAP_VALUE] + FILLING_SINGLE_LITRE_FROM_TAP_COST * difference;
                jugA = JUG_A_MAX;
                break;

            case FILL_JUG_B_FROM_TAP:
                difference = JUG_B_MAX - this.jugBCurrentValue; // get the number of empty litres;
                output += sentences[FILL_JUG_B_FROM_TAP_VALUE] + FILLING_SINGLE_LITRE_FROM_TAP_COST * difference;
                jugB = JUG_B_MAX;
                break;

            case EMPTY_JUG_A:
                output += sentences[EMPTY_JUG_A_VALUE] + (EMPTY_JUG_IN_THE_SINK_COST * jugA);
                jugA = JUG_A_MIN; /* When a person empties jugA, the value of jugA becomes the minimum for jugA not 0;*/
                break;

            case EMPTY_JUG_B:
                output += sentences[EMPTY_JUG_B_VALUE] + (EMPTY_JUG_IN_THE_SINK_COST * jugB);
                jugB = JUG_B_MIN; /* When a person empties jugB, the value of jugB becomes the minimum for jugB not 0;*/
                break;

            default:
                return INCORRECT_ACTION_SENTENCE;

        }
        return output +
                RETURN_STATEMENT_ONE + this.jugACurrentValue +
                RETURN_STATEMENT_TWO + this.jugBCurrentValue +
                RETURN_STATEMENT_THREE + jugA +
                RETURN_STATEMENT_FOUR + jugB;
    }
}

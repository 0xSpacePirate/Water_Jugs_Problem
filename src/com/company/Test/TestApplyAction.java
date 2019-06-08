package com.company.Test;

import com.company.JugAction;
import com.company.JugState;
import com.company.Step;

public class TestApplyAction {
    public static void main(String[] args){

        JugState before = new JugState(3,3);
        JugAction action = new JugAction(0,0, Step.EMPTY_JUG_B);
        JugState after = before.applyAction(action);

        System.out.println("Before:" + before.toString());
        System.out.println("- - - - - - - - - - - - - - - -");
        System.out.println("Action:" + action.toString());
        System.out.println("- - - - - - - - - - - - - - - -");
        System.out.println("After:" + after.toString());
    }
}

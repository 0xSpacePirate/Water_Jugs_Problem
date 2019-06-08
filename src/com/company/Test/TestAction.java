package com.company.Test;

import com.company.JugAction;
import com.company.Step;

public class TestAction {
    public static void main(String[] args){

        JugAction action = new JugAction(3,5, Step.EMPTY_JUG_A);

        System.out.println("\nAction: " + action.toString());
    }
}

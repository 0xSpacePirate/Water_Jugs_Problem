package com.company.Test;

import com.company.JugState;

public class TestSuccessor {
    public static void main(String[] args){

        JugState jugState = new JugState(0,3);
        System.out.println("State " + jugState.toString());
        System.out.println("All action-state:" + jugState.successor().toString());
        System.out.println("= = = = = = = = = = = = = = = = = = = =");


    }
}

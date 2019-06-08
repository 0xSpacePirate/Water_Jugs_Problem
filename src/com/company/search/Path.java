package com.company.search;

import java.util.LinkedList;

public class Path extends LinkedList<ActionStatePair> {
    State head = null;
    public double cost = 0.0D;

    Path() {
    }

    public void print() {
        if (this.head != null) {
            System.out.println(this.head.toString() + "\n");

            this.forEach(next -> {
                System.out.println(next.action.toString());
                System.out.println(next.state.toString());
                System.out.println();
            });

        }
    }
}
package com.company.search;

public class ActionStatePair {
    public Action action;
    public State state;

    public ActionStatePair(Action action, State state) {
        this.action = action;
        this.state = state;
    }

    public String toString() {
        return this.action.toString() + "\n" + this.state.toString();
    }
}
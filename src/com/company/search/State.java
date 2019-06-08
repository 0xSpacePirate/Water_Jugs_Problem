package com.company.search;

import java.util.List;

public interface State {
    String toString();

    boolean equals(Object var1);

    int hashCode();

    List<ActionStatePair> successor();
}
package org.jokertwo.adventofcode.task.impl.task4;

import lombok.Data;


@Data
public class Item {
    private final int number;
    private boolean marked;

    public Item(int number) {
        this.number = number;
        this.marked = false;
    }
}

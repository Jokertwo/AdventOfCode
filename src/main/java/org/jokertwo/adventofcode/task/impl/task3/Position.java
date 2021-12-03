package org.jokertwo.adventofcode.task.impl.task3;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class Position {
    private int horizontal;
    private int depth;

    public Position(int horizontal, int depth) {
        this.horizontal = horizontal;
        this.depth = depth;
    }


    Position setHorizontal(int horizontal) {
        this.horizontal = horizontal;
        return this;
    }


    Position setDepth(int depth) {
        this.depth = depth;
        return this;
    }
}

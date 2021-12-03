package org.jokertwo.adventofcode.dto;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class Position {
    private int horizontal;
    private int depth;
    private int aim;

    public Position(int horizontal, int depth, int aim) {
        this.horizontal = horizontal;
        this.depth = depth;
    }


    public Position setHorizontal(int horizontal) {
        this.horizontal = horizontal;
        return this;
    }


    public Position setDepth(int depth) {
        this.depth = depth;
        return this;
    }


    public Position setAim(int aim) {
        this.aim = aim;
        return this;
    }
}

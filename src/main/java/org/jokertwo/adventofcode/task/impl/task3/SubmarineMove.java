package org.jokertwo.adventofcode.task.impl.task3;

public enum SubmarineMove {
    FORWARD((move, position) -> position.setHorizontal(position.getHorizontal() + move)),
    DOWN((move, position) -> position.setDepth(position.getDepth() + move)),
    UP((move, position) -> position.setDepth(position.getDepth() - move));

    private final MoveFunction<Position> function;

    SubmarineMove(MoveFunction<Position> function) {
        this.function = function;
    }


    MoveFunction<Position> getMove() {
        return function;
    }
}

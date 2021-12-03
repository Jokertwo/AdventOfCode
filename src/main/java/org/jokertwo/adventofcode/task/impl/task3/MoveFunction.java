package org.jokertwo.adventofcode.task.impl.task3;

/**
 * @author PL
 */
@FunctionalInterface
public interface MoveFunction<T> {
    T move(int move, T position);
}

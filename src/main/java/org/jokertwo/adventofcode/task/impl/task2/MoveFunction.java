package org.jokertwo.adventofcode.task.impl.task2;

/**
 * @author PL
 */
@FunctionalInterface
public interface MoveFunction<T> {
    T move(int move, T position);
}

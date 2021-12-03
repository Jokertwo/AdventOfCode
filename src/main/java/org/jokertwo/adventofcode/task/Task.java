package org.jokertwo.adventofcode.task;

/**
 * @author PL
 */
public interface Task {
    /**
     * Solve problem/task/quest
     * 
     * @return return true only if task was successfully solved
     */
    boolean solve();


    /**
     * Name of task
     * 
     * @return return name of task
     */
    String getName();


    /**
     * Order of task
     * 
     * @return return order of task
     */
    int getOrder();


    /**
     * Result of task
     * 
     * @return if task was successfully solved, return result, otherwise return null
     */
    Object getResult();

}

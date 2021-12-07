package org.jokertwo.adventofcode.task.impl.task7;

import java.util.List;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;


@Component
public class Crab2 extends AbstractCrab {

    public Crab2(FileReader fileReader) {
        super(fileReader);
    }


    @Override
    public String getName() {
        return "Task 14";
    }


    @Override
    public int getOrder() {
        return 140;
    }


    @Override
    public int computeFuel(int position, List<Integer> crabs) {
        return crabs.stream()
            .map(crab -> {
                int distance = Math.abs(position - crab);
                return (distance * (distance + 1)) / 2;
            }).mapToInt(Integer::intValue)
            .sum();
    }
}

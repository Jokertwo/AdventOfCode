package org.jokertwo.adventofcode.task.impl.task7;

import java.util.List;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class Crab extends AbstractCrab {

    public Crab(FileReader fileReader) {
        super(fileReader);
    }


    public int computeFuel(int position, List<Integer> crabs) {
        return crabs.stream()
            .map(crab -> Math.abs(position - crab))
            .mapToInt(Integer::intValue)
            .sum();
    }


    @Override
    public String getName() {
        return "Task 7a";
    }


    @Override
    public int getOrder() {
        return 130;
    }

}

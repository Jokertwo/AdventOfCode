package org.jokertwo.adventofcode.task.impl.task7;

import java.io.IOException;
import java.util.List;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.AbstractTask;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractCrab extends AbstractTask {
    private static final String FILE = "task7/resource.txt";
    private Integer result;

    public AbstractCrab(FileReader fileReader) {
        super(FILE, fileReader);
    }


    @Override
    public boolean solve() {
        List<Integer> crabs;
        try {
            crabs = readIntLine();
        } catch (IOException e) {
            log.error("Unable to read from file: {}", FILE, e);
            return false;
        }

        int min = Integer.MAX_VALUE;
        int position = 0;
        int fuel = computeFuel(position, crabs);
        while (min > fuel) {
            min = fuel;
            position++;
            fuel = computeFuel(position, crabs);
        }
        result = min;// 340056
        return true;
    }


    @Override
    public Object getResult() {
        return result;
    }


    public abstract int computeFuel(int position, List<Integer> crabs);
}

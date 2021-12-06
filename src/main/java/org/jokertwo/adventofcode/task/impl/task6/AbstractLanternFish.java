package org.jokertwo.adventofcode.task.impl.task6;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.AbstractTask;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractLanternFish extends AbstractTask {

    private static final String FILE = "task6/resource.txt";
    private Long result;
    private final int numberOfDays;

    public AbstractLanternFish(int numberOfDays, FileReader fileReader) {
        super(FILE, fileReader);
        this.numberOfDays = numberOfDays;
        result = 0L;
    }


    @Override
    public boolean solve() {
        long[] days = new long[9];
        List<Integer> generation;
        try {
            generation = readFile()
                .stream()
                .map(s -> s.split(","))
                .flatMap(Stream::of)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Unable to read from file: {}", FILE, e);
            return false;
        }
        for (Integer gen : generation) {
            days[gen]++;
        }
        for (int i = 0; i < numberOfDays; i++) {
            int today = i % days.length;
            int index = (today + 7) % days.length;
            log.info("Index: {}, value: {}, addValue: {}", (today + 7) % days.length, days[index],
                days[today]);
            days[index] += days[today];
        }
        Arrays.stream(days).forEach(value -> result += value);

        return true;
    }


    @Override
    public Object getResult() {
        return result;
    }
}

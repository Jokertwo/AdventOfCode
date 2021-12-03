package org.jokertwo.adventofcode.task.impl.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.Task;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class DepthDiff2 implements Task {
    private static final String FILE = "task1/resource.txt";
    private final FileReader fileReader;
    private Integer result = null;

    public DepthDiff2(FileReader fileReader) {
        this.fileReader = fileReader;
    }


    @Override
    public boolean solve() {
        List<Integer> sums = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        List<Integer> third = new ArrayList<>();

        try (BufferedReader reader = fileReader.open(FILE)) {
            String line = reader.readLine();
            int iteration = 1;
            while (line != null) {
                int actual = Integer.parseInt(line.trim());
                // first iteration
                if (iteration == 1) {
                    addToList(first, actual, sums);
                }
                // second iteration
                else if (iteration == 2) {
                    addToList(first, actual, sums);
                    addToList(second, actual, sums);
                } else {
                    addToList(first, actual, sums);
                    addToList(second, actual, sums);
                    addToList(third, actual, sums);
                }
                iteration++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            log.error("Unable read from file: {}", FILE, e);
            return false;
        }
        result = countIncreases(sums);
        log.info("Sums total: {}, increases: {}", sums.size(), result);
        return true;
    }


    @Override
    public String getName() {
        return "Task 2";
    }


    @Override
    public int getOrder() {
        return 20;
    }


    @Override
    public Object getResult() {
        return result;
    }


    private void addToList(List<Integer> list, int value, List<Integer> sums) {
        if (list.size() >= 3) {
            throw new IllegalStateException("List can not contains more than 2 items");
        }
        list.add(value);
        if (list.size() == 3) {
            int sum = list.stream().mapToInt(Integer::intValue).sum();
            sums.add(sum);
            list.clear();
        }
    }


    private int countIncreases(List<Integer> sum) {
        int counter = 0;
        int previousDepth = Integer.MAX_VALUE;
        for (int item : sum) {
            counter = previousDepth < item ? counter + 1 : counter;
            previousDepth = item;
        }
        return counter;
    }

}

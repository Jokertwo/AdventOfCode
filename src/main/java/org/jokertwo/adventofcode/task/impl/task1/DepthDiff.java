package org.jokertwo.adventofcode.task.impl.task1;

import java.io.BufferedReader;
import java.io.IOException;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.Task;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * First quest in Advent of Code
 */
@Component
@Slf4j
public class DepthDiff implements Task {
    private static final String FILE = "task1/resource.txt";
    private final FileReader fileReader;
    private Integer result = null;

    public DepthDiff(FileReader fileReader) {
        this.fileReader = fileReader;
    }


    @Override
    public boolean solve() {
        int counter = 0;
        int previousDepth = Integer.MAX_VALUE;
        try (BufferedReader reader = fileReader.open(FILE)) {
            String line = reader.readLine();
            while (line != null) {
                int actual = Integer.parseInt(line.trim());
                counter = previousDepth < actual ? counter + 1 : counter;
                previousDepth = actual;
                line = reader.readLine();
            }
        } catch (IOException e) {
            log.error("Unable to read from file: {}", FILE, e);
            return false;
        }
        log.info("Result: {}", counter);
        result = counter;
        return true;
    }


    @Override
    public String getName() {
        return "Task 1a";
    }


    @Override
    public int getOrder() {
        return 10;
    }


    @Override
    public Object getResult() {
        return result;
    }
}

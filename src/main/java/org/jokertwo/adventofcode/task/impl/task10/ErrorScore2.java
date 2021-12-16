package org.jokertwo.adventofcode.task.impl.task10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class ErrorScore2 extends AbstractErrorScore {
    private final Stack<Integer> stack;

    public ErrorScore2(FileReader fileReader) {
        super(fileReader);
        stack = new Stack<>();
    }


    @Override
    protected void initValues() {
        BRACKET = 1;
        SQUARE_BRACKET = 2;
        CURLY_BRACKET = 3;
        DIAMOND_BRACKET = 4;
    }


    @Override
    public boolean solve() {
        List<String> lines;
        try {
            lines = readFile();
        } catch (IOException e) {
            log.error("Unable to read from file: {}", FILE, e);
            return false;
        }
        result = 0L;
        List<Long> results = new ArrayList<>();
        for (String line : lines) {
            long score = processLine(line);
            if (score > 0) {
                results.add(score);
            }

        }
        Collections.sort(results);
        result = results.get(results.size() / 2);
        return true;
    }


    @Override
    public String getName() {
        return "Task 10b";
    }


    @Override
    public int getOrder() {
        return 200;
    }


    @Override
    long processLine(String line) {
        stack.clear();

        for (char item : line.toCharArray()) {
            if (item == '(') {
                stack.push(item + 1);
            } else if (item == '[' ||
                    item == '{' ||
                    item == '<') {
                stack.push(item + 2);
            } else {
                int pop = stack.pop();
                if (pop != item) {
                    return 0;
                }
            }

        }
        long value = 0;
        StringBuilder end = new StringBuilder();
        if (!stack.empty()) {
            while (!stack.empty()) {
                int pop = stack.pop();
                value = value * 5;
                value += score.get(pop);
                end.append((char) pop);
            }
        }
        log.info("Line: {}, completion: {}", line, end);
        return value;
    }

}

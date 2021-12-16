package org.jokertwo.adventofcode.task.impl.task10;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class ErrorScore extends AbstractErrorScore {
    private final Stack<Integer> stack;


    public ErrorScore(FileReader fileReader) {
        super(fileReader);
        stack = new Stack<>();
    }

    @Override
    protected void initValues() {
        BRACKET = 3;
        SQUARE_BRACKET = 57;
        CURLY_BRACKET = 1197;
        DIAMOND_BRACKET = 25137;
    }

    @Override
    public String getName() {
        return "Task 19";
    }


    @Override
    public int getOrder() {
        return 190;
    }


    @Override
    long processLine(String line) {
        stack.clear();

        for (char item : line.toCharArray()) {
            if (item == '(') {
                stack.push(item + 1);
            }
            else if (item == '[' ||
                    item == '{' ||
                    item == '<') {
                stack.push(item + 2);
            }
            else {
                int pop = stack.pop();
                if (pop != item) {
                    log.warn("Invalid line: {}",line);
                    return score.get((int)item);
                }
            }

        }
        return 0;
    }
}

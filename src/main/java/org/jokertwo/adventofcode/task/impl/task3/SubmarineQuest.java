package org.jokertwo.adventofcode.task.impl.task3;

import java.io.BufferedReader;
import java.io.IOException;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.Task;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class SubmarineQuest implements Task {
    private final static String FILE = "task3/resource.txt";
    private final FileReader fileReader;
    private Integer result = null;

    public SubmarineQuest(FileReader fileReader) {
        this.fileReader = fileReader;
    }


    @Override
    public boolean solve() {
        Position position = new Position(0, 0);
        try (BufferedReader reader = fileReader.open(FILE)) {
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.trim().split("\\s");
                SubmarineMove move = SubmarineMove.valueOf(tokens[0].toUpperCase());
                int units = Integer.parseInt(tokens[1]);
                move.getMove().move(units, position);
                line = reader.readLine();
            }
            result = position.getDepth() * position.getHorizontal();
            log.info("Final position is: {}", position);
            log.info("Result of 3. task is: {} ", result);
            return true;
        } catch (IOException e) {
            log.error("Unable to read file: {}", FILE, e);
        }
        return false;
    }


    @Override
    public String getName() {
        return "Task 3";
    }


    @Override
    public int getOrder() {
        return 30;
    }


    @Override
    public Object getResult() {
        return result;
    }
}

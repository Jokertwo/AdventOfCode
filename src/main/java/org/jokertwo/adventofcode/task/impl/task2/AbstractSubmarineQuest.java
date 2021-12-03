package org.jokertwo.adventofcode.task.impl.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.dto.Position;
import org.jokertwo.adventofcode.task.Task;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractSubmarineQuest implements Task {

    private final String FILE;
    private final FileReader fileReader;
    private Integer result = null;
    private final Map<SubmarineMove, MoveFunction<Position>> functionMap;

    public AbstractSubmarineQuest(String FILE, FileReader fileReader) {
        this.FILE = FILE;
        this.fileReader = fileReader;
        functionMap = initFunctionMap();
    }


    protected abstract Map<SubmarineMove, MoveFunction<Position>> initFunctionMap();


    @Override
    public boolean solve() {
        Position position = new Position(0, 0, 0);
        try (BufferedReader reader = fileReader.open(FILE)) {
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.trim().split("\\s");
                SubmarineMove move = SubmarineMove.valueOf(tokens[0].toUpperCase());
                int units = Integer.parseInt(tokens[1]);
                functionMap.get(move).move(units, position);
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
    public Object getResult() {
        return result;
    }
}

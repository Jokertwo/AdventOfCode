package org.jokertwo.adventofcode.task.impl.task4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.AbstractTask;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class Bingo extends AbstractBingo {
    private static final String FILE = "task7/resource.txt";
    private Integer result;
    private List<Integer> markedValues;

    public Bingo(FileReader fileReader) {
        super(FILE, fileReader);
    }


    @Override
    public boolean solve() {
        List<String> allRows;
        try {
            allRows = readFile();
        } catch (IOException e) {
            log.error("Unable to read from file: {}", FILE, e);
            return false;
        }
        // first lines are numbers which will be marked
        String line = allRows.remove(0);
        markedValues = transFormToMarked(line);

        List<Board> boards = parseBoards(allRows);

        for (int value : markedValues) {
            for (Board board : boards) {
                // BINGO
                if (board.markValue(value)) {
                    log.info("BINGO!");
                    int sumUnmarked = board.sumAllUnmarked();
                    result = sumUnmarked * value;
                    log.info("Result is: {}", result);
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public String getName() {
        return "Task 7";
    }


    @Override
    public int getOrder() {
        return 70;
    }


    @Override
    public Object getResult() {
        return result;
    }
}

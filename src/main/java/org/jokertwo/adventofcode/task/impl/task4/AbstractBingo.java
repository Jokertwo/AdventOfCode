package org.jokertwo.adventofcode.task.impl.task4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.AbstractTask;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractBingo extends AbstractTask {
    private final String file;
    protected List<Integer> markedValues;

    public AbstractBingo(String file, FileReader fileReader) {
        super(file, fileReader);
        this.file = file;
    }


    @Override
    public boolean solve() {
        List<String> allRows;
        try {
            allRows = readFile();
        } catch (IOException e) {
            log.error("Unable to read from file: {}", file, e);
            return false;
        }
        // first lines are numbers which will be marked
        String line = allRows.remove(0);
        markedValues = transFormToMarked(line);

        List<Board> boards = parseBoards(allRows);
        return strategy(boards);
    }


    protected List<Integer> transFormToMarked(String line) {
        String[] tokens = line.trim().split(",");
        return Arrays.stream(tokens)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }


    protected List<Board> parseBoards(List<String> rows) {
        List<Board> boards = new ArrayList<>();
        List<List<Item>> number = null;
        for (String row : rows) {
            if (row.trim().isEmpty()) {
                if (number != null) {
                    boards.add(new Board(number));
                }
                number = new ArrayList<>();
                continue;
            }
            assert number != null;
            number.add(Arrays.stream(row.trim().split("\\s+"))
                .map(Integer::parseInt)
                .map(Item::new)
                .collect(Collectors.toList()));
        }
        boards.add(new Board(number));
        return boards;
    }


    protected abstract boolean strategy(List<Board> boards);
}

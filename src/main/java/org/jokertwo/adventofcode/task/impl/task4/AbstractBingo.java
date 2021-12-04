package org.jokertwo.adventofcode.task.impl.task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.AbstractTask;


public abstract class AbstractBingo extends AbstractTask {

    public AbstractBingo(String file, FileReader fileReader) {
        super(file, fileReader);
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
}

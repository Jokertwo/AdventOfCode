package org.jokertwo.adventofcode.task.impl.task4;

import java.util.List;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class BingoLastWin extends AbstractBingo {
    // same as for first part of task 4
    private static final String FILE = "task4/resource.txt";
    private Integer result;
    private Board lastWinBoard;

    public BingoLastWin(FileReader fileReader) {
        super(FILE, fileReader);
    }


    @Override
    protected boolean strategy(List<Board> boards) {
        for (int value : markedValues) {
            for (Board board : boards) {
                if (board.isWinning()) {
                    continue;
                }
                if (board.markValue(value)) {
                    log.info("BINGO!");
                    lastWinBoard = board;
                }
            }
        }
        return lastWinBoard != null;
    }


    @Override
    public String getName() {
        return "Task 8";
    }


    @Override
    public int getOrder() {
        return 80;
    }


    @Override
    public Object getResult() {
        int sumUnmarked = lastWinBoard.sumAllUnmarked();
        result = sumUnmarked * lastWinBoard.getWinNumber();
        return result;
    }
}

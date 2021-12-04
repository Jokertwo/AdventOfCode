package org.jokertwo.adventofcode.task.impl.task4;

import java.util.List;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class BingoFirstWin extends AbstractBingo {
    private static final String FILE = "task4/resource.txt";
    private Integer result;

    public BingoFirstWin(FileReader fileReader) {
        super(FILE, fileReader);
    }


    @Override
    protected boolean strategy(List<Board> boards) {
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

package org.jokertwo.adventofcode.task.impl.task9;

import java.io.IOException;
import java.util.List;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class SmokeBasin extends AbstractSmokeBasin {

    public SmokeBasin(FileReader fileReader) {
        super(fileReader);
        result = null;
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
        result = 0;
        int[][] matrix = convertToMatrix(lines);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (isLowest(i, j, matrix)) {
                    result += (matrix[i][j] + 1);
                }

            }

        }
        return true;
    }


    @Override
    public String getName() {
        return "Task 9a";
    }


    @Override
    public int getOrder() {
        return 170;
    }

}

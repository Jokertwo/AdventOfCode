package org.jokertwo.adventofcode.task.impl.task11;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class DumboOctopus2 extends AbstractDumboOctopus {

    public DumboOctopus2(FileReader fileReader) {
        super(fileReader);
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
        int[][] matrix = convertToMatrix(lines);

        int total = matrix.length * matrix[0].length;
        for (int i = 0; i < 10000; i++) {
            Stack<Index> toBeFlashed = new Stack<>();
            increase(matrix, toBeFlashed);
            int totalFlashOctopus = 0;
            while (!toBeFlashed.empty()) {
                totalFlashOctopus++;
                processIndex(toBeFlashed, matrix);
            }
            if (totalFlashOctopus == total) {
                result = i + 1;
                return true;
            }
        }
        return false;
    }


    @Override
    public String getName() {
        return "Task 11b";
    }


    @Override
    public int getOrder() {
        return 220;
    }
}

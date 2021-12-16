package org.jokertwo.adventofcode.task.impl.task11;

import java.util.List;
import java.util.Stack;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.AbstractTask;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractDumboOctopus extends AbstractTask {

    public static final String FILE = "task11/resource.txt";

    public AbstractDumboOctopus(FileReader fileReader) {
        super(FILE, fileReader);
    }


    public void processIndex(Stack<Index> toBeFlashed, int[][] matrix) {
        Index index = toBeFlashed.pop();

        int i;
        i = index.getI() - 1;
        // top
        if (i >= 0) {
            processRow(i, index.getJ(), matrix, toBeFlashed);
        }
        // center
        i = index.getI();
        processRow(i, index.getJ(), matrix, toBeFlashed);

        // bottom
        i = index.getI() + 1;
        if (matrix.length > i) {
            processRow(i, index.getJ(), matrix, toBeFlashed);
        }
    }


    public void processRow(int row, int column, int[][] matrix, Stack<Index> toBeFlashed) {
        int j;
        // left
        j = column - 1;
        if (j >= 0) {
            increaseAndValidate(row, j, matrix, toBeFlashed);
        }
        // center
        j = column;
        increaseAndValidate(row, j, matrix, toBeFlashed);

        // right
        j = column + 1;
        if (j < matrix[row].length) {
            increaseAndValidate(row, j, matrix, toBeFlashed);
        }
    }


    public void increaseAndValidate(int i, int j, int[][] matrix, Stack<Index> toBeFlashed) {
        if (matrix[i][j] == 0) {
            // already flashed
            return;
        }
        if (++matrix[i][j] > 9) {
            toBeFlashed.push(new Index(i, j));
            matrix[i][j] = 0;
        }
    }


    public void increase(int[][] matrix, List<Index> toBeFlashed) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j]++;
                if (matrix[i][j] > 9) {
                    toBeFlashed.add(new Index(i, j));
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

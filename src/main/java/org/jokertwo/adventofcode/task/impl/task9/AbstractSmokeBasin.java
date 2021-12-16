package org.jokertwo.adventofcode.task.impl.task9;

import java.util.List;
import java.util.stream.Stream;
import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.AbstractTask;

public abstract class AbstractSmokeBasin extends AbstractTask {

    protected static final String FILE = "task9/resource.txt";

    public AbstractSmokeBasin(FileReader fileReader) {
        super(FILE, fileReader);
    }


    protected boolean isLowest(int i, int j, int[][] matrix) {
        int value = matrix[i][j];
        int topValue, rightValue, bottomValue, leftValue;
        topValue = rightValue = bottomValue = leftValue = Integer.MAX_VALUE;
        // top
        if ((i - 1) >= 0) {
            topValue = matrix[i - 1][j];
        }
        // bottom
        if ((i + 1) < matrix.length) {
            bottomValue = matrix[i + 1][j];
        }
        // left
        if ((j - 1) >= 0) {
            leftValue = matrix[i][j - 1];
        }
        // right
        if ((j + 1) < matrix[i].length) {
            rightValue = matrix[i][j + 1];
        }
        return value < topValue &&
                value < rightValue &&
                value < bottomValue &&
                value < leftValue;

    }
}

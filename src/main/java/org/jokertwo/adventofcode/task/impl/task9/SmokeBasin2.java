package org.jokertwo.adventofcode.task.impl.task9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class SmokeBasin2 extends AbstractSmokeBasin {
    int test = Integer.MIN_VALUE;

    @Data
    private class Pair {
        final int i;
        final int j;
    }

    public SmokeBasin2(FileReader fileReader) {
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
        result = 0;
        List<Integer> basins = new ArrayList<>();
        int[][] matrix = convertToMatrix(lines);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (isLowest(i, j, matrix)) {
                    int basin = findBasin(i, j, matrix, 0, new ArrayList<>());
                    basins.add(basin);
                }

            }
        }
        Collections.sort(basins);
        if (basins.size() > 3) {
            result = basins.get(basins.size() - 1) * basins.get(basins.size() - 2) * basins.get(basins.size() - 3);
            return true;
        }
        log.error("Not enough basins, basins size: {}", basins.size());
        return false;
    }


    protected int findBasin(int i, int j, int[][] matrix, int basin, List<Pair> visited) {
        int value = matrix[i][j];
        visited.add(new Pair(i, j));
        if (value == 9) {
            return basin;
        }
        basin = basin + 1;
        // go top
        if ((i - 1) >= 0 && !visited.contains(new Pair(i - 1, j))) {
            basin = findBasin(i - 1, j, matrix, basin, visited);
        }
        // bottom
        if ((i + 1) < matrix.length && !visited.contains(new Pair(i + 1, j))) {
            basin = findBasin(i + 1, j, matrix, basin, visited);
        }
        // left
        if ((j - 1) >= 0 && !visited.contains(new Pair(i, j - 1))) {
            basin = findBasin(i, j - 1, matrix, basin, visited);
        }
        // right
        if ((j + 1) < matrix[i].length && !visited.contains(new Pair(i, j + 1))) {
            basin = findBasin(i, j + 1, matrix, basin, visited);
        }
        test = Math.max(test, visited.size());

        return basin;
    }


    @Override
    public String getName() {
        return "Task 18";
    }


    @Override
    public int getOrder() {
        return 180;
    }
}

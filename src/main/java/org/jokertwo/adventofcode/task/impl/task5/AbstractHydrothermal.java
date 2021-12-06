package org.jokertwo.adventofcode.task.impl.task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.Task;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractHydrothermal implements Task {
    @FunctionalInterface
    interface DirectionDraw {
        void draw(int x1, int y1, int x2, int y2, int[][] field);
    }

    private static final String FILE = "task5/resource.txt";
    private final FileReader fileReader;
    private Integer result;
    private final Map<Direction, HydrothermalVenture.DirectionDraw> functions;

    public AbstractHydrothermal(FileReader fileReader) {
        this.fileReader = fileReader;
        functions = initFunctions();
    }


    public boolean solve() {
        int maxX = 0;
        int maxY = 0;
        int minX = 0;
        int minY = 0;
        List<Integer[]> coordinates = new ArrayList<>();
        try (BufferedReader reader = fileReader.open(FILE)) {
            String line = reader.readLine();
            while (line != null) {
                Integer[] coordinate = parseLine(line);
                maxX = findMax(maxX, coordinate[0], coordinate[2]);
                maxY = findMax(maxY, coordinate[1], coordinate[3]);
                minX = findMin(minX, coordinate[0], coordinate[2]);
                minY = findMin(minY, coordinate[1], coordinate[3]);

                coordinates.add(coordinate);
                line = reader.readLine();

            }
        } catch (IOException e) {
            log.error("Unable to read file: {}", FILE, e);
            return false;
        }
        int maxValue = Math.max(maxX + 1, maxY + 1);
        int[][] field = new int[maxValue][maxValue];

        coordinates.forEach(coordinate -> {
            Direction direction = resolveDirection(coordinate[0],
                coordinate[1],
                coordinate[2],
                coordinate[3]);
            functions.getOrDefault(direction, (x1, y1, x2, y2, field1) -> {
            }).draw(coordinate[0],
                coordinate[1],
                coordinate[2],
                coordinate[3], field);
        });
        result = 0;
        for (int[] column : field) {
            for (int row : column) {
                if (row > 1) {
                    result++;
                }
            }
        }
        return true;
    }


    abstract Map<Direction, HydrothermalVenture.DirectionDraw> initFunctions();


    @Override
    public Object getResult() {
        return result;
    }


    private Direction resolveDirection(int x1, int y1, int x2, int y2) {
        log.info("Processing x1: {}, y1: {}, x2: {}, y2: {}", x1, y1, x2, y2);
        if (x1 == x2) {
            if (y1 < y2) {
                return Direction.DOWN;
            }
            return Direction.UP;

        } else if (y1 == y2) {
            if (x1 < x2) {
                return Direction.RIGHT;
            }
            return Direction.LEFT;
        } else if (x1 < x2) {
            if (y1 < y2) {
                return Direction.DOWN_RIGHT;
            }
            return Direction.UP_RIGHT;
        } else {
            if (y1 < y2) {
                return Direction.DOWN_LEFT;
            }
            return Direction.UP_LEFT;
        }
    }


    private Integer[] parseLine(String line) {
        Integer[] coordinates = new Integer[4];
        String[] tokens = line.split("->");
        String[] first = tokens[0].split(",");
        String[] second = tokens[1].split(",");
        coordinates[0] = Integer.parseInt(first[0].trim());
        coordinates[1] = Integer.parseInt(first[1].trim());
        coordinates[2] = Integer.parseInt(second[0].trim());
        coordinates[3] = Integer.parseInt(second[1].trim());
        return coordinates;
    }


    private int findMax(int a, int... other) {
        List<Integer> values = new ArrayList<>();
        values.add(a);
        Arrays.stream(other).forEach(values::add);
        return Collections.max(values);
    }


    private int findMin(int a, int... other) {
        List<Integer> values = new ArrayList<>();
        values.add(a);
        Arrays.stream(other).forEach(values::add);
        return Collections.min(values);
    }
}

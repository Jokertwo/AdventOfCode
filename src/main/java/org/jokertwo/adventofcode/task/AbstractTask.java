package org.jokertwo.adventofcode.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jokertwo.adventofcode.common.FileReader;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractTask implements Task {
    @Getter
    private final String file;
    @Getter
    private final FileReader fileReader;
    public Integer result;

    public AbstractTask(String file, FileReader fileReader) {
        this.file = file;
        this.fileReader = fileReader;
    }


    public List<String> readFile() throws IOException {
        List<String> rows = new ArrayList<>();
        try (BufferedReader reader = fileReader.open(file)) {
            String line = reader.readLine();
            while (line != null) {
                rows.add(line.trim());
                line = reader.readLine();
            }
        } catch (IOException e) {
            log.error("Unable to read file: {}", file, e);
            throw e;
        }
        return rows;
    }


    public List<Integer> readIntLine() throws IOException {
        List<Integer> numbers;
        try {
            numbers = readFile()
                .stream()
                .map(s -> s.split(","))
                .flatMap(Stream::of)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Unable to read from file: {}", file, e);
            throw e;
        }
        return numbers;
    }


    public int[][] convertToMatrix(List<String> lines) {
        int[][] matrix = new int[lines.size()][findSize(lines.get(0))];
        for (int i = 0; i < lines.size(); i++) {
            int[] row = Stream.of(lines.get(i).split(""))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .toArray();
            matrix[i] = row;
        }
        return matrix;
    }


    protected int findSize(String line) {
        return line.split("").length;
    }

    public void print(int[][] matrix){
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    @Override
    public Object getResult() {
        return result;
    }

}

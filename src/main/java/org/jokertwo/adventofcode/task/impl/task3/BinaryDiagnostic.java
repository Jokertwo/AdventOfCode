package org.jokertwo.adventofcode.task.impl.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.Task;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class BinaryDiagnostic implements Task {

    private final static String FILE = "task5/resource.txt";
    private final FileReader fileReader;
    private Integer result;

    public BinaryDiagnostic(FileReader fileReader) {
        this.fileReader = fileReader;
    }


    @Override
    public boolean solve() {
        int rowCounter = 0;
        Map<Integer, Integer> bitCounter = new HashMap<>();

        try (BufferedReader reader = fileReader.open(FILE)) {
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                for (int i = 0; i < line.length(); i++) {
                    char bit = line.charAt(i);
                    if (bit == '1') {
                        int counts = bitCounter.getOrDefault(i, 0);
                        bitCounter.put(i, ++counts);
                    }
                }
                rowCounter++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            log.error("Unable to read from file: {}", FILE, e);
            return false;
        }
        StringBuilder gamaRateBuilder = new StringBuilder();
        StringBuilder epsilonRateBuilder = new StringBuilder();
        for (int occurrence : bitCounter.values()) {
            String bit = resolveBit(occurrence, rowCounter);
            gamaRateBuilder.append(bit);
            epsilonRateBuilder.append(bit.equals("1") ? "0" : "1");
        }
        int gamaRate = Integer.parseInt(gamaRateBuilder.toString(), 2);
        int epsilonRate = Integer.parseInt(epsilonRateBuilder.toString(), 2);
        result = gamaRate * epsilonRate;
        log.info("Gama rate binary number: {}, decimal: {}", gamaRateBuilder, gamaRate);
        log.info("Epsilon rate binary number: {}, decimal: {}", epsilonRateBuilder, epsilonRate);
        log.info("Result is: {}", result);
        return true;
    }


    private String resolveBit(int occurrence, int totalRows) {
        return totalRows / 2 < occurrence ? "1" : "0";
    }


    @Override
    public String getName() {
        return "Task 5";
    }


    @Override
    public int getOrder() {
        return 50;
    }


    @Override
    public Object getResult() {
        return result;
    }
}

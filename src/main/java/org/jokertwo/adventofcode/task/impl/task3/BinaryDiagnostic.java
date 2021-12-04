package org.jokertwo.adventofcode.task.impl.task3;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class BinaryDiagnostic extends AbstractBinaryDiagnostic {

    private final static String FILE = "task3/resource.txt";
    private Integer result;

    public BinaryDiagnostic(FileReader fileReader) {
        super(FILE, fileReader);
    }


    @Override
    public boolean solve() {
        Map<Integer, Integer> bitCounter;
        List<String> rows;
        try {
            rows = readFile();
            bitCounter = evaluateBits(rows);
        } catch (IOException e) {
            log.error("Unable to read from file: {}", FILE, e);
            return false;
        }
        StringBuilder gamaRateBuilder = new StringBuilder();
        StringBuilder epsilonRateBuilder = new StringBuilder();
        for (int occurrence : bitCounter.values()) {
            String bit = resolveBit(occurrence, rows.size(), true);
            gamaRateBuilder.append(bit);
            epsilonRateBuilder.append(bit.equals("1") ? "0" : "1");
        }
        int gamaRate = Integer.parseInt(gamaRateBuilder.toString(), 2);
        int epsilonRate = Integer.parseInt(epsilonRateBuilder.toString(), 2);
        result = gamaRate * epsilonRate;
        log.info("Gama rate binary number: {}, decimal: {}", gamaRateBuilder, gamaRate);
        log.info("Epsilon rate binary number: {}, decimal: {}", epsilonRateBuilder, epsilonRate);
        log.info("Result is: {}", result);// 2595824
        return true;
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

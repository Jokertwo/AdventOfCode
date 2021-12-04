package org.jokertwo.adventofcode.task.impl.task3;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class OxygenBinaryDiagnostic extends AbstractBinaryDiagnostic {

    private final static String FILE = "task6/resource.txt";
    private Integer result;

    public OxygenBinaryDiagnostic(FileReader fileReader) {
        super(FILE, fileReader);
    }


    @Override
    public boolean solve() {
        List<String> allRows;
        try {
            allRows = readFile();
        } catch (IOException e) {
            log.error("Unable to read from file: {}", FILE, e);
            return false;
        }
        String oxygen = findValue(allRows, 0, true);
        String co2 = findValue(allRows, 0, false);
        log.info("Oxygen binary value: {}, co2 binary value: {}", oxygen, co2);
        int oxygenInt = Integer.parseInt(oxygen, 2);
        int co2Int = Integer.parseInt(co2, 2);
        result = oxygenInt * co2Int;
        log.info("Result of Oxygen Binary diagnostic is {}", result);
        return true;
    }


    private String findValue(List<String> rows, int index, boolean mostCommon) {
        if (rows.size() == 1) {
            return rows.get(0);
        }
        Map<Integer, Integer> bitCounter = evaluateBits(rows);

        int ocur = bitCounter.getOrDefault(index, 0);

        String bit = resolveBit(ocur, rows.size(), mostCommon);
        List<String> filtered = rows.stream()
            .filter(item -> item.charAt(index) == bit.charAt(0))
            .collect(Collectors.toList());
        return findValue(filtered, index + 1, mostCommon);
    }


    @Override
    public String getName() {
        return "Task 6";
    }


    @Override
    public int getOrder() {
        return 60;
    }


    @Override
    public Object getResult() {
        return result;
    }
}

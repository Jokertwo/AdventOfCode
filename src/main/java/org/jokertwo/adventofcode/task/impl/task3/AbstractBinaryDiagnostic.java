package org.jokertwo.adventofcode.task.impl.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.AbstractTask;


public abstract class AbstractBinaryDiagnostic extends AbstractTask {

    public AbstractBinaryDiagnostic(String file, FileReader fileReader) {
        super(file, fileReader);
    }


    public Map<Integer, Integer> evaluateBits(List<String> rows) {
        Map<Integer, Integer> bitCounter = new HashMap<>();
        for (String row : rows) {
            for (int i = 0; i < row.length(); i++) {
                char bit = row.charAt(i);
                if (bit == '1') {
                    int counts = bitCounter.getOrDefault(i, 0);
                    bitCounter.put(i, ++counts);
                }
            }
        }
        return bitCounter;
    }


    public String resolveBit(int occurrence, int totalRows, boolean mostCommon) {
        int bitZero = totalRows - occurrence;
        if(mostCommon){
            return occurrence >= bitZero? "1" : "0";
        }
        return occurrence < bitZero ? "1" : "0";
    }
}

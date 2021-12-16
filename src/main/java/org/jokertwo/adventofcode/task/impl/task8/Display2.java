package org.jokertwo.adventofcode.task.impl.task8;

import java.io.IOException;
import java.util.List;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.AbstractTask;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class Display2 extends AbstractTask {
    private static final String FILE = "task8/resource.txt";

    public Display2(FileReader fileReader) {
        super(FILE, fileReader);
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
        lines.forEach(this::processLine);
        return true;
    }


    private void processLine(String line) {
        String[] parts = line.split("\\|");
        String[] tokens = parts[1].split("\\s");
        for (String token : tokens) {
            if (token.length() == 2 ||
                    token.length() == 3 ||
                    token.length() == 4 ||
                    token.length() == 7) {
                result++;
            }
        }
    }


    @Override
    public String getName() {
        return "Task 8a";
    }


    @Override
    public int getOrder() {
        return 160;
    }
}

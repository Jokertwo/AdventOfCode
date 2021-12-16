package org.jokertwo.adventofcode.task.impl.task10;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.AbstractTask;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractErrorScore extends AbstractTask {

    protected static final String FILE = "task10/resource.txt";

    protected static final int INCOMPLETE = 0;
    protected int BRACKET;
    protected int SQUARE_BRACKET;
    protected int CURLY_BRACKET;
    protected int DIAMOND_BRACKET;

    protected final Map<Integer, Integer> score;
    protected Long result;

    public AbstractErrorScore(FileReader fileReader) {
        super(FILE, fileReader);
        initValues();
        score = initScore();
    }

    protected abstract void initValues();

    private Map<Integer, Integer> initScore() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(125, CURLY_BRACKET);
        map.put(93, SQUARE_BRACKET);
        map.put(41, BRACKET);
        map.put(62, DIAMOND_BRACKET);
        return map;
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
        result = 0L;
        for (String line : lines) {
            result += processLine(line);
        }
        return true;
    }

    @Override
    public Object getResult() {
        return result;
    }


    abstract long processLine(String line);
}

package org.jokertwo.adventofcode.task.impl.task8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.task.AbstractTask;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class Display extends AbstractTask {
    private static final String FILE = "task8/resource.txt";
    private Integer result;

    public Display(FileReader fileReader) {
        super(FILE, fileReader);
        result = null;
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
        String[] tokens = parts[0].split("\\s");

        Map<String, String> dictionary = initDic(List.of(tokens));
        List<String> sixLong = Stream.of(parts[0].split("\\s")).filter(s -> s.length() == 6)
            .collect(Collectors.toList());
        List<String> fiveLong = Stream.of(parts[0].split("\\s")).filter(s -> s.length() == 5)
            .collect(Collectors.toList());

        // find three
        dictionary.put("3", find(fiveLong, dictionary.get("7"),true));
        // find six
        dictionary.put("6", find(sixLong, dictionary.get("7"),false));
        // find nine
        dictionary.put("9", find(sixLong, dictionary.get("3"),true));
        // add 0
        dictionary.put("0", sortString(sixLong.get(0)));


        int a = matchNumber(fiveLong.get(0),dictionary.get("4"));
        int b = matchNumber(fiveLong.get(1),dictionary.get("4"));
        if(a > b){
            dictionary.put("5",sortString(fiveLong.get(0)));
            dictionary.put("2",sortString(fiveLong.get(1)));
        }else {
            dictionary.put("5",sortString(fiveLong.get(1)));
            dictionary.put("2",sortString(fiveLong.get(0)));
        }
        dictionary = convert(dictionary);
        StringBuilder number = new StringBuilder();
        for(String item : parts[1].split("\\s")){
            if(item.isBlank()){
                continue;
            }
            number.append(dictionary.get(sortString(item)));
        }
        result += Integer.parseInt(number.toString());
    }


    private Map<String,String> convert(Map<String,String> dictionary){
        Map<String,String> converted = new HashMap<>();
        for(Map.Entry<String,String> entry : dictionary.entrySet()){
            converted.put(entry.getValue(),entry.getKey());
        }
        return converted;
    }

    private String find(List<String> fiveLong, String mask, boolean invert) {
        int toBeRemoved = -1;
        String value = "";
        for (int i = 0; i < fiveLong.size(); i++) {
            boolean contains = invert == containsAll(fiveLong.get(i), mask);
            if (contains) {
                toBeRemoved = i;
                value = sortString(fiveLong.get(i));
                break;
            }
        }
        fiveLong.remove(toBeRemoved);
        return sortString(value);
    }


    private boolean containsAll(String token, String mask) {
        List<Character> tokenChar = convertToList(token);
        List<Character> sevenChar = convertToList(mask);
        return tokenChar.containsAll(sevenChar);
    }


    private List<Character> convertToList(String item) {
        List<Character> characters = new ArrayList<>();
        for (char i : item.toCharArray()) {
            characters.add(i);
        }
        return characters;
    }


    private Map<String, String> initDic(List<String> tokens) {
        Map<String, String> dictionary = new HashMap<>();

        for (String token : tokens) {
            String key = sortString(token);
            int length = key.length();
            switch (length) {
                case 2:
                    dictionary.put("1", key);
                    break;
                case 3:
                    dictionary.put("7", key);
                    break;
                case 4:
                    dictionary.put("4", key);
                    break;
                case 7:
                    dictionary.put("8", key);
                    break;
            }
        }
        return dictionary;
    }


    private String sortString(String string) {
        char[] arr = string.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
    }


    private int matchNumber(String token, String mask) {
        List<Character> tokenChar = convertToList(token);
        List<Character> maskChar = convertToList(mask);
        int counter = 0;
        for (char item : maskChar) {
            if (tokenChar.contains(item)) {
                counter++;
            }
        }
        return counter;
    }




    @Override
    public String getName() {
        return "Task 15:";
    }


    @Override
    public int getOrder() {
        return 150;
    }


    @Override
    public Object getResult() {
        return result;
    }
}

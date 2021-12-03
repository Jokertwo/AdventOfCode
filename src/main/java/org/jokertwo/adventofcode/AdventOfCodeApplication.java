package org.jokertwo.adventofcode;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jokertwo.adventofcode.task.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
public class AdventOfCodeApplication {

    public AdventOfCodeApplication(List<Task> tasks) {
        Map<Integer, String> results = new TreeMap<>();
        tasks.forEach(task -> {
            if (results.containsKey(task.getOrder())) {
                log.error("Task with order key: {} already exist, name: {}", task.getOrder(), task.getName());
                throw new IllegalStateException("Duplicate key");
            }
            String resultSentence = String.format("%s , result: %s", task.getName(),
                task.solve() ? task.getResult() : "ERROR");
            results.put(task.getOrder(), resultSentence);

        });
        results.values().forEach(log::info);

    }


    public static void main(String[] args) {
        SpringApplication.run(AdventOfCodeApplication.class, args);
    }

}

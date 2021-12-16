package org.jokertwo.adventofcode.task.impl.task6;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class LanternFish2 extends AbstractLanternFish {
    public LanternFish2(FileReader fileReader) {
        super(256, fileReader);
    }


    @Override
    public String getName() {
        return "Task 6b";
    }


    @Override
    public int getOrder() {
        return 120;
    }
}

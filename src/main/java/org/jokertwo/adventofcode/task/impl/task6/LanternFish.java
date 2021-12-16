package org.jokertwo.adventofcode.task.impl.task6;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class LanternFish extends AbstractLanternFish {

    public LanternFish(FileReader fileReader) {
        super(80, fileReader);
    }


    @Override
    public String getName() {
        return "Task 6a";
    }


    @Override
    public int getOrder() {
        return 110;
    }

}

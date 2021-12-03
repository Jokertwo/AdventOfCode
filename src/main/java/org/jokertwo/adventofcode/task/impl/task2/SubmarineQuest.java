package org.jokertwo.adventofcode.task.impl.task2;

import java.util.HashMap;
import java.util.Map;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.dto.Position;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class SubmarineQuest extends AbstractSubmarineQuest {
    private final static String FILE = "task3/resource.txt";

    public SubmarineQuest(FileReader fileReader) {
        super(FILE, fileReader);
    }


    @Override
    protected Map<SubmarineMove, MoveFunction<Position>> initFunctionMap() {
        Map<SubmarineMove, MoveFunction<Position>> functionMap = new HashMap<>();
        functionMap.put(SubmarineMove.DOWN, (move, position) -> position.setDepth(position.getDepth() + move));
        functionMap.put(SubmarineMove.UP, (move, position) -> position.setDepth(position.getDepth() - move));
        functionMap.put(SubmarineMove.FORWARD,
            (move, position) -> position.setHorizontal(position.getHorizontal() + move));
        return functionMap;
    }


    @Override
    public String getName() {
        return "Task 3";
    }


    @Override
    public int getOrder() {
        return 30;
    }

}

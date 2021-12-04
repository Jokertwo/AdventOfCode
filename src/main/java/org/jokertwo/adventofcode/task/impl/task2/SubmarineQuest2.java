package org.jokertwo.adventofcode.task.impl.task2;

import java.util.HashMap;
import java.util.Map;

import org.jokertwo.adventofcode.common.FileReader;
import org.jokertwo.adventofcode.dto.Position;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class SubmarineQuest2 extends AbstractSubmarineQuest {
    private final static String FILE = "task2/resource.txt";

    public SubmarineQuest2(FileReader fileReader) {
        super(FILE, fileReader);
    }


    @Override
    protected Map<SubmarineMove, MoveFunction<Position>> initFunctionMap() {
        Map<SubmarineMove, MoveFunction<Position>> map = new HashMap<>();
        map.put(SubmarineMove.UP, (move, position) -> position.setAim(position.getAim() - move));
        map.put(SubmarineMove.DOWN, (move, position) -> position.setAim(position.getAim() + move));
        map.put(SubmarineMove.FORWARD, (move, position) -> {
            position.setHorizontal(position.getHorizontal() + move);
            position.setDepth(position.getDepth() + position.getAim() * move);
            return position;
        });
        return map;
    }


    @Override
    public String getName() {
        return "Task 4";
    }


    @Override
    public int getOrder() {
        return 40;
    }

}

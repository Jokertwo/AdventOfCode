package org.jokertwo.adventofcode.task.impl.task5;

import java.util.HashMap;
import java.util.Map;

import org.jokertwo.adventofcode.common.FileReader;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class HydrothermalVenture2 extends AbstractHydrothermal {

    public HydrothermalVenture2(FileReader fileReader) {
        super(fileReader);
    }


    public Map<Direction, DirectionDraw> initFunctions() {
        Map<Direction, DirectionDraw> drawMap = new HashMap<>();
        drawMap.put(Direction.DOWN, (x1, y1, x2, y2, field) -> {
            for (int i = y1; i <= y2; i++) {
                field[x1][i]++;
            }
        });
        drawMap.put(Direction.UP, (x1, y1, x2, y2, field) -> {
            for (int i = y1; i >= y2; i--) {
                field[x1][i]++;
            }
        });
        drawMap.put(Direction.DOWN_LEFT, (x1, y1, x2, y2, field) -> {
            for (int i = 0; i + y1 <= y2; i++) {
                field[x1 - i][y1 + i]++;
            }
        });
        drawMap.put(Direction.DOWN_RIGHT, (x1, y1, x2, y2, field) -> {
            for (int i = 0; i + y1 <= y2; i++) {
                field[x1 + i][y1 + i]++;
            }
        });
        drawMap.put(Direction.LEFT, (x1, y1, x2, y2, field) -> {
            for (int i = x2; i <= x1; i++) {
                field[i][y1]++;
            }
        });
        drawMap.put(Direction.RIGHT, (x1, y1, x2, y2, field) -> {
            for (int i = x1; i <= x2; i++) {
                field[i][y1]++;
            }
        });
        drawMap.put(Direction.UP_LEFT, (x1, y1, x2, y2, field) -> {
            for (int i = 0; i + y2 <= y1; i++) {
                field[x2 + i][y2 + i]++;
            }
        });
        drawMap.put(Direction.UP_RIGHT, (x1, y1, x2, y2, field) -> {
            for (int i = 0; i + x1 <= x2; i++) {
                field[x1 + i][y1 - i]++;
            }
        });

        return drawMap;
    }


    @Override
    public String getName() {
        return "Task 5b";
    }


    @Override
    public int getOrder() {
        return 100;
    }
    
}

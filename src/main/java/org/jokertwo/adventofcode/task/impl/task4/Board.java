package org.jokertwo.adventofcode.task.impl.task4;

import java.util.List;

import lombok.Getter;


@Getter
public class Board {
    private final List<List<Item>> board;

    public Board(List<List<Item>> board) {
        this.board = board;
    }


    public boolean markValue(int value) {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                Item number = board.get(i).get(j);
                if (number.getNumber() == value) {
                    number.setMarked(true);
                    if (testLine(i) || testRow(j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private boolean testLine(int rowIndex) {
        for (int i = 0; i < board.get(rowIndex).size(); i++) {
            if (!board.get(rowIndex).get(i).isMarked()) {
                return false;
            }
        }
        return true;
    }


    private boolean testRow(int columnIndex) {
        for (List<Item> items : board) {
            if (!items.get(columnIndex).isMarked()) {
                return false;
            }
        }
        return true;
    }


    public int sumAllUnmarked() {
        int sum = 0;
        for (List<Item> items : board) {
            for (Item item : items) {
                if (!item.isMarked()) {
                    sum += item.getNumber();
                }
            }
        }
        return sum;
    }

}

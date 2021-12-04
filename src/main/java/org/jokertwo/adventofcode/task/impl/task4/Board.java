package org.jokertwo.adventofcode.task.impl.task4;

import java.util.List;

import lombok.Getter;


@Getter
public class Board {
    private final List<List<Item>> board;
    private boolean winning;
    private int winNumber;

    public Board(List<List<Item>> board) {
        this.board = board;
        this.winning = false;
    }


    public boolean markValue(int value) {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                Item number = board.get(i).get(j);
                if (number.getNumber() == value) {
                    number.setMarked(true);
                    if (testLine(i) || testRow(j)) {
                        this.winning = true;
                        this.winNumber = value;
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

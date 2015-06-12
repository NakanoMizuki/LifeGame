package jp.ac.titech.psg.nakano.lifegame.model;

import static jp.ac.titech.psg.nakano.lifegame.Const.*;

public class Area {
    private final Cell[][] cells;

    public Area() {
        cells = new Cell[CELL_RAW][CELL_COLUMN];
        for (int i = 0; i < CELL_RAW; i++) {
            for (int j = 0; j < CELL_COLUMN; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void reset() {
        for (Cell[] raw : cells) {
            for (Cell c : raw) {
                c.reset();
            }
        }
    }

    public void changeStatus(int raw, int column) {
        cells[raw][column].changeStatus();
    }

    public void update() {
        for (int i = 0; i < CELL_RAW; i++) {
            for (int j = 0; j < CELL_COLUMN; ++j) {
                cells[i][j].adaptRule(getNumOfAroundAliveCells(i, j));
            }
        }
        for (Cell[] raw : cells) {
            for (Cell c : raw) {
                c.update();
            }
        }
    }

    public boolean isAlive(int raw, int column) {
        return cells[raw][column].isAlive();
    }

    private int getNumOfAroundAliveCells(int raw, int column) {
        int count = 0;
        count += getNumOfNeighboringAliveCells(raw, column);
        if ((raw - 1) >= 0) {
            count += getNumOfNeighboringAliveCells(raw - 1, column);
            if (isAlive(raw - 1, column)) {
                count++;
            }
        }
        if ((raw + 1) < CELL_RAW) {
            count += getNumOfNeighboringAliveCells(raw + 1, column);
            if (isAlive(raw + 1, column)) {
                count++;
            }
        }

        assert count < 9;
        return count;
    }

    private int getNumOfNeighboringAliveCells(int raw, int column) {
        int count = 0;
        if ((column - 1) >= 0 && isAlive(raw, column - 1)) {
            count++;
        }
        if ((column + 1) < CELL_COLUMN && isAlive(raw, column + 1)) {
            count++;
        }
        return count;
    }

}

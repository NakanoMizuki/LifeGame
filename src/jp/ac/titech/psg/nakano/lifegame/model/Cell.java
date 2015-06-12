package jp.ac.titech.psg.nakano.lifegame.model;

import static jp.ac.titech.psg.nakano.lifegame.Const.*;

class Cell {
    private boolean status;
    private boolean next;

    public Cell() {
        status = DEAD;
    }

    boolean isAlive() {
        return (status == ALIVE) ? true : false;
    }

    void reset() {
        status = DEAD;
        next = DEAD;
    }

    void changeStatus() {
        if (status == ALIVE) {
            status = DEAD;
        } else {
            status = ALIVE;
        }
    }

    void adaptRule(int numOfAlive) {
        if (status == ALIVE) {
            if (numOfAlive == 2 || numOfAlive == 3) {
                next = ALIVE; // alive
            } else if (numOfAlive <= 1) {
                next = DEAD; // depopulation
            } else {
                assert numOfAlive >= 4;
                next = DEAD; // overpopulation
            }
        } else {
            if (numOfAlive == 3) {
                next = ALIVE; // birth
            } else {
                next = DEAD;
            }
        }
    }

    void update() {
        status = next;
    }

}

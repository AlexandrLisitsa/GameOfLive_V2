package game.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell extends JPanel {

    private final static Random RANDOM = new Random();
    private final List<Cell> neighbors = new ArrayList<>();
    private Color nextState;

    public Cell() {
        int aliveChance = RANDOM.nextInt(100);
        if (aliveChance <= 30) {
            this.setBackground(Color.GREEN);
        } else {
            this.setBackground(Color.WHITE);
        }
    }

    public void defineNextState() {
        int aliveNeighbors = (int) neighbors.stream().filter(Cell::isAlive).count();

        if (this.isAlive()) {
            switch (aliveNeighbors) {
                case 2:
                case 3:
                    this.nextState = Color.GREEN;
                    break;
                default:
                    this.nextState = Color.WHITE;
            }
        } else {
            if (aliveNeighbors == 3) {
                this.nextState = Color.GREEN;
            } else {
                this.nextState = Color.WHITE;
            }
        }
    }

    public void applyState() {
        this.setBackground(nextState);
    }

    public boolean isAlive() {
        return this.getBackground() == Color.GREEN;
    }

    public void addNeighbor(Cell neighbor) {
        this.neighbors.add(neighbor);
    }

    public void setPosition(int x, int y) {
        this.setBounds(x, y, 10, 10);
    }
}

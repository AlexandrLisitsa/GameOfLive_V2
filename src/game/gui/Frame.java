package game.gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Frame extends JFrame {

    private final Map<Integer, Cell> cells = new HashMap<>();

    public Frame(int rows) throws HeadlessException, InterruptedException {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Game of Life");
        this.setLayout(null);
        fillCells(rows);
        defineNeighbors(rows);

        this.setVisible(true);

        // TODO: 23.03.2022 I was too lazy to implement the end of the game validations
        while (true) {
            Thread.sleep(1000);
            cells.values().forEach(Cell::defineNextState);
            cells.values().forEach(Cell::applyState);
            this.repaint();
        }
    }

    private void defineNeighbors(int rows) {
        for (int currentCellIndex = 0; currentCellIndex < cells.size(); currentCellIndex++) {
            int east = currentCellIndex + 1 - ((currentCellIndex % rows) / (rows - 1)) * rows;
            int west = (currentCellIndex - 1) + (((currentCellIndex + rows - 1) % rows) / (rows - 1)) * rows;
            int south = (currentCellIndex + rows) % (rows * rows);
            int north = (currentCellIndex - rows) % (rows * rows);
            if (north < 0) north += (rows * rows);

            int nw = Math.floorMod(west - rows, rows * rows);
            int ne = Math.floorMod(east - rows, rows * rows);
            int se = Math.floorMod(east + rows, rows * rows);
            int sw = Math.floorMod(west + rows, rows * rows);

            Cell currentCell = cells.get(currentCellIndex);

            currentCell.addNeighbor(cells.get(east));
            currentCell.addNeighbor(cells.get(west));
            currentCell.addNeighbor(cells.get(south));
            currentCell.addNeighbor(cells.get(north));
            currentCell.addNeighbor(cells.get(nw));
            currentCell.addNeighbor(cells.get(ne));
            currentCell.addNeighbor(cells.get(se));
            currentCell.addNeighbor(cells.get(sw));
        }
    }

    private void fillCells(int rows) {
        this.setSize((rows * 10) + 15, (rows * 10) + 40);
        this.setLocationRelativeTo(null);

        int cellIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < rows; col++) {
                Cell cell = new Cell();
                cell.setPosition(col * 10, row * 10);
                cells.put(cellIndex, cell);
                this.add(cell);
                cellIndex++;
            }
        }
    }

}
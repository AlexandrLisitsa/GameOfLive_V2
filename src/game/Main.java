package game;

import game.gui.Frame;

public class Main {

    public static void main(String[] args) {
        try {
            new Frame(64);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

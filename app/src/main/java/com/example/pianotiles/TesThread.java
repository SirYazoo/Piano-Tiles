package com.example.pianotiles;

import java.util.Random;

public class TesThread implements Runnable {
    protected Thread thread;
    protected UIThreadedWrapper uiThreadedWrapper;
    private Random random;
    private int newX;
    private int newY;
    protected int maxWidth;
    protected int maxHeight;
    private Tile tile;

    public TesThread(UIThreadedWrapper uiThreadedWrapper, int maxWidth, int maxHeight) {
        this.uiThreadedWrapper = uiThreadedWrapper;
        this.thread = new Thread(this);
        this.random = new Random();
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public void drawTile() {
        this.thread.start();
    }

    @Override
    public void run() {
        this.newX = this.random.nextInt(20) - 10;
        this.newY = this.random.nextInt(20) - 10;
        int x = this.maxWidth / 2;
        int y = this.maxHeight / 2;
        this.tile = new Tile(x, y);
        while (this.tile.getX() - 50 <= this.maxWidth &&
                this.tile.getY() - 50 <= this.maxHeight &&
                this.tile.getX() + 50 >= 0 &&
                this.tile.getY() + 50 >= 0) {
            if (this.uiThreadedWrapper.getStatus()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                uiThreadedWrapper.setTile(this.tile);
                this.tile.setX(tile.getX() + this.newX);
                this.tile.setY(tile.getY() + this.newY);
            } else {
                break;
            }
        }
    }
}

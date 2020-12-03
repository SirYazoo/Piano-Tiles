package com.example.pianotiles;

public class TesThread implements Runnable {
    protected Thread thread;
    protected UIThreadedWrapper uiThreadedWrapper;
    private int newX;
    private int newY;
    protected int maxWidth;
    protected int maxHeight;
    private Tile tile;

    public TesThread(UIThreadedWrapper uiThreadedWrapper, int maxWidth, int maxHeight) {
        this.uiThreadedWrapper = uiThreadedWrapper;
        this.thread = new Thread(this);
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public void drawTile() {
        this.thread.start();
    }

    @Override
    public void run() {
        this.newX = 0;
        this.newY = 0;
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

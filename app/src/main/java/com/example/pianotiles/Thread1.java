package com.example.pianotiles;

public class Thread1 implements Runnable {
    protected Thread thread;
    protected UIThreadedWrapper uiThreadedWrapper;
    protected int maxWidth;
    protected int maxHeight;

    public Thread1(UIThreadedWrapper uiThreadedWrapper, int maxWidth, int maxHeight) {
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
        int newX = 0;
        int newY = 0;
        int x = this.maxWidth / 2;
        int y = 0;
        Tile tile = new Tile(x, y);
        while (tile.getX() - 50 <= this.maxWidth &&
                tile.getY() - 50 <= this.maxHeight &&
                tile.getX() + 50 >= 0 &&
                tile.getY() + 50 >= 0) {


            if (this.uiThreadedWrapper.getStatus()) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                uiThreadedWrapper.setTile(tile);
                tile.setX(tile.getX() + newX);
                tile.setY(tile.getY() + newY);
                newY++;

            } else {
                break;
            }
        }
    }
}

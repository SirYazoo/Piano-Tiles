package com.example.pianotiles;

import android.os.Handler;
import android.os.Message;

public class UIThreadedWrapper extends Handler {
    protected final static int MSG_SET_TEXTVIEW_OUTPUT = 0;
    protected MainActivity mainActivity;
    protected Boolean status;

    public UIThreadedWrapper(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.status = true;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == UIThreadedWrapper.MSG_SET_TEXTVIEW_OUTPUT) {
            Tile tile = (Tile) msg.obj;
            this.mainActivity.setTile(tile);
        }
    }

    public void setTile(Tile tile) {
        Message msg = new Message();
        msg.what = MSG_SET_TEXTVIEW_OUTPUT;
        msg.obj = tile;
        this.sendMessage(msg);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

package com.example.pianotiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivCanvas;
    private Canvas mCanvas;
    private Button startBtn;
    private Button stopBtn;
    private int maxWidth;
    private int maxHeight;
    protected UIThreadedWrapper uiThreadedWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ivCanvas = findViewById(R.id.iv_canvas);
        this.startBtn = findViewById(R.id.btn_start);
        this.stopBtn = findViewById(R.id.btn_stop);
        this.uiThreadedWrapper = new UIThreadedWrapper(this);
        this.startBtn.setOnClickListener(this);
        this.stopBtn.setOnClickListener(this);
    }

    public void initCanvas() {
        this.maxWidth = this.ivCanvas.getWidth();
        this.maxHeight = this.ivCanvas.getHeight();
        int x = this.maxWidth / 2;
        int y = 0;
        int left1 = x - 100;
        int top1 = y;
        int right1 = x + 100;
        int bottom1 = y + 400;
        Bitmap mBitmap = Bitmap.createBitmap(this.maxWidth, this.maxHeight, Bitmap.Config.ARGB_8888);
        this.ivCanvas.setImageBitmap(mBitmap);
        this.mCanvas = new Canvas(mBitmap);
        int mColorBackground = ResourcesCompat.getColor(getResources(), R.color.white, null);
        this.mCanvas.drawColor(mColorBackground);
        Paint paint = new Paint();
        int mColorTest = ResourcesCompat.getColor(getResources(), R.color.teal_200, null);
        paint.setColor(mColorTest);
        Rect rect = new Rect(left1, top1, right1, bottom1);
        this.mCanvas.drawRect(rect, paint);
        this.ivCanvas.invalidate();
    }

    public void resetCanvas() {
        this.mCanvas.drawColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        this.ivCanvas.invalidate();
    }

    public void setTile(Tile tile) {
        this.resetCanvas();
        Paint paint = new Paint();
        int mColorTest = ResourcesCompat.getColor(getResources(), R.color.teal_200, null);
        paint.setColor(mColorTest);
        Rect rect = new Rect(tile.getX() - 100, tile.getY(), tile.getX() + 100, tile.getY() + 400);
        this.mCanvas.drawRect(rect, paint);
        this.ivCanvas.invalidate();
    }

    protected void run() {
        Thread1 thread1 = new Thread1(this.uiThreadedWrapper, this.maxWidth, this.maxHeight);
        thread1.drawTile();
    }

    @Override
    public void onClick(View v) {
        if (v == this.startBtn) {
            this.uiThreadedWrapper.setStatus(true);
            this.initCanvas();
            this.run();
        }
    }
}
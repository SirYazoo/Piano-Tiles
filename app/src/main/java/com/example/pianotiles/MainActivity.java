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
    private Bitmap mBitmap;
    private ImageView ivCanvas;
    private Canvas mCanvas;
    private Button startBtn;
    private Button stopBtn;
    private int maxWidth;
    private int maxHeight;
    private int x;
    private int y;
    private Tile tile;
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
        this.x = this.maxWidth / 2;
        this.y = 0;
        int left = this.x - 100;
        int top = this.y;
        int right = this.x + 100;
        int bottom = this.y + 400;
        this.mBitmap = Bitmap.createBitmap(this.maxWidth, this.maxHeight, Bitmap.Config.ARGB_8888);
        this.ivCanvas.setImageBitmap(this.mBitmap);
        this.mCanvas = new Canvas(this.mBitmap);
        int mColorBackground = ResourcesCompat.getColor(getResources(), R.color.white, null);
        this.mCanvas.drawColor(mColorBackground);
        Paint paint = new Paint();
        int mColorTest = ResourcesCompat.getColor(getResources(), R.color.teal_200, null);
        paint.setColor(mColorTest);
        Rect rect = new Rect(left, top, right, bottom);
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
        TesThread objTest = new TesThread(this.uiThreadedWrapper, this.maxWidth, this.maxHeight);
        objTest.drawTile();
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
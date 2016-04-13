package edu.ub.pis2016.mmanjarrez.ejemplo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by orla_ on 12/04/2016.
 */

public class Sprite {
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 4;
    private Bitmap bmp;
    private int width;
    private int height;
    private int currentFrame = 0;
    private int fila = 1;

    public Sprite(Bitmap bmp) {
        this.bmp=bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    public void setCurrentFrame(int fila) {
        currentFrame = ++currentFrame % BMP_COLUMNS;
        this.fila = fila;
    }

    public void onDraw(Canvas canvas) {
        int srcX = currentFrame * width;
        int srcY = fila * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(500, 40, 500 + width, 40 + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }
}

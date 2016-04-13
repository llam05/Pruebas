package edu.ub.pis2016.mmanjarrez.ejemplo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by orla_ on 13/04/2016.
 */
public class FondosJuego {
    private Bitmap bmp;

    public FondosJuego(Bitmap bmp) {
        this.bmp = bmp;
    }

    public void onDraw(Canvas canvas, int x, int y) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bmp, x, y, null);
    }
}

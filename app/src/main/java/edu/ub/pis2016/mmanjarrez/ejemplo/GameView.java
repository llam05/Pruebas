package edu.ub.pis2016.mmanjarrez.ejemplo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by orla_ on 12/04/2016.
 */
public class GameView extends SurfaceView {
    private Bitmap bmp;
    private final SurfaceHolder holder;
    private int x=100;
    private int y=100;

    //public GameView(Context context) {
      //  super(context);
      //  bmp = BitmapFactory.decodeResource(getResources(), R.drawable.prin);
    //}

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas c = holder.lockCanvas(null);
                onDraw(c);
                holder.unlockCanvasAndPost(c);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.plano);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        canvas.drawBitmap(bmp, -1000, -1000, null);
    }
}

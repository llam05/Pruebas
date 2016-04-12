package edu.ub.pis2016.mmanjarrez.ejemplo;
import android.graphics.Canvas;

/**
 * Created by mmanjaag7.alumnes on 12/04/16.
 */
public class GameLoopThread extends Thread {
    private MainActivity view;
    private boolean running = false;

    public GameLoopThread(MainActivity view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {
            Canvas c = null;
            try {
                c = view.getSurface().getHolder().lockCanvas();
                synchronized (view.getSurface().getHolder()) {
                    view.onDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getSurface().getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}

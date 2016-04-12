package edu.ub.pis2016.mmanjarrez.ejemplo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {
    private GameLoopThread gameLoopThread;
    private SurfaceView surface;
    private Bitmap bmp;
    private Button btnUp;
    private Button btnDown;
    private Button btnLeft;
    private Button btnRight;
    private int x=100;
    private int y=100;
    private Bitmap bmp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(new GameView(this));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        gameLoopThread = new GameLoopThread(this);
        surface = (SurfaceView) findViewById(R.id.surface);
        surface.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }

            }
        });
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.plano);
        bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.prin);

        btnUp = (Button) findViewById(R.id.btnUp);
        btnDown = (Button) findViewById(R.id.btnDown);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnRight = (Button) findViewById(R.id.btnRight);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y += 100;


            }
        });

        btnDown.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                y -= 100;
            }
        });


        btnLeft.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                x += 100;
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                x -= 100;
            }
        });

    }

    public void onDraw(Canvas c) {
        c.drawColor(Color.BLACK);
        c.drawBitmap(bmp, x, y, null);
        c.drawBitmap(bmp2,800,500,null);
    }

    public SurfaceView getSurface () {
        return surface;
    }

}

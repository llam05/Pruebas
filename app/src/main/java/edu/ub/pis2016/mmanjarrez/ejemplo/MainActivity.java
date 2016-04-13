package edu.ub.pis2016.mmanjarrez.ejemplo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
    private Sprite sprite;
    private FondosJuego fondo;
    private Canvas c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
        bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.perfil3);

        sprite = new Sprite(bmp2);
        fondo = new FondosJuego(bmp);

        btnUp = (Button) findViewById(R.id.btnUp);
        btnDown = (Button) findViewById(R.id.btnDown);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnRight = (Button) findViewById(R.id.btnRight);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y += 100;
                sprite.setCurrentFrame(0);
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                y -= 100;
                sprite.setCurrentFrame(1);
            }
        });


        btnLeft.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                x += 100;
                sprite.setCurrentFrame(3);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                x -= 100;
                sprite.setCurrentFrame(2);

            }
        });

    }

    public void onDraw(Canvas c) {
        fondo.onDraw(c,x,y);
        sprite.onDraw(c);
    }

    public SurfaceView getSurface () {
        return surface;
    }

}

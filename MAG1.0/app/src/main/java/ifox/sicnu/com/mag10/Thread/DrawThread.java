package ifox.sicnu.com.mag10.Thread;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import ifox.sicnu.com.mag10.View.DrawInterface;

/**
 * Created by Funchou Fu on 2017/2/20.
 * 关联接口: DrawInterface
 */
public class DrawThread extends Thread {
    private int sleepSpan = 100;
    public boolean flag = false;
    DrawInterface dv;
    SurfaceHolder sh;
    private String TAG = "StartActivity";


    public DrawThread(DrawInterface dv, int sleepSpan){
        this.dv = dv;
        this.sleepSpan = sleepSpan;
        sh = dv.getHolder();
        flag = true;
    }
    @Override
    public void run() {
        Canvas canvas = null;
        while (flag){
            try {
                canvas = sh.lockCanvas();
                synchronized (sh) {
                    dv.doDraw(canvas);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (canvas!=null){
                    sh.unlockCanvasAndPost(canvas);
                }
            }
            try {
                Thread.sleep(sleepSpan);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

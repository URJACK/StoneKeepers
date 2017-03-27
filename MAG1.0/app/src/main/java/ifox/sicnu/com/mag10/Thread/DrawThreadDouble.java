package ifox.sicnu.com.mag10.Thread;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.View.DrawInterface;

/**
 * Created by Funchou Fu on 2017/2/20.
 * 关联接口: DrawInterface
 */
public class DrawThreadDouble extends Thread {
    private int sleepSpan = 100;
    public boolean flag = false;
    DrawInterface dv;
    SurfaceHolder sh;
    Pictures pictures;
    Bitmap left;
    Bitmap right;
    private String TAG = "StartActivity";

    private int startpoint = 0;        //每一个线程开始的时候，该点数会不断的增加。
    private int startlongth = 12;           //前摇动画帧数

    public DrawThreadDouble(DrawInterface dv, int sleepSpan, Pictures pictures) {
        this.dv = dv;
        this.sleepSpan = sleepSpan;
        sh = dv.getHolder();
        flag = true;
        this.pictures = pictures;
        left = pictures.getBitmap("door_left");
        right = pictures.getBitmap("door_right");
    }

    @Override
    public void run() {
        Canvas canvas = null;
        while (flag) {
            if (startpoint++ < startlongth && this.left != null && this.right != null) {
                try {
                    canvas = sh.lockCanvas();
                    Bitmap lf = Bitmap.createScaledBitmap(left, Const.SCREENWIDTH / (startlongth * 2) * startpoint, Const.SCREENHEIGHT, true);
                    Bitmap rf = Bitmap.createScaledBitmap(right, Const.SCREENWIDTH / (startlongth * 2) * startpoint, Const.SCREENHEIGHT, true);
                    synchronized (sh) {
                        canvas.drawBitmap(lf, 0, 0, null);
                        canvas.drawBitmap(rf, Const.SCREENWIDTH / (startlongth * 2) * (startlongth * 2 - startpoint), 0, null);
//                        lf.recycle();
//                        rf.recycle();
                        lf = null;
                        rf = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null)
                        sh.unlockCanvasAndPost(canvas);
                }
            }   //最开始的状态是一个关门
            else {
//                if (this.left != null)
//                    this.left.recycle();
//                if (this.right != null)
//                    this.right.recycle();
                this.right = null;
                this.left = null;
                try {
                    canvas = sh.lockCanvas();
                    synchronized (sh) {
                        dv.doDraw(canvas);
                    }
                    //正常的绘制
                    if (startpoint < 2 * startlongth - 1) {
                        startpoint++;
                        int use = startpoint - startlongth;     //实际使用的计数
                        Bitmap lf = Bitmap.createScaledBitmap(left, Const.SCREENWIDTH / (startlongth * 2) * (startlongth - use), Const.SCREENHEIGHT, true);
                        Bitmap rf = Bitmap.createScaledBitmap(right, Const.SCREENWIDTH / (startlongth * 2) * (startlongth - use), Const.SCREENHEIGHT, true);
                        synchronized (sh) {
                            canvas.drawBitmap(lf, 0, 0, null);
                            canvas.drawBitmap(rf, Const.SCREENWIDTH / (startlongth * 2) * (use + startlongth), 0, null);
                        }
                    }//这是开门的逻辑
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null)
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

package ifox.sicnu.com.mag10.View;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.StartActivity;

/**
 * Created by Funchou Fu on 2017/2/20.
 * 是本应用所有的多线程绘图VIEW的父VIEW，可自动刷新绘图，只需要覆写doDraw方法.
 * 关联类: DrawThreadDouble
 */
public class StartView extends SurfaceView implements SurfaceHolder.Callback {
    private String TAG = "StartActivity";
    private Paint paint;
    Rect backrect;            //背景
    Rect buttonrect;           //开始按钮
    Pictures pictures;
    private Bitmap back_1;          //背景图片
    private Bitmap back_2;          //开始按钮图片
    private Bitmap back_3;          //开始按钮点击后图片
    private boolean ispressed = false;      //是否被按压的逻辑变量


    public StartView(android.content.Context context) {
        super(context);
        getHolder().addCallback(this);
        pictures = ((APP) context.getApplicationContext()).getPictures();
        IniDraw();
    }

    private void IniDraw() {
        backrect = new Rect();
        backrect.left = 0;
        backrect.right = Const.SCREENWIDTH;
        backrect.top = 0;
        backrect.bottom = Const.SCREENHEIGHT;
        //初始化背景的大小
        buttonrect = new Rect();
        buttonrect.left = (int) (Const.SCREENWIDTH * 0.25);
        buttonrect.right = (int) (Const.SCREENWIDTH * 0.75);
        buttonrect.top = (int) (Const.SCREENHEIGHT * 0.65);
        buttonrect.bottom = (int) (buttonrect.top + Const.SCREENWIDTH * 0.25);
        //初始化开始按钮大小

        paint = new Paint();
        paint.setColor(Color.RED);
        //初始化画笔
        back_1 = pictures.getBitmap("startview");
        back_2 = pictures.getBitmap("startbutton_2");
        back_3 = pictures.getBitmap("startbutton");
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas canvas = getHolder().lockCanvas();
        doDraw(canvas);
        getHolder().unlockCanvasAndPost(canvas);
        //执行初始化时，完成第一次制图
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    public void doDraw(Canvas canvas) {
        canvas.drawBitmap(back_1, null, backrect, null);
        if (ispressed == false)
            canvas.drawBitmap(back_2, null, buttonrect, null);
        else
            canvas.drawBitmap(back_3, null, buttonrect, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            Canvas canvas = getHolder().lockCanvas();
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x > buttonrect.left && x < buttonrect.right && y > buttonrect.top && y < buttonrect.bottom) {
                ispressed = true;
                doDraw(canvas);
                getHolder().unlockCanvasAndPost(canvas);
            }   //确实点击到了按钮
            else {
                ispressed = false;
                doDraw(canvas);
                getHolder().unlockCanvasAndPost(canvas);
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x > buttonrect.left && x < buttonrect.right && y > buttonrect.top && y < buttonrect.bottom) {
                ((StartActivity) getContext()).gotoNextActivity();
            }   //确实点击到了按钮
        }
        return true;
    }

    public void clear(){
        this.back_2.recycle();
        this.back_3.recycle();
    }
}

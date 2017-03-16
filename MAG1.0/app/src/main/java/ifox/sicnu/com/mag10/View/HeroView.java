package ifox.sicnu.com.mag10.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Heroes;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.DrawLogic.DrawHero;
import ifox.sicnu.com.mag10.HeroActivity;
import ifox.sicnu.com.mag10.SelectActivity;

/**
 * Created by Funchou Fu on 2017/2/24.
 * 该View用来给玩家提供可供选择的英雄。
 * 因为Heroes里存储英雄是通过HashMap,因为时间原因，在写代码时，不是很清楚Hashmap的遍历。
 * 所以单独在这个View里提供了heronames 来依次访问Hashmap里的英雄
 */
public class HeroView extends SurfaceView implements SurfaceHolder.Callback, DrawInterface {
    Bitmap background;
    Bitmap left_limit;
    Bitmap right_limit;
    Heroes heroes;
    Pictures pictures;
    SurfaceHolder sh;
    private String TAG = "HeroActivity";
    private Paint paint;
    private int offset;         //英雄列表的偏移量
    private int herolacatY = (int) (Const.SCREENWIDTH * 0.22);         //英雄列表相对屏幕顶的距离
    private int herolocatX = (int) (Const.SCREENHEIGHT * 0.2);         //单个英雄距离 屏幕的 逻辑左边 的距离
    private int x;          //记录手指滑动的坐标点
    private int pTarget;         //记录选中的英雄标记
    private boolean makeSure = false;
    private int textsize = Const.SCREENWIDTH / 30;
    public TextView textView;          //介绍英雄属性
    private DrawHero drawHero;
    private ArrayList<Hero> herolines;          //可供选择的英雄序列

    public HeroView(Context context, int pTarget) {
        super(context);
        this.pTarget = pTarget;
        getHolder().addCallback(this);
        APP app = ((APP) context.getApplicationContext());
        pictures = app.getPictures();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(textsize);
        heroes = app.getHeroes();
        herolines = new ArrayList<>();
        drawHero = new DrawHero(pictures.getBitmap("hero_room1"), pictures.getBitmap("hero_room2"));
        IniBitmap();
//        IniHeroname();
        IniHeroLine(herolines);
        sh = getHolder();
    }

    private void IniHeroLine(ArrayList<Hero> herolines) {
        herolines.add(heroes.getHero("斯微法"));
        herolines.add(heroes.getHero("弗拉基米尔"));
        herolines.add(heroes.getHero("赫拉克勒斯"));
        herolines.add(heroes.getHero("马可波罗"));
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
        this.textView.setTextSize((float) (textsize * 0.27));
        this.textView.setTextColor(Color.RED);
    }

    private void IniBitmap() {
        background = pictures.getBitmap("hero_select");
        left_limit = pictures.getBitmap("hero_leftlimit");
        right_limit = pictures.getBitmap("hero_rightlimit");
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas canvas = sh.lockCanvas();
        doDraw(canvas);
        sh.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void doDraw(Canvas canvas) {
        canvas.save();
        canvas.drawBitmap(background, 0, 0, null);
        canvas.restore();
        // 绘制背景图片
        paint.setColor(Color.YELLOW);
        for (int i = 0; i < herolines.size(); i++) {
            boolean flag = pTarget == (i + 1);
            Hero hero = herolines.get(i);
            drawHero.doCanvas(canvas, hero, herolacatY, herolocatX, offset, i, paint, flag);
        }
        canvas.drawBitmap(left_limit, 0, herolacatY, null);
        canvas.drawBitmap(right_limit, (int) (Const.SCREENHEIGHT * 0.88), herolacatY, null);

        paint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.x = (int) event.getX();
            if (event.getY() > herolacatY && event.getY() < herolacatY + Const.BUTTON_WIDTH * 1.5) {
                pTarget = (x - offset) / herolocatX;
            }//点击到了目标

            if (event.getY() > Const.SCREENWIDTH * 0.9 && event.getX() > Const.SCREENHEIGHT * 0.9) {
                this.makeSure = true;
            } else {
                this.makeSure = false;
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int x = (int) event.getX();
            int ofx = x - this.x;
            if (ofx < -5) {
                if (this.offset + ofx > -500)
                    this.offset += ofx;
                Canvas canvas = sh.lockCanvas();
                doDraw(canvas);
                sh.unlockCanvasAndPost(canvas);
            }//执行滑动
            else if (ofx > 5) {
                if (this.offset + ofx < 0)
                    this.offset += ofx;
                Canvas canvas = sh.lockCanvas();
                doDraw(canvas);
                sh.unlockCanvasAndPost(canvas);
            }
            this.x = x;//重新刷新记录点
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            if (event.getY() > herolacatY && event.getY() < herolacatY + Const.BUTTON_WIDTH * 1.5 && pTarget != -1) {
                int pTarget = (x - offset) / herolocatX;
                if (this.pTarget == pTarget) {
                    if (pTarget > 0 && pTarget <= herolines.size()) {
                        int selected = pTarget - 1; //被选择的编号
                        Hero hero = herolines.get(selected);
                        Canvas canvas = sh.lockCanvas();
                        doDraw(canvas);
                        sh.unlockCanvasAndPost(canvas);
                    }//有效选取了英雄后
                }//选择目标的逻辑 此时的pTarget 的编号是 从1 编到 N
                if (pTarget <= herolines.size() && pTarget > 0) {
                    Hero hero = herolines.get(pTarget - 1);
                    textView.setText(hero.introduction);
                }//绘制 选中英雄介绍内容
            }
            if (event.getY() > Const.SCREENWIDTH * 0.9 && event.getX() > Const.SCREENHEIGHT * 0.9 && this.makeSure) {
                ((HeroActivity) getContext()).setResult(pTarget);
                ((HeroActivity) getContext()).finish();
            }//确认选择的英雄,并返回Activity
            this.makeSure = false;
        }
        return true;
    }
}

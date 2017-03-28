package ifox.sicnu.com.mag10.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.ReadyActivity;
import ifox.sicnu.com.mag10.Thread.DrawThreadDouble;

/**
 * Created by Funchou Fu on 2017/2/20.
 */
public class ReadyView extends SurfaceView implements SurfaceHolder.Callback, DrawInterface {
    DrawThreadDouble dt;
    private int[] stonePositions;        //每个石子的垂直位置
    private int[] stoneHoriPositions;    //每个石子的水平位置
    private int[] stoneOffsets;          //每个石子的垂直偏移量
    private int stoneNumber = 9;            //小石子个数
    private int stoneoffround = (int) (Const.SCREENHEIGHT * 0.011);  //小石子的上下移动的数值
    private int stoneStep = (int) (Const.SCREENHEIGHT * 0.003);     //小石子单次移动数值
    private boolean[] stoneDirections;     //小石子移动方向
    Context myContext;

    Pictures pictures;

    public Bitmap[] backs;
    /*
    0 为背景图片
    1，2 分别为 左门和右门
    3~6 为石子图片  6 最大
     7~12 分别为 该活动的几个 按钮
     */
    Rect backrect;

    boolean[] ispressed;        //表示三个按钮是否被按压的逻辑变量数组
    private int btndistance = Const.SCREENHEIGHT / 9 * 4;          //第一个按钮距离屏幕顶部的距离
    private int btnoffset = Const.SCREENHEIGHT / 8;            //每一个按钮左上角点的间距
    private int btnhorizontaldistance = Const.SCREENWIDTH / 3;      //按钮距离屏幕左边的距离
    private int btnheight = Const.SCREENHEIGHT / 10;          //每一个按钮的高度
    private int btnwidth = Const.SCREENHEIGHT / 5;            //每一个按钮的宽度

    public ReadyView(Context context) {
        super(context);
        myContext = context;
        getHolder().addCallback(this);
        pictures = ((APP) (context.getApplicationContext())).getPictures();
        IniDraw();
        IniStones();
    }

    //完成对小石子属性的初始化
    private void IniStones() {
        stonePositions = new int[stoneNumber];
        stoneOffsets = new int[stoneNumber];
        stoneHoriPositions = new int[stoneNumber];
        stoneDirections = new boolean[stoneNumber - 1];
//        for (int i = 0; i < stoneNumber - 1; i++) {
//            if (i % 4 == 0) {
//                stoneHoriPositions[i] = (int) Math.round(Math.random() * (left_max - left_min) + left_min);
//                stonePositions[i] = (int) Math.round(Math.random() * (bottom_min - top_max) + top_max);
//            }//在左的石子
//            else if (i % 4 == 1) {
//                stoneHoriPositions[i] = (int) Math.round(Math.random() * (right_max - right_min) + right_min);
//                stonePositions[i] = (int) Math.round(Math.random() * (bottom_min - top_max) + top_max);
//            }//在右的石子
//            else if (i % 4 == 2) {
//                stonePositions[i] = (int) Math.round(Math.random() * (top_max - top_min) + top_min);
//                stoneHoriPositions[i] = (int) Math.round(Math.random() * (right_min - left_max) + left_max);
//            }//在上的石子
//            else {
//                stonePositions[i] = (int) Math.round(Math.random() * (bottom_max - bottom_min) + bottom_min);
//                stoneHoriPositions[i] = (int) Math.round(Math.random() * (right_min - left_max) + left_max);
//            }//在下的石子
//        }
        stonePositions[0] = (int) (Const.SCREENHEIGHT * 0.27);
        stoneHoriPositions[0] = (int) (Const.SCREENWIDTH * 0.4);
        stonePositions[1] = (int) (Const.SCREENHEIGHT * 0.25);
        stoneHoriPositions[1] = (int) (Const.SCREENWIDTH * 0.5);
        stonePositions[2] = (int) (Const.SCREENHEIGHT * 0.28);
        stoneHoriPositions[2] = (int) (Const.SCREENWIDTH * 0.53);
        stonePositions[3] = (int) (Const.SCREENHEIGHT * 0.33);
        stoneHoriPositions[3] = (int) (Const.SCREENWIDTH * 0.58);
        stonePositions[4] = (int) (Const.SCREENHEIGHT * 0.38);
        stoneHoriPositions[4] = (int) (Const.SCREENWIDTH * 0.54);
        stonePositions[5] = (int) (Const.SCREENHEIGHT * 0.39);
        stoneHoriPositions[5] = (int) (Const.SCREENWIDTH * 0.49);
        stonePositions[6] = (int) (Const.SCREENHEIGHT * 0.38);
        stoneHoriPositions[6] = (int) (Const.SCREENWIDTH * 0.44);
        stonePositions[7] = (int) (Const.SCREENHEIGHT * 0.32);
        stoneHoriPositions[7] = (int) (Const.SCREENWIDTH * 0.4);

        stoneHoriPositions[stoneNumber - 1] = (int) (Const.SCREENWIDTH * 0.44);
        stonePositions[stoneNumber - 1] = (int) (Const.SCREENHEIGHT * 0.29);
        //大石子

        for (int i = 0; i < stoneNumber - 1; i++) {
            stoneDirections[i] = false;
        }
        //初始化所有石子的移动方向
    }

    //完成对图片的加载
    private void IniDraw() {
        backs = new Bitmap[13];

        backs[0] = pictures.getBitmap("readyview");
        backs[1] = pictures.getBitmap("door_left");
        backs[2] = pictures.getBitmap("door_right");
        backs[3] = pictures.getBitmap("stone1");
        backs[4] = pictures.getBitmap("stone2");
        backs[5] = pictures.getBitmap("stone3");
        backs[6] = pictures.getBitmap("stone4");
        backs[7] = pictures.getBitmap("readyview_btn1");
        backs[8] = pictures.getBitmap("readyview_btn2");
        backs[9] = pictures.getBitmap("readyview_btn3");
        backs[10] = pictures.getBitmap("readyview_btn4");
        backs[11] = pictures.getBitmap("readyview_btn5");
        backs[12] = pictures.getBitmap("readyview_btn6");

        backrect = new Rect();
        backrect.left = 0;
        backrect.right = Const.SCREENWIDTH;
        backrect.top = 0;
        backrect.bottom = Const.SCREENHEIGHT;

        ispressed = new boolean[3];
        ispressed[0] = false;
        ispressed[1] = false;
        ispressed[2] = false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        dt = new DrawThreadDouble(this, 50, pictures);
        if (!dt.isAlive()) {
            dt.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        dt.flag = false;
        dt = null;
    }

    @Override
    public void doDraw(Canvas canvas) {
        if (canvas != null)
            canvas.drawBitmap(backs[0], null, backrect, null);
        else
            return;
        //绘制背景

        for (int i = 0; i < 3; i++) {
            if (ispressed[i] == true) {
                canvas.drawBitmap(backs[7 + 2 * i + 1], btnhorizontaldistance, btndistance + i * btnoffset, null);
            }       //如果该按钮被按压
            else {
                canvas.drawBitmap(backs[7 + 2 * i], btnhorizontaldistance, btndistance + i * btnoffset, null);
            }
        }
        //绘制按钮

        for (int i = 0; i < stoneNumber - 1; i++) {
            int d = i % 3 + 3;
            Bitmap bitmap = backs[d];
            canvas.drawBitmap(bitmap, stoneHoriPositions[i], stonePositions[i] + stoneOffsets[i], null);
        }
        //绘制小石子
        canvas.drawBitmap(backs[6], stoneHoriPositions[stoneNumber - 1], stonePositions[stoneNumber - 1], null);
        //绘制大石子

        for (int i = 0; i < stoneNumber - 1; i++) {
            if (stoneDirections[i] == true) {
                stoneOffsets[i] -= stoneStep * Math.random();
                if (stoneOffsets[i] < -stoneoffround) {
                    stoneDirections[i] = false;
                }   //如果向上偏移超过最大值，则改变方向
            }//向上移动
            else {
                stoneOffsets[i] += stoneStep * Math.random();
                if (stoneOffsets[i] > stoneoffround) {
                    stoneDirections[i] = true;
                }
            }//向下移动
        }
        //改变石子位置

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x > btnhorizontaldistance && x < btnhorizontaldistance + btnwidth) {
                Canvas canvas = null;
                if (y > btndistance && y < btndistance + btnheight) {
                    ispressed[0] = true;
                    canvas = getHolder().lockCanvas();
                    doDraw(canvas);
                    getHolder().unlockCanvasAndPost(canvas);
                }       //第1个按钮被按压
                else if (y > btndistance + btnoffset && y < btndistance + btnoffset + btnheight) {
                    ispressed[1] = true;
                    canvas = getHolder().lockCanvas();
                    doDraw(canvas);
                    getHolder().unlockCanvasAndPost(canvas);
                }       //第2个按钮被按压
                else if (y > btndistance + 2 * btnoffset && y < btndistance + 2 * btnoffset + btnheight) {
                    ispressed[2] = true;
                    canvas = getHolder().lockCanvas();
                    doDraw(canvas);
                    getHolder().unlockCanvasAndPost(canvas);
                }       //第3个按钮被按压
                else {
                    for (int i = 0; i < 3; i++) {
                        ispressed[i] = false;
                    }

                }       //没有按钮被按压
            } else {
                for (int i = 0; i < 3; i++) {
                    ispressed[i] = false;
                }
            }       //没有按钮被按压
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x > btnhorizontaldistance && x < btnhorizontaldistance + btnwidth) {
                Canvas canvas = null;
                if (y > btndistance && y < btndistance + btnheight && ispressed[0] == true) {
                    ((ReadyActivity) getContext()).gotoSelectActivity();
                }       //第1个按钮被选择
                else if (y > btndistance + btnoffset && y < btndistance + btnoffset + btnheight && ispressed[1] == true) {
                    Toast.makeText(getContext(), "请咨询在场的工作人员", Toast.LENGTH_SHORT).show();
                }       //第2个按钮被选择
                else if (y > btndistance + 2 * btnoffset && y < btndistance + 2 * btnoffset + btnheight && ispressed[2] == true) {
                    Toast.makeText(getContext(), "退出游戏啦还有其他游戏可以领奖品哦", Toast.LENGTH_SHORT).show();
                    ((Activity) myContext).finish();
                }       //第3个按钮被选择
                for (int i = 0; i < 3; i++) {
                    ispressed[i] = false;
                }       //再执行完选择逻辑后，回复按钮状态
            }
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < backs.length; i++) {
            backs[i].recycle();
            backs[i] = null;
        }
    }
}

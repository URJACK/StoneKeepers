package ifox.sicnu.com.mag10.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Player;

/**
 * Created by 41988 on 2017/3/14.
 */
public class EndView extends SurfaceView implements SurfaceHolder.Callback {
    boolean type;           //true 代表已经通关  false 代表失败
    Bitmap background;          //背景图
    Paint paint;
    Player player;
    private Object score;

    public EndView(Context context) {
        super(context);
        getHolder().addCallback(this);
        background = ((APP) context.getApplicationContext()).getPictures().getBitmap("startview");
        player = ((APP) context.getApplicationContext()).getPlayer();
        paint = new Paint();
    }

    private void doDraw(Canvas canvas) {
        if (!type) {
            paint.setColor(Color.rgb(140, 170, 180));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawBitmap(background, 0, 0, null);
            canvas.drawRect((int) (Const.SCREENWIDTH * 0.2), (int) (Const.SCREENHEIGHT * 0.2), (int) (Const.SCREENWIDTH * 0.8), (int) (Const.SCREENHEIGHT * 0.8), paint);
            paint.setColor(Color.RED);
            canvas.drawText(String.format("你已经死亡了"), (int) (Const.SCREENWIDTH * 0.3), (int) (Const.SCREENHEIGHT * 0.5), paint);
            paint.setColor(Color.YELLOW);
            canvas.drawText(String.format("你当前的总得分是%d", getScore()),(int) (Const.SCREENWIDTH * 0.3),(int) (Const.SCREENHEIGHT * 0.6),paint);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas canvas = getHolder().lockCanvas();
        doDraw(canvas);
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public int getScore() {
        int score = 0;
        for (int i = 0; i < player.level; i++) {
            score += Const.Exp.levels[i];
        }
        score += player.exp;
        return score;
    }
}

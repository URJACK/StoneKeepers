package ifox.sicnu.com.mag10.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.R;

/**
 * Created by 41988 on 2017/3/14.
 */
public class EndView extends SurfaceView implements SurfaceHolder.Callback {
    boolean type;           //true 代表已经通关  false 代表失败
    Bitmap background;          //背景图
    Paint paint;
    Player player;
    private Object score;
    Context context;

    public EndView(Context context) {
        super(context);
        this.context = context;
        getHolder().addCallback(this);
        //background = ((APP) context.getApplicationContext()).getPictures().getBitmap("startview");
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.endview_bg);
        background = Bitmap.createScaledBitmap(background,Const.SCREENHEIGHT,Const.SCREENWIDTH,true);
        player = ((APP) context.getApplicationContext()).getPlayer();
        paint = new Paint();
    }

    private void doDraw(Canvas canvas) {
        if (!type) {
            paint.setColor(Color.rgb(140, 170, 180));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawBitmap(background, 0, 0, null);
            //canvas.drawRect((int) (Const.SCREENWIDTH * 0.2), (int) (Const.SCREENHEIGHT * 0.2), (int) (Const.SCREENWIDTH * 0.8), (int) (Const.SCREENHEIGHT * 0.8), paint);
            paint.setTextSize(Const.CELL_HEIGHT/2);
            //canvas.drawText(String.format("你已经死亡了"), (int) (Const.SCREENWIDTH * 0.3), (int) (Const.SCREENHEIGHT * 0.5), paint);
            paint.setColor(Color.YELLOW);
            canvas.drawText(getScore()+"",(int) (Const.SCREENHEIGHT * 0.49),(int) (Const.SCREENWIDTH * 0.72),paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            x= (int) event.getX();
            y = (int) event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP){

            if(x>Const.SCREENHEIGHT*0.44&&x<Const.SCREENHEIGHT*0.55&&y>Const.SCREENWIDTH*0.86){
                Toast.makeText(context,"over",Toast.LENGTH_SHORT).show();
               // Log.i("end", "onTouchEvent: ---");
            }
        }
        return true;
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

package ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import ifox.sicnu.com.mag10.Data.Const;

/**
 * Created by 41988 on 2017/3/13.
 */
public class PpgetSpecialEffects extends SpecialEffects {

    int i;                  //时钟变量
    int end_time;
    int offset;             //变成弧线偏移量
    int per_x;
    int per_y;
    Paint paint;

    public PpgetSpecialEffects(int x, int y) {
        super(x, y);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAlpha(90);
        end_time = 10;
        per_x = (int) ((Const.SCREENHEIGHT * 0.63 - x) / end_time);
        per_y = (int) ((Const.SCREENWIDTH * 0.93 - y) / end_time);
    }

    @Override
    public void reset() {
        this.i = 0;
    }

    @Override
    public boolean isAlive() {
        if (i == -1)
            return false;
        else
            return true;
    }

    @Override
    public void docanva(Canvas canvas) {
        if (i != -1) {
            canvas.drawCircle(x + per_x * i, y + per_y * i, (int) ((Const.CELL_WIDTH * 0.2)+i*Const.CELL_WIDTH/10 ), paint);
            this.i = i+1;
            Log.i("nini", "docanva: "+i+"--"+per_x+"per"+per_y);
            if (i >= end_time)
                i = -1;
        }
    }
}

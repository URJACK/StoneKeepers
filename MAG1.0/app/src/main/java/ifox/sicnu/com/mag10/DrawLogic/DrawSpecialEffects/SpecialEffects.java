package ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects;

import android.graphics.Canvas;

/**
 * Created by 41988 on 2017/3/13.
 */
public abstract class SpecialEffects {
    protected int x;
    protected int y;
    public SpecialEffects(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public abstract void reset();

    public abstract boolean isAlive();

    public abstract void docanva(Canvas canvas);

}

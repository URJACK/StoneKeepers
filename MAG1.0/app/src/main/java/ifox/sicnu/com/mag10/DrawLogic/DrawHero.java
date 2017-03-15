package ifox.sicnu.com.mag10.DrawLogic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Hero;

/**
 * Created by Funchou Fu on 2017/2/24.
 * 该类是用静态方法即可
 */
public class DrawHero {
    Bitmap room1;
    Bitmap room2;
    private int repairewidth = (int) (Const.SCREENHEIGHT * 0.026);
    private int repaireheight = (int) (Const.SCREENWIDTH * 0.026);

    private int repaireheight2 = (int) (Const.SCREENWIDTH * 0.046);
    private int repairewidth2 = (int) (Const.SCREENHEIGHT * 0.024);

    public DrawHero(Bitmap room1, Bitmap room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    public void doCanvas(Canvas canvas, Hero hero, int y, int x_first, int offset, int index, Paint paint, boolean flag) {
        canvas.drawBitmap(hero.face, x_first * (index + 1) + offset + repairewidth, y + repaireheight, null);
        if (flag)
            canvas.drawBitmap(room2, x_first * (index + 1) + offset, y, null);
        else
            canvas.drawBitmap(room1, x_first * (index + 1) + offset, y, null);
        canvas.drawText(hero.heroName, x_first * (index + 1) + offset + repairewidth2, (float) (y + Const.BUTTON_WIDTH * 2.2) + repaireheight2, paint);
    }
}

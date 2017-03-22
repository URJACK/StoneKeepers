package ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.R;

/**
 * Created by 41988 on 2017/3/22.
 */
public class Doublefileball_SpecialEffects extends SpecialEffects{
    int i = 0;
    int times = 12;
    static Bitmap[] bitmaps;
    int per_y;

    public Doublefileball_SpecialEffects(int x, int y) {
        super(x, y);
        bitmaps = new Bitmap[12];
        initBitmap();
        bitmaps = initBitmap();
        per_y = y / times;
    }

    private Bitmap[] initBitmap() {
        bitmaps = new Bitmap[12];
        if (bitmaps[0]==null) {
            bitmaps[0] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu_01);
            bitmaps[0] = Bitmap.createScaledBitmap(bitmaps[0], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);
        }
        bitmaps[1] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu_02);
        bitmaps[1] = Bitmap.createScaledBitmap(bitmaps[1], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);

        bitmaps[2] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu03);
        bitmaps[2] = Bitmap.createScaledBitmap(bitmaps[2], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);
        bitmaps[3] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu04);
        bitmaps[3] = Bitmap.createScaledBitmap(bitmaps[3], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);
        bitmaps[4] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu05);
        bitmaps[4] = Bitmap.createScaledBitmap(bitmaps[4], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);
        bitmaps[5] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu06);
        bitmaps[5] = Bitmap.createScaledBitmap(bitmaps[5], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);
        bitmaps[6] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu07);
        bitmaps[6] = Bitmap.createScaledBitmap(bitmaps[6], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);
        bitmaps[7] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu08);
        bitmaps[7] = Bitmap.createScaledBitmap(bitmaps[7], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);
        bitmaps[8] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu09);
        bitmaps[8] = Bitmap.createScaledBitmap(bitmaps[8], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);
        bitmaps[9] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu10);
        bitmaps[9] = Bitmap.createScaledBitmap(bitmaps[9], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);
        bitmaps[10] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu11);
        bitmaps[10] = Bitmap.createScaledBitmap(bitmaps[10], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);
        bitmaps[11] = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.huoqiu12);
        bitmaps[11] = Bitmap.createScaledBitmap(bitmaps[11], Const.CELL_WIDTH * 3, Const.CELL_HEIGHT * 3, true);

        return bitmaps;
    }

    @Override
    public void reset() {
        i = 0;
    }

    @Override
    public boolean isAlive() {

        if (i == -1)
            return false;

        else return true;
    }

    @Override
    public void docanva(Canvas canvas) {
        if (i != -1) {
            canvas.drawBitmap(bitmaps[i], x, y, null);
            i++;
            if(i>=times){
                i = -1;
            }
        }
    }
}

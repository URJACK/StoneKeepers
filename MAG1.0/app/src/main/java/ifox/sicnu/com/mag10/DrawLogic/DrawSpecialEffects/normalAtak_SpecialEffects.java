package ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.SoundPool;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.R;

/**
 * Created by 41988 on 2017/3/17.
 */
public class normalAtak_SpecialEffects extends SpecialEffects {
    int music_id;

    int i;
    int times = 10;
    public normalAtak_SpecialEffects(int x, int y) {
        super(x, y);
        int[] music ={R.raw.gameview_normalatak1,R.raw.gameview_normalatak2,R.raw.gameview_normalatak3};
        int i = (int) (Math.random()*10%3);
        music_id = Const.soundPool_Game.load(Const.mContext_Game, music[i], 1);
        Const.soundPool_Game.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Const.soundPool_Game.play(music_id, 1, 1, 1, 0, 1);

            }
        });

    }

    @Override
    public void reset() {
        i = 0;
    }

    @Override
    public boolean isAlive() {
        if(i!=-1){
            return true;
        }
        else return false;
    }

    @Override
    public void docanva(Canvas canvas) {
        if(i!=-1) {
            for (; i < times; i++) {
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                paint.setAlpha(i * 10);
                Rect rect = new Rect(0, 0, (int) (Const.SCREENHEIGHT * 0.6), Const.SCREENWIDTH);
                canvas.drawRect(rect, paint);
            }
        }
        i=-1;
    }
}

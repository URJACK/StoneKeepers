package ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects;

import android.graphics.Canvas;
import android.media.SoundPool;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.R;

/**
 * Created by 41988 on 2017/3/17.
 */
public class normalAtak_SpecialEffects extends SpecialEffects {
    int music_id;

    public normalAtak_SpecialEffects(int x, int y) {
        super(x, y);
        music_id = Const.soundPool_Game.load(Const.mContext_Game, R.raw.gameview_click, 1);
        Const.soundPool_Game.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Const.soundPool_Game.play(music_id, 1, 1, 1, 0, 1);

            }
        });

    }

    @Override
    public void reset() {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void docanva(Canvas canvas) {

    }
}

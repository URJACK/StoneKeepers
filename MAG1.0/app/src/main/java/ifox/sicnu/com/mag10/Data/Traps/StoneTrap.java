package ifox.sicnu.com.mag10.Data.Traps;

import android.media.SoundPool;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Trap;
import ifox.sicnu.com.mag10.R;

/**
 * Created by 41988 on 2017/3/14.
 */
public class StoneTrap extends Trap {
    int damage;
    int music_id;

    public StoneTrap(int damage) {
        this.damage = damage;
    }

    @Override
    public void doTrap(int x, int y, BattleManager battleManager) {
        music_id = Const.soundPool_Game.load(Const.mContext_Game, R.raw.game_luoshi, 1);
        Const.soundPool_Game.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                Const.soundPool_Game.play(music_id, 1, 1, 1, 0, 1);
            }
        });
        battleManager.player.sufferDamage(damage, true);
    }
}

package ifox.sicnu.com.mag10;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.View.EndView;

public class EndActivity extends Activity {

    boolean type; //结束游戏的类型true（success）or false(failer)
    EndView endView;
    int music_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        type = getIntent().getBooleanExtra("type", false);
        endView = new EndView(this, type);
        setContentView(endView);
        if (!type) {
            music_id = Const.soundPool_Game.load(Const.mContext_Game, R.raw.gameview_siwang, 1);
            Const.soundPool_Game.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    Const.soundPool_Game.play(music_id, 1, 1, 1, 0, 1);
                }
            });
        } else {
            music_id = Const.soundPool_Game.load(Const.mContext_Game, R.raw.gameview_shengli, 1);
            Const.soundPool_Game.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    Const.soundPool_Game.play(music_id, 1, 1, 1, 0, 1);
                }
            });
        }
        ((APP) getApplicationContext()).getHeroBuff().saveValue();
    }
}

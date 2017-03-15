package ifox.sicnu.com.mag10;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Service.MusicService;
import ifox.sicnu.com.mag10.View.GameView;

public class GameActivity extends Activity {

    GameView gv;
    int floor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Const.mContext_Game = this;
        ((APP) getApplicationContext()).gameActivity = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        floor = getIntent().getIntExtra("floor", 1);
        gv = new GameView(this, floor);
        setContentView(gv);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ReadyActivity ra = ((APP) getApplicationContext()).readyActivity;
        if (ra != null)
            ra.finish();
    }

    public void gotoNextActivity() {
        gv.clear();
        gv = null;
        Intent intent = new Intent(GameActivity.this, GameActivity.class);
        intent.putExtra("floor", floor + 1);
        startActivity(intent);
        finish();
    }

    public void gotoEndActivity(boolean type){
        gv.clear();
        gv = null;
        Intent intent = new Intent(GameActivity.this,EndActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
        finish();
    }
    public void gotoEndActivity(boolean type,String killer){
        gv.clear();
        gv = null;
        Intent intent = new Intent(GameActivity.this,EndActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("killer",killer);
        startActivity(intent);
        finish();
    }
}

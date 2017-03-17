package ifox.sicnu.com.mag10;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Heroes;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.Service.MusicService;
import ifox.sicnu.com.mag10.View.SelectView;

/**
 * 这个Activity 提供英雄的选择界面 以及一些装备的穿戴
 * 除了前两个Activity，后面的Activity 为了效果，均以横屏示人。
 */
public class SelectActivity extends Activity {

    private String TAG = "SelectActivity";
    Heroes heroes;
    private ArrayList<Hero> herolines;
    SelectView selectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Const.mContext_Game = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        heroes = ((APP) getApplicationContext()).getHeroes();
        herolines = new ArrayList<>();
        heroes.IniHeroLine(herolines);
        selectView = new SelectView(this);
        setContentView(selectView);
    }

    public void gotoHeroActivity() {
        startActivityForResult(new Intent(SelectActivity.this, HeroActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 0) {
                Toast.makeText(this, "你尚未改变自己的英雄", Toast.LENGTH_SHORT).show();
                return;
            }
            Hero hero = herolines.get(resultCode - 1);
            selectView.changeHero(hero);
        }
    }

    public void gotoAbilityActivity() {
        startActivity(new Intent(SelectActivity.this, AbilityActivity.class));
    }

    public void gotoGameActivity() {
        Intent intent = new Intent(SelectActivity.this, GameActivity.class);
        intent.putExtra("floor", 1);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MusicService.class));
        selectView.bagground.recycle();
        selectView.bmskiil.recycle();
        selectView.bmperson.recycle();
        selectView.background.recycle();
        selectView = null;
        System.gc();
    }
}
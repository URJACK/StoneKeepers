package ifox.sicnu.com.mag10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.View.StartView;

/**
 * 点开ＡＰＰ后进入的第一个Ａctivity，这个activity 承担了对全局变量进行初始化赋值的作用。
 */
public class StartActivity extends Activity {
    StartView startView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Const.SCREENHEIGHT = getResources().getDisplayMetrics().heightPixels;
        Const.SCREENWIDTH = getResources().getDisplayMetrics().widthPixels;
        Const.PERSON_LCTY = (int) (Const.SCREENHEIGHT * 0.6);
        Const.OPERATE_HEIGHT = (int) (Const.SCREENHEIGHT * 0.6);
        Const.BUTTON_WIDTH = (int) (Const.SCREENWIDTH * 0.12);
        Const.SKILL_WIDTH = (int) (Const.SCREENHEIGHT * 0.078);
        Const.SKILL_HEIGHT = (int) (Const.SCREENWIDTH * 0.16);
        Const.BAG_WIDTH = (int) (Const.SCREENHEIGHT * 0.05);
        Const.BAG_HEIGHT = (int) (Const.SCREENWIDTH * 0.09);
        Const.BAG_FLOATWIDTH = (int) (Const.SCREENHEIGHT * 0.15);
        Const.BAG_FLOATHEIGHT = (int) (Const.SCREENWIDTH * 0.27);
        Const.CELL_WIDTH = (int) (Const.SCREENHEIGHT * 0.067);
        Const.CELL_HEIGHT = (int) (Const.SCREENWIDTH * 0.11);
        Const.BASE_CELL_OFFX = (int) (Const.SCREENWIDTH*0.115);
        Const.BASE_CELL_OFFY = (int) (Const.SCREENHEIGHT*0.0666);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ((APP) getApplicationContext()).createDatas(this);      //执行数据创建的创建过程
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startView = new StartView(this);
        setContentView(startView);
    }

    public void gotoNextActivity() {
        startActivity(new Intent(StartActivity.this, ReadyActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startView.clear();
        startView = null;
    }

}

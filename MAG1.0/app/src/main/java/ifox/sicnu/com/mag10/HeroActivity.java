package ifox.sicnu.com.mag10;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.View.HeroView;

/**
 * 这个界面用来呈现英雄列表，从而让玩家可以改变自己所选的英雄
 */
public class HeroActivity extends Activity implements ExitActivity {
    TextView textView;      //介绍英雄属性
    HeroView heroView;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        heroView = new HeroView(this, 1);
        textView = new TextView(this);
        heroView.setTextView(textView);
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams((int) (Const.SCREENHEIGHT * 0.4), (int) (Const.SCREENWIDTH * 0.3));
        lp2.topMargin = (int) (Const.SCREENWIDTH * 0.65);
        lp2.leftMargin = (int) (Const.SCREENHEIGHT * 0.3);

        rl = new RelativeLayout(this);
        rl.addView(heroView);
        rl.addView(textView);
        heroView.setLayoutParams(lp1);
        textView.setLayoutParams(lp2);

        setContentView(rl);
    }

    @Override
    public void exitself() {
        this.finish();
    }
}

package ifox.sicnu.com.mag10;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import ifox.sicnu.com.mag10.View.EndView;

public class EndActivity extends Activity {

    boolean type; //结束游戏的类型true（success）or false(failer)
    EndView endView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        endView = new EndView(this);
        setContentView(endView);
    }
}

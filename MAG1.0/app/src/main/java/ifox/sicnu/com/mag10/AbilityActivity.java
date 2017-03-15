package ifox.sicnu.com.mag10;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.View.AbilityView;


public class AbilityActivity extends Activity {
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        player = ((APP)getApplicationContext()).getPlayer();
        setContentView(new AbilityView(this,player));
    }
}

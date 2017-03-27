package ifox.sicnu.com.mag10.TouchLogic;

import android.util.Log;
import android.view.MotionEvent;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.ExitActivity;

/**
 * Created by Funchou Fu on 2017/3/27.
 */
public class ExitTouchLogic {
    private static final String TAG = "ExitTouchLogic";
    ExitActivity ha;
    boolean flag;              //是否按压到了按钮

    public ExitTouchLogic(ExitActivity heroActivity) {
        ha = heroActivity;
    }

    //给界面添加退出的逻辑
    public void exitTouch(MotionEvent event) {
        Log.i(TAG, "exitTouch: ");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getX() < Const.SCREENHEIGHT * 0.08 && event.getY() > Const.SCREENWIDTH * 0.9) {
                flag = true;
                Log.i(TAG, "exitTouch: okok");
            } else
                flag = false;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getX() < Const.SCREENHEIGHT * 0.08 && event.getY() > Const.SCREENWIDTH * 0.9 && flag) {
                ha.exitself();
                Log.i(TAG, "exitTouch: gogogo");
            }
        }

    }
}

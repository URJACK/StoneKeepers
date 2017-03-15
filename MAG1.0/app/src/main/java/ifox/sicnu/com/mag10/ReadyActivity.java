package ifox.sicnu.com.mag10;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import ifox.sicnu.com.mag10.Service.MusicService;
import ifox.sicnu.com.mag10.View.ReadyView;

/**
 * 这个Activity开始进行播放背景音乐，同时开始进行记录用户的三个选择：
 * 1`开始
 * 2`教程
 * 3`退出
 */
public class ReadyActivity extends Activity {

    private String TAG = "ReadyActivity";
    MusicService musicService;
    ReadyView readyView;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            musicService = ((MusicService.ServiceBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            musicService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((APP) getApplicationContext()).readyActivity = this;
        bindService(new Intent(ReadyActivity.this, MusicService.class), conn, Context.BIND_AUTO_CREATE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        readyView = new ReadyView(this);
        setContentView(new ReadyView(this));
        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra("number", 0);
        startService(intent);
    }

    public void gotoSelectActivity() {
        startActivity(new Intent(ReadyActivity.this, SelectActivity.class));
        setContentView(new View(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        readyView.clear();
        readyView = null;
        System.gc();
        unbindService(conn);
        stopService(new Intent(this, MusicService.class));
    }

    /*接收选择英雄activity 带来的改变*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

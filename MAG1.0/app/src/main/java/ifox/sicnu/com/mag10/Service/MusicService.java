package ifox.sicnu.com.mag10.Service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/2/22.
 */
public class MusicService extends Service {
    private static final String TAG = "MusicService";
    MediaPlayer mp;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final int sungnumber = intent.getIntExtra("number", 0);
        Log.i(TAG, "onStartCommand: " + sungnumber);
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mp = GetMusic(sungnumber);
                mp.setLooping(true);
                if (mp.isPlaying()) {
                    mp.reset();
                }
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.start();
            }
        };
        Thread t1 = new Thread(runnable);
        t1.start();

        return START_NOT_STICKY;
    }

    private MediaPlayer GetMusic(int sungnumber) {
        switch (sungnumber) {
            case 0:
                return MediaPlayer.create(this, R.raw.tavern);
            case 1:
                return MediaPlayer.create(this, R.raw.viliage);
        }
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: STOPSTOPSTOPSTOPSTOPSTOPSTOPSTOPSTOPSTOPSTOP");
        if (mp != null && mp.isPlaying()) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }



    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        return null;
    }

    public class ServiceBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
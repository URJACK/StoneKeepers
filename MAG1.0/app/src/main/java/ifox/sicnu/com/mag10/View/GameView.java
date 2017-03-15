package ifox.sicnu.com.mag10.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.ShopManager;
import ifox.sicnu.com.mag10.DrawLogic.DrawGame;
import ifox.sicnu.com.mag10.DrawLogic.DrawPlayer;
import ifox.sicnu.com.mag10.DrawLogic.DrawSkill;
import ifox.sicnu.com.mag10.R;
import ifox.sicnu.com.mag10.Thread.DrawThread;
import ifox.sicnu.com.mag10.TouchLogic.BagTouch;
import ifox.sicnu.com.mag10.TouchLogic.GameTouch;
import ifox.sicnu.com.mag10.TouchLogic.HeroTouch;

/**
 * Created by Funchou Fu on 2017/3/8.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback, DrawInterface {
    private static final String TAG = "GAMEVIEW";
    Context mcontext;
    BagTouch bagTouch;
    Player player;
    DrawGame drawGame;
    Bitmap bitmap;
    Bitmap background;
    Bitmap undiscovered;
    Pictures pictures;
    DrawPlayer drawPlayer;
    DrawSkill drawSkill;
    HeroTouch heroTouch;
    Bitmap skill_bg;
    DrawThread drawThread;
    BattleManager battleManager;
    ShopManager shopManager;
    GameTouch gameTouch;

    public GameView(Context context,int floor) {
        super(context);
        getHolder().addCallback(this);
        this.mcontext = context;
        pictures = ((APP) (mcontext.getApplicationContext())).getPictures();
        background = pictures.getBitmap("gameview_bg");
        bitmap = pictures.getBitmap("person_bag");
        undiscovered = BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.gameview_undiscover2);
        undiscovered = Bitmap.createScaledBitmap(undiscovered, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        player = ((APP) (mcontext.getApplicationContext())).getPlayer();
        skill_bg = pictures.getBitmap("person_skill");
        shopManager = new ShopManager(mcontext, player);
        battleManager = new BattleManager(context, shopManager);
        battleManager.createFloor(floor);
        init();
    }

    private void init() {
        bagTouch = new BagTouch(0, 0, (int) (Const.SCREENHEIGHT * 0.6), Const.SCREENWIDTH, player);
        drawGame = new DrawGame(player, 0, 0, (int) (Const.SCREENHEIGHT * 0.6), Const.SCREENWIDTH, background, bitmap, undiscovered, bagTouch, battleManager);
        drawPlayer = new DrawPlayer(player, pictures, (int) (Const.SCREENHEIGHT * 0.6), 0, (int) (Const.SCREENHEIGHT * 0.25), Const.SCREENWIDTH);
        drawSkill = new DrawSkill(player, (int) (Const.SCREENHEIGHT * 0.85), 0, (int) (Const.SCREENHEIGHT * 0.25), Const.SCREENWIDTH, skill_bg, battleManager);
        heroTouch = new HeroTouch((int) (Const.SCREENHEIGHT * 0.6), 0, (int) (Const.SCREENHEIGHT * 0.25), Const.SCREENWIDTH, player);
        gameTouch = new GameTouch(battleManager);
        System.gc();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        drawThread = new DrawThread(this, 50);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        drawThread.flag = false;
        drawThread = null;
    }

    @Override
    public void doDraw(Canvas canvas) {
        drawGame.doCanvas(canvas);
        drawPlayer.doCanvas(canvas);
        drawSkill.doCanvas(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        heroTouch.getTouch(event);
        bagTouch.getTouch(event);
        gameTouch.getTouch(event);
        return true;
    }


    public void clear(){
        bitmap.recycle();
        background.recycle();
        skill_bg.recycle();
    }
}
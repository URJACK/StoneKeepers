package ifox.sicnu.com.mag10.View;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.HeroBuff;
import ifox.sicnu.com.mag10.Data.Heroes;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DrawLogic.DrawBackground;
import ifox.sicnu.com.mag10.DrawLogic.DrawPlayer;
import ifox.sicnu.com.mag10.DrawLogic.DrawSkill;
import ifox.sicnu.com.mag10.DrawLogic.Draw_SelectViewBg;
import ifox.sicnu.com.mag10.SelectActivity;
import ifox.sicnu.com.mag10.Thread.DrawThreadDouble;
import ifox.sicnu.com.mag10.TouchLogic.BagTouch;
import ifox.sicnu.com.mag10.TouchLogic.HeroTouch;

/**
 * Created by Funchou Fu on 2017/2/23.
 */
public class SelectView extends SurfaceView implements SurfaceHolder.Callback, DrawInterface {
    DrawThreadDouble dt;
    Pictures pictures;
    Heroes heroes;
    HeroBuff heroBuff;
    Player player;
    DrawPlayer drawPlayer;      //绘制player 的对象
    HeroTouch heroTouch;
    BagTouch bagTouch;
    DrawBackground drawBackground;
    DrawSkill drawSkill;

    public Bitmap background;      //游戏背景界面
    public Bitmap bmperson;        //人物界面
    public Bitmap bmskiil;         //人物使用技能界面
    public Bitmap bagground;       //人物背包界面

    SurfaceHolder sh;

    Context mContext;

    private boolean select_Hero = false;         //被按压的坐标，是否为选择英雄
    private boolean select_Ability = false;
    private boolean select_Game = false;

    public SelectView(Context context) {
        super(context);
        mContext = context;
        getHolder().addCallback(this);
        sh = getHolder();
        APP app = ((APP) context.getApplicationContext());
        pictures = app.getPictures();
        IniBitmap();
        heroBuff = app.getHeroBuff();
        heroes = app.getHeroes();
        player = new Player(heroes.getHero("弗拉基米尔"), heroBuff);
        Initilization();
        ((APP) mContext.getApplicationContext()).setPlayer(player);
    }

    private void Initilization() {
        drawPlayer = new DrawPlayer(player, pictures, Const.OPERATE_HEIGHT, 0, (int) (Const.SCREENHEIGHT * 0.25), Const.SCREENWIDTH);
        heroTouch = new HeroTouch(Const.OPERATE_HEIGHT, 0, (int) (Const.SCREENHEIGHT * 0.25), Const.SCREENWIDTH, player);
        bagTouch = new BagTouch(0, 0, (int) (Const.SCREENHEIGHT * 0.6), Const.SCREENWIDTH, player);
        drawBackground = new Draw_SelectViewBg(player, 0, 0, (int) (Const.SCREENHEIGHT * 0.6), Const.SCREENWIDTH, background, bagground, bagTouch);
        drawSkill = new DrawSkill(player, (int) (Const.SCREENHEIGHT * 0.85), 0, (int) (Const.SCREENHEIGHT * 0.15), Const.SCREENWIDTH, bmskiil);
    }

    private void IniBitmap() {
        background = pictures.getBitmap("selectview_background");
        bmperson = pictures.getBitmap("person");
        bmskiil = pictures.getBitmap("person_skill");
        bagground = pictures.getBitmap("person_bag");
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        dt = new DrawThreadDouble(this, 50, pictures);
        if (!dt.isAlive()) {
            dt.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        dt.flag = false;
        dt = null;
    }

    @Override
    public void doDraw(Canvas canvas) {
        drawBackground.doCanvas(canvas);
        drawPlayer.doCanvas(canvas);
        drawSkill.doCanvas(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            if (event.getX() < Const.SCREENHEIGHT / 5 && event.getY() < Const.SCREENWIDTH / 3) {
                select_Hero = true;
            }//点击到了选择英雄的界面---动作1
            else {
                select_Hero = false;
            }
            if (event.getX() < Const.SCREENHEIGHT * 20 / 37 && event.getX() > Const.SCREENHEIGHT * 17 / 37 && event.getY() < Const.SCREENWIDTH / 3) {
                select_Ability = true;
            } else {
                select_Ability = false;
            }

            if (event.getX() < Const.SCREENHEIGHT * 0.34 && event.getX() > Const.SCREENHEIGHT * 0.27 && event.getY() < Const.SCREENWIDTH * 0.56 && event.getY() > Const.SCREENWIDTH * 0.44) {
                select_Game = true;
            } else {
                select_Game = false;
            }
        } else if (action == MotionEvent.ACTION_UP) {
            if (event.getX() < Const.SCREENHEIGHT / 5 && event.getY() < Const.SCREENWIDTH / 3 && select_Hero && !player.bagswitch) {
                select_Hero = false;
                ((SelectActivity) getContext()).gotoHeroActivity();
            }//点击到了选择英雄的界面---动作2
            if (event.getX() < Const.SCREENHEIGHT * 20 / 37 && event.getX() > Const.SCREENHEIGHT * 17 / 37 && event.getY() < Const.SCREENWIDTH / 3 && select_Ability && !player.bagswitch) {
                ((SelectActivity) getContext()).gotoAbilityActivity();
            }
            if (event.getX() < Const.SCREENHEIGHT * 0.34 && event.getX() > Const.SCREENHEIGHT * 0.27 && event.getY() < Const.SCREENWIDTH * 0.56 && event.getY() > Const.SCREENWIDTH * 0.44 && select_Game) {
                Toast.makeText(mContext, "dianjile", Toast.LENGTH_SHORT).show();
                ((SelectActivity) getContext()).gotoGameActivity();
            }

        }
        heroTouch.getTouch(event);      //英雄界面的监听处理
        bagTouch.getTouch(event);
        Canvas canvas = sh.lockCanvas();
        doDraw(canvas);
        sh.unlockCanvasAndPost(canvas);
        return true;
    }

    /**
     * 在执行完HeroView 后的改变英雄操作后
     */
    public void changeHero(Hero hero) {
        if (hero == null)
            return;
        player = new Player(hero, heroBuff);
        Initilization();
        ((APP) mContext.getApplicationContext()).setPlayer(player);
    }

}
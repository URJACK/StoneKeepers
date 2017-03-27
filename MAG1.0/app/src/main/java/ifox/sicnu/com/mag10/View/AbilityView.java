package ifox.sicnu.com.mag10.View;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.HeroBuff;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DrawLogic.DrawAbility;
import ifox.sicnu.com.mag10.ExitActivity;
import ifox.sicnu.com.mag10.TouchLogic.AbilityTouch;
import ifox.sicnu.com.mag10.TouchLogic.ExitTouchLogic;

/**
 * Created by 41988 on 2017/3/3.
 */
public class AbilityView extends SurfaceView implements SurfaceHolder.Callback {
    private HeroBuff heroBuff;
    private Player player;
    private APP mapp;
    public static final int COST = 15;
    ExitTouchLogic etl;


    private int offset;
    private int offset_2;
    private int y;
    private int click_x;
    private int click_y;
    private Context mcontext;

    private AbilityTouch abilityTouch;
    private DrawAbility drawAbility;

    public AbilityView(Context context, Player player) {
        super(context);
        getHolder().addCallback(this);
        this.player = player;
        mapp = (APP) (context.getApplicationContext());
        heroBuff = mapp.getHeroBuff();
        abilityTouch = new AbilityTouch();
        drawAbility = new DrawAbility(context, heroBuff);
        this.mcontext = context;
        etl = new ExitTouchLogic((ExitActivity) context);
    }

    //当前activity 调用画的逻辑
    public void Draw(int offset) {
        Canvas canvas = getHolder().lockCanvas();
        drawAbility.setMoney(player.money);

        drawAbility.isSlide(offset);
        drawAbility.doCanvas(canvas);
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        etl.exitTouch(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            y = (int) event.getY();
            click_x = (int) event.getX();
            click_y = (int) event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (offset_2 > 1500) {
                offset_2 = 1500;
            } else if (offset_2 < -100) {
                offset_2 = -100;
            }
            offset = (int) (y - event.getY());
            offset_2 += offset;
            y = (int) event.getY();
            Draw(offset_2);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            y = (int) event.getY();
            click_y = (int) event.getY();
            click_x = (int) event.getX();
            //判断点击按钮
            // if(click_x > Const.SCREENHEIGHT*17/25&&click_x<Const.SCREENHEIGHT*4/5&&click_y>Const.SCREENWIDTH*0.24&&click_y<Const.SKILL_WIDTH*0.32){}
            if (click_x > Const.SCREENHEIGHT * 17 / 25 && click_x < Const.SCREENHEIGHT * 4 / 5 && click_y > Const.SCREENWIDTH * 0.17) {
                int topofs = (int) (Const.SCREENHEIGHT / 18 + Const.SCREENWIDTH * 5 / 16);
                for (int i = 0; i < 5; i++) {
                    if (click_y > Const.SCREENWIDTH * 0.24 - offset_2 + (i * topofs) && click_y < Const.SCREENWIDTH * 0.32 - offset_2 + (i * topofs)) {
                        if (player.money >= COST) {
                            AbilityUp(i);
                            Draw(offset_2);
                            break;
                        } else {
                            Toast.makeText(mcontext, "你的金钱不够！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }


        }

        return true;

    }

    public void AbilityUp(int id) {
        switch (id) {
            case 0:
                if (heroBuff.hp == 5) {
                    Toast.makeText(mcontext, "你都升到了满级，不用升级了", Toast.LENGTH_SHORT).show();
                    break;
                }
                heroBuff.hp++;
                player.money = player.money - COST;
                player.maxHp += 5;
                player.hp = player.maxHp;
                heroBuff.saveValue();
                break;
            case 1:
                if (heroBuff.mp == 5) {
                    Toast.makeText(mcontext, "你都升到了满级，不用升级了", Toast.LENGTH_SHORT).show();
                    break;
                }
                heroBuff.mp++;
                player.money = player.money - COST;
                player.maxMp += 2;
                player.mp = player.maxMp;
                heroBuff.saveValue();
                break;
            case 2:
                if (heroBuff.pp == 5) {
                    Toast.makeText(mcontext, "你都升到了满级，不用升级了", Toast.LENGTH_SHORT).show();
                    break;
                }
                heroBuff.pp++;
                player.maxPp += 3;
                player.money = player.money - COST;
                heroBuff.saveValue();
                break;
            case 3:
                if (heroBuff.atk == 5) {
                    Toast.makeText(mcontext, "你都升到了满级，不用升级了", Toast.LENGTH_SHORT).show();
                    break;
                }
                heroBuff.atk++;
                player.atk += 2;
                player.money = player.money - COST;
                heroBuff.saveValue();
                break;
            case 4:
                if (heroBuff.armor == 5) {
                    Toast.makeText(mcontext, "你都升到了满级，不用升级了", Toast.LENGTH_SHORT).show();
                    break;
                }
                heroBuff.armor++;
                player.armor += 4;
                player.money = player.money - COST;
                heroBuff.saveValue();
                break;

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Draw(offset);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
//abilityView

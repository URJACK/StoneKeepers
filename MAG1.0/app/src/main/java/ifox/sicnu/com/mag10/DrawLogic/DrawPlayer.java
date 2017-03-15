package ifox.sicnu.com.mag10.DrawLogic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.SelectActivity;

/**
 * Created by Funchou Fu on 2017/2/23.
 * 2017年3月12日17:43:39 绘制各种条。。
 *
 */
public class DrawPlayer implements ModeDrawIn{
    private static final String TAG = "SelectActivity";
    public int base_X;
    public int base_Y;          //绘制Play界面的左上角的坐标
    public int width;
    public int height;          //Play界面的宽度与长度
    private Player player;       //需要被绘制的Player
    private Pictures pictures;
    private Bitmap bag_close;
    private Bitmap bag_open;
    private Bitmap weapon_1;
    private Bitmap weapon_2;
    private Bitmap background;
    private Bitmap face;
    private Paint paint;

    public DrawPlayer(Player player, Pictures pictures, int x, int y, int width, int height) {
        this.player = player;
        this.pictures = pictures;
        this.base_X = x;
        this.base_Y = y;
        this.width = width;
        this.height = height;

        background = pictures.getBitmap("person");
        bag_open = pictures.getBitmap("person_bagopen");
        bag_close = pictures.getBitmap("person_bagclose");
        face = player.face;
        weapon_1 = pictures.getBitmap("person_weapon1");
        weapon_2 = pictures.getBitmap("person_weapon2");

        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(Const.SCREENWIDTH / 21);
    }

    @Override
    public void doCanvas(Canvas canvas) {
        canvas.drawBitmap(face, base_X + (int) (width * 0.325), base_Y + (int) (height * 0.325), null);
        //绘制人物头像
        canvas.drawBitmap(background, base_X, base_Y, null);
        if (player.bagswitch == true)
            canvas.drawBitmap(bag_open, (int) (width * 0.1) + base_X, base_Y + (int) (height * 0.14), null);
        else
            canvas.drawBitmap(bag_close, (int) (width * 0.1) + base_X, base_Y + (int) (height * 0.14), null);
        if (player.skillswitch == true) {
            canvas.drawBitmap(weapon_1, base_X + (int) (width * 0.7), base_Y + (int) (height * 0.14), null);
        }//需要额外绘制技能栏
        else {
            canvas.drawBitmap(weapon_2, base_X + (int) (width * 0.7), base_Y + (int) (height * 0.14), null);
        }//需要额外绘制道具栏
        paint.setColor(Color.YELLOW);
        canvas.drawText(String.valueOf(player.money), base_X + (int) (width * 0.25), base_Y + (int) (height * 0.07), paint);
        //绘制现在有多少钱
        canvas.drawText(String.valueOf(player.key), base_X + (int) (width * 0.78), base_Y + (int) (height * 0.07), paint);
        //绘制现在有多少钥匙
        paint.setColor(Color.GRAY);
        canvas.drawText(String.valueOf(player.atk), base_X + (int) (width * 0.22), base_Y + (int) (height * 0.6), paint);
        //绘制现在攻击力
        canvas.drawText(String.valueOf(player.def), base_X + (int) (width * 0.76), base_Y + (int) (height * 0.6), paint);
        //绘制现在护甲
        paint.setColor(Color.WHITE);
        canvas.drawText(String.valueOf(player.level), base_X + (int) (width * 0.47), base_Y + (int) (height * 0.57), paint);
        RectF exe_rect = new RectF((int)(Const.SCREENHEIGHT*0.712),(int)(Const.SCREENWIDTH*0.51),(int)(Const.SCREENHEIGHT*0.747),(int)(Const.SCREENWIDTH*0.586));
        paint.setColor(Color.GREEN);
        paint.setAlpha(125);

        canvas.drawArc(exe_rect, -90, 360 * player.exp / (player.getNeedExp()), true, paint);


        //canvas.drawArc(exe_rect,-90,180,false,paint);
        //绘制现在等级
        paint.setColor(Color.LTGRAY);
        canvas.drawText(String.valueOf(player.pp), base_X + (int) (width * 0.23), base_Y + (int) (height * 0.72), paint);
        Rect pp_rect = new Rect((int)(Const.SCREENHEIGHT*0.78),(int)(Const.SCREENWIDTH*0.96-(Const.SCREENWIDTH*0.28*player.mp/player.maxMp)),(int)(Const.SCREENHEIGHT*0.8),(int)(Const.SCREENWIDTH*0.96));
        paint.setAlpha(130);
        canvas.drawRect(pp_rect,paint);
        //绘制现在有多少魂力
        paint.setColor(Color.GREEN);
        canvas.drawText(String.valueOf(player.mp), base_X + (int) (width * 0.71), base_Y + (int) (height * 0.72), paint);
        Rect mp_rect = new Rect((int)(Const.SCREENHEIGHT*0.65),(int)(Const.SCREENWIDTH*0.96-(Const.SCREENWIDTH*0.28*player.pp/player.maxPp)),(int)(Const.SCREENHEIGHT*0.69),(int)(Const.SCREENWIDTH*0.96));
        paint.setColor(Color.BLUE);
        paint.setAlpha(90);
        canvas.drawRect(mp_rect,paint);
        //绘制现在有多少法力
        paint.setColor(Color.RED);
        canvas.drawText(String.valueOf(player.hp), base_X + (int) (width * 0.47), base_Y + (int) (height * 0.96), paint);
        //绘制现在有多少生命
        Rect hp_rect = new Rect((int)(Const.SCREENHEIGHT*0.71),(int)(Const.SCREENWIDTH*0.71),(int)(Const.SCREENHEIGHT*0.76),(int)((Const.SCREENWIDTH*0.71+(Const.SCREENWIDTH*0.25*player.hp/player.maxHp))));
        paint.setAlpha(125);
        canvas.drawRect(hp_rect,paint);

    }
}

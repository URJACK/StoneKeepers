package ifox.sicnu.com.mag10.DrawLogic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Player;

/**
 * Created by Funchou Fu on 2017/2/23.
 * 这个类用来绘制人物的窗口
 */
public class DrawSkill implements ModeDrawIn {
    private Player player;
    private Bitmap background;
    private int baseX;
    private int baseY;
    private int width;
    private int height;
    private int offset;
    private BattleManager bm;
    Paint paint;

    public DrawSkill(Player player, int baseX, int baseY, int width, int height, Bitmap background) {
        this.player = player;
        this.baseX = baseX;
        this.baseY = baseY;
        this.width = width;
        this.height = height;
        this.background = background;
        this.offset = (int) (this.height * 0.2);

    }

    public DrawSkill(Player player, int baseX, int baseY, int width, int height, Bitmap background, BattleManager bm) {
        this(player, baseX, baseY, width, height, background);
        this.bm = bm;
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAlpha(126);
    }


    @Override
    public void doCanvas(Canvas canvas) {
        canvas.drawBitmap(background, baseX, baseY, null);
        if (player.skillswitch) {
            if (player.skills[0] != null) {
                canvas.drawBitmap(player.skills[0].bitmap, baseX + (int) (Const.SCREENHEIGHT * 0.038), baseY + (int) (Const.SCREENWIDTH * 0.095), null);
            }
            if (player.skills[1] != null) {
                canvas.drawBitmap(player.skills[1].bitmap, baseX + (int) (Const.SCREENHEIGHT * 0.038), baseY + (int) (Const.SCREENWIDTH * 0.406), null);
            }
            if (player.skills[2] != null) {
                canvas.drawBitmap(player.skills[2].bitmap, baseX + (int) (Const.SCREENHEIGHT * 0.038), baseY + (int) (Const.SCREENWIDTH * 0.715), null);
            }
        } else {
            if (player.tools[0] != null) {
                canvas.drawBitmap(player.tools[0].bitmap, baseX + (int) (Const.SCREENHEIGHT * 0.038), baseY + (int) (Const.SCREENWIDTH * 0.095), null);
            }
            if (player.tools[1] != null) {
                canvas.drawBitmap(player.tools[1].bitmap, baseX + (int) (Const.SCREENHEIGHT * 0.038), baseY + (int) (Const.SCREENWIDTH * 0.406), null);
            }
            if (player.tools[2] != null) {
                canvas.drawBitmap(player.tools[2].bitmap, baseX + (int) (Const.SCREENHEIGHT * 0.038), baseY + (int) (Const.SCREENWIDTH * 0.715), null);
            }
        }
        if (bm != null && bm.function_switch != -1) {
            canvas.drawCircle((int) (Const.SCREENHEIGHT * 0.93), (int) (Const.SCREENWIDTH * 0.180) + bm.function_switch * (int) (Const.SCREENWIDTH * 0.31), (int) (Const.SCREENWIDTH * 0.085), paint);
        }
    }
}

package ifox.sicnu.com.mag10.DrawLogic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Equipment;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.TouchLogic.BagTouch;

/**
 * Created by Funchou Fu on 2017/2/27.
 * 绘制主要操作界面
 */
public class DrawBackground implements ModeDrawIn {
    Player player;
    Bitmap background;      //非背包的背景
    Bitmap bagground;       //背包的背景
    Bitmap bagfloat;        //点击装备后的悬浮框背景

    protected int baseX;
    protected int baseY;
    protected int width;            //1160
    protected int height;           //1060

    private int baseoffx;       //装备栏第三件装备初始横坐标量
    private int offx_1;     //装备栏的每件装备横坐标偏移量  128
    private int offy_1;     //装备栏的每件装备纵坐标偏移量 130
    private int offx_2;     //背包里的每件装备横坐标偏移量 100
    private int offy_2;     //背包里的每件装备纵坐标偏移量 110
    private int offy_3;
    private String TAG = "SelectActivity";
    BagTouch bagTouch;
    private Paint paint;

    public DrawBackground(Player player, int baseX, int baseY, int width, int height, Bitmap background, Bitmap bagground, BagTouch bagTouch) {
        this.player = player;
        this.baseX = baseX;
        this.baseY = baseY;
        this.width = width;
        this.height = height;
        this.background = background;
        this.bagground = bagground;
        this.bagTouch = bagTouch;
        offx_1 = (int) (width * 0.13);
        offy_1 = (int) (height * 0.12);
        offx_2 = (int) (width * 0.118);
        offy_2 = (int) (height * 0.1);
        offy_3 = (int) (height * 0.052);

        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(Const.SCREENWIDTH / 30);
    }

    //具体绘制其他界面的逻辑在子类中体现
    @Override
    public void doCanvas(Canvas canvas) {
        if (player.bagswitch) {
            drawPlayerBag(canvas);

        }
    }

    //绘制背包界面以及背包内的其他内容的逻辑
    protected void drawPlayerBag(Canvas canvas) {
        canvas.drawBitmap(bagground, baseX, baseY, null);
        drawEquipmentMove(canvas);
        drawEquipment(canvas);
        drawPlayerShuXing(canvas);
        drawEquipmentInformation(canvas);
    }

    //绘制玩家点击一件装备后，这件装备会出现的悬浮框
    private void drawEquipmentInformation(Canvas canvas) {
        if (bagTouch.needtoShow != null) {
            Equipment need = bagTouch.needtoShow;
            int x = bagTouch.x + Const.BAG_WIDTH / 2;
            int y = bagTouch.y + Const.BAG_HEIGHT / 2;
            String moneystring = String.format("%d$", need.money);
            int length = (need.getRealName().length() + moneystring.length());
            int floatheight = (int) (Const.BAG_FLOATHEIGHT * ((float) length / 4));
            int floatwidth = (int) (Const.BAG_FLOATWIDTH * ((float) length / 4));     //悬浮窗的宽高

            paint.setColor(Color.rgb(109, 109, 111));
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(x, y, x + floatwidth, y + floatheight, paint);
            paint.setColor(Color.rgb(25, 12, 30));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(x + 2, y + 2, x + floatwidth - 2, y + floatheight - 2, paint);
            // 绘制悬浮窗的背景

            x += Const.BAG_WIDTH * 0.05;
            y += Const.BAG_HEIGHT * 0.35;
            paint.setColor(Color.YELLOW);
            canvas.drawText(need.getRealName(), x, y, paint);                               //绘制武器的名字
            canvas.drawText(moneystring, x + floatwidth * (need.getRealName().length() / (float) length), y, paint);          //绘制武器价值


            if (need.atk != 0) {
                paint.setColor(Color.rgb(100, 50, 50));
                y += floatheight * 0.15;
                canvas.drawText(String.format("伤害 增加 %d", need.atk), x, y, paint);
            }
            if (need.hitrate != 0) {
                paint.setColor(Color.rgb(100, 50, 100));
                y += floatheight * 0.15;
                canvas.drawText(String.format("命中 增加 %.2f", need.hitrate), x, y, paint);
            }
            if (need.crit != 0) {
                paint.setColor(Color.rgb(150, 50, 50));
                y += floatheight * 0.15;
                canvas.drawText(String.format("爆击 增加 %.2f", need.crit), x, y, paint);
            }
            if (need.armor != 0) {
                paint.setColor(Color.GREEN);
                y += floatheight * 0.15;
                canvas.drawText(String.format("护甲 增加 %d", need.armor), x, y, paint);
            }
            if (need.maxHp != 0) {
                paint.setColor(Color.GREEN);
                y += floatheight * 0.15;
                canvas.drawText(String.format("生命 增加 %d", need.maxHp), x, y, paint);
            }
            if (need.maxMp != 0) {
                paint.setColor(Color.GREEN);
                y += floatheight * 0.15;
                canvas.drawText(String.format("法力 增加 %d", need.maxMp), x, y, paint);
            }
            if (need.maxPp != 0) {
                paint.setColor(Color.GREEN);
                y += floatheight * 0.15;
                canvas.drawText(String.format("灵魂 增加 %d", need.maxPp), x, y, paint);
            }


        }
    }

    //绘制玩家拖动装备的效果
    private void drawEquipmentMove(Canvas canvas) {
        if (bagTouch.touchNumber != -1) {
            Bitmap bitmapMove = null;
            if (bagTouch.touchNumber < 12) {
                if (player.equipments[bagTouch.touchNumber] != null)
                    bitmapMove = player.equipments[bagTouch.touchNumber].bitmap;
            } else {
                switch (bagTouch.touchNumber) {
                    case 12:
                        if (player.weapon != null)
                            bitmapMove = player.weapon.bitmap;
                        break;
                    case 13:
                        if (player.co_weapon != null)
                            bitmapMove = player.co_weapon.bitmap;
                        break;
                    case 14:
                        if (player.helmet != null)
                            bitmapMove = player.helmet.bitmap;
                        break;
                    case 15:
                        if (player.hand != null)
                            bitmapMove = player.hand.bitmap;
                        break;
                    case 16:
                        if (player.wear_up != null)
                            bitmapMove = player.wear_up.bitmap;
                        break;
                    case 17:
                        if (player.belt != null)
                            bitmapMove = player.belt.bitmap;
                        break;
                    case 18:
                        if (player.wear_down != null)
                            bitmapMove = player.wear_down.bitmap;
                        break;
                    case 19:
                        if (player.shoe != null)
                            bitmapMove = player.shoe.bitmap;
                        break;
                }
            }
            if (bitmapMove != null)
                canvas.drawBitmap(bitmapMove, bagTouch.x - Const.BAG_WIDTH / 2, bagTouch.y - Const.BAG_HEIGHT / 2, null);
        }//此时说明有物体正在被选中
    }

    //绘制玩家所拥有的装备
    private void drawEquipment(Canvas canvas) {
        for (int i = 0; i < 12; i++) {
            if (player.equipments[i] != null && bagTouch.touchNumber != i) {
                canvas.drawBitmap(player.equipments[i].bitmap, baseX + (int) (width * 0.275) + (i % 4) * offx_2, baseY + (int) (height * 0.34) + (i / 4) * offy_2, null);
            }
        }

        if (player.weapon != null && bagTouch.touchNumber != 12)
            canvas.drawBitmap(player.weapon.bitmap, baseX + (int) (width * 0.34), baseY + (int) (height * 0.05), null);
        //主武器 x380 y50

        if (player.co_weapon != null && bagTouch.touchNumber != 13)
            canvas.drawBitmap(player.co_weapon.bitmap, baseX + (int) (width * 0.34), baseY + (int) (height * 0.05) + offy_1, null);
        //副武器

        if (player.helmet != null && bagTouch.touchNumber != 14)
            canvas.drawBitmap(player.helmet.bitmap, baseX + (int) (width * 0.515), baseY + (int) (height * 0.05), null);

        if (player.hand != null && bagTouch.touchNumber != 15)
            canvas.drawBitmap(player.hand.bitmap, baseX + (int) (width * 0.515), baseY + (int) (height * 0.05) + offy_1, null);

        if (player.wear_up != null && bagTouch.touchNumber != 16)
            canvas.drawBitmap(player.wear_up.bitmap, baseX + (int) (width * 0.515) + offx_1, baseY + (int) (height * 0.05), null);

        if (player.belt != null && bagTouch.touchNumber != 17)
            canvas.drawBitmap(player.belt.bitmap, baseX + (int) (width * 0.515) + offx_1, baseY + (int) (height * 0.05) + offy_1, null);

        if (player.wear_down != null && bagTouch.touchNumber != 18)
            canvas.drawBitmap(player.wear_down.bitmap, baseX + (int) (width * 0.515) + offx_1 * 2, baseY + (int) (height * 0.05), null);

        if (player.shoe != null && bagTouch.touchNumber != 19)
            canvas.drawBitmap(player.shoe.bitmap, baseX + (int) (width * 0.515) + offx_1 * 2, baseY + (int) (height * 0.05) + offy_1, null);
    }

    //绘制玩家的属性
    private void drawPlayerShuXing(Canvas canvas) {
        String s;

        paint.setColor(Color.YELLOW);
        canvas.drawText(String.valueOf(player.key), baseX + (int) (width * 0.17), baseY + (int) (height * 0.16), paint);
        canvas.drawText(String.valueOf(player.money), baseX + (int) (width * 0.18), baseY + (int) (height * 0.265), paint);

        paint.setColor(Color.RED);
        canvas.drawText(String.valueOf(player.r_power), baseX + (int) (width * 0.26), baseY + (int) (height * 0.83), paint);
        canvas.drawText(String.valueOf(player.atk), baseX + (int) (width * 0.53), baseY + (int) (height * 0.83), paint);
        canvas.drawText(String.valueOf(player.armor), baseX + (int) (width * 0.78), baseY + (int) (height * 0.83), paint);

        paint.setColor(Color.GREEN);
        canvas.drawText(String.valueOf(player.r_agile), baseX + (int) (width * 0.26), baseY + (int) (height * 0.83) + offy_3, paint);
        s = String.valueOf(player.hitrate);
        if (s.length() > 4)
            s = s.substring(0, 4);
        canvas.drawText(s, baseX + (int) (width * 0.53), baseY + (int) (height * 0.83) + offy_3, paint);
        s = String.valueOf(player.dodge);
        if (s.length() > 4)
            s = s.substring(0, 4);
        canvas.drawText(String.valueOf(player.dodge), baseX + (int) (width * 0.78), baseY + (int) (height * 0.83) + offy_3, paint);

        paint.setColor(Color.BLUE);
        canvas.drawText(String.valueOf(player.r_intelligence), baseX + (int) (width * 0.26), baseY + (int) (height * 0.83) + offy_3 * 2, paint);
        s = String.valueOf(player.crit);
        if (s.length() > 4)
            s = s.substring(0, 4);
        canvas.drawText(s, baseX + (int) (width * 0.53), baseY + (int) (height * 0.83) + offy_3 * 2, paint);
        s = String.valueOf(player.resistance);
        if (s.length() > 4)
            s = s.substring(0, 4);
        canvas.drawText(s, baseX + (int) (width * 0.78), baseY + (int) (height * 0.83) + offy_3 * 2, paint);

    }
}

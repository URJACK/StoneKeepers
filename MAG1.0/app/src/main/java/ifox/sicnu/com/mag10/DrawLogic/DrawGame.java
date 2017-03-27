package ifox.sicnu.com.mag10.DrawLogic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Buff.Buff;
import ifox.sicnu.com.mag10.DataStructure.Cell;
import ifox.sicnu.com.mag10.DataStructure.Equipment;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Shop;
import ifox.sicnu.com.mag10.DataStructure.Skill.Skill;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.SpecialEffects;
import ifox.sicnu.com.mag10.R;
import ifox.sicnu.com.mag10.TouchLogic.BagTouch;

/**
 * Created by Funchou Fu on 2017/3/8.
 */

public class DrawGame extends DrawBackground {
    private static final String TAG = "DrawGame";
    BattleManager battleManager;
    Paint paint;
    Bitmap undiscovered;//怪物周围的红叉叉
    Bitmap shopcell;        //地图上的商店图标
    Bitmap door;            //地图上的门
    Bitmap trap;            //地图上的陷阱
    Rect rect;

    public DrawGame(Player player, int baseX, int baseY, int width, int height, Bitmap background, Bitmap bagground, Bitmap undiscovered, BagTouch bagTouch, BattleManager battleManager) {
        super(player, baseX, baseY, width, height, background, bagground, bagTouch);
        this.battleManager = battleManager;
        this.undiscovered = undiscovered;
        this.door = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.gameview_door);
        this.door = Bitmap.createScaledBitmap(this.door, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);

        this.shopcell = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.gameview_shopcell);
        this.shopcell = Bitmap.createScaledBitmap(this.shopcell, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);

        this.trap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.trap_stone);
        this.trap = Bitmap.createScaledBitmap(this.trap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);

    }


    @Override
    public void doCanvas(Canvas canvas) {
        super.doCanvas(canvas);
        if (!player.bagswitch) {
            canvas.drawBitmap(background, baseX, baseY, null);
            if (battleManager.shop != null && battleManager.shop.isopen)
                DrawShop(canvas);
            else if (battleManager.showObject != null) {
                DrawShowObject(canvas);
            } else {
                DrawCell(canvas);
                if (!battleManager.effectses.isEmpty()) {
                    for (int i = 0; i < battleManager.effectses.size(); i++) {
                        SpecialEffects e = battleManager.effectses.get(i);
                        if (e.isAlive())
                            e.docanva(canvas);//展示特效
                        else {
                            battleManager.effectses.remove(e);
                            System.gc();
                        }
                    }
                }
            }
        }
    }

    private void DrawShowObject(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        paint.setTextSize((float) (Const.CELL_HEIGHT * 0.4));
        paint.setColor(Color.WHITE);
        Object object = battleManager.showObject;
        if (object instanceof Monster) {
            Monster monster = (Monster) object;
            canvas.drawBitmap(battleManager.getMonster_shuxing_bg(), 0, 0, null);
            canvas.drawText(monster.getName(), (int) (Const.SCREENHEIGHT * 0.26), (int) (Const.SCREENWIDTH * 0.06), paint);
            canvas.drawBitmap(monster.getBitmap(), (int) (Const.SCREENHEIGHT * 0.075), (int) (Const.SCREENWIDTH * 0.14), null);
            canvas.drawText(monster.hp + "", (int) (Const.SCREENHEIGHT * 0.372), (int) (Const.SCREENWIDTH * 0.16), paint);
            canvas.drawText(monster.atk + "", (int) (Const.SCREENHEIGHT * 0.23), (int) (Const.SCREENWIDTH * 0.16), paint);
            canvas.drawText(monster.def + "", (int) (Const.SCREENHEIGHT * 0.50), (int) (Const.SCREENWIDTH * 0.16), paint);
            String buf = monster.getIntroduce();
            int length = buf.length();
            int j = 0;
            String buf2;
            paint.setTextSize((float) (Const.CELL_HEIGHT / 3.5));
            for (int i = 0; i < length / 28 + 1; i++) {
                if (length <= j + 28) {
                    buf2 = buf.substring(j, length);
                    canvas.drawText(buf2, (int) (Const.SCREENWIDTH * 0.1), (int) (Const.SCREENWIDTH * 0.335 + i * Const.CELL_WIDTH / 4.5), paint);
                } else {
                    buf2 = buf.substring(j, j + 28);
                    canvas.drawText(buf2, (int) (Const.SCREENWIDTH * 0.1), (int) (Const.SCREENWIDTH * 0.335 + i * Const.CELL_WIDTH / 4.5), paint);
                    j = j + 28;
                }
            }
            ArrayList<Buff> buffs = monster.unkeepBuffs;
            Log.i(TAG, "DrawShowObject: " + buffs.size());
            for (int i = 0; i < buffs.size(); i++) {
                if (buffs.get(i).bitmap != null)
                    canvas.drawBitmap(buffs.get(i).bitmap, (int) (Const.SCREENHEIGHT * 0.05), (int) (Const.SCREENWIDTH * 0.49 + i * Const.CELL_WIDTH), null);
                String introduce = buffs.get(i).introduce;
                if (introduce.length() < 25) {
                    canvas.drawText(introduce, (int) (Const.SCREENHEIGHT * 0.13), (int) (Const.SCREENWIDTH * 0.53 + i * Const.CELL_WIDTH), paint);
                } else {
                    canvas.drawText(introduce.substring(0, 25), (int) (Const.SCREENHEIGHT * 0.13), (int) (Const.SCREENWIDTH * 0.53 + i * Const.CELL_WIDTH), paint);
                    canvas.drawText(introduce.substring(25), (int) (Const.SCREENHEIGHT * 0.13), (int) (Const.SCREENWIDTH * 0.53 + i * Const.CELL_WIDTH + Const.CELL_WIDTH / 4), paint);
                }
//                String buf3 = buffs.get(i).introduce;
//                int length2 = buf3.length();
//                int jj = 0;
//                String buf4;
//                for (int ii = 0; ii < length2 / 25 + 1; ii++) {
//                    if (length2 <= jj + 25) {
//                        buf4 = buf3.substring(j, length);
//                        canvas.drawText(buf4, (int) (Const.SCREENHEIGHT * 0.13), (int) (Const.SCREENWIDTH * 0.53 + ii * Const.CELL_WIDTH / 4.5)+i*Const.CELL_WIDTH, paint);
//                    } else {
//                        buf4 = buf3.substring(jj, jj + 25);
//                        canvas.drawText(buf4, (int) (Const.SCREENHEIGHT * 0.13), (int) (Const.SCREENWIDTH * 0.53 + ii * Const.CELL_WIDTH / 4.5)+i*Const.CELL_WIDTH, paint);
//                        jj = jj + 28;
//                    }
//                }
            }

        } else if (object instanceof Skill) {
            paint.setTextSize((float) (Const.CELL_HEIGHT * 0.4));
            Skill skill = (Skill) object;
            canvas.drawBitmap(battleManager.getSkill_shuxing_bg(), 0, 0, null);
            canvas.drawText(skill.name, (int) (Const.SCREENWIDTH * 0.45), (int) (Const.SCREENWIDTH * 0.15), paint);
            int length = skill.introduce.length();
            if (length <= 15) {
                canvas.drawText(skill.introduce, (int) (Const.SCREENWIDTH * 0.19), (int) (Const.SCREENWIDTH * 0.4), paint);
            } else {
                String buf = skill.introduce.substring(0, 15);
                canvas.drawText(buf, (int) (Const.SCREENWIDTH * 0.32), (int) (Const.SCREENWIDTH * 0.52), paint);
                buf = skill.introduce.substring(15);
                canvas.drawText(buf, (int) (Const.SCREENWIDTH * 0.32), (int) (Const.SCREENWIDTH * 0.58), paint);
            }
            canvas.drawBitmap(skill.bitmap, (int) (Const.SCREENWIDTH * 0.1), (int) (Const.SCREENWIDTH * 0.51), null);
        }
    }

    private void DrawShop(Canvas canvas) {
        Shop shop = battleManager.shop;
        canvas.drawBitmap(shop.shop_bg, 0, 0, null);
        //绘制选定的装备的装备描述
        paint.setTextSize(Const.CELL_HEIGHT / 4);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        if (shop.S_equipment_index < shop.S_equipments.size()) {
            Equipment mequipment = shop.S_equipments.get(shop.S_equipment_index);
            if (mequipment != null) {                   //如果没有得到该装备，那么进行返回   [例如购买完装备后]
                canvas.drawText(mequipment.money + "", (float) (Const.SCREENHEIGHT * 0.28), (float) (Const.SCREENWIDTH * 0.7), paint);
                canvas.drawText(mequipment.introduce, (float) (Const.SCREENHEIGHT * 0.07), (float) (Const.SCREENWIDTH * 0.7), paint);
                paint.setColor(Color.RED);
            }
        }

        paint.setStrokeWidth(8);
        //绘制商店装备
        for (int i = 0; i < shop.S_equipments.size(); i++) {
            Equipment equipment = shop.S_equipments.get(i);
            if (equipment == null)          //如果装备为空，停止绘制
                continue;
            boolean flag = false;       //为true 代表此时被选中，需要绘制被选中的边框.
            if (i == shop.S_equipment_index)
                flag = true;
            if (i < 4) {
                if (flag)
                    canvas.drawRect((int) (Const.BASE_CELL_OFFX * 0.9) + (int) (Const.CELL_WIDTH * i * 0.98), (int) (Const.SCREENWIDTH * 0.33), (int) (Const.BASE_CELL_OFFX * 0.9) + (int) (Const.CELL_WIDTH * (i + 0.75) * 0.98), (int) (Const.SCREENWIDTH * 0.33) + (int) (Const.CELL_HEIGHT * 0.75), paint);
                canvas.drawBitmap(equipment.bitmap, (int) (Const.BASE_CELL_OFFX * 0.9) + (int) (Const.CELL_WIDTH * i * 0.98), (int) (Const.SCREENWIDTH * 0.33), null);
            } else {
                int j = i % 4;
                if (flag)
                    canvas.drawRect((int) (Const.BASE_CELL_OFFX * 0.9) + (int) (Const.CELL_WIDTH * j * 0.98), (int) (Const.SCREENWIDTH * 0.44), (int) (Const.BASE_CELL_OFFX * 0.9) + (int) (Const.CELL_WIDTH * (j + 0.75) * 0.98), (int) (Const.SCREENWIDTH * 0.44) + (int) (Const.CELL_HEIGHT * 0.75), paint);
                canvas.drawBitmap(equipment.bitmap, (int) (Const.BASE_CELL_OFFX * 0.9) + (int) (Const.CELL_WIDTH * j * 0.98), (int) (Const.SCREENWIDTH * 0.44), null);
            }
        }

        //绘制玩家的装备
        for (int i = 0; i < 12; i++) {
            Equipment e = shop.player.equipments[i];
            if (e == null) {
                continue;
                //如果发现空闲的装备，直接跳出循环
            } else {
                int x = i % 4;
                int y = i / 4;
                canvas.drawBitmap(e.bitmap, (int) (Const.SCREENHEIGHT * 0.335) + (int) (x * Const.BAG_WIDTH * 1.25), (int) (Const.SCREENWIDTH * 0.275) + (int) (y * Const.BAG_HEIGHT * 1.23), null);
            }
        }

    }

    private void DrawCell(Canvas canvas) {
        LinkedList<Cell> cells = battleManager.cells;
        paint.setTextSize((float) (Const.CELL_HEIGHT * 0.25));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        for (int i = 0; i < 56; i++) {
            Cell cell = cells.get(i);
            int x = cell.x;
            int y = cell.y;
            //如果是 可以被探索的 Cell
            if (cell.status == Cell.UNDISCORVERED) {
                rect = new Rect(Const.BASE_CELL_OFFX + Const.CELL_WIDTH * x, Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * y, Const.BASE_CELL_OFFX + Const.CELL_WIDTH * (x + 1), Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * (y + 1));
                paint.setColor(Color.BLACK);
                paint.setAlpha(126);
                canvas.drawRect(rect, paint);
            } else if (cell.status == Cell.UNDISCORVERED2) {       //如果是 被禁止探索 的Cell  将怪物杀死后可以解除这种状态
                canvas.drawBitmap(undiscovered, Const.BASE_CELL_OFFX + Const.CELL_WIDTH * x, Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * y, null);
            }
            //如果是 已经被探索 的Cell
            else if (cell.status == Cell.DISCORVERED) {
                if (cell.monster != null) {
                    cell.drawMonster(canvas);
                    paint.setColor(Color.WHITE);
                    canvas.drawText(String.valueOf(cell.monster.atk), (float) (Const.BASE_CELL_OFFX + Const.CELL_WIDTH * (x + 0.13)), Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * (y + 1), paint);
                    canvas.drawText(String.valueOf(cell.monster.hp), (float) (Const.BASE_CELL_OFFX + Const.CELL_WIDTH * (x + 0.7)), Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * (y + 1), paint);
                    canvas.drawText(String.valueOf(cell.monster.def), (float) (Const.BASE_CELL_OFFX + Const.CELL_WIDTH * (x + 0.7)), (float) (Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * (y + 0.25)), paint);
                }   //绘制怪物的 图片、攻击(左下)、生命(右下)、护甲(右上)
                else if (cell.shop != null) {
                    paint.setColor(Color.WHITE);
                    canvas.drawBitmap(this.shopcell, (float) (Const.BASE_CELL_OFFX + Const.CELL_WIDTH * (x + 0.03)), Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * y, null);
                } else if (cell.trap != null) {
                    paint.setColor((Color.YELLOW));
                    canvas.drawBitmap(this.trap, (float) (Const.BASE_CELL_OFFX + Const.CELL_WIDTH * (x + 0.03)), Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * y, null);
                }
            }
            //如果是 不可以被探索的 黑色Cell
            else {
                paint.setColor(Color.BLACK);
                paint.setAlpha(255);
                rect = new Rect(Const.BASE_CELL_OFFX + Const.CELL_WIDTH * x, Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * y, Const.BASE_CELL_OFFX + Const.CELL_WIDTH * (x + 1), Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * (y + 1));
                canvas.drawRect(rect, paint);
            }
        }
        if (battleManager.doornumber != -1 && battleManager.cells.get(battleManager.doornumber).status == Cell.DISCORVERED) {
            Cell cell = battleManager.cells.get(battleManager.doornumber);
            int x = cell.x;
            int y = cell.y;
            paint.setColor(Color.WHITE);
            canvas.drawBitmap(this.door, (float) (Const.BASE_CELL_OFFX + Const.CELL_WIDTH * (x + 0.03)), Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * y, null);
        }
    }
}

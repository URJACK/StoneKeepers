package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Cell;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/17.
 * 地下蜘蛛
 * 被探索的时候随机召唤一个怪物 buff
 * 受伤返回迷雾 受伤特效
 */
public class Spider extends Monster {
    public static Bitmap bitmap;

    public Spider(Context context, int level) {
        this.atk = 2 + 2 * level;
        this.hitrate = (float) 0.9;
        this.armor = 3 + 3 * level;
        this.dodge = (float) 0.2;
        this.resistance = (float) 0.1;
        this.setMaxhp((int) (10 + Math.random() * 3 * level));
        this.def = this.armor;
        this.exp = 25 + 7 * level;
        this.money = 2;
        this.setMonsterType(Monster.NORMAL);
        setIntroduce("出现的时候会翻开一格的迷雾，期望自己的运气并不是太差吧~，而且被攻击了就会很没勇气的逃跑，并且还回复全部的生命值" +
                "，恶劣至极。准备好翻开格子就给对面来一发的武器吧，对付这玩意很有用，比起杀虫剂来说更有用。——它们听说在别的地方的蜘蛛都" +
                "过的非常好，至少比他们好，有充足的食物，温暖的巢穴巢穴，或许还有医疗保险，以及不用随时担心世界末日。");
        setName("Spider");
        setMonsterType(Monster.NORMAL);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_dixiazhizhu);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
        this.wearBuff(BuffFactory.createNoKeepBuff("summon"));                //给蜘蛛增加一个召唤技能
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public boolean sufferDamage(int damage, boolean overdef) {
        boolean b = super.sufferDamage(damage, overdef);
        int myself = 0;                 //当前怪物自己的坐标点
        for (int i = 0; i < Const.bm.cells.size(); i++) {
            if (Const.bm.cells.get(i).monster == this) {
                myself = i;
                break;
            }
        }
        for (int i = 0; i < Const.bm.cells.size(); i++) {
            if (Const.bm.cells.get(i).status == Cell.UNDISCORVERED && Const.bm.cells.get(i).monster == null) {
                Const.bm.cells.get(myself).monster = null;
                Const.bm.cells.get(i).monster = this;
                this.wearBuff(BuffFactory.createNoKeepBuff("summon"));                //给蜘蛛增加一个召唤技能
                break;
            }
        }           //遍历合适的阴影

        return b;
    }
}

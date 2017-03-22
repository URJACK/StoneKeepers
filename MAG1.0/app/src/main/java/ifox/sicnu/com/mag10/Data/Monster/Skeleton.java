package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Buff.MonsterSkillFactory;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Unit;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/19.
 * 骷髅兵
 *  攻击额外削弱目标的 3点 护甲 ， 攻击特效
 */
public class Skeleton extends Monster {
    public static Bitmap bitmap;

    public Skeleton(Context context,int level){
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
        setIntroduce("攻击能够破开玩家的护甲，额外削减玩家的护甲值，所以不推荐在有护甲的时候来打这个怪物" +
                "，并不是需要优先解决的怪物，为什么人类就是不明白这一点呢！By：被赶出法师协会的亡灵法师");
        setName("Skeleton");
        setMonsterType(Monster.NORMAL);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_kulou);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
        wearBuff(MonsterSkillFactory.create("subarmor"));
    }
    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public boolean normalAtk(Unit target) {
        boolean r = super.normalAtk(target);
        target.def -= 3;
        if (target.def<0)
            target.def = 0;
        return r;
    }
}

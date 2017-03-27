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
 * Created by Funchou Fu on 2017/3/7.
 * 符石巫师
 * 攻击 特效:攻击会使得玩家的mp减少一点
 * 死灵书 buff:死亡使得出现的怪物攻击力增加一点
 */
public class RuneSorcerer extends Monster {
    private static Bitmap bitmap;

    public RuneSorcerer(Context context, int level) {
        this.atk = 7 + level;
        this.hitrate = (float) 0.9;
        this.crit = 0;
        this.armor = 6 + level;
        this.dodge = (float) 0.1;
        this.resistance = (float) 0.1;
        this.setMaxhp(12 + 3 * level / 2);
        this.crit = (float) 0.01;
        this.resistance = (float) 0.1;
        this.dodge = (float) 0.1;

        this.def = this.armor;
        this.exp = 2;
        this.money = 5;
        this.setMonsterType(Monster.NORMAL);
        setIntroduce("非常烦人的怪物。而且一出现就是一群，这群家伙能够吸收你的魔法值，穿过你的护甲攻击你，还能从死去的怪物身上汲取力量，哦，我想海妖笛子和反射护甲能够狠狠的教导" +
                "这群家伙怎么当一个守法公民。——研究奇怪的巫术，非法集会，不顾他人反对强行抽取魔力，或许还要加上非法拍卖符石和恶意囤积货物，都" +
                "世界末日了这群家伙也不能安宁些。 By：地下城治安官");
        setName("RuneSorcerer");
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_fushiwushi);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
        wearBuff(BuffFactory.createNoKeepBuff("necronomicon"));
        wearBuff(MonsterSkillFactory.create("overmana"));
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public boolean normalAtk(Unit target) {
        boolean r = super.normalAtk(target);
        if (target.mp > 1)
            target.mp -= 1;
        return r;
    }
}
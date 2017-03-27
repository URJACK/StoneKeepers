package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/22.
 * 繁殖　buff
 */
public class MotherMouse extends Monster {
    public static Bitmap bitmap;

    public MotherMouse(Context context, int level) {
        this.atk = (int) (2 +2 * level);
        this.hitrate = (float) 0.9;
        this.armor = (int) (6 );
        this.setMaxhp((int) (10 + 5 * level));
        this.def = this.armor;
        this.dodge = (float) 0.3;
        this.crit = (float) 0.01;
        this.resistance = (float) 0.01;
        this.exp = 3;
        this.money = 10;
        this.setMonsterType(Monster.NORMAL);
        setIntroduce("每回合就能生产一只老鼠，血量很高，基本上做不到一击秒杀，不过生出来的老鼠属性相对很差，很多时候可以用来触发杀敌回血，杀敌回蓝的装备，所以并不是那么的讨厌，不过在深渊模式和崩塌的房间里就要小心了，大量的老鼠会占用你的土地，把你逼到深渊里去" +
                "！——老鼠，很多的老鼠！河流一般涌出来的老鼠！我的天！有什么能够阻止它！都世界末日了能不能安宁一点！地下城已经有够混乱了！By:地下城治安官");
        setName("MotherMouse");
        setMonsterType(Monster.ELITE);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_mulaoshu);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
        wearBuff(BuffFactory.createNoKeepBuff("reproduce"));
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

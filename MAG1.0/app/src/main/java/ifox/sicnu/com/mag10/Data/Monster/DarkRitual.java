package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/7.
 * 黑暗祭祀
 */
public class DarkRitual extends Monster {
    private static Bitmap bitmap;

    public DarkRitual(Context context, int level) {
        this.atk = 9 + 3 * level;
        this.hitrate = (float) 0.95;
        this.crit = 0;
        this.armor = 12 + 5 * level;
        this.dodge = (float) 0.05;
        this.resistance = (float) 0.01;
        this.setMaxhp(32 + 5 * level);
        this.def = this.armor;
        this.exp = 3;
        this.money = 10;
        this.setMonsterType(Monster.NORMAL);
        setIntroduce("buff类怪物，听我的，遇到这货的第一时间就干掉他，会帮别的怪物加buff的怪都不是好怪物！即使会复活也不能阻止冒险家们的怒火！属性并不是太高，蛮好解" +
                "决的。——他们整天都在想着怎么为周围的同伴施加各种各样的奇怪状态，就连他们自己都是试验品，他们能够复活！不过据大量的黑暗祭祀反映，复活的感" +
                "觉简直糟糕透了，因为你要死两次！");
        setName("DarkRitual");
        setMonsterType(Monster.NORMAL);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_heianjisi);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
        wearBuff(BuffFactory.createNoKeepBuff("necronomicon"));
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

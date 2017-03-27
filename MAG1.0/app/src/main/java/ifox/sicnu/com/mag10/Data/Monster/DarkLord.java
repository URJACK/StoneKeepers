package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/27.
 * 分身到四角:四个对象持有同样一个引用
 */
public class DarkLord extends Monster {
    public static Bitmap bitmap;

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    public DarkLord(Context context, int level) {
        this.atk = 8 + 3 * level/2;
        this.hitrate = (float) 0.9;
        this.armor = 9 + 2 * level/2;
        this.dodge = (float) 0.2;
        this.resistance = (float) 0.1;
        this.setMaxhp((int) (40 + level));
        this.def = this.armor;
        this.exp = 3;
        this.dodge = (float) 0.01;
        this.hitrate = (float) 1.2;
        this.money = 10;
        this.setMonsterType(Monster.NORMAL);
        setIntroduce("初期攻击力高，而且还能进行远程攻击，不少的新手就是死在了这个怪物的手上，初期的隐藏房间的boss基本都是这货，" +
                "知道的只有一点，这种怪物非常的烦人，因为它用弓射你，你用弓还击却只能发现你的箭矢从它的骨头缝里穿过去了！这根本不公平！");
        setName("DarkLord");
        setMonsterType(Monster.NORMAL);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_mowang);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
        wearBuff(BuffFactory.createNoKeepBuff("darklord"));
        wearBuff(BuffFactory.createNoKeepBuff("rangedattack"));
    }
}
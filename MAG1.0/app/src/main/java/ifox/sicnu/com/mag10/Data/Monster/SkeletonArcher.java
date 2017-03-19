package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/19.
 * 骷髅弓箭手
 *   远攻，每回合自动攻击玩家, buff
 */
public class SkeletonArcher extends Monster {
    public static Bitmap bitmap;

    public SkeletonArcher(Context context,int level){
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
        setIntroduce("初期攻击力高，而且还能进行远程攻击，不少的新手就是死在了这个怪物的手上，初期的隐藏房间的boss基本都是这货，" +
                "知道的只有一点，这种怪物非常的烦人，因为它用弓射你，你用弓还击却只能发现你的箭矢从它的骨头缝里穿过去了！这根本不公平！");
        setName("SkeletonArcher");
        setMonsterType(Monster.NORMAL);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_kulougongjianshou);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
        wearBuff(BuffFactory.createNoKeepBuff("rangedattack"));
    }
    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

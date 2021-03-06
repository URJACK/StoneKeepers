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
 * 恐怖南瓜
 */
public class Pumpkin extends Monster {
    public static Bitmap bitmap;

    public Pumpkin(Context context, int level) {
        this.atk = 15 + level * 3 / 2;
        this.hitrate = (float) 0.9;
        this.crit = 0;
        this.armor = 40;
        this.dodge = (float) 0.1;
        this.resistance = (float) 0.1;
        this.setMaxhp(30);
        this.dodge = (float) 0.1;
        this.resistance = (float) 0.01;
        this.crit = (float) 0.01;
        this.def = this.armor;
        this.exp = 4;
        this.money = 15;
        this.setMonsterType(Monster.NORMAL);
        this.setIntroduce("攻击力和血量都非常高，还会在三回合后爆炸，造成三倍当前攻击力的伤害，非常棘手的怪物。如果在三回合之内打不死南瓜，那就不要去打了，因为这样你会被南瓜打3次，再被南瓜自爆炸一次，这个就真的是瞬间爆炸了。" +
                "法师看到了就直接羊了吧。不过眩晕和冰冻可以防止爆炸倒计时。——“我说，别紧张，那只是一个南瓜而已，我想怪物也过万圣节？");
        this.setName("Pumpkin");
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_kongbunangua);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
        wearBuff(BuffFactory.createNoKeepBuff("burstcurse"));
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

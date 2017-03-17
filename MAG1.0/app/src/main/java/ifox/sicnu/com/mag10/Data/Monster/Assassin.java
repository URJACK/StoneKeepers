package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Unit;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/17.
 * 刺客
 * 1`攻击破防
 * 2`攻击两次
 */
public class Assassin extends Monster {
    public static Bitmap bitmap;

    public Assassin(Context context, int level) {
        this.atk = 6 + level / 2;
        this.hitrate = (float) 0.9;
        this.armor = 3 + 1 * level;
        this.dodge = (float) 0.2;
        this.resistance = (float) 0.1;
        this.setMaxhp((int) (10 + Math.random() * 2 * level));
        this.hp = this.maxHp;
        this.def = this.armor;
        this.exp = 20 + 6 * level;
        this.money = 2;

        this.setMonsterType(Monster.NORMAL);
        setIntroduce("棘手的怪物，高攻击力，并且能够穿透护甲，还能一瞬间造成两次伤害，反射能教他做人，在不能秒掉之前，请谨慎" +
                "对这个怪物动手。——哦，别和他提起兄弟会什么的，他曾经因为仰慕刺客导师而前往兄弟会的大厅，但是却被狠" +
                "狠赶了出来，或许他没有看到兄弟会前面的符石两个字？");
        setName("Assassin");
        setMonsterType(Monster.NORMAL);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_cike);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
    }

    @Override
    public boolean normalAtk(Unit target) {
        float realhitrate = (this.hitrate - target.dodge);            //根据A命中 和B闪避 得到这次攻击的实际命中率
        double randomrate = Math.random();        //得到一个随机的 0~1 浮点数
        if (randomrate < realhitrate) {
            float realcritrate = (this.crit - target.resistance);        //根据A爆击 和B抗性 得到这次命中攻击的实际暴击率
            double randomrate2 = Math.random();
            int damage;
            if (randomrate2 < realcritrate) {            //这是一次暴击伤害
                damage = this.atk * 2;
            } else {                        //这是一次非暴击伤害
                damage = this.atk;
            }
            if (target.sufferDamage(damage, true))
                return true;
            if (target.sufferDamage(damage, true))
                return true;
        }       //A确实命中了B
        return false;           //遭受攻击后，并未死亡，返回false
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

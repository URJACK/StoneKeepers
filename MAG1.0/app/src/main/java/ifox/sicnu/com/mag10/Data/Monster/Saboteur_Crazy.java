package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Unit;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/19.
 * 双击，并且额外削弱玩家的护甲  ，攻击特效
 */
public class Saboteur_Crazy extends Monster {
    public static Bitmap bitmap;

    public Saboteur_Crazy(Context context, int level) {
        this.atk = 5 + 2 * level;
        this.hitrate = (float) 0.9;
        this.crit = 0;
        this.armor = 1 + 2 * level;
        this.dodge = (float) 0.5;
        this.resistance = (float) 0.1;
        this.setMaxhp((int) (7 + Math.random() * 2 * level));
        this.def = this.armor;
        this.exp = 10 + 3 * level;
        this.money = 2;
        this.setMonsterType(Monster.NORMAL);
        setIntroduce("蛮烦人的怪物，能够攻击两次，还能够削弱玩家的护甲，同时攻击力还不低！能够秒杀就尽量秒杀，不" +
                "被这种怪物打到永远是最好的，损失大量的护甲和血量这种事情没人会喜欢。—" +
                "—地精，胆小，贪婪，你基本在它们的身上找不到一个你能找到的优点，而且这还是个疯了的地精，糟糕透了。你要知道，疯子杀了人，可是不用偿命的。");
        setName("Saboteur_Crazy");
        setMonsterType(Monster.NORMAL);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_kuangluandijing);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
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
            if (target.sufferDamage(damage, true)) {
                return true;
            }
            target.def -= this.atk / 8 + 1;
            if (target.def < 0)
                target.def = 0;
            if (target.sufferDamage(damage, true)) {
                return true;
            }
            target.def -= this.atk / 8 + 1;
            if (target.def < 0)
                target.def = 0;
        }       //A确实命中了B
        return false;           //遭受攻击后，并未死亡，返回false
    }
}

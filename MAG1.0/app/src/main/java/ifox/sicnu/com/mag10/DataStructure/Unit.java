package ifox.sicnu.com.mag10.DataStructure;

import java.util.ArrayList;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.AttackMethod.AttackMethod;
import ifox.sicnu.com.mag10.DataStructure.Buff.Buff;
import ifox.sicnu.com.mag10.DataStructure.Buff.KeepBuff;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.SpecialEffects;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.normalAtak_SpecialEffects;

/**
 * Created by Funchou Fu on 2017/2/27.
 */
public class Unit implements UnitWearBuff {
    public int atk = 0;             //伤害
    public float hitrate = 0;         //命中
    public float crit = 0;            //爆击

    public int armor = 0;           //可回复到的护甲
    public float dodge = 0;           //闪避
    public float resistance = 0;      //抗性

    public int hp = 0;         //当前生命值
    public int mp = 0;         //当前法力值
    public int pp = 0;         //当前魂力值
    public int def = 0;        //当前护甲值

    public int maxHp = 0;      //当前最大生命值
    public int maxMp = 0;      //当前最大法力值
    public int maxPp = 0;      //当前最大魂力值

    public ArrayList<AttackMethod> atms = new ArrayList<>();        //攻击特效

    //遭受技能伤害的时候调用的方法，如果自身死亡，返回true// 。
    //如果overdef 为 true 则忽视 护甲的影响
    public boolean sufferDamage(int damage, boolean overdef) {
        if (!overdef) {
            this.def -= damage;
            if (this.def <= 0) {
                this.hp += this.def;
                this.def = 0;
            }
        } else {
            this.hp -= damage;
        }

        if (this.hp <= 0)
            return true;
        else
            return false;
    }

    /**
     * 玩家的普通攻击
     */
    public boolean normalAtk(Unit target) {
        normalAtak_SpecialEffects normalAtak_specialEffects = new normalAtak_SpecialEffects(-1, -1);
        Const.bm.putEffects(normalAtak_specialEffects);
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
            if (target.sufferDamage(damage, false))
                return true;
        }       //A确实命中了B
        return false;           //遭受攻击后，并未死亡，返回false
    }

    /**
     * 玩家带特效的攻击
     */
    public boolean specialAtk(Unit target) {
        boolean flag = false;
        for (int i = 0; i < atms.size(); i++) {
            if (atms.get(i).attack(this, target))
                flag = true;
        }
        return flag;
    }

    public ArrayList<KeepBuff> keepBuffs = new ArrayList<>();

    public ArrayList<Buff> unkeepBuffs = new ArrayList<>();

    @Override
    public void wearBuff(Buff buff) {
        if (buff instanceof KeepBuff) {
            keepBuffs.add((KeepBuff) buff);
            ((KeepBuff) buff).doWork(this, true);
        }//如果是可以持续的Buff，则把当前单位
        else {
            unkeepBuffs.add(buff);
        }
    }

    @Override
    public void dropBuff(Buff buff) {
        if (buff instanceof KeepBuff) {
            keepBuffs.remove(buff);
            ((KeepBuff) buff).doWork(this, false);
        } else {
            unkeepBuffs.remove(buff);
        }
    }

    public void addAtm(AttackMethod atm) {
        if (atm != null)
            this.atms.add(atm);
    }

    public void rmvAtm(AttackMethod atm) {
        this.atms.remove(atm);
    }
}
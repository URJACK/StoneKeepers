package ifox.sicnu.com.mag10.DataStructure.AttackMethod;

import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/3/16.
 */
public class AttackMethodFactory {
    /**
     * 强悍攻击 powerAttack :  两倍伤害
     */
    public static AttackMethod makeMethod(String name) {
        if (name.equals("powerAttack")) {
            AttackMethod lightpower = new AttackMethod() {
                @Override
                public boolean attack(Unit me, Unit unit) {
                    if (unit.sufferDamage(me.atk * 2, true))
                        return true;
                    else
                        return false;
                }
            };
            return lightpower;
        }
        return null;
    }
}

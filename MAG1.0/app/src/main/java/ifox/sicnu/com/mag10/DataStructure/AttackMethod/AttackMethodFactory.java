package ifox.sicnu.com.mag10.DataStructure.AttackMethod;

import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/3/16.
 */
public class AttackMethodFactory {
    /**
     * 迅捷攻击 fastAttack : 自身攻击两次
     */
    public static AttackMethod makeMethod(String name) {
        if (name.equals("fastAttack")) {
            AttackMethod lightpower = new AttackMethod() {
                @Override
                public boolean attack(Unit me, Unit unit) {
                    if (me.normalAtk(unit))
                        return true;
                    if (me.normalAtk(unit))
                        return true;
                    return false;
                }
            };
            return lightpower;
        }
        return null;
    }
}

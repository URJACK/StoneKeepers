package ifox.sicnu.com.mag10.DataStructure.AttackMethod;

import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/3/16.
 */
public abstract class AttackMethod {
    /**
     * 如果被攻击的死亡，会返回true
     */
    public abstract boolean attack(Unit me, Unit unit);
}

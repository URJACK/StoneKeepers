package ifox.sicnu.com.mag10.Data.Buffs.Keep;

import ifox.sicnu.com.mag10.DataStructure.Buff.KeepBuff;
import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/3/7.
 */
public class PowerIncreaseBuff extends KeepBuff {
    int value;

    public PowerIncreaseBuff(int value) {
        this.value = value;
    }

    @Override
    public void doWork(Unit unit, boolean flag) {
        if (flag)
            unit.atk += value;
        else
            unit.atk -= value;
    }
}

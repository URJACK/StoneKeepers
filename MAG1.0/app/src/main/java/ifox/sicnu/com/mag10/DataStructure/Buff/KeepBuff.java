package ifox.sicnu.com.mag10.DataStructure.Buff;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/3/7.
 * 给玩家增加属性的状态doWork(Unit unit,boolean flag); flag == true 为增加属性，反之..减少
 */
public abstract class KeepBuff extends Buff {
    public char time;

    public abstract void doWork(Unit unit, boolean flag);//如果flag 为true，则代表给unit 增添属性
}

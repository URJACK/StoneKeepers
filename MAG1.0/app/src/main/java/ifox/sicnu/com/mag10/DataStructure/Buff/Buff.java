package ifox.sicnu.com.mag10.DataStructure.Buff;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/2/27.
 */
public abstract class Buff {
    public static final int KEEPALLTIME = 0;        //附加属性的BUFF; 得到Buff 时，获得属性；失去Buff 时，失去属性；
    public static final int ROUNDEND = 1;           //每回合结算的时候，会进行结算的类型；
    public static final int SUFFERDAMAGE = 2;       //遭受伤害的时候，进行结算Buff
    public static final int MAKEDAMAGE = 3;         //制造伤害的时候，进行结算Buff
    public static final int MONSTERCLEAR = 4;       //在计算是否有清场怪物的时候。

    public abstract void doWork(int x, int y, BattleManager bm);
}

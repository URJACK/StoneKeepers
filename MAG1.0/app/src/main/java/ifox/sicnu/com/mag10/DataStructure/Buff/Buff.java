package ifox.sicnu.com.mag10.DataStructure.Buff;

import android.graphics.Bitmap;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/2/27.
 * 如果buff 传入的横纵坐标有一个为-1,则说明此处的buff拥有者是player
 */
public abstract class Buff {
    public static final int KEEPALLTIME = 0;        //附加属性的BUFF; 得到Buff 时，获得属性；失去Buff 时，失去属性；
    public static final int ROUNDEND = 1;           //每回合结算的时候，会进行结算的类型；
    public static final int SUFFERDAMAGE = 2;       //遭受伤害的时候，进行结算Buff
    public static final int MAKEDAMAGE = 3;         //制造伤害的时候，进行结算Buff
    public static final int MONSTERCLEAR = 4;       //在计算是否有清场怪物的时候。

    public Bitmap bitmap;
    public String name;
    public String introduce;
    public String id;                   //根据这个id 来通过factory 找到 对应的buff

    public int time;                    //持续时间

    public abstract void doWork(int x, int y, BattleManager bm);

    /**
     * 如果自己的 time 已经为0 那么返回 ture
     */
    public boolean clear() {
        if (this.time <= 0)
            return true;
        else
            return false;
    }
}

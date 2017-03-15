package ifox.sicnu.com.mag10.Data.Buffs.ROUNDEND;

import ifox.sicnu.com.mag10.DataStructure.Buff.RoundEndBuff;
import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/3/7.
 */
public class Poisoning extends RoundEndBuff {
    int value;
    Unit root;      //作用的对象

    public Poisoning(int value, Unit root) {
        this.value = value;
        this.root = root;
    }

    @Override
    public boolean doWork() {
        this.root.hp -= value;      //中毒导致扣除生命值
        if (this.root.hp < 0)
            return true;
        else
            return false;
    }
}

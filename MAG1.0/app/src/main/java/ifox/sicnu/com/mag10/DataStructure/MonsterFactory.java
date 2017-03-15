package ifox.sicnu.com.mag10.DataStructure;

import android.content.Context;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Monster.Goblin;

/**
 * Created by 41988 on 2017/3/15.
 */
public class MonsterFactory {
    public static Monster createMonster(String name, int level) {
        if (name.equals("Goblin"))
            return new Goblin(Const.mContext_Game, level);
        else
            return null;
    }
}

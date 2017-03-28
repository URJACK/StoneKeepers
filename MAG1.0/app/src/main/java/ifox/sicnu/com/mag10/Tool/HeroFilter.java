package ifox.sicnu.com.mag10.Tool;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Player;

/**
 * Created by Funchou Fu on 2017/3/17.
 */
public abstract class HeroFilter {
    public abstract void uplevel(Player player);

    public abstract void doSkill(BattleManager bm);
}
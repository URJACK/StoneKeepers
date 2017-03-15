package ifox.sicnu.com.mag10.Data.Traps;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Trap;

/**
 * Created by 41988 on 2017/3/14.
 */
public class StoneTrap extends Trap {
    int damage;

    public StoneTrap(int damage){
        this.damage = damage;
    }
    @Override
    public void doTrap(int x, int y, BattleManager battleManager) {
        battleManager.player.sufferDamage(damage,true);
    }
}

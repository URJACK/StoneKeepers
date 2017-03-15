package ifox.sicnu.com.mag10.Data.Traps;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Cell;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.MonsterFactory;
import ifox.sicnu.com.mag10.DataStructure.Trap;

/**
 * Created by 41988 on 2017/3/14.
 */
public class MonsterTrap extends Trap {
    int x;
    int y;
    BattleManager battleManager;
    Monster monster;

    public MonsterTrap(Monster monster) {
        this.monster = monster;
    }

    @Override
    public void doTrap(int x, int y, BattleManager battleManager) {
        this.x = x;
        this.y = y;
        this.battleManager = battleManager;
        setMonster();

    }

    public void setMonster() {
        int index = x + y * 8;
        if (index >= 1 && index % 8 != 0) {
            //left
            Cell cell = battleManager.cells.get(index - 1);
            if (cell.isEmpty() && battleManager.doornumber != index - 1 && Math.random() > 0.5) {
                cell.monster = MonsterFactory.createMonster(this.monster.getName(), 1);
                battleManager.registMonster(cell.monster);
                cell.status = Cell.DISCORVERED;
            }
        }
        if (index > 7) {
            //top
            Cell cell = battleManager.cells.get(index - 8);
            if (cell.isEmpty() && battleManager.doornumber != index - 1 && Math.random() > 0.5) {
                cell.monster = MonsterFactory.createMonster(this.monster.getName(), 1);
                battleManager.registMonster(cell.monster);
                cell.status = Cell.DISCORVERED;
            }
        }
        if (index < 56 && (index + 1) % 8 != 0) {
            //right
            Cell cell = battleManager.cells.get(index + 1);
            if (cell.isEmpty() && battleManager.doornumber != index - 1 && Math.random() > 0.5) {
                cell.monster = MonsterFactory.createMonster(this.monster.getName(), 1);
                battleManager.registMonster(cell.monster);
                cell.status = Cell.DISCORVERED;
            }
        }
        if (index < 48) {
            //bottom
            Cell cell = battleManager.cells.get(index + 8);
            if (cell.isEmpty() && battleManager.doornumber != index - 1 && Math.random() > 0.5) {
                cell.monster = MonsterFactory.createMonster(this.monster.getName(), 1);
                battleManager.registMonster(cell.monster);
                cell.status = Cell.DISCORVERED;
            }
        }

    }

}

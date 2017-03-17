package ifox.sicnu.com.mag10.DataStructure.Buff;

import android.util.Log;

import ifox.sicnu.com.mag10.Data.Monster.MonsterFactory;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Cell;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/3/16.
 * 通过Unit.java 的 wear 和 diswear 进行调用
 * 如果传入的x , y == -1 则说明buff持有者是Player
 * RoundEndBuff必须在手动写的时候，让其time -= 1 ;
 */
public class BuffFactory {
    private static final String TAG = "BuffFactory";

    /**
     * 攻击强化 atkIncrease :玩家自己使用，给自己增加5点攻击力
     */
    public static KeepBuff createKeepBuff(String name) {
        if (name.equals("atkIncrease")) {                        //攻击增益buff
            KeepBuff keepBuff = new KeepBuff() {
                @Override
                public void doWork(Unit unit, boolean flag) {
                    if (flag) {
                        unit.atk += 5;
                    } else
                        unit.atk -= 5;
                }

                @Override
                public void doWork(int x, int y, BattleManager bm) {
                    return;
                }
            };
            keepBuff.time = 3;
            keepBuff.name = "攻击强化";
            keepBuff.introduce = "自身获得五点攻击力的加成，总共会持续三回合";
            keepBuff.id = "atkIncrease";
            return keepBuff;
        }
        return null;
    }

    /**
     * 毒药状态 poison :玩家对怪物释放后叠加的状态，怪物会因此每回合减少自己(monster 5% maxHp + Player 50% intelligence)
     * 出生召唤 summon :召唤一个怪物(默认哥布林)。
     */
    public static RoundEndBuff createRoundEndBuff(String name) {
        if (name.equals("poison")) {
            RoundEndBuff roundEndBuff = new RoundEndBuff() {
                @Override
                public void doWork(int x, int y, BattleManager bm) {
                    this.time -= 1;
                    if (x == -1 || y == -1) {
                        bm.player.hp -= bm.player.maxHp * 0.05 + 5;
                    } else {
                        Cell c = bm.cells.get(x + y * 8);
                        if (c.monster != null) {
                            c.monster.hp -= c.monster.maxHp * 0.05 + 5;
                        } else {
                            Log.i(TAG, "doWork: Monster Is Null!");
                        }
                    }
                }
            };
            roundEndBuff.time = 3;
            roundEndBuff.id = "poison";
            roundEndBuff.name = "中毒";
            roundEndBuff.introduce = "每回合减少自己 5%的最大生命值后，还会额外受到5点伤害";
            return roundEndBuff;
        }
        if (name.equals("summon")) {
            RoundEndBuff roundEndBuff = new RoundEndBuff() {
                @Override
                public void doWork(int x, int y, BattleManager bm) {
                    this.time -= 1;
                    Log.i(TAG, "doWork: Summon");
                    if (x == -1 || y == -1) {
                        return;
                    } else {
                        Cell cell = bm.cells.get(x + 8 * y);
                        if (BuffFactory.leftisOK(x + y * 8, bm)) {
                            Monster m = MonsterFactory.createMonster("Goblin", bm.floor);
                            bm.cells.get(x + 8 * y - 1).status = Cell.DISCORVERED;
                            bm.cells.get(x + 8 * y - 1).monster = m;
                            bm.registMonster(m);
                        } else if (BuffFactory.topisOK(x + y * 8, bm)) {
                            Monster m = MonsterFactory.createMonster("Goblin", bm.floor);
                            bm.cells.get(x + 8 * y - 8).status = Cell.DISCORVERED;
                            bm.cells.get(x + 8 * y - 8).monster = m;
                            bm.registMonster(m);
                        } else if (BuffFactory.rightisOK(x + y * 8, bm)) {
                            Monster m = MonsterFactory.createMonster("Goblin", bm.floor);
                            bm.cells.get(x + 8 * y + 1).status = Cell.DISCORVERED;
                            bm.cells.get(x + 8 * y + 1).monster = m;
                            bm.registMonster(m);
                        } else if (BuffFactory.bottomisOK(x + y * 8, bm)) {
                            Monster m = MonsterFactory.createMonster("Goblin", bm.floor);
                            bm.cells.get(x + 8 * y + 8).status = Cell.DISCORVERED;
                            bm.cells.get(x + 8 * y + 8).monster = m;
                            bm.registMonster(m);
                        }

                    }
                }
            };
            roundEndBuff.time = 1;
            roundEndBuff.id = "summon";
            roundEndBuff.name = "召唤";
            roundEndBuff.introduce = "刚被探索的时候，召唤一个哥布林";
            return roundEndBuff;
        }
        return null;
    }

    public static boolean leftisOK(int index, BattleManager bm) {
        if (index >= 1 && index % 8 != 0) {
            Cell cell = bm.cells.get(index - 1);
            if (cell.isEmpty() && bm.doornumber != index - 1) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    public static boolean rightisOK(int index, BattleManager bm) {
        if (index < 56 && (index + 1) % 8 != 0) {
            Cell cell = bm.cells.get(index + 1);
            if (cell.isEmpty() && bm.doornumber != index + 1) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    public static boolean topisOK(int index, BattleManager bm) {
        if (index > 7) {
            Cell cell = bm.cells.get(index - 8);
            if (cell.isEmpty() && bm.doornumber != index - 8) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    public static boolean bottomisOK(int index, BattleManager bm) {
        if (index < 48) {
            Cell cell = bm.cells.get(index + 8);
            if (cell.isEmpty() && bm.doornumber != index + 8) {
                return true;
            } else
                return false;
        } else
            return false;
    }
}

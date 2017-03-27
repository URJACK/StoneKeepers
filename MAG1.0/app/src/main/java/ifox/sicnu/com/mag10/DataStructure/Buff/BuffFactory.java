package ifox.sicnu.com.mag10.DataStructure.Buff;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Monster.MonsterFactory;
import ifox.sicnu.com.mag10.DataStructure.AttackMethod.AttackMethod;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Cell;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Unit;
import ifox.sicnu.com.mag10.GameActivity;
import ifox.sicnu.com.mag10.R;

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
            return createAtkIncrease();
        } else if (name.equals("defDecrease"))
            return createDefDecrease();
        return null;
    }

    private static KeepBuff createDefDecrease() {
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
        keepBuff.time = 2;
        keepBuff.name = "防御削弱";
        keepBuff.introduce = "该目标的护甲遭到了削弱";
        keepBuff.id = "atkIncrease";
        keepBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.buf_addpower);
        keepBuff.bitmap = Bitmap.createScaledBitmap(keepBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.1), (int) (Const.SCREENHEIGHT * 0.1), true);
        return keepBuff;
    }

    private static KeepBuff createAtkIncrease() {
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
        keepBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.buf_addpower);
        keepBuff.bitmap = Bitmap.createScaledBitmap(keepBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.1), (int) (Const.SCREENHEIGHT * 0.1), true);
        return keepBuff;
    }

    /**
     * 毒药状态 poison :玩家对怪物释放后叠加的状态，怪物会因此每回合减少自己(monster 5% maxHp + Player 50% intelligence)。
     * 出生召唤 summon :召唤一个怪物(默认哥布林)。
     * 死灵书 necronomicon :自己死亡会让在场其他怪物的攻击力 + floor /2 。
     * 荆棘护甲 thornarmor :反弹给对手等同于自己护甲值的伤害。
     * 持续强化 alwaysstrengthen : 每回合结束后，增加自己的攻击力和生命值
     */
    public static Buff createNoKeepBuff(String name) {
        if (name.equals("poison")) {
            RoundEndBuff roundEndBuff = createPoison();
            return roundEndBuff;
        } else if (name.equals("summon")) {
            RoundEndBuff roundEndBuff = createSummon();
            return roundEndBuff;
        } else if (name.equals("necronomicon")) {
            MonsterDieBuff monsterDieBuff = createNecronomicon();
            return monsterDieBuff;
        } else if (name.equals("thornarmor")) {
            SufferDamageBuff sufferDamageBuff = createThornarmor();
            return sufferDamageBuff;
        } else if (name.equals("alwaysstrengthen")) {
            RoundEndBuff roundEndBuff = createAlwaysstrengthen();
            return roundEndBuff;
        } else if (name.equals("burstcurse")) {
            RoundEndBuff roundEndBuff = createBurstcurse();
            return roundEndBuff;
        } else if (name.equals("rangedattack")) {
            RoundEndBuff roundEndBuff = createRangedattack();
            return roundEndBuff;
        } else if (name.equals("xixuegongji")) {
            RoundEndBuff roundEndBuff = createxixuegongji();
            return roundEndBuff;
        } else if (name.equals("curse")) {
            MonsterDieBuff monsterDieBuff = createCurse();
            return monsterDieBuff;
        } else if (name.equals("reproduce")) {
            RoundEndBuff roundEndBuff = createReproduce();
            return roundEndBuff;
        } else if (name.equals("darklord")) {
            MonsterDieBuff monsterDieBuff = createDarkLord();
            return monsterDieBuff;
        }
        return null;
    }

    private static MonsterDieBuff createDarkLord() {
        MonsterDieBuff monsterDieBuff = new MonsterDieBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {
                Const.bm.player.money += 100;
                ((GameActivity) Const.mContext_Game).gotoEndActivity(true);
            }
        };
        monsterDieBuff.id = "darklord";
        monsterDieBuff.name = "魔王之力";
        monsterDieBuff.introduce = "四角都是他的分身,但是会平分伤害";
        monsterDieBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.buff_darklord);
        monsterDieBuff.bitmap = Bitmap.createScaledBitmap(monsterDieBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        return monsterDieBuff;
    }

    private static RoundEndBuff createReproduce() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {
                Log.i(TAG, "doWork: 召唤老鼠");
                if (leftisOK(x + y * 8, bm)) {
                    Monster e = MonsterFactory.createMonster("Mouse", bm.floor);
                    bm.cells.get(x + 8 * y - 1).status = Cell.DISCORVERED;
                    bm.cells.get(x + 8 * y - 1).monster = e;
                    bm.registMonster(e);
                } else if (topisOK(x + y * 8, bm)) {
                    Monster e = MonsterFactory.createMonster("Mouse", bm.floor);
                    bm.cells.get(x + 8 * y - 8).status = Cell.DISCORVERED;
                    bm.cells.get(x + 8 * y - 8).monster = e;
                    bm.registMonster(e);
                } else if (rightisOK(x + y * 8, bm)) {
                    Monster e = MonsterFactory.createMonster("Mouse", bm.floor);
                    bm.cells.get(x + 8 * y + 1).status = Cell.DISCORVERED;
                    bm.cells.get(x + 8 * y + 1).monster = e;
                    bm.registMonster(e);
                } else if (bottomisOK(x + y * 8, bm)) {
                    Monster e = MonsterFactory.createMonster("Mouse", bm.floor);
                    bm.cells.get(x + 8 * y + 8).status = Cell.DISCORVERED;
                    bm.cells.get(x + 8 * y + 8).monster = e;
                    bm.registMonster(e);
                }
            }
        };
        roundEndBuff.id = "reproduce";
        roundEndBuff.time = 1;
        roundEndBuff.name = "繁殖";
        roundEndBuff.introduce = "每回合自动生成一只老鼠";
        roundEndBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.buff_reproduce);
        roundEndBuff.bitmap = Bitmap.createScaledBitmap(roundEndBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        return roundEndBuff;
    }

    private static MonsterDieBuff createCurse() {
        MonsterDieBuff monsterDieBuff = new MonsterDieBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {
                Toast.makeText(Const.mContext_Game, "你被木乃伊诅咒了", Toast.LENGTH_SHORT).show();
                bm.player.maxHp -= 1;
            }
        };
        monsterDieBuff.id = "curse";
        monsterDieBuff.name = "诅咒";
        monsterDieBuff.introduce = " 击杀该怪物会使得你的生命值上限永久减少一点";
        monsterDieBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.buff_curse);
        monsterDieBuff.bitmap = Bitmap.createScaledBitmap(monsterDieBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        return monsterDieBuff;
    }

    private static RoundEndBuff createxixuegongji() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {
                if (time == 1) {
                    Log.i(TAG, "doWork: ClearXixue");
                    for (int i = 0; i < bm.player.atms.size(); i++) {
                        AttackMethod atm = bm.player.atms.get(i);
                        if (atm.id == 1) {            //此处的id 为吸血攻击的id
                            bm.player.rmvAtm(atm);
                        }
                    }
                }
                time--;
            }
        };
        roundEndBuff.time = 2;
        roundEndBuff.id = "xixuegongji";
        roundEndBuff.name = "吸血攻击";
        roundEndBuff.introduce = "吸血攻击，两回合，攻击100%命中并会造成吸血效果";
        roundEndBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_xianxue);
        roundEndBuff.bitmap = Bitmap.createScaledBitmap(roundEndBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        return roundEndBuff;
    }

    private static RoundEndBuff createRangedattack() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {
                Monster m = bm.cells.get(x + 8 * y).monster;
                bm.player.sufferDamage(m.atk, false);
            }
        };
        roundEndBuff.time = 1;
        roundEndBuff.id = "rangedattack";
        roundEndBuff.name = "远程攻击";
        roundEndBuff.introduce = "远程攻击，即是你不攻击它，它也会攻击你";
        roundEndBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.buff_rangedattack);
        roundEndBuff.bitmap = Bitmap.createScaledBitmap(roundEndBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        return roundEndBuff;
    }

    private static RoundEndBuff createBurstcurse() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {
                if (time <= 1) {
                    Monster e = bm.cells.get(x + y * 8).monster;
                    bm.player.sufferDamage(e.atk * 3, true);
                    e.hp = 0;
                }
                time--;
            }
        };
        roundEndBuff.time = 4;
        roundEndBuff.id = "burstcurse";
        roundEndBuff.name = "爆裂诅咒";
        roundEndBuff.introduce = "怪物出现后第三个回合，会自动爆炸，对玩家造成三倍于该怪物当前攻击力的伤害";
        roundEndBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.buff_burstcurse);
        roundEndBuff.bitmap = Bitmap.createScaledBitmap(roundEndBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        return roundEndBuff;
    }

    private static RoundEndBuff createAlwaysstrengthen() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {
                Cell c = bm.cells.get(x + y * 8);
                if (c.monster != null) {
                    c.monster.hp += bm.floor / 2 + 1;
                    c.monster.atk += bm.floor / 3 + 1;
                } else
                    Log.i(TAG, "doWork: 怎么可能为空");
            }
        };
        roundEndBuff.id = "alwaysstrengthen";
        roundEndBuff.name = "持续强化";
        roundEndBuff.introduce = "每回合结束后，增加自己的攻击力和生命值";
        roundEndBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.buff_alwaysstrengthen);
        roundEndBuff.bitmap = Bitmap.createScaledBitmap(roundEndBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        return roundEndBuff;
    }

    private static SufferDamageBuff createThornarmor() {
        SufferDamageBuff sufferDamageBuff = new SufferDamageBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {
                if (x != -1 || y != -1) {
                    bm.player.sufferDamage(bm.cells.get(x + y * 8).monster.def, false);
                }           //如果判定该对象不是player，而是怪物，那么将对player造成等同于怪物伤害
            }
        };
        sufferDamageBuff.id = "thornarmor";
        sufferDamageBuff.name = "荆棘护甲";
        sufferDamageBuff.introduce = "受到普通攻击,反弹给对手等同于自己剩余护甲值的伤害，该数值是自己受伤之后的数值";
        sufferDamageBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.buf_thornarmor);
        sufferDamageBuff.bitmap = Bitmap.createScaledBitmap(sufferDamageBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);

        return sufferDamageBuff;
    }

    private static MonsterDieBuff createNecronomicon() {
        MonsterDieBuff monsterDieBuff = new MonsterDieBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {
                Toast.makeText(Const.mContext_Game, "怪物触发了死灵书", Toast.LENGTH_LONG).show();
                for (int i = 0; i < bm.cells.size(); i++) {
                    if (bm.cells.get(i).status == Cell.DISCORVERED && bm.cells.get(i).monster != null) {
                        Monster m = bm.cells.get(i).monster;
                        m.atk += Const.bm.floor / 2 + 1;
                    }
                }
            }
        };
        monsterDieBuff.id = "necronomicon";
        monsterDieBuff.name = "死灵书";
        monsterDieBuff.introduce = "该怪物死亡后，会给周围的怪物增加攻击力";
        monsterDieBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.buf_necronomicon);
        monsterDieBuff.bitmap = Bitmap.createScaledBitmap(monsterDieBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        return monsterDieBuff;
    }

    private static RoundEndBuff createSummon() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {
                this.time -= 1;
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

    private static RoundEndBuff createPoison() {
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
        roundEndBuff.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.buf_poison);
        roundEndBuff.bitmap = Bitmap.createScaledBitmap(roundEndBuff.bitmap, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        return roundEndBuff;
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

package ifox.sicnu.com.mag10.DataStructure;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Monster.Goblin;
import ifox.sicnu.com.mag10.Data.Traps.MonsterTrap;
import ifox.sicnu.com.mag10.Data.Traps.StoneTrap;
import ifox.sicnu.com.mag10.DataStructure.Buff.MonsterClearBuff;
import ifox.sicnu.com.mag10.DataStructure.Buff.RoundEndBuff;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.PpgetSpecialEffects;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.SpecialEffects;
import ifox.sicnu.com.mag10.GameActivity;

/**
 * Created by Funchou Fu on 2017/3/7.
 * BattleManager 这个类 是控制Player 以及地图中的怪物
 */
public class BattleManager {
    public LinkedList<Cell> cells;
    private LinkedList<Monster> monsters;
    public Context mContext;
    public Player player;
    public int function_switch = -1;
    public Shop shop;
    public ShopManager shopManager;
    public Object showObject;
    public int doornumber;
    public SpecialEffects effects;              //当前正要被展示的特效对象
    public ArrayList<SpecialEffects> effectses; //保存effects 队列。
    public int xx, yy;


    public BattleManager(Context context, ShopManager shopManager) {
        mContext = context;
        this.shopManager = shopManager;
        cells = new LinkedList<>();
        monsters = new LinkedList<>();
        player = ((APP) context.getApplicationContext()).getPlayer();
        effectses = new ArrayList<>();
    }

    /**
     * createFloor 方法里的index参数代表当前是给第index 层创建怪物和商店等数据
     * index 的取值 从1~20
     * 创建怪物的时候，怪物的个数呈现波动分布
     * 每一层 怪物的个数 平均在7~9 个怪物
     */
    public void createFloor(int index) {
        //得到这层的总共的单位数
        int number = (int) (Math.random() * 10 % 3) + 11;
        int trap_num = (int) (Math.random() * 4) + 1;
        Set<Integer> integers = new HashSet<>();
        for (int i = 0; i < number; i++) {
            integers.add((int) (Math.random() * 1000 % 56));
        }
        Iterator iterator = integers.iterator();

        for (int i = 0; i < 56; i++) {
            cells.add(new Cell(i % 8, i / 8));
        }

        for (int i = 0; i < integers.size() - 2 - trap_num; i++) {
            int n = (int) iterator.next();
            Monster m = getMonsterLevel(index);
            monsters.add(m);
            cells.get(n).monster = m;
        }

        int n = (int) iterator.next();      //生成商店
        Shop shop = shopManager.createShop(index);
        if (shop != null) {
            cells.get(n).shop = shop;
            this.shop = shop;
        }

        this.doornumber = (int) iterator.next();          //生成门


        //生成陷阱
        for (int i = 0; i < trap_num; i++) {
            n = (int) iterator.next();
            //Trap trap = new StoneTrap((int)(Math.random()*3 + 1));
            Trap trap = new MonsterTrap(MonsterFactory.createMonster("Goblin", index));
            cells.get(n).trap = trap;
        }


        int first_index = (int) (Math.random() * 1000 % 56);
        Cell first_cell = cells.get(first_index);
        while (!first_cell.isEmpty()) {
            first_cell = cells.get((int) (Math.random() * 1000 % 56));
        }
        first_index = first_cell.x + first_cell.y * 8;
        first_cell.status = Cell.DISCORVERED;
        Change_Cell(first_index, Cell.UNDISCORVERED, Cell.FORBID);

    }

    /**
     * 每次回合结束后的逻辑。
     */
    private void Action_ROUNDEND(int first_index) {
        Cell cell = cells.get(first_index);
        boolean flag = false;            //只有当flag 为 true 的时候，才会执行时间流逝.
        //首先必须满足 不是黑块
        if (cell.status != Cell.FORBID) {
            //探索
            if (cells.get(first_index).status == Cell.UNDISCORVERED) {
                cells.get(first_index).status = Cell.DISCORVERED;
                if (player.pp < player.maxPp) {
                    //增加pp
                    player.pp++;
                    effects = new PpgetSpecialEffects(xx * Const.CELL_WIDTH + Const.BASE_CELL_OFFX, yy * Const.CELL_HEIGHT + Const.BASE_CELL_OFFY);
                    effectses.add(effects);
                }
                if (cell.trap != null) {
                    //出发陷阱
                    cell.trap.doTrap(cell.x, cell.y, this);
                }
                if (cells.get(first_index).monster != null) {
                    Change_Cell(first_index, Cell.UNDISCORVERED2, Cell.FORBID);
                    return;
                } else
                    Change_Cell(first_index, Cell.UNDISCORVERED, Cell.FORBID);
                flag = true;
            } else {
                //攻击怪物
                if (cell.monster != null) {
                    if (!player.normalAtk(cell.monster)) {
                        cell.monster.normalAtk(player);
                    }           //如果怪物没有死亡，怪物会对玩家再次造成一次攻击
                    flag = true;
                }

                //商店的点击
                else if (cell.shop != null) {
                    this.shop.isopen = true;
                }
            }
        }
        if (flag) {
            TimeGoOn();
        }
        MonsterClear();
    }

    /**
     * 时间流逝的操作，会在攻击和移动后，进行调用该方法
     * 1·会对玩家进行死亡检测
     * 2·
     */
    private void TimeGoOn() {
        if (player.hp <= 0) {
            Toast.makeText(mContext, "你死了", Toast.LENGTH_SHORT).show();
            ((GameActivity) mContext).gotoEndActivity(false);
        }
    }

    /**
     * 怪物清理的操作，会在玩家释放技能后，进行调用该方法
     */
    public void MonsterClear() {
        for (int i = 0; i < monsters.size(); i++) {
            Monster m = monsters.get(i);
            if (m.hp <= 0) {
                monsters.remove(m);
                player.addExp(m.exp);
                Log.i("DEBUG", "MonsterClear: " + m.exp + "---" + player.exp);
                for (int j = 0; j < cells.size(); j++) {
                    if (m == cells.get(j).monster) {
                        cells.get(j).monster = null;
                        Change_Cell(j, Cell.UNDISCORVERED, Cell.UNDISCORVERED2);
                    }
                }
            }
        }
    }

    /**
     * @param nowstatu    当前状态
     * @param statu       改变后的状态
     * @param first_index 点击的坐标点的下标
     */
    private void Change_Cell(int first_index, int statu, int nowstatu) {
        Cell cell1;
        if (first_index >= 1 && first_index % 8 != 0) {
            cell1 = cells.get(first_index - 1);
            if (cell1.status == nowstatu)
                cell1.status = statu;
        }

        if (first_index > 7) {
            cell1 = cells.get(first_index - 8);
            if (cell1.status == nowstatu)
                cell1.status = statu;
        }
        if (first_index < 56 && (first_index + 1) % 8 != 0) {
            cell1 = cells.get(first_index + 1);
            if (cell1.status == nowstatu)
                cell1.status = statu;
        }
        if (first_index < 48) {
            cell1 = cells.get(first_index + 8);
            if (cell1.status == nowstatu)
                cell1.status = statu;
        }
    }

    /**
     * 在接受到坐标后，根据传入的function_switch 选择具体的动作
     */
    public void battleWork(int xx, int yy, int function_switch) {
        this.xx = xx;
        this.yy = yy;
        //执行探索
        if (function_switch == -1) {
            if (xx + yy * 8 == doornumber && cells.get(doornumber).status == Cell.DISCORVERED) {
                ((GameActivity) mContext).gotoNextActivity();
            } else
                Action_ROUNDEND(xx + yy * 8);
        } else {
            //释放技能或者道具
            if (player.skillswitch) {
                //释放技能
                if (!player.useSkill(xx, yy, this, function_switch)) {
                    Toast.makeText(mContext, "释放技能失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                //释放道具
                if (!player.useTool(xx, yy, this, function_switch)) {
                    Toast.makeText(mContext, "使用道具失败", Toast.LENGTH_SHORT).show();
                }
            }
            this.function_switch = -1;
        }
    }

    private Monster getMonsterLevel(int level) {
        int num = (int) (Math.random() * 100 % 10);
        switch (level) {
            case 1:
                return MonsterFactory.createMonster("Goblin", 1);
            case 2:
                return MonsterFactory.createMonster("Goblin", 2);
            case 3:
                return MonsterFactory.createMonster("Goblin", 3);
        }
        return null;
    }

    public void changeFunction(int i) {
        if (player.skillswitch && player.skills[i] == null) {
            return;
        } else if (!player.skillswitch && player.tools[i] == null) {
            return;
        }
        //如果此处没有技能或者道具进行返回
        if (this.function_switch == i)  //选定了相同的技能则 取消释放。
            this.function_switch = -1;
        else
            this.function_switch = i; //技能栏第一个
    }

    /**
     * @param i 这个参数是  BattleManager 的参数
     *          通过传入i 以及自身的 skillswitch 是否打开，来判定将自身的ShowObject 进行改变成何种事物
     */
    public void changeShowObject(int i) {
        if (i == -1)
            this.showObject = null;
        else {
            if (this.player.skillswitch) {
                if (this.player.skills[i] != null) {
                    this.showObject = this.player.skills[i];
                }
            } else {
                if (this.player.tools[i] != null) {
                    this.showObject = this.player.tools[i];
                }
            }
        }
    }

    public void registMonster(Monster monster) {
        monsters.add(monster);
    }

    /**
     * buffwork()方法能够自动遍历player的拥有的状态，并让他们起自己本应当起的作用，
     *
     * @param timespan :
     *                 1·在 TimeGoOn()中被调用 timespan == 0,
     *                 2·在 MonsterClear()中被调用 timespan == 1
     */
    private void buffWork(int timespan) {
        if (timespan == 0) {

            for (int i = 0; i < player.unkeepBuffs.size(); i++) {
                if (player.unkeepBuffs.get(i) instanceof RoundEndBuff) {
                    player.unkeepBuffs.get(i).doWork(0, 0, this);
                }
            }           //遍历Player的Buff

            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).monster != null) {
                    Monster m = cells.get(i).monster;
                    for (int j = 0; j < player.unkeepBuffs.size(); j++) {
                        if (m.unkeepBuffs.get(j) instanceof RoundEndBuff)
                            m.unkeepBuffs.get(j).doWork(i % 8, i / 8, this);
                    }
                }
            }           //遍历所有Monster的Buff

        } else {

            for (int i = 0; i < player.unkeepBuffs.size(); i++) {
                if (player.unkeepBuffs.get(i) instanceof MonsterClearBuff) {
                    player.unkeepBuffs.get(i).doWork(0, 0, this);
                }
            }           //遍历Player的Buff

            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).monster != null) {
                    Monster m = cells.get(i).monster;
                    for (int j = 0; j < player.unkeepBuffs.size(); j++) {
                        if (m.unkeepBuffs.get(j) instanceof MonsterClearBuff)
                            m.unkeepBuffs.get(j).doWork(i % 8, i / 8, this);
                    }
                }
            }           //遍历所有Monster的Buff

        }
    }
}

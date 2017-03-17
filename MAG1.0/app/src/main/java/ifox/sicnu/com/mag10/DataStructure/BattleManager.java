package ifox.sicnu.com.mag10.DataStructure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.SoundPool;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Monster.MonsterFactory;
import ifox.sicnu.com.mag10.Data.Traps.MonsterTrap;
import ifox.sicnu.com.mag10.Data.Traps.StoneTrap;
import ifox.sicnu.com.mag10.DataStructure.Buff.Buff;
import ifox.sicnu.com.mag10.DataStructure.Buff.MonsterClearBuff;
import ifox.sicnu.com.mag10.DataStructure.Buff.MonsterDieBuff;
import ifox.sicnu.com.mag10.DataStructure.Buff.RoundEndBuff;
import ifox.sicnu.com.mag10.DataStructure.Buff.SufferDamageBuff;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.PpgetSpecialEffects;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.SpecialEffects;
import ifox.sicnu.com.mag10.GameActivity;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/7.
 * BattleManager 这个类 是控制Player 以及地图中的怪物
 */
public class BattleManager {
    private static final String TAG = "BattleManager";
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
    public int floor;                           //保存下当前的层数
    public SoundPool sp;

    public BattleManager(Context context, ShopManager shopManager) {
        mContext = context;
        this.shopManager = shopManager;
        cells = new LinkedList<>();
        monsters = new LinkedList<>();
        player = ((APP) context.getApplicationContext()).getPlayer();
        sp = ((APP) context.getApplicationContext()).getSp();
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
        this.floor = index;
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
            Monster m = getMonsterByLevel(index);
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
            Trap trap;
            if (index < 3) {
                trap = new StoneTrap((int) (Math.random() * 3 + 1));                    // 0 ~ 2 层有落石陷阱
            } else {
                if (Math.random() > 0.6)
                    trap = new StoneTrap((int) (Math.random() * 3 + 1));
                else
                    trap = new MonsterTrap(MonsterFactory.createMonster("RuneSorcerer", index));            // 之后 层有落石+伏兵陷阱
            }
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
                sp.load(mContext, R.raw.gameview_music2, 1);
                sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        sp.play(1, 1, 1, 1, 0, 1);
                    }
                });

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
                } else
                    Change_Cell(first_index, Cell.UNDISCORVERED, Cell.FORBID);
                flag = true;
            } else {
                //攻击怪物
                if (cell.monster != null) {
                    Monster monster = cell.monster;
                    buffWork(cell.x, cell.y, monster, true);               //在怪物受伤前，对受伤怪物的状态进行检测
                    if (player.atm == null) {
                        if (!player.normalAtk(monster)) {
                            buffWork(-1, -1, player, true);
                            monster.normalAtk(player);                     //在人物受伤前，对人的状态进行检测
                        }
                        //如果怪物没有死亡，怪物会对玩家再次造成一次攻击
                    } else {
                        if (!player.specialAtk(monster)) {
                            buffWork(-1, -1, player, true);
                            monster.normalAtk(player);                     //在人物受伤前，对人的状态进行检测
                        }
                    }
                    flag = true;            //将时间流逝的flag设置为ture
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
     * 2·之后一定会触发MonsterClear 并且这个触发是在调用TimeGoOn 的父类函数中进行调用的
     */
    private void TimeGoOn() {
        buffWork(true);                 //检测所有的Round_End Buff
        if (player.hp <= 0) {
            Toast.makeText(mContext, "你死了", Toast.LENGTH_SHORT).show();
            ((GameActivity) mContext).gotoEndActivity(false);
        }
    }

    /**
     * 怪物清理的操作，会在玩家释放技能后，进行调用该方法
     */
    public void MonsterClear() {
        buffWork(false);                //检测所有的Monster_Clear Buff
        for (int i = 0; i < monsters.size(); i++) {
            Monster m = monsters.get(i);
            if (m.hp <= 0) {
                unregistMonster(m);
                player.addExp(m.exp);
                for (int j = 0; j < cells.size(); j++) {
                    if (m == cells.get(j).monster) {
                        cells.get(j).monster = null;
                        buffWork(j % 8, j / 8, m, false);
                        Change_Cell(j, Cell.UNDISCORVERED, Cell.UNDISCORVERED2);
                        Change_Cell(j, Cell.UNDISCORVERED, Cell.FORBID);
                        break;
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

    private Monster getMonsterByLevel(int level) {
        double num = Math.random();
        if (level < 4) {
            if (num < 0.3)
                return MonsterFactory.createMonster("Goblin", level);
            else if (num >= 0.3 && num < 0.6)
                return MonsterFactory.createMonster("Saboteur", level);
            else
                return MonsterFactory.createMonster("Assassin", level);
        } else if (level < 7) {
            if (num < 0.35)
                return MonsterFactory.createMonster("Goblin", level);
            else if (num >= 0.35 && num < 0.55)
                return MonsterFactory.createMonster("Spider", level);
            else if (num >= 0.55 && num < 0.75)
                return MonsterFactory.createMonster("Assassin", level);
            else
                return MonsterFactory.createMonster("Saboteur", level);
        } else if (level < 10) {
            return MonsterFactory.createMonster("Spider", level);
        }
        else
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

    public void unregistMonster(Monster monster) {
        monsters.remove(monster);
    }

    /**
     * buffwork()方法能够自动遍历player以及怪物的拥有的状态，并让他们起自己本应当起的作用，
     *
     * @param timespan :
     *                 1·在 TimeGoOn()中被调用 timespan == true,
     *                 2·在 MonsterClear()中被调用 timespan == false
     */
    private void buffWork(boolean timespan) {
        if (timespan) {
            for (int i = 0; i < player.keepBuffs.size(); i++) {
                Buff buff = player.keepBuffs.get(i);
                buff.time -= 1;
                if (buff.clear()) {
                    this.player.dropBuff(buff);
                }
            }

            for (int i = 0; i < player.unkeepBuffs.size(); i++) {
                if (player.unkeepBuffs.get(i) instanceof RoundEndBuff) {
                    Buff buff = player.unkeepBuffs.get(i);
                    buff.doWork(-1, -1, this);
                    if (buff.clear()) {
                        player.dropBuff(buff);                  //如果buff已经到期，那么丢掉这个buff
                    }
                }
            }           //遍历Player的Buff

            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).status == Cell.DISCORVERED && cells.get(i).monster != null) {
                    Monster m = cells.get(i).monster;
                    for (int j = 0; j < m.unkeepBuffs.size(); j++) {
                        if (m.unkeepBuffs.get(j) instanceof RoundEndBuff) {
                            Buff buff = m.unkeepBuffs.get(j);
                            buff.doWork(i % 8, i / 8, this);
                            if (buff.clear()) {
                                m.dropBuff(buff);                   //如果该buff已经到期，那么怪物将把这个状态放下
                            }
                        }
                    }
                }
            }           //遍历所有Monster的Buff

        } else {

            for (int i = 0; i < player.unkeepBuffs.size(); i++) {
                if (player.unkeepBuffs.get(i) instanceof MonsterClearBuff) {
                    Buff buff = player.unkeepBuffs.get(i);
                    buff.doWork(-1, -1, this);
                    if (buff.clear()) {
                        player.dropBuff(buff);                   //如果该buff已经到期，那么怪物将把这个状态放下
                    }

                }
            }           //遍历Player的Buff

            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).status == Cell.DISCORVERED && cells.get(i).monster != null) {
                    Monster m = cells.get(i).monster;
                    for (int j = 0; j < m.unkeepBuffs.size(); j++) {
                        if (m.unkeepBuffs.get(j) instanceof MonsterClearBuff) {
                            Buff buff = m.unkeepBuffs.get(j);
                            buff.doWork(i % 8, i / 8, this);
                            if (buff.clear()) {
                                m.dropBuff(buff);                   //如果该buff已经到期，那么怪物将把这个状态放下
                            }
                        }
                    }
                }
            }           //遍历所有Monster的Buff
        }
    }

    /**
     * buffwork()方法代入具体的Unit 参数，能够遍历该Unit的所有的状态，并执行它们
     *
     * @param x    :       死亡，或者受伤的单位的 x 坐标;如果为 -1 则默认为 player
     * @param y    :       死亡，或者受伤的单位的 y 坐标;如果为 -1 则默认为 player
     * @param unit :    死亡的或者受伤的单位.
     * @param type :    如果type为true ，则代表Unit是受伤，否则代表Unit死亡
     */
    private void buffWork(int x, int y, Unit unit, boolean type) {
        if (type) {
            for (int i = 0; i < unit.unkeepBuffs.size(); i++) {
                if (unit.unkeepBuffs.get(i) instanceof SufferDamageBuff) {
                    Buff buff = unit.unkeepBuffs.get(i);
                    buff.doWork(x, y, this);
                    if (buff.clear()) {
                        unit.dropBuff(buff);
                    }
                }
            }
        } else {
            for (int i = 0; i < unit.unkeepBuffs.size(); i++) {
                if (unit.unkeepBuffs.get(i) instanceof MonsterDieBuff) {
                    Buff buff = unit.unkeepBuffs.get(i);
                    buff.doWork(x, y, this);
                    if (buff.clear()) {
                        unit.dropBuff(buff);
                    }
                }
            }
        }
    }

    /**
     * 获取showobject 的图片资源
     */
    public Bitmap getMonster_shuxing_bg() {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.gameview_monster_shuxing);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (Const.SCREENHEIGHT * 0.6), (int) (Const.SCREENWIDTH), true);
        return bitmap;
    }

    public Bitmap getSkill_shuxing_bg() {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.gameview_skill_shuxing_bg);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (Const.SCREENHEIGHT * 0.6), Const.SCREENWIDTH, true);
        return bitmap;
    }

}

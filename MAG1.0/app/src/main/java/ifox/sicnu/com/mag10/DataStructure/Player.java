package ifox.sicnu.com.mag10.DataStructure;

import android.graphics.Bitmap;
import android.util.Log;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.HeroBuff;
import ifox.sicnu.com.mag10.DataStructure.Skill.Skill;
import ifox.sicnu.com.mag10.EquipmentFactory;
import ifox.sicnu.com.mag10.Tool.UpLevelFilter;

/**
 * Created by Funchou Fu on 2017/2/24.
 * 玩家的属性
 * 2017年3月12日 17:35:49 新增加玩家经验值的获取功能
 * 2017年3月13日12:53:14 修改代码
 */
public class Player extends Unit {
    public Bitmap face;        //玩家选择的英雄，及其对应的面容

    public int r_power;             //力量
    public int r_agile;             //敏捷
    public int r_intelligence;      //智力

    public boolean bagswitch;          //true 则代表当前背包被打开，false 代表背包被关闭
    public boolean skillswitch;        //true 则代表当前选择的是技能栏，false 选择的是道具栏
    public int money;          //当前玩家拥有的金钱
    public int key;            //当前玩家拥有的钥匙

    public int level;          //当前玩家的等级
    public int exp;             //玩家当前拥有的经验值


    public Equipment weapon;
    public Equipment co_weapon;
    public Equipment helmet;
    public Equipment wear_up;
    public Equipment wear_down;
    public Equipment hand;
    public Equipment belt;
    public Equipment shoe;

    private UpLevelFilter upLevelFilter;

    public Skill[] skills = new Skill[3];                  //玩家拥有的技能

    public Skill[] tools = new Skill[3];

    public Equipment[] equipments = new Equipment[12];     //玩家的背包

    public Player(Hero hero, HeroBuff heroBuff) {
        this.face = hero.face;

        this.r_power = hero.r_power;
        this.r_agile = hero.r_agile;
        this.r_intelligence = hero.r_intelligence;

        this.atk = hero.atk + heroBuff.atk;
        this.hitrate = hero.hitrate;
        this.crit = hero.crit;

        this.armor = hero.armor + heroBuff.armor;
        this.dodge = hero.dodge;
        this.resistance = hero.resistance;

        this.maxHp = hero.maxHp + heroBuff.hp;
        this.maxMp = hero.maxMp + heroBuff.mp;
        this.maxPp = hero.maxPp + heroBuff.pp;

        this.upLevelFilter = hero.upLevelFilter;

        this.def = this.armor;                  //将实际的护甲值等于为自己的防御值
        this.hp = this.maxHp;
        this.mp = this.maxMp;
        this.pp = 10;                           //DEBUG 期间，将pp属性设置为10

        this.level = 1;                         //等级默认为1
        this.money = heroBuff.money + 100;            //金钱读取
        this.key = 0;                           //钥匙默认为0
        this.skillswitch = false;               //默认选择为道具
        this.bagswitch = false;                 //默认背包为未打开

        this.exp = 160;                          //DEBUG 期间，将exp属性设置为10;

        if (hero.weapon != null)
            this.weapon = new Equipment(hero.weapon);
        if (hero.co_weapon != null)
            this.co_weapon = new Equipment(hero.co_weapon);
        if (hero.helmet != null)
            this.helmet = new Equipment(hero.helmet);
        if (hero.hand != null)
            this.hand = new Equipment(hero.hand);
        if (hero.wear_up != null)
            this.wear_up = new Equipment(hero.wear_up);
        if (hero.wear_down != null)
            this.wear_down = new Equipment(hero.wear_down);
        if (hero.belt != null)
            this.belt = new Equipment(hero.belt);
        if (hero.shoe != null)
            this.shoe = new Equipment(hero.shoe);                  //初始装备从选择的英雄里进行刻录

        for (int i = 0; i < 3; i++) {
            this.skills[i] = hero.skills[i];
            if (this.skills[i] != null)
                this.skills[i].setnewUnit(this);
        }//同步技能

        /**
         * 测试时，将所有背包装到 10 个*/
        for (int i = 0; i < 8; i++) {
            this.equipments[i] = EquipmentFactory.createWeapon("sword_2");
        }
        for (int i = 8; i < 10; i++) {
            this.equipments[i] = EquipmentFactory.createWeapon("sword_3");
        }
    }

    //将装备装备到自己的指定部位
    public Equipment wear(Equipment equipment) {
        if (equipment == null)
            return null;     //如果传入的装备是空的，返回

        Equipment e = null;
        switch (equipment.type) {
            case Equipment.WEAPON:
                e = this.weapon;
                this.weapon = equipment;
                break;
            case Equipment.CO_WEAPON:
                e = this.co_weapon;
                this.co_weapon = equipment;
                break;
            case Equipment.HELMET:
                e = this.helmet;
                this.helmet = equipment;
                break;
            case Equipment.HAND:
                e = this.hand;
                this.hand = equipment;
                break;
            case Equipment.WEAR_UP:
                e = this.wear_up;
                this.wear_up = equipment;
                break;
            case Equipment.BELT:
                e = this.belt;
                this.belt = equipment;
                break;
            case Equipment.WEAR_BELOW:
                e = this.wear_down;
                this.wear_down = equipment;
                break;
            case Equipment.SHOE:
                e = this.shoe;
                this.shoe = equipment;
                break;
        }
        this.atm = equipment.atm;

        this.atk += equipment.atk;
        this.crit += equipment.crit;
        this.hitrate += equipment.hitrate;

        this.armor += equipment.armor;
        this.dodge += equipment.dodge;
        this.resistance += equipment.resistance;

        this.maxHp += equipment.maxHp;
        this.maxMp += equipment.maxMp;
        this.maxPp += equipment.maxPp;
        return e;
    }

    //传来一个装备，将其放入自己的背包里
    public void inbag(Equipment equipment) {
        if (equipment == null)
            return;     //如果传入的装备是空，返回
        for (int i = 0; i < 12; i++) {
            if (this.equipments[i] != null) {
                continue;
            } else {
                this.equipments[i] = equipment;
                break;
            }
        }
    }

    //将装备从自己的身上卸下
    public void diswear(Equipment equipment) {
        if (equipment == null) {
            return;
        }
        if (this.weapon == equipment)
            this.weapon = null;
        else if (this.co_weapon == equipment)
            this.co_weapon = null;
        else if (this.helmet == equipment)
            this.helmet = null;
        else if (this.hand == equipment)
            this.hand = null;
        else if (this.wear_up == equipment)
            this.equipments = null;
        else if (this.belt == equipment)
            this.belt = null;
        else if (this.wear_down == equipment)
            this.wear_down = null;
        else if (this.shoe == equipment)
            this.shoe = null;

        this.atm = null;

        this.atk -= equipment.atk;
        this.crit -= equipment.crit;
        this.hitrate -= equipment.hitrate;

        this.armor -= equipment.armor;
        this.dodge -= equipment.dodge;
        this.resistance -= equipment.resistance;

        this.maxHp -= equipment.maxHp;
        this.maxMp -= equipment.maxMp;
        this.maxPp -= equipment.maxPp;
    }

    public void disinbag(Equipment equipment) {
        for (int i = 0; i < 12; i++) {
            if (equipments[i] == equipment) {
                equipments[i] = null;
            }
        }
    }

    //如果自身没有那件装备，会返回空
    public Equipment getEquipmentByNumber(int index) {
        if (index == -1)
            return null;
        if (index < 12) {
            return this.equipments[index];
        } else {
            switch (index) {
                case 12:
                    return this.weapon;
                case 13:
                    return this.co_weapon;
                case 14:
                    return this.helmet;
                case 15:
                    return this.hand;
                case 16:
                    return this.wear_up;
                case 17:
                    return this.belt;
                case 18:
                    return this.wear_down;
                case 19:
                    return this.shoe;

            }
        }
        return null;
    }

    /**
     * @param x,y 横坐标，纵坐标
     * @param n   释放的第几个技能 0~2
     */
    public boolean useSkill(int x, int y, BattleManager bm, int n) {
        return this.skills[n].doSkill(x, y, bm);
    }

    /**
     * 和useSkill相同，在释放完技能后，该道具即会消除
     */
    public boolean useTool(int x, int y, BattleManager bm, int n) {
        boolean b = this.tools[n].doSkill(x, y, bm);
        this.tools[n] = null;
        return b;
    }

    /**
     * 为true 表示购买成功
     */
    public boolean buy(Equipment e) {
        Log.i("Player", "buy: " + String.valueOf(e == null));
        if (e == null || this.money < e.money) {
            return false;
        } else {
            for (int i = 0; i < equipments.length; i++) {
                if (equipments[i] == null) {
                    this.equipments[i] = e;
                    this.money -= e.money;
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 这是Player 获得经验时调用的方法
     * 如果Player 升级了，会返还true ， 否则返还 false
     *
     * @param exp 获得的经验值
     */
    public boolean addExp(int exp) {
        this.exp += exp;
        if (this.exp > Const.Exp.levels[this.level]) {
            this.exp -= Const.Exp.levels[this.level];
            this.level += 1;
            return true;
        } else
            return false;
    }

    /**
     * 这是外部查看自己当前需要多少经验值才能升级的时候调用的方法
     */
    public int getNeedExp() {
        return Const.Exp.levels[this.level];
    }

    public void upLevel() {
        this.upLevelFilter.uplevel(this);
        this.mp = this.maxMp;
        this.hp = this.maxHp;
        this.def = this.armor;
    }

}
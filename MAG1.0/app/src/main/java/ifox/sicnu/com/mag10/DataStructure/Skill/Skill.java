package ifox.sicnu.com.mag10.DataStructure.Skill;

import android.graphics.Bitmap;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/2/27.
 * 2017年3月13日13:12:52 增加cost变量
 */
public abstract class Skill {
    public String name;            //技能的名字
    public String introduce;       //技能的介绍
    public Bitmap bitmap;          //技能的图片
    protected Unit user;         //技能的使用者
    public int cost;            //如果是Tool则会花费魂力


    public static final int ATK = 0;
    public static final int HITRATE = 1;
    public static final int CRIT = 2;
    public static final int ARMMOR = 3;
    public static final int DODGE = 4;
    public static final int RESISTANCE = 5;
    public static final int MAXHP = 6;
    public static final int MAXMP = 7;
    public static final int MAXPP = 8;
    public static final int HP = 9;
    public static final int MP = 10;
    public static final int PP = 11;
    public static final int DEF = 12;

    public Skill(Unit user) {
        this.user = user;
    }

    public void setnewUnit(Unit unit) {
        this.user = unit;
    }

    //为true 代表释放技能成功
    public abstract boolean doSkill(int x, int y, BattleManager bm);

    /**
     * 这个方法能够根据传入的type 来返回 使用者的各项数据
     */
    protected float getValue(int type) {
        if (type == ATK)
            return this.user.atk;
        else if (type == HITRATE)
            return this.user.hitrate;
        else if (type == CRIT)
            return this.user.crit;
        else if (type == ARMMOR)
            return this.user.armor;
        else if (type == DODGE)
            return this.user.dodge;
        else if (type == RESISTANCE)
            return this.user.resistance;
        else if (type == MAXHP)
            return this.user.maxHp;
        else if (type == MAXMP)
            return this.user.maxMp;
        else if (type == MAXPP)
            return this.user.maxPp;
        else if (type == HP)
            return this.user.hp;
        else if (type == MP)
            return this.user.mp;
        else if (type == PP)
            return this.user.pp;
        else if (type == DEF)
            return this.user.def;
        return 0;
    }

    protected void subValue(int type, float costValue) {
        if (type == ATK)
            this.user.atk -= costValue;
        else if (type == HITRATE)
            this.user.hitrate -= costValue;
        else if (type == CRIT)
            this.user.crit -= costValue;
        else if (type == ARMMOR)
            this.user.armor -= costValue;
        else if (type == DODGE)
            this.user.dodge -= costValue;
        else if (type == RESISTANCE)
            this.user.resistance -= costValue;
        else if (type == MAXHP)
            this.user.maxHp -= costValue;
        else if (type == MAXMP)
            this.user.maxMp -= costValue;
        else if (type == MAXPP)
            this.user.maxPp -= costValue;
        else if (type == HP)
            this.user.hp -= costValue;
        else if (type == MP)
            this.user.mp -= costValue;
        else if (type == PP)
            this.user.pp -= costValue;
        else if (type == DEF)
            this.user.def -= costValue;
    }
}

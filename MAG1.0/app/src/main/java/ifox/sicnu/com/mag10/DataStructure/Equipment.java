package ifox.sicnu.com.mag10.DataStructure;

import android.graphics.Bitmap;

import ifox.sicnu.com.mag10.Affix.Affix;
import ifox.sicnu.com.mag10.DataStructure.AttackMethod.AttackMethod;

/**
 * Created by Funchou Fu on 2017/2/27.
 * 玩家通过装备增加自身属性是通过wear(Equipment e)方法来获取 装备对应的Buff.
 * 同样玩家通过unWear(Equipment e)方法会根据Buff 记录的根(装备) 来卸下相应的Buff。
 * 武器分为 主武器，副武器，双手武器
 */
public class Equipment {
    public static final int WEAPON = 0;
    public static final int CO_WEAPON = 1;
    public static final int D_WEAPON = 2;
    public static final int HELMET = 3;
    public static final int WEAR_UP = 4;
    public static final int WEAR_BELOW = 5;
    public static final int HAND = 6;
    public static final int BELT = 7;
    public static final int SHOE = 8;

    public int power_require;              //力量
    public int agile_require;              //敏捷
    public int wize_require;               //智力

    public int atk;             //伤害
    public float hitrate;         //命中
    public float crit;            //爆击

    public int armor;           //可回复到的护甲
    public float dodge;           //闪避
    public float resistance;      //抗性

    public int maxHp;          //增加最大生命值
    public int maxMp;          //增加最大法力值
    public int maxPp;          //增加最大魂力值

    public String equipmentName;   //装备名字
    public Bitmap bitmap;          //装备图片
    public String bitmapID;        //装备图片名字 R.drawble.XXX
    public String introduce;       //装备介绍

    public int money;           //购买该装备的金钱
    public int type;      //该装备的类型

    public Affix affix;         //该装备的词缀，能够附加额外的属性 词缀 不会被存在数据库中

    public AttackMethod atm;

    public Equipment() {

    }

    public Equipment(Equipment e) {
        this.power_require = e.power_require;
        this.agile_require = e.agile_require;
        this.wize_require = e.wize_require;

        this.atk = e.atk;
        this.hitrate = e.hitrate;
        this.crit = e.crit;

        this.armor = e.armor;
        this.dodge = e.dodge;
        this.resistance = e.resistance;

        this.maxHp = e.maxHp;
        this.maxMp = e.maxMp;
        this.maxPp = e.maxPp;

        this.equipmentName = e.equipmentName;
        this.bitmap = e.bitmap;
        this.bitmapID = e.bitmapID;
        this.introduce = e.introduce;
        this.type = e.type;
    }

    public String getRealName() {
        if (affix != null)
            return affix.toString() + this.equipmentName;
        else
            return this.equipmentName;
    }

    public void addAfix(Affix affix) {
        affix.doWork(this);
        this.affix = affix;
    }
}
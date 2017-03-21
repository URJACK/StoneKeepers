package ifox.sicnu.com.mag10.DataStructure;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.AttackMethod.AttackMethod;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Equipment;
import ifox.sicnu.com.mag10.DataStructure.Unit;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/20.
 */
public class EquipmentFactory {
    public static Bitmap bm_sword_1;
    public static Bitmap bm_sword_2;
    public static Bitmap bm_axe_1;
    public static Bitmap bm_axe_2;

    static {
        bm_sword_1 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.sword_2);
        bm_sword_1 = Bitmap.createScaledBitmap(bm_sword_1, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_sword_2 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.sword_1);
        bm_sword_2 = Bitmap.createScaledBitmap(bm_sword_2, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_axe_1 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.axe_1);
        bm_axe_1 = Bitmap.createScaledBitmap(bm_axe_1, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_axe_2 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.axe_2);
        bm_axe_2 = Bitmap.createScaledBitmap(bm_axe_2, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);

    }

    public static Equipment createWeapon(String name) {
        if (name.equals("sword_1")) {
            return createSword_1();
        } else if (name.equals("sword_2")) {
            return createSword_2();
        } else if (name.equals("sword_3")) {
            return createSword_3();
        } else if (name.equals("sword_4")) {
            return createSword_4();
        } else if (name.equals("sword_5")) {
            return createSword_5();
        } else if (name.equals("axe_1")) {
            return createAxe_1();
        } else if (name.equals("axe_2")) {
            return createAxe_2();
        } else if (name.equals("axe_3")) {
            return createAxe_3();
        } else if (name.equals("axe_4")) {
            return createAxe_4();
        }
        return null;
    }
    public static Equipment createCoWeapon(String name){
        if (name.equals("shield_1"))
            return createshield_1();
        else if (name.equals("shield_2"))
            return createshield_2();
        else if (name.equals("shield_3"))
            return createshield_3();
        return null;
    }

    private static Equipment createshield_3() {
        return null;
    }

    private static Equipment createshield_2() {
        return null;
    }

    private static Equipment createshield_1() {

        return null;
    }


    private static Equipment createAxe_4() {
        Equipment equipment = new Equipment();
        equipment.atk = 20;
        equipment.atm = new AttackMethod() {
            @Override
            public boolean attack(Unit me, Unit unit) {
                if (unit.sufferDamage(me.atk, true))
                    return true;
                else
                    return false;
            }
        };

        equipment.equipmentName = "大斧";
        equipment.introduce = "这是一把大斧";
        equipment.money = 40;
        equipment.type = Equipment.D_WEAPON;
        equipment.bitmap = bm_axe_2;
        return equipment;
    }

    private static Equipment createAxe_3() {
        Equipment equipment = new Equipment();
        equipment.atk = 13;
        equipment.atm = new AttackMethod() {
            @Override
            public boolean attack(Unit me, Unit unit) {
                unit.wearBuff(BuffFactory.createKeepBuff("defDecrease"));
                if (me.normalAtk(unit))
                    return true;
                else
                    return false;
            }
        };

        equipment.equipmentName = "猎豹斧";
        equipment.introduce = "这是一把猎豹斧";
        equipment.money = 40;
        equipment.type = Equipment.D_WEAPON;
        equipment.bitmap = bm_axe_2;
        return equipment;
    }

    private static Equipment createAxe_2() {
        Equipment equipment = new Equipment();
        equipment.atk = 13;

        equipment.equipmentName = "强化斧";
        equipment.introduce = "这是一把强化斧";
        equipment.money = 20;
        equipment.type = Equipment.D_WEAPON;
        equipment.bitmap = bm_axe_1;
        return equipment;
    }

    private static Equipment createAxe_1() {
        Equipment equipment = new Equipment();
        equipment.atk = 9;

        equipment.equipmentName = "普通斧";
        equipment.introduce = "这是一把普通斧";
        equipment.money = 10;
        equipment.type = Equipment.D_WEAPON;
        equipment.bitmap = bm_axe_1;
        return equipment;
    }

    private static Equipment createSword_5() {
        Equipment equipment = new Equipment();
        equipment.atk = 9;
        equipment.hitrate = (float) 0.1;
        equipment.crit = (float) 0.1;
        equipment.resistance = (float) 0.1;
        equipment.maxHp = 5;
        equipment.maxMp = 5;
        equipment.maxPp = 5;
        equipment.atm = new AttackMethod() {
            @Override
            public boolean attack(Unit me, Unit unit) {
                unit.wearBuff(BuffFactory.createKeepBuff("defDecrease"));
                if (me.normalAtk(unit))
                    return true;
                else
                    return false;
            }
        };
        equipment.equipmentName = "猎豹剑";
        equipment.introduce = "这是一把猎豹剑";
        equipment.money = 50;
        equipment.type = Equipment.WEAPON;
        equipment.bitmap = bm_sword_2;
        return equipment;
    }

    private static Equipment createSword_4() {
        Equipment equipment = new Equipment();
        equipment.atk = 9;
        equipment.hitrate = (float) 0.12;
        equipment.atm = new AttackMethod() {
            @Override
            public boolean attack(Unit me, Unit unit) {
                if (me.normalAtk(unit))
                    ;
                else
                    return false;
                if (me.normalAtk(unit))
                    return true;
                else
                    return false;
            }
        };

        equipment.equipmentName = "神风剑";
        equipment.introduce = "这是一把神风剑";
        equipment.money = 50;
        equipment.type = Equipment.WEAPON;
        equipment.bitmap = bm_sword_2;
        return equipment;
    }

    private static Equipment createSword_3() {
        Equipment equipment = new Equipment();
        equipment.atk = 11;
        equipment.hitrate = (float) 0.1;
        equipment.crit = (float) 0.1;

        equipment.equipmentName = "爆炎剑";
        equipment.introduce = "这是一把爆炎剑";
        equipment.money = 35;
        equipment.type = Equipment.WEAPON;
        equipment.bitmap = bm_sword_2;
        return equipment;
    }

    private static Equipment createSword_2() {
        Equipment equipment = new Equipment();
        equipment.atk = 9;
        equipment.hitrate = (float) 0.1;

        equipment.equipmentName = "强化剑";
        equipment.introduce = "这是一把强化剑";
        equipment.money = 20;
        equipment.type = Equipment.WEAPON;
        equipment.bitmap = bm_sword_1;
        return equipment;
    }

    private static Equipment createSword_1() {
        Equipment equipment = new Equipment();
        equipment.atk = 6;
        equipment.hitrate = (float) 0.1;

        equipment.equipmentName = "普通剑";
        equipment.introduce = "这是一把普通剑";
        equipment.money = 10;
        equipment.type = Equipment.WEAPON;
        equipment.bitmap = bm_sword_1;
        return equipment;
    }
}

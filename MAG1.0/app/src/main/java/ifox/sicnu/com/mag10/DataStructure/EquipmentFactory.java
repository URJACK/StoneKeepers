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
    public static Bitmap bm_shield_1;
    public static Bitmap bm_shield_2;
    public static Bitmap bm_hand_1;
    public static Bitmap bm_hand_2;
    public static Bitmap bm_up_1;
    public static Bitmap bm_up_2;
    public static Bitmap bm_down_1;
    public static Bitmap bm_down_2;
    public static Bitmap bm_belt_1;
    public static Bitmap bm_belt_2;
    public static Bitmap bm_shoe_1;
    public static Bitmap bm_shoe_2;
    public static Bitmap bm_helmet_1;
    public static Bitmap bm_helmet_2;

    static {
        bm_sword_1 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.sword_2);
        bm_sword_1 = Bitmap.createScaledBitmap(bm_sword_1, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_sword_2 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.sword_1);
        bm_sword_2 = Bitmap.createScaledBitmap(bm_sword_2, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_axe_1 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.axe_1);
        bm_axe_1 = Bitmap.createScaledBitmap(bm_axe_1, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_axe_2 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.axe_2);
        bm_axe_2 = Bitmap.createScaledBitmap(bm_axe_2, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_shield_1 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.shield_1);
        bm_shield_1 = Bitmap.createScaledBitmap(bm_shield_1, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_shield_2 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.shield_2);
        bm_shield_2 = Bitmap.createScaledBitmap(bm_shield_2, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_hand_1 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.hand_1);
        bm_hand_1 = Bitmap.createScaledBitmap(bm_hand_1, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_hand_2 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.hand_2);
        bm_hand_2 = Bitmap.createScaledBitmap(bm_hand_2, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_up_1 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.upwear_1);
        bm_up_1 = Bitmap.createScaledBitmap(bm_up_1, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_up_2 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.upwear_2);
        bm_up_2 = Bitmap.createScaledBitmap(bm_up_2, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_down_1 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.downwear_1);
        bm_down_1 = Bitmap.createScaledBitmap(bm_down_1, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_down_2 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.downwear_2);
        bm_down_2 = Bitmap.createScaledBitmap(bm_down_2, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_belt_1 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.belt_1);
        bm_belt_1 = Bitmap.createScaledBitmap(bm_belt_1, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_belt_2 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.belt_2);
        bm_belt_2 = Bitmap.createScaledBitmap(bm_belt_2, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_shoe_1 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.shoe_1);
        bm_shoe_1 = Bitmap.createScaledBitmap(bm_shoe_1, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_shoe_2 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.shoe_2);
        bm_shoe_2 = Bitmap.createScaledBitmap(bm_shoe_2, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_helmet_1 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.helmet_1);
        bm_helmet_1 = Bitmap.createScaledBitmap(bm_helmet_1, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        bm_helmet_2 = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.helmet_2);
        bm_helmet_2 = Bitmap.createScaledBitmap(bm_helmet_2, Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
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

    public static Equipment createCoWeapon(String name) {
        if (name.equals("shield_1"))
            return createshield_1();
        else if (name.equals("shield_2"))
            return createshield_2();
        else if (name.equals("shield_3"))
            return createshield_3();
        return null;
    }

    public static Equipment createHand(String name) {
        if (name.equals("hand_1")) {
            return createHand_1();
        } else if (name.equals("hand_2")) {
            return createHand_2();
        } else if (name.equals("hand_3")) {
            return createHand_3();
        }
        return null;
    }

    public static Equipment createUpWear(String name) {
        if (name.equals("up_1"))
            return createUp_1();
        else if (name.equals("up_2"))
            return createUp_2();
        else if (name.equals("up_3"))
            return createUp_3();
        return null;
    }

    public static Equipment createDownWear(String name) {
        if (name.equals("down_1"))
            return createDown_1();
        else if (name.equals("down_2"))
            return createDown_2();
        else if (name.equals("down_3"))
            return createDown_3();
        return null;
    }

    public static Equipment createBelt(String name) {
        if (name.equals("belt_1"))
            return createBelt_1();
        else if (name.equals("belt_2"))
            return createBelt_2();
        else if (name.equals("belt_3"))
            return createBelt_3();
        else
            return null;
    }

    public static Equipment createShoe(String name) {
        if (name.equals("shoe_1"))
            return createShoe_1();
        else if (name.equals("shoe_2"))
            return createShoe_2();
        else if (name.equals("shoe_3"))
            return createShoe_3();
        else
            return null;
    }

    public static Equipment createHelmet(String name) {
        if (name.equals("helmet_1"))
            return createHelmet_1();
        else if (name.equals("helmet_2"))
            return createHelmet_2();
        else if (name.equals("helmet_3"))
            return createHelmet_3();
        else
            return null;
    }


    private static Equipment createHelmet_3() {
        Equipment equipment = new Equipment();
        equipment.maxHp = 80;

        equipment.equipmentName = "宝盔";
        equipment.introduce = "这是宝盔";
        equipment.money = 40;
        equipment.type = Equipment.HELMET;
        equipment.bitmap = bm_helmet_2;

        equipment.atm = new AttackMethod() {
            @Override
            public boolean attack(Unit me, Unit unit) {
                me.hp += 3;
                if (me.hp > me.maxHp)
                    me.hp = me.maxHp;
                if (unit.hp < 0)
                    return true;
                else
                    return false;
            }
        };
        return equipment;
    }

    private static Equipment createHelmet_2() {
        Equipment equipment = new Equipment();
        equipment.maxHp = 50;

        equipment.equipmentName = "精良头盔";
        equipment.introduce = "这是一条精良头盔";
        equipment.money = 20;
        equipment.type = Equipment.HELMET;
        equipment.bitmap = bm_helmet_1;
        return equipment;
    }

    private static Equipment createHelmet_1() {
        Equipment equipment = new Equipment();
        equipment.maxHp = 20;

        equipment.equipmentName = "普通头盔";
        equipment.introduce = "这是一条普通头盔";
        equipment.money = 10;
        equipment.type = Equipment.HELMET;
        equipment.bitmap = bm_helmet_1;
        return equipment;
    }


    private static Equipment createShoe_3() {
        Equipment equipment = new Equipment();
        equipment.dodge = (float) 0.2;
        equipment.maxPp = 15;

        equipment.equipmentName = "宝鞋";
        equipment.introduce = "这是一条宝鞋";
        equipment.money = 40;
        equipment.type = Equipment.SHOE;
        equipment.bitmap = bm_shoe_2;
        return equipment;
    }

    private static Equipment createShoe_2() {
        Equipment equipment = new Equipment();
        equipment.dodge = (float) 0.15;
        equipment.maxPp = 10;

        equipment.equipmentName = "精良鞋子";
        equipment.introduce = "这是一条精良鞋子";
        equipment.money = 20;
        equipment.type = Equipment.SHOE;
        equipment.bitmap = bm_shoe_1;
        return equipment;
    }

    private static Equipment createShoe_1() {
        Equipment equipment = new Equipment();
        equipment.dodge = (float) 0.1;
        equipment.maxPp = 5;

        equipment.equipmentName = "普通鞋子";
        equipment.introduce = "这是一条普通鞋子";
        equipment.money = 10;
        equipment.type = Equipment.SHOE;
        equipment.bitmap = bm_shoe_1;
        return equipment;
    }


    private static Equipment createBelt_3() {
        Equipment equipment = new Equipment();
        equipment.resistance = (float) 0.3;
        equipment.maxMp = 15;

        equipment.equipmentName = "宝带";
        equipment.introduce = "这是宝带";
        equipment.money = 20;
        equipment.type = Equipment.BELT;
        equipment.bitmap = bm_belt_2;

        equipment.atm = new AttackMethod() {
            @Override
            public boolean attack(Unit me, Unit unit) {
                if (unit.hp < 0) {
                    me.hp += unit.maxHp * 0.2;
                    return true;
                } else
                    return false;
            }
        };
        return equipment;
    }

    private static Equipment createBelt_2() {
        Equipment equipment = new Equipment();
        equipment.resistance = (float) 0.2;
        equipment.maxMp = 10;

        equipment.equipmentName = "精良腰带";
        equipment.introduce = "这是一条精良腰带";
        equipment.money = 20;
        equipment.type = Equipment.BELT;
        equipment.bitmap = bm_belt_1;
        return equipment;
    }

    private static Equipment createBelt_1() {
        Equipment equipment = new Equipment();
        equipment.resistance = (float) 0.1;
        equipment.maxMp = 5;

        equipment.equipmentName = "普通腰带";
        equipment.introduce = "这是一条普通腰带";
        equipment.money = 10;
        equipment.type = Equipment.BELT;
        equipment.bitmap = bm_belt_1;
        return equipment;
    }


    private static Equipment createDown_3() {
        Equipment equipment = new Equipment();
        equipment.armor = 15;
        equipment.maxHp = 35;

        equipment.equipmentName = "宝裤";
        equipment.introduce = "这是宝裤";
        equipment.money = 40;
        equipment.type = Equipment.WEAR_BELOW;
        equipment.bitmap = bm_down_2;
        return equipment;
    }

    private static Equipment createDown_2() {
        Equipment equipment = new Equipment();
        equipment.armor = 10;
        equipment.maxHp = 25;

        equipment.equipmentName = "精良裤子";
        equipment.introduce = "这是一条精良裤子";
        equipment.money = 20;
        equipment.type = Equipment.WEAR_BELOW;
        equipment.bitmap = bm_down_1;
        return equipment;
    }

    private static Equipment createDown_1() {
        Equipment equipment = new Equipment();
        equipment.armor = 5;
        equipment.maxHp = 10;

        equipment.equipmentName = "普通裤子";
        equipment.introduce = "这是一条普通裤子";
        equipment.money = 10;
        equipment.type = Equipment.WEAR_BELOW;
        equipment.bitmap = bm_down_1;
        return equipment;
    }


    private static Equipment createUp_3() {
        Equipment equipment = new Equipment();
        equipment.armor = 30;
        equipment.maxHp = 15;

        equipment.equipmentName = "宝衣";
        equipment.introduce = "这是宝衣";
        equipment.money = 40;
        equipment.type = Equipment.WEAR_UP;
        equipment.bitmap = bm_up_2;
        return equipment;
    }

    private static Equipment createUp_2() {
        Equipment equipment = new Equipment();
        equipment.armor = 20;
        equipment.maxHp = 10;

        equipment.equipmentName = "精良上衣";
        equipment.introduce = "这是一件精良上衣";
        equipment.money = 20;
        equipment.type = Equipment.WEAR_UP;
        equipment.bitmap = bm_up_1;
        return equipment;
    }

    private static Equipment createUp_1() {
        Equipment equipment = new Equipment();
        equipment.armor = 10;

        equipment.equipmentName = "普通上衣";
        equipment.introduce = "这是一件普通上衣";
        equipment.money = 10;
        equipment.type = Equipment.WEAR_UP;
        equipment.bitmap = bm_up_1;
        return equipment;
    }


    private static Equipment createHand_3() {
        Equipment equipment = new Equipment();
        equipment.hitrate = (float) 0.15;
        equipment.crit = (float) 0.1;
        equipment.resistance = (float) 0.1;
        equipment.armor = 40;

        equipment.equipmentName = "血手";
        equipment.introduce = "这是血手";
        equipment.money = 40;
        equipment.type = Equipment.HAND;
        equipment.bitmap = bm_hand_2;

        equipment.atm = new AttackMethod() {
            @Override
            public boolean attack(Unit me, Unit unit) {
                me.hp += 3;
                if (me.hp > me.maxHp)
                    me.hp = me.maxHp;
                if (unit.hp < 0)
                    return true;
                else
                    return false;
            }
        };
        return equipment;
    }

    private static Equipment createHand_2() {
        Equipment equipment = new Equipment();
        equipment.hitrate = (float) 0.15;
        equipment.crit = (float) 0.1;
        equipment.armor = 8;
        equipment.resistance = (float) 0.1;

        equipment.equipmentName = "精良手套";
        equipment.introduce = "这是一把精良手套";
        equipment.money = 20;
        equipment.type = Equipment.HAND;
        equipment.bitmap = bm_hand_1;
        return equipment;
    }

    private static Equipment createHand_1() {
        Equipment equipment = new Equipment();
        equipment.hitrate = (float) 0.1;
        equipment.armor = 5;

        equipment.equipmentName = "普通手套";
        equipment.introduce = "这是一把普通手套";
        equipment.money = 10;
        equipment.type = Equipment.HAND;
        equipment.bitmap = bm_hand_1;
        return equipment;
    }


    private static Equipment createshield_3() {
        Equipment equipment = new Equipment();
        equipment.armor = 40;

        equipment.equipmentName = "神盾";
        equipment.introduce = "这是一个神盾";
        equipment.money = 40;
        equipment.type = Equipment.CO_WEAPON;
        equipment.bitmap = bm_shield_2;
        return equipment;
    }

    private static Equipment createshield_2() {
        Equipment equipment = new Equipment();
        equipment.armor = 25;

        equipment.equipmentName = "精良盾";
        equipment.introduce = "这是一个精良盾";
        equipment.money = 20;
        equipment.type = Equipment.CO_WEAPON;
        equipment.bitmap = bm_shield_1;
        return equipment;
    }

    private static Equipment createshield_1() {
        Equipment equipment = new Equipment();
        equipment.armor = 10;

        equipment.equipmentName = "普通盾";
        equipment.introduce = "这是一个普通盾";
        equipment.money = 10;
        equipment.type = Equipment.CO_WEAPON;
        equipment.bitmap = bm_shield_1;
        return equipment;
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

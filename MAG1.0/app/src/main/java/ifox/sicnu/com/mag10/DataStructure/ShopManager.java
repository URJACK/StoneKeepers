package ifox.sicnu.com.mag10.DataStructure;

import android.content.Context;
import android.util.Log;

import ifox.sicnu.com.mag10.Affix.AffixFactory;
import ifox.sicnu.com.mag10.Data.Pictures;

/**
 * Created by 41988 on 2017/3/9.
 */
public class ShopManager {
    private static final String TAG = "ShopManager";
    public Context mcontext;
    private static double span = ((double) 1) / 9;
    Shop shop;

    private Pictures pictures;
    Player player;

    public ShopManager(Context context, Player player) {
        this.mcontext = context;
        this.player = player;
    }

    //index 当前层数
    public Shop createShop(int index) {
        shop = new Shop(player, mcontext);
        setShoplist(index);
        return shop;
    }

    //通过代入floor 即，当前的层数的编号，来给传入的 shop生成对应的商品列表
    public void setShoplist(int floor) {
        double random = Math.random();
//        if (floor < 5) {
//            for (int i = 0; i < 8; i++) {
//                double f = Math.random();
//                if (f < 0.5) {
//                    Equipment e = EquipmentFactory.createWeapon("axe_1");
//                    e.addAfix(AffixFactory.createAffix());
//                    shop.S_equipments.add(e);
//                } else {
//                    Equipment e = EquipmentFactory.createWeapon("sword_1");
//                    e.addAfix(AffixFactory.createAffix());
//                    shop.S_equipments.add(e);
//                }
//            }
//        }
        if (floor < 5) {
            for (int i = 0; i < 8; i++)
                setbylist("sword_1", "axe_1", "shield_1", "hand_1", "up_1", "down_1", "belt_1", "shoe_1", "helmet_1");
        } else if (floor < 10) {
            for (int i = 0; i < 8; i++)
                setbylist("sword_1", "axe_2", "shield_1", "hand_1", "up_2", "down_1", "belt_2", "shoe_1", "helmet_2");
        } else if (floor < 15) {
            for (int i = 0; i < 8; i++)
                setbylist("sword_2", "axe_2", "shield_2", "hand_2", "up_2", "down_2", "belt_2", "shoe_2", "helmet_2");
        } else if (floor < 20) {
            for (int i = 0; i < 8; i++)
                setbylist("sword_3", "axe_3", "shield_2", "hand_2", "up_3", "down_2", "belt_3", "shoe_2", "helmet_3");
        } else if (floor < 25) {
            for (int i = 0; i < 8; i++)
                setbylist("sword_4", "axe_3", "shield_2", "hand_3", "up_3", "down_3", "belt_3", "shoe_3", "helmet_3");
        } else {
            for (int i = 0; i < 8; i++)
                setbylist("sword_5", "axe_4", "shield_3", "hand_3", "up_3", "down_3", "belt_3", "shoe_3", "helmet_3");
        }
    }

    private void setbylist(String sword, String axe, String coweapon, String hand, String up, String down, String belt, String shoe, String helmet) {
        double random = Math.random();
        Equipment e;
        if (random < span) {
            e = EquipmentFactory.createWeapon(sword);
        } else if (random < span * 2) {
            e = EquipmentFactory.createWeapon(axe);
        } else if (random < span * 3) {
            e = EquipmentFactory.createCoWeapon(coweapon);
        } else if (random < span * 4) {
            e = EquipmentFactory.createHand(hand);
        } else if (random < span * 5) {
            e = EquipmentFactory.createUpWear(up);
        } else if (random < span * 6) {
            e = EquipmentFactory.createDownWear(down);
        } else if (random < span * 7) {
            e = EquipmentFactory.createBelt(belt);
        } else if (random < span * 8) {
            e = EquipmentFactory.createShoe(shoe);
        } else {
            e = EquipmentFactory.createHelmet(helmet);
        }
        e.addAfix(AffixFactory.createAffix());
        shop.S_equipments.add(e);
    }
}

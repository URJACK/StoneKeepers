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

    private Pictures pictures;
    Player player;

    public ShopManager(Context context, Player player) {
        this.mcontext = context;
        this.player = player;
    }

    //index 当前层数
    public Shop createShop(int index) {
        Shop shop;
        shop = new Shop(player, mcontext);
        setShoplist(index, shop);
        return shop;
    }

    //通过代入floor 即，当前的层数的编号，来给传入的 shop生成对应的商品列表
    public void setShoplist(int floor, Shop shop) {
        double random = Math.random();
        double span = ((double) 1) / 9;
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
    }
}

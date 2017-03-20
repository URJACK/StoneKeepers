package ifox.sicnu.com.mag10.DataStructure;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ifox.sicnu.com.mag10.Data.Pictures;

/**
 * Created by 41988 on 2017/3/9.
 */
public class ShopManager {
    public Context mcontext;

    private Pictures pictures;
    private Shop shop;
    Player player;

    public ShopManager(Context context, Player player) {
        this.mcontext = context;
        this.player = player;
    }

    //index 当前层数
    public Shop createShop(int index) {
        if (index == 1) {
            shop = new Shop(player, mcontext);
            return shop;
        }
        if (index == 2) {
            shop = new Shop(player, mcontext);
            return shop;
        }
        if (index == 3) {
            shop = new Shop(player, mcontext);
            return shop;
        }
        return null;
    }


}

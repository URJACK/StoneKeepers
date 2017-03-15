package ifox.sicnu.com.mag10.DataStructure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Equipments;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.R;

/**
 * Created by 41988 on 2017/3/9.
 */
public class Shop {
    public Player player;
    public boolean isopen = false;  //商店的打开状态
    public static Bitmap shop_bg;
    public Context context;
    public Equipment[] P_equipments;
    public List<Equipment> S_equipments; //商店的货架
    Equipments equipments;
    Pictures pictures;

    public int S_equipment_index; //当前选中的商店装备的S_equipment的下标，默认选中第一个装备

    public Shop(Player player, Context context) {
        S_equipment_index = 0;
        this.player = player;
        this.context = context;
        if (shop_bg == null) {
            shop_bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_shop);
            shop_bg = Bitmap.createScaledBitmap(shop_bg, (int) (Const.SCREENHEIGHT * 0.6), (Const.SCREENWIDTH), true);
        }
        pictures = ((APP) context.getApplicationContext()).getPictures();
        equipments = Equipments.getEquipments();
        S_equipments = new ArrayList<>();
        getShopEquipments();
        getPlayerEquipments();
    }

    //向货架 放入货物
    private void getShopEquipments() {
        for (int i = 0; i < 8; i++) {
            Equipment equipment;
            equipment = equipments.getWeapon("sword_normal");
            equipment.money += 1;
            S_equipments.add(equipment);
        }
    }

    public void getPlayerEquipments() {
        if (isopen) {
            P_equipments = player.equipments; //得到当前角色的背包装备
        }
    }


    //点击关闭商店的按钮
    public void closeShop() {
        isopen = false;
    }

    //从商店买装备,传入的是S_equipments的下标;
    public void BuyEquipments(int i) {
        Equipment equipment = S_equipments.get(i);
        //购买的情况
        if (P_equipments.length >= 1 || equipment.money > player.money || player.r_power < equipment.power_require || equipment.agile_require > player.r_agile || equipment.wize_require > player.r_intelligence) {
            Toast.makeText(context, "购买失败", Toast.LENGTH_SHORT).show();
        } else {
            player.inbag(equipment);
            player.money -= equipment.money;
            Toast.makeText(context, "购买成功", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 将自己已经被选择的装备，售卖给使用者
     */
    public boolean sell() {
        Equipment e = this.S_equipments.get(this.S_equipment_index);
        if(player.buy(e)) {
            disinShop(e);
            return true;
        }
        else
            return false;
    }

    private void disinShop(Equipment e) {
        for (int i = 0; i < S_equipments.size(); i++) {
            if (S_equipments.get(i) == e) {
                S_equipments.set(i, null);
                return;
            }
        }
    }
}

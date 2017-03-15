package ifox.sicnu.com.mag10.Data.Weapon;

import android.graphics.Bitmap;

import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.Equipment;

/**
 * Created by Funchou Fu on 2017/3/5.
 */
public class Sword extends Equipment {
    public Sword(Pictures pictures, int atk,String name,String introduce,String drawblename) {
        this.atk = atk;
        this.bitmap = pictures.getBitmap(drawblename);
        this.introduce = introduce;
        this.equipmentName = name;
        this.type = Equipment.WEAPON;
    }
}

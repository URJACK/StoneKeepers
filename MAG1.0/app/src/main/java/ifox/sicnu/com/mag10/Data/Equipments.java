package ifox.sicnu.com.mag10.Data;

import ifox.sicnu.com.mag10.DataStructure.Equipment;
import ifox.sicnu.com.mag10.Data.Weapon.Sword;

/**
 * Created by Funchou Fu on 2017/2/28.
 */
public class Equipments {
    Pictures pictures;
    static Equipments equipments;

    public Equipments(Pictures pictures) {
        this.pictures = pictures;
        if (equipments == null)
            equipments = this;
    }

    public static Equipments getEquipments() {
        return equipments;
    }

    public Equipment getWeapon(String weaponName) {
        if (weaponName.equals("sword_normal")) {
            return new Sword(pictures, 4, "剑", "普普通通的一把剑", "sword_2");
        } else if (weaponName.equals("sword_bad"))
            return new Sword(pictures, 3, "渣渣剑", "比普通剑还坑爹的一把剑", "sword_2");
        return null;
    }
}

package ifox.sicnu.com.mag10.Tool;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ifox.sicnu.com.mag10.DataStructure.Affix;
import ifox.sicnu.com.mag10.DataStructure.Equipment;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.HeroActivity;

/**
 * Created by Funchou Fu on 2017/3/1.
 */
public class DtbsControler {
    SQLiteDatabase db;
    Context context;
    private String TAG = "StartActivity";

    public DtbsControler(SQLiteDatabase db, Context context) {
        this.db = db;
        this.context = context;
    }

    public void addHerointoDatabase(Hero hero) {
        db.execSQL("insert into heroes(" +
                "r_power,r_agile,r_intelligence," +
                "atk,hitrate,crit," +
                "armor,dodge,resistance," +
                "maxHp,maxMp,maxPp," +
                "introduction,heroName,face," +
                "weapon,co_weapon," +
                "helmet,wear_up,wear_down," +
                "hand,belt,shoe)values(?,?,?," +      // 三基本属性
                "?,?,?," +                  //攻击三属性
                "?,?,?," +                  //防御三属性
                "?,?,?," +                  //上限三属性
                "?,?,?," +                  //英雄介绍，英雄名字，英雄图片
                "?,?," +                    //主，副武器
                "?,?,?," +                  //头盔，上、下衣
                "?,?,?)", new String[]{String.valueOf(hero.r_power), String.valueOf(hero.r_agile), String.valueOf(hero.r_intelligence),
                String.valueOf(hero.atk), String.valueOf(hero.hitrate), String.valueOf(hero.crit),
                String.valueOf(hero.armor), String.valueOf(hero.dodge), String.valueOf(hero.resistance),
                String.valueOf(hero.maxHp), String.valueOf(hero.maxMp), String.valueOf(hero.maxPp),
                hero.introduction, hero.heroName, hero.faceId,
                hero.weapon != null ? hero.weapon.bitmapID : null, hero.co_weapon != null ? hero.co_weapon.bitmapID : null,
                hero.helmet != null ? hero.helmet.bitmapID : null, hero.wear_up != null ? hero.wear_up.bitmapID : null, hero.wear_down != null ? hero.wear_down.bitmapID : null,
                hero.hand != null ? hero.hand.bitmapID : null, hero.belt != null ? hero.belt.bitmapID : null, hero.shoe != null ? hero.shoe.bitmapID : null
        });                  //手套、腰带、鞋子
    }

    public Hero getHerofromDatabase(String key) {
        Cursor cursor = db.rawQuery("select * from heroes where face = ?", new String[]{key});
        cursor.moveToFirst();
        Hero hero = new Hero();
        hero.r_power = cursor.getInt(cursor.getColumnIndex("r_power"));
        hero.r_agile = cursor.getInt(cursor.getColumnIndex("r_agile"));
        hero.r_intelligence = cursor.getInt(cursor.getColumnIndex("r_intelligence"));
        hero.atk = cursor.getInt(cursor.getColumnIndex("atk"));
        hero.hitrate = cursor.getFloat(cursor.getColumnIndex("hitrate"));
        hero.crit = cursor.getFloat(cursor.getColumnIndex("crit"));
        hero.armor = cursor.getInt(cursor.getColumnIndex("armor"));
        hero.dodge = cursor.getFloat(cursor.getColumnIndex("dodge"));
        hero.resistance = cursor.getFloat(cursor.getColumnIndex("resistance"));
        hero.maxHp = cursor.getInt(cursor.getColumnIndex("maxHp"));
        hero.maxMp = cursor.getInt(cursor.getColumnIndex("maxMp"));
        hero.maxPp = cursor.getInt(cursor.getColumnIndex("maxPp"));
        hero.introduction = cursor.getString(cursor.getColumnIndex("introduction"));
        hero.heroName = cursor.getString(cursor.getColumnIndex("heroName"));
        hero.faceId = cursor.getString(cursor.getColumnIndex("face"));
        hero.face = getBitmapfromInnerFile(hero.faceId);
        hero.weapon = getEquipmentfromDatabase(cursor.getString(cursor.getColumnIndex("weapon"))); //获取武器的逻辑之后再写
        hero.co_weapon = getEquipmentfromDatabase(cursor.getString(cursor.getColumnIndex("co_weapon"))); //获取武器的逻辑之后再写
        hero.helmet = getEquipmentfromDatabase(cursor.getString(cursor.getColumnIndex("helmet"))); //获取武器的逻辑之后再写
        hero.hand = getEquipmentfromDatabase(cursor.getString(cursor.getColumnIndex("hand"))); //获取武器的逻辑之后再写
        hero.wear_up = getEquipmentfromDatabase(cursor.getString(cursor.getColumnIndex("wear_up"))); //获取武器的逻辑之后再写
        hero.wear_down = getEquipmentfromDatabase(cursor.getString(cursor.getColumnIndex("wear_down"))); //获取武器的逻辑之后再写
        hero.belt = getEquipmentfromDatabase(cursor.getString(cursor.getColumnIndex("belt"))); //获取武器的逻辑之后再写
        hero.shoe = getEquipmentfromDatabase(cursor.getString(cursor.getColumnIndex("shoe"))); //获取武器的逻辑之后再写
        return hero;
    }

    public void addEquipmentintoDatabase(Equipment equipment) {
        if (equipment == null) {
            Log.i(TAG, "addEquipmentintoDatabase: NULL!");
            return;
        }
        db.execSQL("insert into equipments(" +
                "power_require,agile_require,wize_require," +       //三项属性
                "atk,hitrate,crit," +                               //攻击属性
                "armor,dodge,resistance," +                         //防御属性
                "maxHp,maxMp,maxPp," +                              //上限属性
                "equipmentName,bitmap,introduce," +                 //装备名字，装备图片名字,装备介绍
                "money,type)values(" +                              //钱，类型
                "?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?,?)", new String[]{
                String.valueOf(equipment.power_require), String.valueOf(equipment.agile_require), String.valueOf(equipment.wize_require),
                String.valueOf(equipment.atk), String.valueOf(equipment.hitrate), String.valueOf(equipment.crit),
                String.valueOf(equipment.armor), String.valueOf(equipment.dodge), String.valueOf(equipment.resistance),
                String.valueOf(equipment.maxHp), String.valueOf(equipment.maxMp), String.valueOf(equipment.maxPp),
                equipment.equipmentName, equipment.bitmapID, equipment.introduce,
                String.valueOf(equipment.money), String.valueOf(equipment.type)
        });
    }

    //与getHerofromdatabase 一样 传入的key 为equipment的bitmapID
    public Equipment getEquipmentfromDatabase(String key) {
        if (key == null)
            return null;
        Cursor cursor = db.rawQuery("select * from equipments where bitmap = ?", new String[]{key});
        cursor.moveToFirst();
        Equipment equipment = new Equipment();
        equipment.power_require = cursor.getInt(cursor.getColumnIndex("power_require"));
        equipment.agile_require = cursor.getInt(cursor.getColumnIndex("agile_require"));
        equipment.wize_require = cursor.getInt(cursor.getColumnIndex("wize_require"));
        equipment.atk = cursor.getInt(cursor.getColumnIndex("atk"));
        equipment.hitrate = cursor.getFloat(cursor.getColumnIndex("hitrate"));
        equipment.crit = cursor.getFloat(cursor.getColumnIndex("crit"));
        equipment.armor = cursor.getInt(cursor.getColumnIndex("armor"));
        equipment.dodge = cursor.getFloat(cursor.getColumnIndex("dodge"));
        equipment.resistance = cursor.getFloat(cursor.getColumnIndex("resistance"));
        equipment.maxHp = cursor.getInt(cursor.getColumnIndex("maxHp"));
        equipment.maxMp = cursor.getInt(cursor.getColumnIndex("maxPp"));
        equipment.maxPp = cursor.getInt(cursor.getColumnIndex("maxPp"));
        equipment.equipmentName = cursor.getString(cursor.getColumnIndex("equipmentName"));
        equipment.bitmapID = cursor.getString(cursor.getColumnIndex("bitmap"));
        equipment.bitmap = getBitmapfromInnerFile(equipment.bitmapID);
        equipment.introduce = cursor.getString(cursor.getColumnIndex("introduce"));
        equipment.money = cursor.getInt(cursor.getColumnIndex("money"));
        equipment.type = cursor.getInt(cursor.getColumnIndex("type"));
        return equipment;
    }

    public void addAffixsintoDatabase(Affix affix) {

    }

    public void writeBitmapintoInnerFile(Bitmap bitmap, String bitmapname) {
        try {
            FileOutputStream fos = context.openFileOutput(bitmapname, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmapfromInnerFile(String bitmapname) {
        try {
            FileInputStream fis = context.openFileInput(bitmapname);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

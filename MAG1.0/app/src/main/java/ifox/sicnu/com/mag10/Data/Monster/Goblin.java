package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/7.
 * 哥布林
 */
public class Goblin extends Monster {
    private static Bitmap bitmap;

    public Goblin(Context context, int level) {
        this.atk = (int) (1 + Math.random() * 1 * level);
        this.hitrate = (float) 0.9;
        this.armor = (int) (4 + Math.random() * 2 * level);
        this.def = this.armor;
        this.setMaxhp((int) (30 + Math.random() * 3 * level));
        this.exp = 8 + 2 * level;
        this.money = 2;
        this.setMonsterType(Monster.NORMAL);
        setIntroduce("身如矮人瘦五分，体似半身高三寸，阔面凹鼻琥珀目，尖耳毒牙垂膝拳，钉锤撩动惊日月，座狼穿行泣鬼神，九州遍处皆兄弟，诨名唤做哥布林。");
        setName("Goblin");
        setMonsterType(Monster.NORMAL);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_gebulin);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

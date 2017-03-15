package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/7.
 * 哥布林的属性会随机波动
 */
public class Goblin extends Monster {
    private static Bitmap bitmap;

    public Goblin(Context context, int level) {
        this.atk = (int) (4 + (2 + Math.random() * level));
        this.hitrate = (float) 0.9;
        this.maxHp = (int) (10 + Math.random() * 3 * level);
        this.armor = (int) (4 + Math.random() * level);
        this.hp = this.maxHp;
        this.def = this.armor;
        this.exp = (int) (2 + 3 * Math.random() * level);
        setIntroduce("最基本的哥布林");
        setName("Goblin");
        setMonsterLevel(1);
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

package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/7.
 * 母老鼠下的小老鼠
 */
public class Mouse extends Monster {
    private static Bitmap bitmap;

    public Mouse(Context context, int level) {
        this.atk = (int) (1 + Math.random() * 1 * level);
        this.hitrate = (float) 0.9;
        this.armor = (int) (2 + Math.random() * 2 * level);
        this.setMaxhp((int) (30 + Math.random() * 3 * level));
        this.def = this.armor;
        this.exp = 0;
        this.money = 2;
        this.setMonsterType(Monster.NORMAL);
        setIntroduce("长的跟母老鼠贼像，除了不能繁殖和没有经验值，这个家伙还是很好对付的。");
        setName("Mouse");
        setMonsterType(Monster.NORMAL);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_xiashuidaolaoshu);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/19.
 */
public class Sheep extends Monster {
    public static Bitmap bitmap;

    public Sheep(Context context, int level) {
        this.atk = 1 + level/2;
        this.armor = level;
        this.def = this.armor;
        this.setMaxhp(level/2+1);
        this.dodge = (float) 0.1;
        this.exp = 5 ;
        this.money = 2;
        this.setMonsterType(Monster.NORMAL);
        setName("Sheep");
        setIntroduce("法师使用了变形术之后制造出的萌物，经验值还很高，它温顺，可爱，如果地下城里都是这种怪物该有多好。——" +
                "曾经有一个法师想要靠着变形术制造大量的绵羊来致富，不过他很快就发现，他有那个时间还不如去找份正经工作！因为从地下城里带一大堆绵羊出来实在是太没面子了！");
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_mianyang);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

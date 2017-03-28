package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/17.
 * 矮人矿工
 * 高闪避率
 */
public class Saboteur extends Monster {
    public static Bitmap bitmap;

    public Saboteur(Context context, int level) {
        this.atk = 5 + level;
        this.hitrate = (float) 0.9;
        this.crit = 0;
        this.armor = 1 + level;
        this.dodge = (float) 0.5;
        this.resistance = (float) 0.1;
        this.setMaxhp((int) (7 + Math.random() * level));
        this.def = this.armor;
        this.exp = 2 ;
        this.money = 2;
        this.setMonsterType(Monster.NORMAL);
        setIntroduce("非常没有特征的一个怪物，地精的血量加强翻版，出现时探索一个迷雾的特性有的时候会摸到奖(召唤出个加血怪什么的" +
                ")并且本来回避率就不高，畏光特性更让他倒霉不少。——矮人，固执，挖矿，锻造，嗜酒，淳朴。我想这就是我们对于矮" +
                "人的全部印象，恩，或许还有不分男女都长胡子这点。");
        setName("Saboteur");
        setMonsterType(Monster.NORMAL);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_airenkuanggong);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/7.
 * 木乃伊
 * 诅咒 buff
 */
public class Mummy extends Monster {
    private static Bitmap bitmap;

    public Mummy(Context context, int level) {
        this.atk = (int) (1 + Math.random() * 1 * level);
        this.hitrate = (float) 0.9;
        this.armor = (int) (12 + Math.random() * 2 * level);
        this.setMaxhp((int) (30 + Math.random() * 3 * level));
        this.def = this.armor;
        this.exp = 8 + 2 * level;
        this.money = 2;
        this.setMonsterType(Monster.NORMAL);
        setIntroduce("木乃伊，即“人工干尸”。此词译自英语mummy,源自波斯语mumiai，意为“沥青”。这家伙你如果把它弄死，它可是会好好的报复你一下的。");
        setName("Mummy");
        setMonsterType(Monster.NORMAL);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_munaiyi);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
        wearBuff(BuffFactory.createNoKeepBuff("curse"));
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

}

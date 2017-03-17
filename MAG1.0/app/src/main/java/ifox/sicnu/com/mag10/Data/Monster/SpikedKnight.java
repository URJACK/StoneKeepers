package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/17.
 */
public class SpikedKnight extends Monster {

    private static Bitmap bitmap;

    public SpikedKnight(Context context, int level) {
        this.atk = (int) (1 + Math.random() * 1 * level);
        this.hitrate = (float) 0.9;
        this.armor = (int) (12 + Math.random() * 2 * level);
        this.setMaxhp((int) (30 + Math.random() * 3 * level));
        this.def = this.armor;
        this.exp = 8 + 2 * level;
        this.money = 2;
        this.setMonsterType(Monster.NORMAL);
        setIntroduce("法师最讨厌的怪物之一，因为法师的魔杖攻击无法消减护甲，也就是说会一直被他的反射护甲给山伤害到，典型的打不死你也要恶心你一下的怪物类型，不过翻开格子造成伤害加上那个消" +
                "减敌人护甲这两个属性可以狠狠的削弱他们。没有护甲的尖刺骑士连鸡都不如！——“哦，又是那群家伙，就不能消停会么。你们身上的刺烦死人了！”“我们" +
                "只是想要一个温暖的拥抱！”");
        setName("SpikedKnight");
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
package ifox.sicnu.com.mag10.Data.Monster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/19.
 * 巨魔
 * 每回合,hp+1
 */
public class Ogres extends Monster{
    private static Bitmap bitmap;

    public Ogres(Context context,int level){
        this.atk = 3 + level/2;
        this.hitrate = (float) 0.9;
        this.crit = 0;
        this.armor = 3 + 3 * level;
        this.dodge = (float) 0.1;
        this.resistance = (float) 0.1;
        this.setMaxhp(8 + 3 * level);
        this.def = this.armor;
        this.exp = 20 + 7 * level;
        this.money = 2;
        this.setMonsterType(Monster.NORMAL);
        this.setIntroduce("充分的发挥了巨魔的传统，冒险家每探索一个区域，他就能恢复身上的一些伤势，但是这又有什么用呢？为何要打残了他然后又放着他不管去探索区域呢？" +
                "又不是太强。见敌必杀！——嘿！伙计！巨魔也是有分别的！别拿我和那些只会穿着裤衩到处乱跑见人就喊着“塔斯丁够！”" +
                "然后冲上去的家伙相提并论！什么？你说这群家伙不会喊“塔斯丁够！”那是巨魔嘛？By：某个不愿意透露姓名的岛上的巨魔");
        this.setName("Ogres");
        if (bitmap == null){
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster_jumo);
            bitmap = Bitmap.createScaledBitmap(bitmap, Const.CELL_WIDTH, Const.CELL_HEIGHT, true);
        }
        wearBuff(BuffFactory.createNoKeepBuff("alwaysstrengthen"));
    }
    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

package ifox.sicnu.com.mag10.DataStructure.Buff;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/22.
 * 有些怪物的攻击特效，必须依赖buff 来说明，但是它的实际效果并非是依靠该buff来执行的。
 */
public class MonsterSkillFactory {
    public static Bitmap bm_doubleattack;
    public static Bitmap bm_armor;
    public static Bitmap bm_escape;
    public static Bitmap bm_overmana;
    public static Bitmap bm_subarmor;

    static {

        bm_armor = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.nonebuff_armor);
        bm_armor = Bitmap.createScaledBitmap(bm_armor, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        bm_escape = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.nonebuff_escape);
        bm_escape = Bitmap.createScaledBitmap(bm_escape, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        bm_overmana = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.nonebuff_overmana);
        bm_overmana = Bitmap.createScaledBitmap(bm_overmana, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        bm_doubleattack = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.nonebuff_bishou);
        bm_doubleattack = Bitmap.createScaledBitmap(bm_doubleattack, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);
        bm_subarmor = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.nonebuff_subarmor);
        bm_subarmor = Bitmap.createScaledBitmap(bm_subarmor, (int) (Const.SCREENHEIGHT * 0.06), (int) (Const.SCREENHEIGHT * 0.06), true);

    }

    public static RoundEndBuff create(String name) {
        if (name.equals("doubleattack"))
            return createDoubleattack();
        else if (name.equals("escape"))
            return createEscape();
        else if (name.equals("overmana"))
            return createOverMana();
        else if (name.equals("armor"))
            return createArmor();
        else if (name.equals("subarmor"))
            return createSubArmor();
        else if (name.equals("subarmor_double"))
            return createSubArmorDouble();
        return null;
    }

    private static RoundEndBuff createSubArmorDouble() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {

            }
        };
        roundEndBuff.time = 1;
        roundEndBuff.id = "subarmor";
        roundEndBuff.name = "双击破甲";
        roundEndBuff.introduce = "这个怪物的攻击会攻击目标两次且额外的削弱它的护甲";
        roundEndBuff.bitmap = bm_subarmor;
        return roundEndBuff;
    }

    private static RoundEndBuff createSubArmor() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {

            }
        };
        roundEndBuff.time = 1;
        roundEndBuff.id = "subarmor";
        roundEndBuff.name = "破甲";
        roundEndBuff.introduce = "这个怪物的攻击会额外的削弱它攻击目标的护甲";
        roundEndBuff.bitmap = bm_subarmor;
        return roundEndBuff;
    }

    private static RoundEndBuff createArmor() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {

            }
        };
        roundEndBuff.time = 1;
        roundEndBuff.id = "armor";
        roundEndBuff.name = "荆棘护甲";
        roundEndBuff.introduce = "这个怪物承受伤害后，会对玩家造成根据自己护甲值的伤害";
        roundEndBuff.bitmap = bm_armor;
        return roundEndBuff;
    }

    private static RoundEndBuff createOverMana() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {

            }
        };
        roundEndBuff.time = 1;
        roundEndBuff.id = "overmana";
        roundEndBuff.name = "法力过载";
        roundEndBuff.introduce = "这个怪物能够在攻击玩家后，耗掉玩家的法力值。";
        roundEndBuff.bitmap = bm_overmana;
        return roundEndBuff;
    }

    private static RoundEndBuff createEscape() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {

            }
        };
        roundEndBuff.time = 1;
        roundEndBuff.id = "escape";
        roundEndBuff.name = "胆小";
        roundEndBuff.introduce = "这个胆小的家伙会在受到伤害后，立马跑回迷雾中去。";
        roundEndBuff.bitmap = bm_escape;
        return roundEndBuff;
    }

    private static RoundEndBuff createDoubleattack() {
        RoundEndBuff roundEndBuff = new RoundEndBuff() {
            @Override
            public void doWork(int x, int y, BattleManager bm) {

            }
        };
        roundEndBuff.time = 1;
        roundEndBuff.id = "doubleattack";
        roundEndBuff.name = "刺客之道";
        roundEndBuff.introduce = "刺客之道能让该怪物拥有攻击两次的能力，并且无视你的护甲。";
        roundEndBuff.bitmap = bm_doubleattack;
        return roundEndBuff;
    }
}

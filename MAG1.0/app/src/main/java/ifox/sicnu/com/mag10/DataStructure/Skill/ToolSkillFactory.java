package ifox.sicnu.com.mag10.DataStructure.Skill;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.R;

/**
 * Created by Funchou Fu on 2017/3/22.
 */
public class ToolSkillFactory {
    public static Bitmap bm_hp;
    public static Bitmap bm_mp;
    public static Bitmap bm_damage;

    static {
        bm_hp = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.tool_hp);
        bm_hp = Bitmap.createScaledBitmap(bm_hp, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);
        bm_mp = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.tool_mp);
        bm_mp = Bitmap.createScaledBitmap(bm_mp, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);
        bm_damage = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.tool_damage);
        bm_damage = Bitmap.createScaledBitmap(bm_damage, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);
    }

    public static Skill createTool(String name) {
        if (name.equals("hp")) {
            return createHpSkill();
        } else if (name.equals("mp")) {
            return createMpSkill();
        } else if (name.equals("damage")) {
            return createDamageSkill();
        }
        return null;
    }

    public static Skill createTool() {
        double a = Math.random();
        if (a < 0.33)
            return createHpSkill();
        else if (a < 0.66)
            return createMpSkill();
        else
            return createDamageSkill();
    }

    private static Skill createDamageSkill() {
        Skill skill = new SingleTargetSkill(Const.bm.player, 0, 0, Skill.PP, 10, null) {
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if (super.doSkill(x, y, bm)) {
                    bm.cells.get(x + y * 8).monster.sufferDamage(5 + Const.bm.floor * 2, true);
                    bm.MonsterClear();
                    return true;
                } else
                    return false;
            }
        };
        skill.name = "飞刀";
        skill.introduce = String.format("这把飞刀能够对单体目标造成%d点真实伤害", 5 + Const.bm.floor * 2);
        skill.bitmap = bm_damage;
        return skill;
    }

    private static Skill createMpSkill() {
        Skill skill = new NoTargetSkill(Const.bm.player, 0, 0, Skill.PP, 10, null) {
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if (getValue(costType) < costValue)
                    return false;       //因为消耗不够，而释放失败
                else {
                    subValue(costType, costValue);      //减少自己需要被减少的数值
                    bm.player.mp += Const.bm.floor / 2 + 2;
                    return true;
                }
            }
        };
        skill.name = "蓝瓶";
        skill.introduce = String.format("吃掉血瓶回复%d点法力值", Const.bm.floor / 2 + 2);
        skill.bitmap = bm_mp;
        return skill;
    }

    private static Skill createHpSkill() {
        Skill skill = new NoTargetSkill(Const.bm.player, 0, 0, Skill.PP, 10, null) {
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if (getValue(costType) < costValue)
                    return false;       //因为消耗不够，而释放失败
                else {
                    subValue(costType, costValue);      //减少自己需要被减少的数值
                    bm.player.hp += Const.bm.floor * 3 / 2 + 5;
                    return true;
                }
            }
        };
        skill.name = "血瓶";
        skill.bitmap = bm_hp;
        skill.introduce = String.format("吃掉血瓶回复%d点生命值", Const.bm.floor * 3 / 2 + 5);
        return skill;
    }

}

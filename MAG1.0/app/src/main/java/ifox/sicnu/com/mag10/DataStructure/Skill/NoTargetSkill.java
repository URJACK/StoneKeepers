package ifox.sicnu.com.mag10.DataStructure.Skill;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Buff.Buff;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/3/16.
 * 这是一个无目标的使用技能
 */
public class NoTargetSkill extends Skill {

    public int type;
    public float rate;
    public int costValue;
    public int costType;
    public Buff buff;


    public NoTargetSkill(Unit user, int type, float rate, int costType, int costValue, Buff buff) {
        super(user);
        this.type = type;
        this.rate = rate;
        this.costType = costType;
        this.costValue = costValue;
        this.buff = buff;
    }

    @Override
    public boolean doSkill(int x, int y, BattleManager bm) {
        if (this.buff != null)
            user.wearBuff(BuffFactory.createBuff(this.buff.id));
        if (getValue(costType) < costValue)
            return false;       //因为消耗不够，而释放失败
        else {
            subValue(costType, costValue);      //减少自己需要被减少的数值
        }
        if (user.pp < cost)
            return false;
        else
            user.pp -= cost;
        bm.MonsterClear();
        return true;
    }
}
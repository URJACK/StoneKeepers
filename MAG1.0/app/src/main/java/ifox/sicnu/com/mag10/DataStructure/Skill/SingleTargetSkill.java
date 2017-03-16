package ifox.sicnu.com.mag10.DataStructure.Skill;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Buff.Buff;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/3/9.
 * 通过type 来决定是获得player的何种属性，rate 是这个属性会实际作用的比率
 */
public class SingleTargetSkill extends Skill {
    private int type;
    private float rate;
    private int costType;
    private int costValue;
    private Buff buff;                  //此处的buff如果需要被创建，不可是keepBuff

    /**
     * @param type 表明了自己是使用的Player 的哪种属性
     */
    public SingleTargetSkill(Unit user, int type, float rate, int costType, int costValue, Buff buff) {
        super(user);
        this.type = type;
        this.rate = rate;
        this.costType = costType;
        this.costValue = costValue;
        this.buff = buff;
    }

    public SingleTargetSkill(Unit user, int type, float rate, int costType, int costValue, Buff buff, int cost) {
        super(user);
        this.type = type;
        this.rate = rate;
        this.costType = costType;
        this.costValue = costValue;
        this.cost = cost;
        this.buff = buff;
    }


    @Override
    public boolean doSkill(int x, int y, BattleManager bm) {
        Monster target = bm.cells.get(x + y * 8).monster;
        if (target == null)
            return false;
        if (this.buff != null)
            target.wearBuff(BuffFactory.createRoundEndBuff(this.buff.id));
        if (getValue(costType) < costValue)
            return false;       //因为消耗不够，而释放失败
        else {
            subValue(costType, costValue);
        }
        if (user.pp < cost)
            return false;
        else
            user.pp -= cost;
        int damage = (int) (getValue(type) * rate);
        target.sufferDamage(damage, true);
        bm.MonsterClear();
        return true;
    }


}

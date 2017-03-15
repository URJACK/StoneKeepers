package ifox.sicnu.com.mag10.DataStructure.Skill;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Cell;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by Funchou Fu on 2017/3/9.
 * 通过type 来决定是获得player的何种属性，rate 是这个属性会实际作用的比率
 */
public class SingleTargetSkill extends Skill {
    private int type;
    private float rate;
    private int cost;
    private int costType;
    private int costValue;

    /**
     * @param type 表明了自己是使用的Player 的哪种属性
     */
    public SingleTargetSkill(Unit unit, int type, float rate, int costType, int costValue) {
        super(unit);
        this.type = type;
        this.rate = rate;
        this.costType = costType;
        this.costValue = costValue;
    }

    public SingleTargetSkill(Unit unit, int type, float rate, int costType, int costValue,int cost) {
        super(unit);
        this.type = type;
        this.rate = rate;
        this.costType = costType;
        this.costValue = costValue;
        this.cost = cost;
    }


    @Override
    public boolean doSkill(int x, int y, BattleManager bm) {
        Monster target = bm.cells.get(x + y * 8).monster;
        if (target == null)
            return false;
        if (getValue(costType) < costValue)
            return false;       //因为消耗不够，而释放失败
        if(unit.pp<cost)
            return false;
        else {
            subValue(costType, costValue);
        }
        int damage = (int) (getValue(type) * rate);
        target.sufferDamage(damage, true);
        bm.MonsterClear();
        return true;
    }


}

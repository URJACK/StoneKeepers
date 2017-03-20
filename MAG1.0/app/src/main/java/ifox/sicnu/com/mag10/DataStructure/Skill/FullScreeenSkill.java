package ifox.sicnu.com.mag10.DataStructure.Skill;

import android.util.Log;

import java.util.ArrayList;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Buff.Buff;
import ifox.sicnu.com.mag10.DataStructure.Cell;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Unit;

/**
 * Created by 41988 on 2017/3/20.
 */
public class FullScreeenSkill extends Skill {
    private static final String TAG = "FullScreeenSkill";
    private int type;
    private float rate;
    private int costType;
    private int costValue;
    private Buff buff;                  //此处的buff如果需要被创建，不可是keepBuff
    private int music_id;


    public FullScreeenSkill(Unit user,int type,float rate, int costType, int costValue, Buff buff) {
        super(user);
        this.type = type;
        this.rate = rate;
        this.costType = costType;
        this.costValue = costValue;
        this.buff = buff;
    }
    public FullScreeenSkill(Unit user,int type,float rate, int costType, int costValue, int cost ,Buff buff) {
        super(user);
        this.type = type;
        this.rate = rate;
        this.costType = costType;
        this.costValue = costValue;
        this.buff = buff;
        this.cost = cost;
    }

    @Override
    public boolean doSkill(int x, int y, BattleManager bm) {
        if (getValue(costType) < costValue)
            return false;       //因为消耗不够，而释放失败
        else {
            subValue(costType, costValue);

        }


        if (user.pp < cost)
            return false;
        else {
            user.pp -= cost;
            return true;
        }
    }

    public static ArrayList<Monster> getFullMonster(BattleManager bm){
        ArrayList<Monster>  monsters = new ArrayList<>();
        for(int i=0;i<bm.cells.size();i++){
            if(bm.cells.get(i).monster!=null&&bm.cells.get(i).status == Cell.DISCORVERED){
                monsters.add(bm.cells.get(i).monster);
            }
        }
        return monsters;
    }
}

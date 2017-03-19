package ifox.sicnu.com.mag10.DataStructure.Skill;

import android.media.SoundPool;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Buff.Buff;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Unit;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.Arcmissle_SpecialEffects;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.SpecialEffects;
import ifox.sicnu.com.mag10.R;

/**
 * Created by 41988 on 2017/3/19.
 */
public class DoubleTargetSkill extends Skill{
    private int type;
    private float rate;
    private int costType;
    private int costValue;
    private Buff buff;
    private int music_id;
    public DoubleTargetSkill(Unit user,int type, float rate, int costType, int costValue, Buff buff) {
        super(user);
        this.type = type;
        this.rate = rate;
        this.costType = costType;
        this.costValue = costValue;
        this.buff = buff;
    }

    //多重范围伤害技能，对目标（x，y）的上下左右进行技能伤害
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
            DamageForAround(x,y,bm);
            bm.MonsterClear();
            return true;
        }
    }

    private void DamageForAround(int x,int y,BattleManager bm){
        int damage = (int) (getValue(type) * rate);
        Monster target = bm.cells.get(x + y * 8).monster;
        //点击 点
        if(target!=null){
            target.sufferDamage(damage, true);
        }
        if (this.buff != null)
            target.wearBuff(BuffFactory.createNoKeepBuff(this.buff.id));
        int index = x+y*8;
        //top
        if(index>=8){
            Monster monster_top = bm.cells.get(index-8).monster;
            if(monster_top!=null)
                monster_top.sufferDamage(damage,true);
        }
        //bottom
        if(index<=47){
            Monster monster_bottom = bm.cells.get(index+8).monster;
            if(monster_bottom!=null)
                monster_bottom.sufferDamage(damage,true);
        }
        //right
        if(index>=0&&index<55){
            Monster monster_right = bm.cells.get(index+1).monster;
            if(monster_right!=null)
                monster_right.sufferDamage(damage,true);
        }
        //left
        if(index>0&&index<=55){
            Monster monster_left = bm.cells.get(index-1).monster;
            if(monster_left!=null)
                monster_left.sufferDamage(damage,true);
        }

    }
}

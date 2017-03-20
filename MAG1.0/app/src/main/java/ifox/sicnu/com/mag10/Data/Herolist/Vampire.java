package ifox.sicnu.com.mag10.Data.Herolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Skill.NoTargetSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.Skill;
import ifox.sicnu.com.mag10.R;
import ifox.sicnu.com.mag10.Tool.UpLevelFilter;

/**
 * Created by Funchou Fu on 2017/3/5.
 */
public class Vampire extends Hero{
    public Vampire(Pictures pictures){
        this.atk = 10;
        this.hitrate = (float) 0.9;
        this.crit = (float) 0.1;

        this.armor = 10;
        this.dodge = 0;
        this.resistance = 0;

        this.maxHp = 40;
        this.maxMp = 20;
        this.maxPp = 40;

        this.r_power = 6;
        this.r_agile = 6;
        this.r_intelligence = 2;


        this.face = pictures.getBitmap("hero_2");
        this.heroName = "弗拉基米尔";
        this.introduction = "一名强大的吸血鬼族的战士";

        this.upLevelFilter = new UpLevelFilter() {
            @Override
            public void uplevel(Player player) {
                player.r_power += 3;
                player.r_agile += 1;
                player.r_intelligence += 1;

                player.maxMp += 9;
                player.atk += 1;
                player.armor += 2;
            }
        };
        Skill xianxue = new NoTargetSkill(this,Skill.MAXHP,2,Skill.HP,10,null){
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if(super.doSkill(x, y, bm)){
                    if(bm.player.hp>10) {
                        bm.player.hp -= 10;
                        bm.player.atk += 15;
                        return true;
                    }
                }else
                    return false;
            return false;}
        };
        xianxue.name = "献血神祭";
        xianxue.introduce = "把自己的鲜血给神灵，换取攻击力";
        xianxue.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_xianxue);
        xianxue.bitmap = Bitmap.createScaledBitmap(xianxue.bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);

        skills[0] = xianxue;
    }
}

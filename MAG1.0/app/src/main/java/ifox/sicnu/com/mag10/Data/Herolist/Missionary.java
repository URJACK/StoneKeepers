package ifox.sicnu.com.mag10.Data.Herolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Equipments;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Cell;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Skill.FullScreeenSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.NoTargetSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.Skill;
import ifox.sicnu.com.mag10.R;
import ifox.sicnu.com.mag10.Tool.UpLevelFilter;

/**
 * Created by Funchou Fu on 2017/3/5.
 */
public class Missionary extends Hero {
    public Missionary(Pictures pictures,Equipments equipments){

        this.atk = 7;                //攻击力
        this.hitrate = (float) 0.9;  //初始命中
        this.crit = (float) 0.1;     //初始爆击

        this.armor = 10;               //防御
        this.dodge = 0;              //初始闪避
        this.resistance = 0;         //初始抗性

        this.maxHp = 28;             //最大生命值
        this.maxMp = 40;             //最大法力值
        this.maxPp = 60;             //最大魔法值

        this.r_power = 3;            //初始力量
        this.r_agile = 3;            //初始敏捷
        this.r_intelligence = 8;     //初始智力

        this.face = pictures.getBitmap("hero_3");        //从文件中取得图片
        this.heroName = "马可波罗";                      //英雄名字
        this.introduction = "一个从西欧过来的传教士.";   //英雄简介
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

        Skill miwu = new FullScreeenSkill(this,Skill.MAXHP,50,Skill.HP,10,null){
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if(super.doSkill(x, y, bm)){
                    ArrayList<Monster> monsters = FullScreeenSkill.getFullMonster_miwu(bm);
                    for(int i=0;i<monsters.size();i++){
                        monsters.get(i).hp -= bm.player.maxHp*0.5;
                        bm.MonsterClear();
                    }
                    return true;
                }
                return false;

            }
        };
        miwu.name = "迷雾猎杀";
        miwu.introduce = "失去自己的生命，对迷雾中的怪物进行猎杀";
        miwu.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_miwu);
        miwu.bitmap = Bitmap.createScaledBitmap(miwu.bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);


        Skill miwu_hudun = new NoTargetSkill(this,Skill.MAXMP,10,Skill.MP,5,null){
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if(super.doSkill(x, y, bm)){
                    ArrayList<Cell> cells = new ArrayList<>();
                    for(int i =0;i<bm.cells.size();i++){
                        if(bm.cells.get(i).status==Cell.DISCORVERED){
                            cells.add(bm.cells.get(i));
                        }
                    }
                    bm.player.def += cells.size();
                    return true;
                }else
                    return false;
            }
        };
        miwu_hudun.name = "迷雾护盾";
        miwu_hudun.introduce = "让自己的护甲增加当前翻开迷雾的个数";
        miwu_hudun.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_miwu_hudun);
        miwu_hudun.bitmap = Bitmap.createScaledBitmap(miwu_hudun.bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);

        skills[0] = miwu;
        skills[1] = miwu_hudun;
    }
}

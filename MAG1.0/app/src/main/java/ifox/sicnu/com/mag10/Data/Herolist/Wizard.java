package ifox.sicnu.com.mag10.Data.Herolist;

import ifox.sicnu.com.mag10.Data.Equipments;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.DataStructure.Skill.SingleTargetSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.Skill;

/**
 * Created by Funchou Fu on 2017/3/5.
 * 在HeroList 里添加了英雄，别忘了在Heroes里进行判定处理
 */
public class Wizard extends Hero {
    public Wizard(Pictures pictures, Equipments equipments) {
        this.atk = 7;
        this.hitrate = (float) 0.9;
        this.crit = (float) 0.1;

        this.dodge = 0;
        this.resistance = 0;
        this.armor = 10;

        this.maxHp = 28;
        this.maxMp = 40;
        this.maxPp = 60;

        this.r_power = 3;
        this.r_agile = 3;
        this.r_intelligence = 8;

        this.face = pictures.getBitmap("hero_1");
        this.heroName = "斯威法";
        this.introduction = "来自奥兹威尔山谷的强大奥术法师";

        Skill arcmissle = new SingleTargetSkill(this, Skill.MAXMP, (float) 0.2, Skill.MP, 5);
        arcmissle.name = "奥术飞弹";
        arcmissle.introduce = "强大的奥术飞弹，能够造成自己最大法力值20% 的高额伤害";
        arcmissle.bitmap = pictures.getBitmap("skill_arcmissle");
        this.skills[0] = arcmissle;
        this.skills[1] = arcmissle;
        this.skills[2] = arcmissle;

        this.weapon = equipments.getWeapon("剑");
    }
}

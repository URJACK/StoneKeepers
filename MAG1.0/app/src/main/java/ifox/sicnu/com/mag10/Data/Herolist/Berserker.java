package ifox.sicnu.com.mag10.Data.Herolist;

import ifox.sicnu.com.mag10.Data.Equipments;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.Hero;

/**
 * Created by Funchou Fu on 2017/3/5.
 */
public class Berserker extends Hero{
    public Berserker(Pictures pictures,Equipments equipments){
        this.atk = 14;
        this.hitrate = (float) 0.9;
        this.crit = (float) 0.1;

        this.armor = 5;
        this.dodge = 0;
        this.resistance = 0;

        this.maxHp = 40;
        this.maxMp = 5;
        this.maxPp = 30;

        this.r_power = 10;
        this.r_agile = 5;
        this.r_intelligence = 1;


        this.face = pictures.getBitmap("hero_5");
        this.heroName = "赫拉克勒斯";
        this.introduction = "来自古希腊传说中的战士";
    }
}

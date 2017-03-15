package ifox.sicnu.com.mag10.Data.Herolist;

import ifox.sicnu.com.mag10.Data.Equipments;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.Hero;

/**
 * Created by Funchou Fu on 2017/3/5.
 */
public class Vampire extends Hero{
    public Vampire(Pictures pictures,Equipments equipments){
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
    }
}

package ifox.sicnu.com.mag10.Data.Herolist;

import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.Hero;

/**
 * Created by Funchou Fu on 2017/3/5.
 */
public class Missionary extends Hero {
    public Missionary(Pictures pictures){

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
    }
}

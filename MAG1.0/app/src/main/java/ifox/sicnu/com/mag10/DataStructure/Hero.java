package ifox.sicnu.com.mag10.DataStructure;

import android.graphics.Bitmap;

import ifox.sicnu.com.mag10.DataStructure.Skill.Skill;

/**
 * Created by Funchou Fu on 2017/2/24.
 * Hero 类是给Player 类赋予基础属性的类
 */
public class Hero extends Unit{
    public int r_power;             //力量
    public int r_agile;             //敏捷
    public int r_intelligence;      //智力

    public String introduction;     //英雄的介绍
    public String heroName;         //英雄的名字
    public Bitmap face;     //英雄的图片
    public String faceId;       //英雄图片的索引 R.drawble. XXX  XXX 即为faceId

    public Equipment weapon;
    public Equipment co_weapon;
    public Equipment helmet;
    public Equipment wear_up;
    public Equipment wear_down;
    public Equipment hand;
    public Equipment belt;
    public Equipment shoe;

    //这里英雄的属性，均为穿戴装备后的最终属性

    public Skill[] skills = new Skill[3];
}

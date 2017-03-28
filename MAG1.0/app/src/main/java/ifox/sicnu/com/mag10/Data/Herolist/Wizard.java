package ifox.sicnu.com.mag10.Data.Herolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.SoundPool;
import android.util.Log;
import android.widget.Toast;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Cell;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Skill.DoubleTargetSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.SingleTargetSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.Skill;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.Arcmissle_SpecialEffects;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.Doublefileball_SpecialEffects;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.SpecialEffects;
import ifox.sicnu.com.mag10.R;
import ifox.sicnu.com.mag10.Tool.HeroFilter;

/**
 * Created by Funchou Fu on 2017/3/5.
 * 在HeroList 里添加了英雄，别忘了在Heroes里进行判定处理
 */
public class Wizard extends Hero {
    public Wizard(Pictures pictures) {
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
        this.heroName = "法师";
        this.introduction = "一名强大法师，拥有单体法术和群体法术。使用技能能够回复自身的生命值。";

        this.heroFilter = new HeroFilter() {
            @Override
            public void uplevel(Player player) {
                player.r_power += 1;
                player.r_agile += 1;
                player.r_intelligence += 3;

                player.maxMp += 9;
                player.atk += 1;
                player.armor += 2;
            }
            @Override
            public void doSkill(BattleManager bm) {
                bm.player.hp += bm.floor + 5;
                if (bm.player.hp > bm.player.maxHp) {
                    bm.player.hp = bm.player.maxHp;
                }
                Toast.makeText(Const.mContext_Game, "使用技能，回复了自己的生命值", Toast.LENGTH_SHORT).show();
            }
        };

        Skill arcmissle = new SingleTargetSkill(this, Skill.MAXMP, (float) 0.2, Skill.MP, 5, BuffFactory.createNoKeepBuff("poison")) {
            int music_id;


            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if (super.doSkill(x, y, bm)) {
                    SpecialEffects specialEffects = new Arcmissle_SpecialEffects(x * Const.CELL_WIDTH, y * Const.CELL_HEIGHT);
                    bm.putEffects(specialEffects);
                    user.pp -= cost;
                    music_id = Const.soundPool_Game.load(Const.mContext_Game, R.raw.gameview_dianliu, 1);
                    Const.soundPool_Game.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                        @Override
                        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                            Const.soundPool_Game.play(music_id, 1, 1, 1, 0, 1);
                        }
                    });
                    return true;
                } else
                    return false;
            }
        };
        arcmissle.name = "毒电";
        arcmissle.introduce = "蕴含了毒药的闪电，能够造成自己最大法力值20% 的高额伤害并附带中毒效果";

        Bitmap bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_arcmissle);
        bitmap = Bitmap.createScaledBitmap(bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);

        arcmissle.bitmap = bitmap;

//        Skill strenthenpower = new NoTargetSkill(this, Skill.ARMMOR, 0, Skill.MP, 8, BuffFactory.createKeepBuff("atkIncrease"));
//        strenthenpower.name = "力量强化";
//        strenthenpower.introduce = "强化自己的肉体，从而能够增加自己的 攻击力";
//
//        bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_strenthenpower);
//        bitmap = Bitmap.createScaledBitmap(bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);
//        strenthenpower.bitmap = bitmap;

        Skill double_fireball = new DoubleTargetSkill(this, Skill.MAXMP, (float) 0.2, Skill.MP, 5, null) {
            int music_id;
            int music_id2;
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if (super.doSkill(x, y, bm)) {
                   int index = x+y*8;
                    Log.i("111", "doSkill: " + index);
                    if(index>=8&&bm.cells.get(index-8).status== Cell.DISCORVERED){
                        SpecialEffects specialEffects = new Doublefileball_SpecialEffects(x * Const.CELL_WIDTH, (y-1) * Const.CELL_HEIGHT);
                        bm.putEffects(specialEffects);

                    }

                    if(index<=47&&bm.cells.get(index+8).status==Cell.DISCORVERED){
                        SpecialEffects specialEffects = new Doublefileball_SpecialEffects(x * Const.CELL_WIDTH, (y+1) * Const.CELL_HEIGHT);
                        bm.putEffects(specialEffects);

                    }

                    if(index<=55&&bm.cells.get(index+1).status==Cell.DISCORVERED){
                        SpecialEffects specialEffects = new Doublefileball_SpecialEffects((x+1) * Const.CELL_WIDTH, (y) * Const.CELL_HEIGHT);
                        bm.putEffects(specialEffects);
                    }

                    if(index>0&&bm.cells.get(index-1).status==Cell.DISCORVERED){
                        SpecialEffects specialEffects = new Doublefileball_SpecialEffects((x-1) * Const.CELL_WIDTH, (y) * Const.CELL_HEIGHT);
                        bm.putEffects(specialEffects);
                        music_id = Const.soundPool_Game.load(Const.mContext_Game, R.raw.huoqiu_music, 1);

                    }
                    Const.soundPool_Game.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                        @Override
                        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                            Const.soundPool_Game.play(music_id, 1, 1, 1, 0, 1);
                        }
                    });
                    SpecialEffects specialEffects = new Doublefileball_SpecialEffects(x * Const.CELL_WIDTH, y * Const.CELL_HEIGHT);
                    bm.putEffects(specialEffects);



                    music_id = Const.soundPool_Game.load(Const.mContext_Game, R.raw.huoqiu_music, 1);
                    Const.soundPool_Game.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                        @Override
                        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                            Const.soundPool_Game.play(music_id, 1, 1, 1, 0, 1);
                        }
                    });
                    return true;
                } else
                    return false;
            }
        };
        double_fireball.name = "多重火球";
        double_fireball.introduce = "发射五个火球，对每个怪物造成最大法力值的 20%的伤害";
        double_fireball.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(),R.drawable.skill_doublefireball);
        double_fireball.bitmap = Bitmap.createScaledBitmap(double_fireball.bitmap,Const.SKILL_WIDTH,Const.SKILL_HEIGHT,true);

        this.skills[0] = arcmissle;
        this.skills[1] = double_fireball;

    }
}

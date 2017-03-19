package ifox.sicnu.com.mag10.Data.Herolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.SoundPool;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Equipments;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Skill.NoTargetSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.SingleTargetSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.Skill;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.Arcmissle_SpecialEffects;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.SpecialEffects;
import ifox.sicnu.com.mag10.R;
import ifox.sicnu.com.mag10.Tool.UpLevelFilter;

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

        Skill arcmissle = new SingleTargetSkill(this, Skill.MAXMP, (float) 0.2, Skill.MP, 5, BuffFactory.createNoKeepBuff("poison")){
            int music_id;
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if(super.doSkill(x, y, bm)) {
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
                }
                else
                    return false;
            }
        };
        arcmissle.name = "奥术飞弹";
        arcmissle.introduce = "强大的奥术飞弹，能够造成自己最大法力值20% 的高额伤害";

        Bitmap bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_arcmissle);
        bitmap = Bitmap.createScaledBitmap(bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);

        arcmissle.bitmap = bitmap;

        Skill strenthenpower = new NoTargetSkill(this, Skill.ARMMOR, 0, Skill.MP, 8, BuffFactory.createKeepBuff("atkIncrease"));
        strenthenpower.name = "力量强化";
        strenthenpower.introduce = "强化自己的肉体，从而能够增加自己的 攻击力";

        bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_strenthenpower);
        bitmap = Bitmap.createScaledBitmap(bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);
        strenthenpower.bitmap = bitmap;


        this.skills[0] = arcmissle;
        this.skills[1] = strenthenpower;

        this.weapon = equipments.getWeapon("剑");
    }
}

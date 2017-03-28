package ifox.sicnu.com.mag10.Data.Herolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.SoundPool;
import android.widget.Toast;

import java.util.ArrayList;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Skill.FullScreeenSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.NoTargetSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.Skill;
import ifox.sicnu.com.mag10.DataStructure.Skill.ToolSkillFactory;
import ifox.sicnu.com.mag10.R;
import ifox.sicnu.com.mag10.Tool.HeroFilter;

/**
 * Created by Funchou Fu on 2017/3/5.
 */
public class Missionary extends Hero {
    public Missionary(Pictures pictures) {

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
        this.heroName = "商人";                      //英雄名字
        this.introduction = "精通经商的商人.使用技能会大概率获得道具，使用道具，会返还30%的魂力消耗";   //英雄简介
        this.heroFilter = new HeroFilter() {
            @Override
            public void uplevel(Player player) {
                player.r_power += 1;
                player.r_agile += 2;
                player.r_intelligence += 2;

                player.maxMp += 6;
                player.atk += 1;
                player.armor += 4;
            }

            @Override
            public void doSkill(BattleManager bm) {
                if (Math.random() < 0.5) {
                    bm.player.pushTool(ToolSkillFactory.createTool());
                    bm.player.pp += 3;
                    if (bm.player.pp > bm.player.maxPp) {
                        bm.player.pp = bm.player.maxPp;
                    }
                    Toast.makeText(Const.mContext_Game, "得到了道具，并返还30%灵魂消耗", Toast.LENGTH_SHORT).show();
                }
            }
        };
        Skill shield = new NoTargetSkill(this, Skill.MAXPP, 100, Skill.MP, 5, null) {
            int music_id;

            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if (super.doSkill(x, y, bm)) {
                    bm.player.def += bm.player.pp;
                    bm.player.pp = 0;
                    music_id = Const.soundPool_Game.load(Const.mContext_Game, R.raw.gameview_nuhou, 1);
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
        shield.name = "灵魂护盾";
        shield.introduce = "把你的灵魂数值全转换为护盾";
        shield.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_linghunhudun);
        shield.bitmap = Bitmap.createScaledBitmap(shield.bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);


        Skill money_skill = new FullScreeenSkill(this, Skill.MAXPP, 5, Skill.MP, 5, null) {
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if (super.doSkill(x, y, bm)) {

                    ArrayList<Monster> monsters = FullScreeenSkill.getFullMonster(bm);
                    if (bm.player.money > 10) {
                        for (int i = 0; i < monsters.size(); i++) {
                            monsters.get(i).hp -= bm.player.money * 0.1;
                            bm.MonsterClear();
                        }
                        bm.player.money -= 10;
                        return true;
                    } else
                        return false;
                }
                return false;
            }
        };
        money_skill.name = "乾坤一掷";
        money_skill.introduce = "用钱将造成全屏怪物的伤害(100% 的最大灵魂值)";
        money_skill.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_qiankunyizhi);
        money_skill.bitmap = Bitmap.createScaledBitmap(money_skill.bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);

        skills[0] = shield;
        skills[1] = money_skill;
    }
}

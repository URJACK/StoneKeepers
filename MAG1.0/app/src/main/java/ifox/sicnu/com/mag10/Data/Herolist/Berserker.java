package ifox.sicnu.com.mag10.Data.Herolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.SoundPool;

import java.util.ArrayList;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Buff.Buff;
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
public class Berserker extends Hero{
    public Berserker(Pictures pictures){
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

        Skill shield = new NoTargetSkill(this,Skill.MAXPP,100,Skill.MP,5, null){
            int music_id;
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if(super.doSkill(x, y, bm)){
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
                }else
                    return false;
            }
        };
        shield.name = "灵魂护盾";
        shield.introduce = "把你的灵魂转换为护盾";
        shield.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_linghunhudun);
        shield.bitmap = Bitmap.createScaledBitmap(shield.bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);

        Skill money_skill = new FullScreeenSkill(this,Skill.MAXPP,5,Skill.MP,5,null){
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if(super.doSkill(x, y, bm)){

                    ArrayList<Monster> monsters = FullScreeenSkill.getFullMonster(bm);
                    if(bm.player.money>10) {
                        for (int i = 0; i < monsters.size(); i++) {
                            monsters.get(i).hp -= bm.player.money * 0.1;
                            bm.MonsterClear();
                        }
                        bm.player.money -= 10;
                        return true;
                    }
                    else
                        return false;
                }
                return false;
            }
        };
        money_skill.name = "乾坤一掷";
        money_skill.introduce = "用钱将造成全屏怪物的伤害";
        money_skill.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(),R.drawable.skill_qiankunyizhi);
        money_skill.bitmap = Bitmap.createScaledBitmap(money_skill.bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);







        this.skills[0]= shield;
        this.skills[1] = money_skill;
    }
}

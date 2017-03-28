package ifox.sicnu.com.mag10.Data.Herolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.AttackMethod.AttackMethodFactory;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Buff.BuffFactory;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Skill.NoTargetSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.Skill;
import ifox.sicnu.com.mag10.R;
import ifox.sicnu.com.mag10.Tool.HeroFilter;

/**
 * Created by Funchou Fu on 2017/3/5.
 */
public class Vampire extends Hero {
    public Vampire(Pictures pictures) {
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
        this.heroName = "吸血鬼";
        this.introduction = "一名强大的吸血鬼族的战士，精通";

        this.heroFilter = new HeroFilter() {
            @Override
            public void uplevel(Player player) {
                player.r_power += 3;
                player.r_agile += 1;
                player.r_intelligence += 1;

                player.maxMp += 3;
                player.atk += 3;
                player.armor += 2;
            }

            @Override
            public void doSkill(BattleManager bm) {
                Toast.makeText(Const.mContext_Game, "我就要吸血!", Toast.LENGTH_SHORT).show();
            }
        };

        Skill xianxue = new NoTargetSkill(this, Skill.MAXHP, 2, Skill.HP, 10, BuffFactory.createKeepBuff("atkIncrease"));
        xianxue.name = "献血神祭";
        xianxue.introduce = "把自己的鲜血(10hp)给神灵，换取攻击力(5)，持续两回合，可叠加";
        xianxue.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_xianxue);
        xianxue.bitmap = Bitmap.createScaledBitmap(xianxue.bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);


        Skill xixue = new NoTargetSkill(this, Skill.MAXMP, 2, Skill.MP, 5, BuffFactory.createNoKeepBuff("xixuegongji")) {
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if (super.doSkill(x, y, bm)) {
                    bm.player.addAtm(AttackMethodFactory.createMethod("xixuegongji"));
                    return true;
                } else
                    return false;
            }
        };
        xixue.name = "吸血攻击";
        xixue.introduce = "让自己的攻击能够获得吸血效果";
        xixue.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_xixue);
        xixue.bitmap = Bitmap.createScaledBitmap(xixue.bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);


        skills[0] = xianxue;
        skills[1] = xixue;
    }
}

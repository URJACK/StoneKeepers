package ifox.sicnu.com.mag10.Data.Herolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.util.ArrayList;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Cell;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.DataStructure.Monster;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DataStructure.Skill.FullScreeenSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.NoTargetSkill;
import ifox.sicnu.com.mag10.DataStructure.Skill.Skill;
import ifox.sicnu.com.mag10.R;
import ifox.sicnu.com.mag10.Tool.HeroFilter;

/**
 * Created by Funchou Fu on 2017/3/5.
 */
public class Berserker extends Hero {
    public Berserker(Pictures pictures) {
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
        this.heroName = "地主保镖";
        this.introduction = "地主旁边强壮的保镖.他精通探索，能够对迷雾中的怪物进行猎杀，也能够基于当前探索的迷雾个数获取护甲";

        this.heroFilter = new HeroFilter() {
            @Override
            public void uplevel(Player player) {
                player.r_power += 2;
                player.r_agile += 2;
                player.r_intelligence += 1;

                player.maxMp += 3;
                player.atk += 2;
                player.armor += 4;
            }

            @Override
            public void doSkill(BattleManager bm) {
                if (Math.random() < 0.3)
                    Toast.makeText(Const.mContext_Game, "我需要力量", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Const.mContext_Game, "AAAAAAAAA", Toast.LENGTH_SHORT).show();
            }
        };

        Skill miwu_hudun = new NoTargetSkill(this, Skill.MAXMP, 10, Skill.MP, 5, null) {
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if (super.doSkill(x, y, bm)) {
                    ArrayList<Cell> cells = new ArrayList<>();
                    for (int i = 0; i < bm.cells.size(); i++) {
                        if (bm.cells.get(i).status == Cell.DISCORVERED) {
                            cells.add(bm.cells.get(i));
                        }
                    }
                    bm.player.def += cells.size();
                    return true;
                } else
                    return false;
            }
        };
        miwu_hudun.name = "迷雾护盾";
        miwu_hudun.introduce = "让自己的护甲增加当前翻开迷雾的个数";
        miwu_hudun.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_miwu_hudun);
        miwu_hudun.bitmap = Bitmap.createScaledBitmap(miwu_hudun.bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);

        Skill miwu = new FullScreeenSkill(this, Skill.MAXHP, 50, Skill.HP, 10, null) {
            @Override
            public boolean doSkill(int x, int y, BattleManager bm) {
                if (super.doSkill(x, y, bm)) {
                    ArrayList<Monster> monsters = FullScreeenSkill.getFullMonster_miwu(bm);
                    for (int i = 0; i < monsters.size(); i++) {
                        monsters.get(i).hp -= bm.player.maxHp * 0.35;
                        bm.MonsterClear();
                    }
                    return true;
                }
                return false;

            }
        };
        miwu.name = "迷雾猎杀";
        miwu.introduce = "失去自己的生命10点，对迷雾中的怪物进行猎杀(自身最大Hp35%)";
        miwu.bitmap = BitmapFactory.decodeResource(Const.mContext_Game.getResources(), R.drawable.skill_miwu);
        miwu.bitmap = Bitmap.createScaledBitmap(miwu.bitmap, Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);

        this.skills[0] = miwu;
        this.skills[1] = miwu_hudun;
    }
}

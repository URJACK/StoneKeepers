package ifox.sicnu.com.mag10.Data.Monster;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.Monster.Goblin;
import ifox.sicnu.com.mag10.DataStructure.Monster;

/**
 * Created by 41988 on 2017/3/15.
 */
public class MonsterFactory {
    public static Monster createMonster(String name, int level) {
        if (name.equals("Goblin"))
            return new Goblin(Const.mContext_Game, level);
        else if (name.equals("Saboteur"))
            return new Saboteur(Const.mContext_Game, level);
        else if (name.equals("Saboteur_Crazy"))
            return new Saboteur_Crazy(Const.mContext_Game, level);
        else if (name.equals("Assassin"))
            return new Assassin(Const.mContext_Game, level);
        else if (name.equals("Spider"))
            return new Spider(Const.mContext_Game, level);
        else if (name.equals("RuneSorcerer"))
            return new RuneSorcerer(Const.mContext_Game, level);
        else if (name.equals("DarkRitual"))
            return new DarkRitual(Const.mContext_Game, level);
        else if (name.equals("SpikedKnight"))
            return new SpikedKnight(Const.mContext_Game, level);
        else if (name.equals("Ogres"))
            return new Ogres(Const.mContext_Game, level);
        else if (name.equals("Pumpkin"))
            return new Pumpkin(Const.mContext_Game, level);
        else if (name.equals("Skeleton"))
            return new Skeleton(Const.mContext_Game, level);
        else if (name.equals("SkeletonArcher"))
            return new SkeletonArcher(Const.mContext_Game, level);
        else if (name.equals("Sheep"))
            return new Sheep(Const.mContext_Game, level);
        else if (name.equals("MotherMouse"))
            return new MotherMouse(Const.mContext_Game, level);
        else if (name.equals("Mouse"))
            return new Mouse(Const.mContext_Game, level);
        else if (name.equals("Mummy"))
            return new Mummy(Const.mContext_Game, level);
        else if (name.equals("DarkLord"))
            return new DarkLord(Const.mContext_Game, level);
        else
            return null;
    }
}
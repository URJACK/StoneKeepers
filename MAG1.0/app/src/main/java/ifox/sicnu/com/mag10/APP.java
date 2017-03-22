package ifox.sicnu.com.mag10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ifox.sicnu.com.mag10.Data.HeroBuff;
import ifox.sicnu.com.mag10.Data.Heroes;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.Data.Skills;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.Tool.DtbsControler;
import ifox.sicnu.com.mag10.Tool.DtbsOpHelper;

/**
 * Created by Funchou Fu on 2017/2/21.
 * 先创建出游戏内的资源对象，之后将它们塞进数据库中，之后在把他们从内存中删除
 */
public class APP extends android.app.Application {
    Pictures pictures;          //游戏里的图片资源
    Heroes heroes;      //游戏里的英雄资源
    Skills skills;              //游戏里的技能资源

    HeroBuff heroBuff;          //游戏里的存储单位
    SQLiteDatabase db;
    DtbsControler dc;




    public ReadyActivity readyActivity;        //在GameActivity 结束后，结束此Activity
    public GameActivity gameActivity;

    Player player;

    public APP() {

    }

    //在第一个Activity 时， 使用该方法传入 Context 生成游戏资源
    public void createDatas(Context context) {
        DtbsOpHelper dtbsOpHelper = new DtbsOpHelper(context, "stonekeeper", null, 2, pictures);
        db = dtbsOpHelper.getWritableDatabase();
        dc = new DtbsControler(db, context);
        pictures = dtbsOpHelper.pictures;
        if (pictures == null){
            pictures = new Pictures(context,false);
        }
        pictures.setDc(dc);                 //图片是所有的依赖项，必须优先处理
        pictures.clear();

        skills = new Skills(pictures);
        heroBuff = new HeroBuff(context);

        heroes = new Heroes(pictures, skills);


    }

    public Pictures getPictures() {
        return pictures;
    }

    public HeroBuff getHeroBuff() {
        return heroBuff;
    }

    public Heroes getHeroes() {
        return heroes;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }



}

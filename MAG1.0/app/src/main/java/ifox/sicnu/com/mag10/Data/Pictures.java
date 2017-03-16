package ifox.sicnu.com.mag10.Data;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.util.Log;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ifox.sicnu.com.mag10.R;
import ifox.sicnu.com.mag10.Tool.DtbsControler;

/**
 * Created by Funchou Fu on 2017/2/21.
 * 游戏内的图片资源存在该对象中
 */
public class Pictures {
    private HashMap<String, Bitmap> pictures = new HashMap<>();
    private Context context;
    private Resources resources;
    private DtbsControler dc;


    public Pictures(Context context, boolean flag) {
        this.context = context;
        if (flag) {
            resources = context.getResources();
            Picture_Skill();
            Picture_StartView();
            Picture_ReadyView();
            Picture_SelectView();
            Picture_HeroView();
            Picture_Sword();
            Picture_GameView();
        }
    }

    private void Picture_Skill() {
        Bitmap[] backs = new Bitmap[2];
        backs[0] = BitmapFactory.decodeResource(resources, R.drawable.skill_arcmissle);
        backs[0] = Bitmap.createScaledBitmap(backs[0], Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);
        backs[1] = BitmapFactory.decodeResource(resources, R.drawable.skill_strenthenpower);
        backs[1] = Bitmap.createScaledBitmap(backs[0], Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);

        pictures.put("skill_arcmissle", backs[0]);
        pictures.put("skill_strenthenpower", backs[1]);
    }

    private void Picture_GameView() {
        Bitmap[] backs = new Bitmap[1];
        backs[0] = BitmapFactory.decodeResource(resources, R.drawable.gameview_bg);
        backs[0] = Bitmap.createScaledBitmap(backs[0], (int) (Const.SCREENHEIGHT * 0.6), Const.SCREENWIDTH, true);
        pictures.put("gameview_bg", backs[0]);
    }

    //添加剑
    private void Picture_Sword() {
        Bitmap[] backs = new Bitmap[2];
        backs[0] = BitmapFactory.decodeResource(resources, R.drawable.sword_1);
        backs[0] = Bitmap.createScaledBitmap(backs[0], Const.SKILL_WIDTH, Const.SKILL_HEIGHT, true);
        backs[1] = BitmapFactory.decodeResource(resources, R.drawable.sword_2);
        backs[1] = Bitmap.createScaledBitmap(backs[1], Const.BAG_WIDTH, Const.BAG_HEIGHT, true);
        pictures.put("sword_1", backs[0]);
        pictures.put("sword_2", backs[1]);
    }

    //添加英雄选择背景
    private void Picture_HeroView() {
        Bitmap[] backs;
        backs = new Bitmap[5];
        backs[0] = BitmapFactory.decodeResource(resources, R.drawable.hero_select);
        backs[0] = Bitmap.createScaledBitmap(backs[0], Const.SCREENHEIGHT, Const.SCREENWIDTH, true);
        backs[1] = BitmapFactory.decodeResource(resources, R.drawable.hero_leftlimit);
        backs[1] = Bitmap.createScaledBitmap(backs[1], (int) (Const.SCREENHEIGHT * 0.11), (int) (Const.SCREENWIDTH * 0.38), true);
        backs[2] = BitmapFactory.decodeResource(resources, R.drawable.hero_rightlimit);
        backs[2] = Bitmap.createScaledBitmap(backs[2], (int) (Const.SCREENHEIGHT * 0.12), (int) (Const.SCREENWIDTH * 0.38), true);
        backs[3] = BitmapFactory.decodeResource(resources, R.drawable.hero_room1);
        backs[3] = Bitmap.createScaledBitmap(backs[3], Const.BUTTON_WIDTH * 2, (int) (Const.SCREENWIDTH * 0.38), true);
        backs[4] = BitmapFactory.decodeResource(resources, R.drawable.hero_room2);
        backs[4] = Bitmap.createScaledBitmap(backs[4], Const.BUTTON_WIDTH * 2, (int) (Const.SCREENWIDTH * 0.38), true);

        pictures.put("hero_select", backs[0]);
        pictures.put("hero_leftlimit", backs[1]);
        pictures.put("hero_rightlimit", backs[2]);
        pictures.put("hero_room1", backs[3]);
        pictures.put("hero_room2", backs[4]);
    }

    /**
     * 给SelectView添加图片
     * 以及英雄图片的录入
     */
    private void Picture_SelectView() {
        Bitmap[] backs;
        backs = new Bitmap[13];
        //0 是 SelectView 的 背景图片
        //1 是 人物的背包区域
        //2 是 人物的头像区域
        //3 是 人物的技能栏
        //4~5 是 背包按钮图片 先关后开
        //6~7 是 武器切换按钮图片 先I 后 II
        //8~18 是 英雄的图片
        //因为此时横屏显示，所以有些WIDTH 和 HEIGHT 的位置互换了
        backs[0] = BitmapFactory.decodeResource(resources, R.drawable.selectview_background);
        backs[0] = Bitmap.createScaledBitmap(backs[0], (int) (Const.SCREENHEIGHT * 0.6), Const.SCREENWIDTH, true);
        backs[1] = BitmapFactory.decodeResource(resources, R.drawable.person_bag);
        backs[1] = Bitmap.createScaledBitmap(backs[1], (int) (Const.SCREENHEIGHT * 0.6), Const.SCREENWIDTH, true);
        backs[2] = BitmapFactory.decodeResource(resources, R.drawable.person);
        backs[2] = Bitmap.createScaledBitmap(backs[2], (int) (Const.SCREENHEIGHT * 0.25), Const.SCREENWIDTH, true);
        backs[3] = BitmapFactory.decodeResource(resources, R.drawable.person_skill);
        backs[3] = Bitmap.createScaledBitmap(backs[3], (int) (Const.SCREENHEIGHT * 0.15), Const.SCREENWIDTH, true);
        backs[4] = BitmapFactory.decodeResource(resources, R.drawable.person_bagclose);
        backs[4] = Bitmap.createScaledBitmap(backs[4], Const.BUTTON_WIDTH, Const.BUTTON_WIDTH, true);
        backs[5] = BitmapFactory.decodeResource(resources, R.drawable.person_bagopen);
        backs[5] = Bitmap.createScaledBitmap(backs[5], Const.BUTTON_WIDTH, Const.BUTTON_WIDTH, true);
        backs[6] = BitmapFactory.decodeResource(resources, R.drawable.person_weapon1);
        backs[6] = Bitmap.createScaledBitmap(backs[6], Const.BUTTON_WIDTH, Const.BUTTON_WIDTH, true);
        backs[7] = BitmapFactory.decodeResource(resources, R.drawable.person_weapon2);
        backs[7] = Bitmap.createScaledBitmap(backs[7], Const.BUTTON_WIDTH, Const.BUTTON_WIDTH, true);
        backs[8] = BitmapFactory.decodeResource(resources, R.drawable.hero_1);
        backs[8] = Bitmap.createScaledBitmap(backs[8], (int) (Const.BUTTON_WIDTH * 1.5), (int) (Const.BUTTON_WIDTH * 1.5), true);
        backs[9] = BitmapFactory.decodeResource(resources, R.drawable.hero_2);
        backs[9] = Bitmap.createScaledBitmap(backs[9], (int) (Const.BUTTON_WIDTH * 1.5), (int) (Const.BUTTON_WIDTH * 1.5), true);
        backs[10] = BitmapFactory.decodeResource(resources, R.drawable.hero_3);
        backs[10] = Bitmap.createScaledBitmap(backs[10], (int) (Const.BUTTON_WIDTH * 1.5), (int) (Const.BUTTON_WIDTH * 1.5), true);
        backs[11] = BitmapFactory.decodeResource(resources, R.drawable.hero_4);
        backs[11] = Bitmap.createScaledBitmap(backs[11], (int) (Const.BUTTON_WIDTH * 1.5), (int) (Const.BUTTON_WIDTH * 1.5), true);
        backs[12] = BitmapFactory.decodeResource(resources, R.drawable.hero_5);
        backs[12] = Bitmap.createScaledBitmap(backs[12], (int) (Const.BUTTON_WIDTH * 1.5), (int) (Const.BUTTON_WIDTH * 1.5), true);


        pictures.put("selectview_background", backs[0]);
        pictures.put("person_bag", backs[1]);
        pictures.put("person", backs[2]);
        pictures.put("person_skill", backs[3]);
        pictures.put("person_bagclose", backs[4]);
        pictures.put("person_bagopen", backs[5]);
        pictures.put("person_weapon1", backs[6]);
        pictures.put("person_weapon2", backs[7]);
        pictures.put("hero_1", backs[8]);
        pictures.put("hero_2", backs[9]);
        pictures.put("hero_3", backs[10]);
        pictures.put("hero_4", backs[11]);
        pictures.put("hero_5", backs[12]);
    }

    /**
     * 给ReadyView添加图片
     */
    private void Picture_ReadyView() {
        Bitmap back_1;          //背景图片
        Bitmap back_2;          //门左边图片
        Bitmap back_3;          //门右边图片
        Bitmap back_4;
        Bitmap back_5;
        Bitmap back_6;
        Bitmap back_7;          //4~6 为小石子图片 7为大石子
        Bitmap back_8;
        Bitmap back_9;
        Bitmap back_10;
        Bitmap back_11;
        Bitmap back_12;
        Bitmap back_13;         //8~13 分别为 该活动的几个 按钮
        back_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.readyview);
        back_1 = Bitmap.createScaledBitmap(back_1, Const.SCREENWIDTH, Const.SCREENHEIGHT, true);
        back_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.door_left);
        back_2 = Bitmap.createScaledBitmap(back_2, Const.SCREENWIDTH / 2, Const.SCREENHEIGHT, true);
        back_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.door_right);
        back_3 = Bitmap.createScaledBitmap(back_3, Const.SCREENWIDTH / 2, Const.SCREENHEIGHT, true);
        back_4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone1);
        back_4 = Bitmap.createScaledBitmap(back_4, Const.SCREENWIDTH / 24, Const.SCREENHEIGHT / 44, true);
        back_5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone2);
        back_5 = Bitmap.createScaledBitmap(back_5, Const.SCREENWIDTH / 18, Const.SCREENHEIGHT / 36, true);
        back_6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone3);
        back_6 = Bitmap.createScaledBitmap(back_6, Const.SCREENWIDTH / 20, Const.SCREENHEIGHT / 40, true);
        back_7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone4);
        back_7 = Bitmap.createScaledBitmap(back_7, Const.SCREENWIDTH / 8, Const.SCREENHEIGHT / 10, true);

        back_8 = BitmapFactory.decodeResource(context.getResources(), R.drawable.readyview_btn1);
        back_8 = Bitmap.createScaledBitmap(back_8, Const.SCREENHEIGHT / 5, Const.SCREENHEIGHT / 10, true);
        back_9 = BitmapFactory.decodeResource(context.getResources(), R.drawable.readyview_btn2);
        back_9 = Bitmap.createScaledBitmap(back_9, Const.SCREENHEIGHT / 5, Const.SCREENHEIGHT / 10, true);
        back_10 = BitmapFactory.decodeResource(context.getResources(), R.drawable.readyview_btn3);
        back_10 = Bitmap.createScaledBitmap(back_10, Const.SCREENHEIGHT / 5, Const.SCREENHEIGHT / 10, true);
        back_11 = BitmapFactory.decodeResource(context.getResources(), R.drawable.readyview_btn4);
        back_11 = Bitmap.createScaledBitmap(back_11, Const.SCREENHEIGHT / 5, Const.SCREENHEIGHT / 10, true);
        back_12 = BitmapFactory.decodeResource(context.getResources(), R.drawable.readyview_btn5);
        back_12 = Bitmap.createScaledBitmap(back_12, Const.SCREENHEIGHT / 5, Const.SCREENHEIGHT / 10, true);
        back_13 = BitmapFactory.decodeResource(context.getResources(), R.drawable.readyview_btn6);
        back_13 = Bitmap.createScaledBitmap(back_13, Const.SCREENHEIGHT / 5, Const.SCREENHEIGHT / 10, true);


        pictures.put("readyview", back_1);
        pictures.put("door_left", back_2);
        pictures.put("door_right", back_3);
        pictures.put("stone1", back_4);
        pictures.put("stone2", back_5);
        pictures.put("stone3", back_6);
        pictures.put("stone4", back_7);
        pictures.put("readyview_btn1", back_8);
        pictures.put("readyview_btn2", back_9);
        pictures.put("readyview_btn3", back_10);
        pictures.put("readyview_btn4", back_11);
        pictures.put("readyview_btn5", back_12);
        pictures.put("readyview_btn6", back_13);
    }

    /**
     * 给StartView添加图片
     */
    private void Picture_StartView() {
        Bitmap back_1;          //背景图片
        Bitmap back_2;          //开始按钮图片
        Bitmap back_3;          //开始按钮点击后图片
        back_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.startview);
        back_1 = Bitmap.createScaledBitmap(back_1, Const.SCREENWIDTH, Const.SCREENHEIGHT, true);
        back_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.startbutton_2);
        back_2 = Bitmap.createScaledBitmap(back_2, (int) (Const.SCREENWIDTH * 0.5), (int) (Const.SCREENWIDTH * 0.25), true);
        back_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.startbutton);
        back_3 = Bitmap.createScaledBitmap(back_3, (int) (Const.SCREENWIDTH * 0.5), (int) (Const.SCREENWIDTH * 0.25), true);
        pictures.put("startview", back_1);
        pictures.put("startbutton_2", back_2);
        pictures.put("startbutton", back_3);
    }

    public Bitmap getBitmap(String name) {
        return dc.getBitmapfromInnerFile(name);
    }

    public HashMap<String, Bitmap> getPictures() {
        return pictures;
    }

    public void clear() {
        Collection e = this.pictures.entrySet();
        Iterator iterator = e.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Bitmap bitmap = (Bitmap) entry.getValue();
            bitmap.recycle();
        }
        this.pictures = null;
    }

    public void setDc(DtbsControler dc) {
        this.dc = dc;
    }
}

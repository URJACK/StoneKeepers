package ifox.sicnu.com.mag10.TouchLogic;

import android.util.Log;
import android.view.MotionEvent;

import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.SelectActivity;

/**
 * Created by Funchou Fu on 2017/2/27.
 * 这个Touch文件是关于对英雄界面的操作类
 * 提供的操作有：
 * 1·打开、关闭背包
 * 2·切换 技能、道具面板
 * 对Player具有依赖关系
 */
public class HeroTouch {
    private int baseX;      //英雄界面的左上角x坐标
    private int baseY;      //英雄界面的左上角y坐标
    private int width;      //英雄界面的宽度占比
    private int height;     //英雄界面的长度占比
    private Player player;          //绘制player的类

    private boolean ischangebag = false;            //是否按压bag
    private boolean ischangeskill = false;          //是否按压skill

    public HeroTouch(int baseX, int baseY, int width, int height, Player player) {
        this.baseX = baseX;
        this.baseY = baseY;
        this.width = width;
        this.height = height;
        this.player = player;
    }

    public void getTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x > baseX + width * 0.1 && x < baseX + width * 0.4 && y > baseY + 0.15 * height && y < baseY + 0.27 * height) {
                ischangebag = true;
                ischangeskill = false;
            }   //点击背包按钮
            else if (x > baseX + width * 0.7 && x < baseX + width && y > baseY + height * 0.15 && y < baseY + 0.27 * height) {
                ischangeskill = true;
                ischangebag = false;
            }   //点击切换技能按钮
            else {
                ischangebag = false;
                ischangeskill = false;
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP){
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x > baseX + width * 0.1 && x < baseX + width * 0.4 && y > baseY + 0.15 * height && y < baseY + 0.27 * height && this.ischangebag) {
                if (player.bagswitch)
                    player.bagswitch = false;
                else
                    player.bagswitch = true;
            }
            else if (x > baseX + width * 0.7 && x < baseX + width && y > baseY + height * 0.15 && y < baseY + 0.27 * height && this.ischangeskill) {
                if (player.skillswitch)
                    player.skillswitch = false;
                else
                    player.skillswitch = true;
            }   //点击切换技能按钮
        }
    }
}

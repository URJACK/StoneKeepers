package ifox.sicnu.com.mag10.DataStructure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import ifox.sicnu.com.mag10.Data.Const;

/**
 * Created by Funchou Fu on 2017/3/7.
 * Cell 是游戏地图中一个最基本的元素，它具有横坐标，纵坐标的属性。
 * Cell内部里，可能是:
 * 怪物、商店、事件、陷阱、门
 * 为了方便对这些的设置与修改，我就讲他们均设置为Public变量
 * 默认地牢界面的屏幕为横屏：宽度占比为60%,高度占比为100%;
 * Cell类自带 绘制的方法
 * 2017年3月14日12:51:08 创造陷阱。
 */
public class Cell {
    public Monster monster;
    public Shop shop;
    public Trap trap;

    public int x;
    public int y;
    public int status;

    public static final int UNDISCORVERED = 1;//可探索 但未被探索
    public static final int DISCORVERED = 2;//探索过
    public static final int FORBID = 3; //不可探索
    public static final int UNDISCORVERED2 = 4;//怪物周围 不可探索的红叉叉

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        status = FORBID;

    }

    public void drawMonster(Canvas canvas) {
        canvas.drawBitmap(monster.getBitmap(), Const.BASE_CELL_OFFX + Const.CELL_WIDTH * x, Const.BASE_CELL_OFFY + Const.CELL_HEIGHT * y, null);
    }

    //如果返还为true，则说明该点没有任何元素
    public boolean isEmpty() {
        if (monster == null && shop == null &&trap==null ) {
            return true;
        } else
            return false;
    }
}
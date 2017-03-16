package ifox.sicnu.com.mag10.TouchLogic;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.Equipment;
import ifox.sicnu.com.mag10.DataStructure.Player;

/**
 * Created by Funchou Fu on 2017/3/3.
 */
public class BagTouch {
    private static final String TAG = "SelectActivity";
    public int baseX;
    public int baseY;
    public int width;
    public int height;
    //代表监听背包的范围

    public Player player;          //传入背包所属的Player对象

    public Equipment needtoShow;            //当前正在被展示的装备，如果为空，则不展示

    public int touchNumber = -1;            //被选择的编号
    private Const.Bag bag;          //根据传入参数记录坐标的一个类

    public int y;
    public int x;           //手滑动时，x y坐标

    public BagTouch(int baseX, int baseY, int width, int height, Player player) {
        this.baseX = baseX;
        this.baseY = baseY;
        this.width = width;
        this.height = height;
        this.player = player;
        bag = new Const.Bag(baseX, baseY, width, height);
    }

    //外界传入的 Event 事件
    public void getTouch(MotionEvent event) {
        if (!player.bagswitch)
            return;
        //如果背包未打开，停止监听事件
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            this.needtoShow = null;         //把自己要展示的装备进行置空
            if (y > bag.unitY[0] && y < bag.unitY[11] + Const.BAG_HEIGHT) {
                for (int i = 0; i < 12; i++) {
                    if (x > bag.unitX[i] && x < bag.unitX[i] + Const.BAG_WIDTH && y > bag.unitY[i] && y < bag.unitY[i] + Const.BAG_HEIGHT) {
                        touchNumber = i;
                        break;
                    }//确实选中了一个
                }
            }//此时 选择的是 背包栏的内容 0~11
            else if (y < bag.unitY[0]) {
                for (int i = 12; i < 20; i++) {
                    if (x > bag.unitX[i] && x < bag.unitX[i] + Const.BAG_WIDTH && y > bag.unitY[i] && y < bag.unitY[i] + Const.BAG_HEIGHT) {
                        touchNumber = i;
                        break;
                    }//确实选中了一个
                }
            }//此时 选择的是 装备的栏的物件 12~19
            else {
                touchNumber = -1;
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            this.x = (int) event.getX();
            this.y = (int) event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (touchNumber == -1)
                return;
            int y = (int) event.getY();
            if (y > bag.unitY[0] && y < bag.unitY[11] + Const.BAG_HEIGHT) {
                if (touchNumber > 11) {
                    Equipment e1 = player.getEquipmentByNumber(touchNumber);
                    player.inbag(e1);
                    player.diswear(e1);
                }//确实选中了一个
            }//表明 要脱下装备
            else if (y < bag.unitY[0]) {
                if (touchNumber < 12) {
                    Equipment e1 = player.getEquipmentByNumber(touchNumber);
                    Equipment e = player.wear(e1);
                    player.diswear(e);
                    player.inbag(e);
                    player.disinbag(e1);
                }
            }//表明要 穿上装备

            int elseSelect = -1;                //松手时，选择的对象编号
            if (y > bag.unitY[0] && y < bag.unitY[11] + Const.BAG_HEIGHT) {
                for (int i = 0; i < 12; i++) {
                    if (x > bag.unitX[i] && x < bag.unitX[i] + Const.BAG_WIDTH && y > bag.unitY[i] && y < bag.unitY[i] + Const.BAG_HEIGHT) {
                        elseSelect = i;
                        break;
                    }//确实选中了一个
                }
            }//此时 选择的是 背包栏的内容 0~11
            else if (y < bag.unitY[0]) {
                for (int i = 12; i < 20; i++) {
                    if (x > bag.unitX[i] && x < bag.unitX[i] + Const.BAG_WIDTH && y > bag.unitY[i] && y < bag.unitY[i] + Const.BAG_HEIGHT) {
                        elseSelect = i;
                        break;
                    }//确实选中了一个
                }
            }//此时 选择的是 装备的栏的物件 12~19
            Log.i(TAG, "getTouch: " + elseSelect + " " + touchNumber);
            if (elseSelect != -1 && touchNumber == elseSelect) {
                needtoShow = player.getEquipmentByNumber(touchNumber);
            }   //elseSelect 如果 不是 默认数值， 并且与 touchNumber  相同 则表示touchNumber 个 单位 是被选择的单位。 needtoShow 可能为空
            else {
                needtoShow = null;
            }

            touchNumber = -1; //执行完松手逻辑后，将touchNumber置为默认值
        }
    }
}

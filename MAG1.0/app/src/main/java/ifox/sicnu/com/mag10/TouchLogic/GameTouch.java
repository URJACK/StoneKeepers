package ifox.sicnu.com.mag10.TouchLogic;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.LinkedList;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.DataStructure.BattleManager;
import ifox.sicnu.com.mag10.DataStructure.Cell;
import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.DrawLogic.DrawSpecialEffects.PpgetSpecialEffects;

/**
 * Created by 41988 on 2017/3/8.
 * 屏幕的点击动作分为:探索、触发事件(攻击，进入下一关)
 * 探索:1·将探索点从 UNDISCOVERED - > DISCOVERED
 * 2·探索周围点，检查他们的横纵坐标是否可行。如果可行，
 * 检查他们的状态: UNDISCOVERED 、DISCOVERED -> 不变
 * FORBIT -> UNDISCOVERED
 */
public class GameTouch {
    private static final String TAG = "GameTouch";
    MotionEvent mevent;
    BattleManager battleManager;
    LinkedList<Cell> cells;
    int x;
    int y;
    int xx;
    int yy;     //选中的坐标点
    int presslongth = 0;        //默认为0 ，如果长按时，移出了目标区域，则置为-1

    public GameTouch(BattleManager battleManager) {
        this.battleManager = battleManager;
        cells = battleManager.cells;
    }

    public void getTouch(MotionEvent event) {
        mevent = event;
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            int xx = (x - Const.BASE_CELL_OFFX) / Const.CELL_WIDTH;
            int yy = (y - Const.BASE_CELL_OFFY) / Const.CELL_HEIGHT;
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                this.battleManager.changeShowObject(-1);
                presslongth = 0;
            }
            if ((event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_UP)) {
                if (battleManager.shop != null && battleManager.shop.isopen) {
                    ShopEvent(x, y);
                } else if (battleManager.showObject != null) {
                    //如果此时已经进入了展示的阶段，那么屏蔽掉 ACTION_DOWN 和 ACTION_UP 的操作 ， 在下一次 ACTION_DOWN 的时候，会将showObject 的单位进行重置
                } else {
                    DiscoverChangeSkillEvent(xx, yy, x, y);
                }
            } else {
                LongPressEvent(xx, yy, x, y);
            }
        }
    }

    private void ShopEvent(int x, int y) {
        if (x > Const.SCREENHEIGHT * 0.5 && x < Const.SCREENHEIGHT * 0.6 && y > Const.SCREENWIDTH * 0.1 && y < Const.SCREENWIDTH * 0.2) {
            battleManager.shop.isopen = false;
        }
        //商店内一系列的操作
        else if (y < Const.SCREENWIDTH * 0.7) {
            int left_ofs = Const.BASE_CELL_OFFX;
            int top_ofs = (int) (Const.SCREENWIDTH * 0.33);
            //判定点击商店的哪个装备
            if (x < Const.SCREENHEIGHT * 0.32 && y > top_ofs && y < Const.SCREENWIDTH * 0.53) {
                int xx = x - left_ofs;
                int index = xx / Const.CELL_WIDTH;
                if (y > Const.SCREENWIDTH * 0.43) {
                    index += 4;
                }
                battleManager.shop.S_equipment_index = index;
            }
        } else {
            if (x > Const.SCREENHEIGHT * 0.25 && x < Const.SCREENHEIGHT * 0.3 && y > Const.SCREENWIDTH * 0.9 && mevent.getAction() == MotionEvent.ACTION_UP) {
                if (!battleManager.shop.sell()) {              //商店销售掉自己被选中的商品,如果销售失败返回false
                    Toast.makeText(battleManager.mContext, "购买失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(battleManager.mContext, "购买成功", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    /**
     * LongPressEvent 主要管理 changeShowObject() 方法
     */
    private void LongPressEvent(int xx, int yy, int x, int y) {
        if (xx == this.xx && yy == this.yy && presslongth != -1) {
            presslongth++;
        } else
            presslongth = -1;
        if (presslongth >= 20) {
            if (xx > -1 && xx < 8 && yy > -1 && yy < 7) {
                Cell cell = battleManager.cells.get(xx + yy * 8);
                if (!cell.isEmpty()) {
                    if (cell.monster != null) {
                        battleManager.showObject = cell.monster;
                    } else if (cell.shop != null) {
                        battleManager.showObject = cell.shop;
                    }
                }
            }       //此段代码表示点击的坐标是地图内的元素
            else {
                if (x > Const.SCREENHEIGHT * 0.9 && x < Const.SCREENHEIGHT * 0.96 && y > Const.SCREENWIDTH * 0.11 && y < Const.SCREENWIDTH * 0.28) {
                    battleManager.changeShowObject(0);
                } else if (x > Const.SCREENHEIGHT * 0.9 && x < Const.SCREENHEIGHT * 0.96 && y > Const.SCREENWIDTH * 0.42 && y < Const.SCREENWIDTH * 0.59) {
                    battleManager.changeShowObject(1);
                } else if (x > Const.SCREENHEIGHT * 0.9 && x < Const.SCREENHEIGHT * 0.96 && y > Const.SCREENWIDTH * 0.73 && y < Const.SCREENWIDTH * 0.9) {
                    battleManager.changeShowObject(2);
                }
            }       //如果长按的是地图外的元素
        }
    }

    public void DiscoverChangeSkillEvent(int xx, int yy, int x, int y) {
        if (mevent.getAction() == MotionEvent.ACTION_DOWN) {
            this.xx = xx;
            this.yy = yy;
            this.x = x;
            this.y = y;
        } else {
            if (this.xx == xx && this.yy == yy) {
                boolean flag = true;               //flag  为 true  代表点击的是地图
                if (xx > 7 || xx < 0 || yy > 6 || yy < 0)
                    flag = false;
                //点击的是游戏地图
                if (flag) {
                    battleManager.battleWork(xx, yy, battleManager.function_switch);

                }       //对地图左边的点击
                else if (battleManager.function_switch != -1 && x < Const.SCREENHEIGHT * 0.87 && x > Const.SCREENHEIGHT * 0.62) {
                    battleManager.function_switch = -1;
                } else {
                    if (x > Const.SCREENHEIGHT * 0.9 && x < Const.SCREENHEIGHT * 0.96 && y > Const.SCREENWIDTH * 0.11 && y < Const.SCREENWIDTH * 0.28) {
                        battleManager.changeFunction(0);
                    } else if (x > Const.SCREENHEIGHT * 0.9 && x < Const.SCREENHEIGHT * 0.96 && y > Const.SCREENWIDTH * 0.42 && y < Const.SCREENWIDTH * 0.59) {
                        battleManager.changeFunction(1);
                    } else if (x > Const.SCREENHEIGHT * 0.9 && x < Const.SCREENHEIGHT * 0.96 && y > Const.SCREENWIDTH * 0.73 && y < Const.SCREENWIDTH * 0.9) {
                        battleManager.changeFunction(2);
                    }
                }       //对function_switch 的改变
            }
        }
    }
}

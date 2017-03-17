package ifox.sicnu.com.mag10.Data;

import android.content.Context;
import android.media.SoundPool;

import ifox.sicnu.com.mag10.DataStructure.BattleManager;

/**
 * Created by Funchou Fu on 2017/2/20.
 * 2017年3月3日 14:37:51
 * 2017年3月12日 17:29:10 新增人物的经验值常量
 */
public class Const {
    public static int SCREENHEIGHT;

    public static int SCREENWIDTH;

    public static int PERSON_LCTY;      //人物界面相对于屏幕的偏移量

    public static int OPERATE_HEIGHT;       //操作界面所占的长

    public static int BUTTON_WIDTH;         //操作界面按钮的长宽

    public static int SKILL_WIDTH;

    public static int SKILL_HEIGHT;         //释放的技能和道具的所占长宽

    public static int BAG_WIDTH;

    public static int BAG_HEIGHT;           //背包里的物品所占长宽

    public static int BAG_FLOATWIDTH;

    public static int BAG_FLOATHEIGHT;      //背包里的悬浮窗的长宽

    public static int CELL_WIDTH;

    public static int CELL_HEIGHT;          //地宫视图中，每个CELL 的长宽。

    public static int BASE_CELL_OFFX;

    public static int BASE_CELL_OFFY;       //地宫视图中，初始CELL于屏幕左上角的偏移量

    public static Context mContext_Game;         //用于获取游戏资源时，的公共mContext 常量：默认为GameActivity.在玩家初始化属性的时候，默认为SelectActivity

    public static BattleManager bm;            //用于获取游戏资源时，的公共mContext 常量：默认为GameActivity.在玩家初始化属性的时候，默认为SelectActivity

    public static SoundPool soundPool_Game;          //在游戏界面时，需要播放一些声效。

    public static class Bag {
        int baseX;
        int baseY;
        int width;
        int height;

        int offx_1;
        int offy_1;
        int offx_2;
        int offy_2;

        public int unitX[];
        public int unitY[];

        public Bag(int baseX, int baseY, int width, int height) {
            this.baseX = baseX;
            this.baseY = baseY;
            this.width = width;
            this.height = height;

            offx_1 = (int) (width * 0.13);
            offy_1 = (int) (height * 0.12);
            offx_2 = (int) (width * 0.118);
            offy_2 = (int) (height * 0.1);

            unitX = new int[20];
            unitY = new int[20];

//            canvas.drawBitmap(player.equipments[i].bitmap, baseX + (int) (width * 0.275) + (i % 4) * offx_2, baseY + (int) (height * 0.34) + (i / 4) * offy_2, null);
            for (int i = 0; i < 12; i++) {
                unitX[i] = baseX + (int) (width * 0.275) + (i % 4) * offx_2;
                unitY[i] = baseY + (int) (height * 0.34) + (i / 4) * offy_2;
            }
            unitX[12] = baseX + (int) (width * 0.34);
            unitY[12] = baseY + (int) (height * 0.05);

            unitX[13] = baseX + (int) (width * 0.34);
            unitY[13] = baseY + (int) (height * 0.05) + offy_1;

            unitX[14] = baseX + (int) (width * 0.515);
            unitY[14] = baseY + (int) (height * 0.05);

            unitX[15] = baseX + (int) (width * 0.515);
            unitY[15] = (int) (height * 0.05) + offy_1;

            unitX[16] = baseX + (int) (width * 0.515) + offx_1;
            unitY[16] = baseY + (int) (height * 0.05);

            unitX[17] = (int) (width * 0.515) + offx_1;
            unitY[17] = baseY + (int) (height * 0.05) + offy_1;

            unitX[18] = baseX + (int) (width * 0.515) + offx_1 * 2;
            unitY[18] = baseY + (int) (height * 0.05);

            unitX[19] = baseX + (int) (width * 0.515) + offx_1 * 2;
            unitY[19] = baseY + (int) (height * 0.05) + offy_1;
        }
    }
    // bag 对象 记录下了 bag里每一个 物件的可选坐标
    //0~11 为 人物的背包
    //12~19 为 人物的装备栏 依次对应 weapon,co_weapon,helmet,hand,wear_up,belt,wear_down,shoe

    public static class Exp {
        //表明从 0to1  到  9to10 级的经验值
        public static final int[] levels = new int[10];

        static {
            if (levels[0] != -1) {
                levels[0] = -1;
                levels[1] = 200;
                levels[2] = 500;
                levels[3] = 1000;
                levels[4] = 2500;
                levels[5] = 3000;
                levels[6] = 3500;
                levels[7] = 4000;
                levels[8] = 5000;
                levels[9] = 6000;
            }
        }
    }

    public static class Cryption {
        public static int encrypt(int index) {
            int r = 0;
            r += index * 4 + 1;
            r += r;
            r += r * 2;
            r += r * 3;
            r %= 700;
            return r;
        }//加密
    }
}

package ifox.sicnu.com.mag10.DrawLogic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import ifox.sicnu.com.mag10.DataStructure.Player;
import ifox.sicnu.com.mag10.TouchLogic.BagTouch;

/**
 * Created by Funchou Fu on 2017/2/27.
 * 绘制SelectView 的主要操作界面
 * 绘制背包的逻辑在DrawBackground 类已经完成，
 * 所以在该类的doCanvas 里，只写出不绘制背包的逻辑,所以必须加上
 * if(!player.bagswitch){
 * 【LOGIC】
 * }
 */
public class Draw_SelectViewBg extends DrawBackground {


    public Draw_SelectViewBg(Player player, int baseX, int baseY, int width, int height, Bitmap background, Bitmap bagground, BagTouch bagTouch) {
        super(player, baseX, baseY, width, height, background, bagground, bagTouch);
    }

    @Override
    public void doCanvas(Canvas canvas) {
        super.doCanvas(canvas);
        if (!player.bagswitch) {
            canvas.drawBitmap(background, baseX, baseY, null);
        }
    }
}
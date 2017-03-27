package ifox.sicnu.com.mag10.DrawLogic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ifox.sicnu.com.mag10.Data.Const;
import ifox.sicnu.com.mag10.Data.HeroBuff;
import ifox.sicnu.com.mag10.R;
import ifox.sicnu.com.mag10.View.AbilityView;

/**
 * Created by 41988 on 2017/3/3.
 */
public class DrawAbility implements ModeDrawIn {

    private HeroBuff heroBuff;
    private Bitmap bg_bitmap;
    private Bitmap bg_2bitmap;

    private Bitmap abilitycycle;
    private Bitmap abilitycycle2;
    private Bitmap[] bitmaps;
    private BoxView[] boxViews;
    private Context mcontext;
    private int box_Height = Const.SCREENHEIGHT * 25 / 37;
    private int box_Width = Const.SCREENWIDTH * 5 / 16;
    private int offset_hight = Const.SCREENHEIGHT / 18;    //能力卡片之间的距离

    private Paint paint;

    private int offset;                                  //滑动偏移
    private int money;

    private Canvas canvas;


    public DrawAbility(Context context, HeroBuff heroBuff) {
        mcontext = context;
        this.heroBuff = heroBuff;
        bg_bitmap = BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.ability_bg);
        bg_bitmap = Bitmap.createScaledBitmap(bg_bitmap, Const.SCREENHEIGHT, Const.SCREENWIDTH, true);

        abilitycycle = BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.abilicycle);
        abilitycycle2 = BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.abilitycycle2);
        abilitycycle = Bitmap.createScaledBitmap(abilitycycle, (int) (Const.SCREENWIDTH * 0.05), (int) (Const.SCREENWIDTH * 0.05), true);
        abilitycycle2 = Bitmap.createScaledBitmap(abilitycycle2, (int) (Const.SCREENWIDTH * 0.05), (int) (Const.SCREENWIDTH * 0.05), true);

        bg_2bitmap = BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.ability_bg2);
        bg_2bitmap = Bitmap.createScaledBitmap(bg_2bitmap, Const.SCREENHEIGHT, Const.SCREENWIDTH * 8 / 45, true);
        bitmaps = new Bitmap[5];
        boxViews = new BoxView[5];
        Bitmap bitmap = BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.ability_cbox);
        bitmap = bitmap.createScaledBitmap(bitmap, box_Height, box_Width, true);
        for (int i = 0; i < 5; i++) {
            bitmaps[i] = bitmap;
            boxViews[i] = new BoxView();
            boxViews[i].setBg_Box(bitmaps[i]);
        }

        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(Const.SCREENWIDTH * 5 / 108);


    }

    @Override
    public void doCanvas(Canvas canvas) {
        this.canvas = canvas;
        canvas.drawBitmap(bg_bitmap, 0, 0, null);
        int base_x = Const.SCREENHEIGHT * 6 / 37;
        int base_y = Const.SCREENWIDTH * 5 / 27;
        int baseoff = (int) (Const.SCREENHEIGHT * 0.05);
        for (int i = 0; i < 5; i++) {
            canvas.drawBitmap(boxViews[i].getBg_Box(), base_x, base_y - offset, null);

            DrawAbility_Circle(canvas, offset);
            canvas.drawText(heroBuff.buffer_name.get(i), base_x + baseoff, (float) (base_y - offset + Const.SCREENHEIGHT * 0.06), paint);
            canvas.drawText(heroBuff.buffer_worlds.get(i), base_x + baseoff, (float) (base_y - offset + Const.SCREENHEIGHT * 0.11), paint);

            base_y = base_y + offset_hight + box_Width;
            canvas.drawText(String.valueOf(AbilityView.COST), (int) (Const.SCREENHEIGHT * 18 / 25), (int) (Const.SCREENWIDTH * 0.31) + i * (Const.SCREENHEIGHT / 18 + Const.SCREENWIDTH * 5 / 16) - offset, paint);
        }

        canvas.drawBitmap(bg_2bitmap, 0, 0, null);
        canvas.drawText(String.valueOf(money), (int) (Const.SCREENHEIGHT * 0.9), (int) (Const.SCREENWIDTH * 0.06), paint);

    }

    public void isSlide(int offset) {
        this.offset = offset;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void DrawAbility_Circle(Canvas canvas, int offset) {
        List<Integer> ability_lel = new ArrayList<>();
        ability_lel.add(heroBuff.hp);
        ability_lel.add(heroBuff.mp);
        ability_lel.add(heroBuff.pp);
        ability_lel.add(heroBuff.atk);
        ability_lel.add(heroBuff.armor);
        int base_y = Const.SCREENWIDTH * 8 / 27;
        int off = Const.SCREENWIDTH / 40;
        for (int i = 0; i < 5; i++) {

            int lel = ability_lel.get(i);     //lel 为黄圈个数
            for (int j = 0; j < 5; j++) {
                if (j < lel)
                    canvas.drawBitmap(abilitycycle2, (int) (Const.SCREENHEIGHT * 0.19) + j * off, base_y - offset, null);
                else
                    canvas.drawBitmap(abilitycycle, (int) (Const.SCREENHEIGHT * 0.19) + j * off, base_y - offset, null);

            }
            base_y = base_y + offset_hight + box_Width;
        }
    }


}

class BoxView {
    private Bitmap bg_Box;


    public void setBg_Box(Bitmap bg_Box) {
        this.bg_Box = bg_Box;
    }

    public Bitmap getBg_Box() {
        return bg_Box;
    }
}

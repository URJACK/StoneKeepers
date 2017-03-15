package ifox.sicnu.com.mag10.View;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Funchou Fu on 2017/2/20.
 * 关联
 */
public interface DrawInterface {
    void doDraw(Canvas canvas);
    SurfaceHolder getHolder();
}

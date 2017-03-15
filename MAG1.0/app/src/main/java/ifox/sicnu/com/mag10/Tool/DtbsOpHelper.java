package ifox.sicnu.com.mag10.Tool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import ifox.sicnu.com.mag10.Data.Affixs;
import ifox.sicnu.com.mag10.Data.Equipments;
import ifox.sicnu.com.mag10.Data.Heroes;
import ifox.sicnu.com.mag10.Data.Pictures;
import ifox.sicnu.com.mag10.DataStructure.Equipment;
import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.StartActivity;

/**
 * Created by Funchou Fu on 2017/2/28.
 * ·对于Heroes ：英雄可能会穿戴装备，以及背包里可能会穿戴装备，但是在存储的时候，我们只存储该装备的名字作为英雄装备的索引。
 * ·关于Skills ：Skills 类充满了多样性，使得该类存储在数据库里并不是一个明智的决定。
 * ·关于图片，所有英雄图片，装备图片，存储的均为索引:text。 最终从数据库中取得数据时，需要先访问再转换成Bitmap图片
 */
public class DtbsOpHelper extends SQLiteOpenHelper {

    Context mContext;
    private String TAG = "StartActivity";
    public Pictures pictures;
    public boolean flag = false;

    public DtbsOpHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, Pictures pictures) {
        super(context, name, factory, version);
        mContext = context;
        this.pictures = pictures;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "onCreate: Database********************************");
        flag = true;
        pictures = new Pictures(mContext, true);
        DtbsControler dtbsControler = new DtbsControler(sqLiteDatabase, mContext);
        AddPictures(dtbsControler);
    }

    private void AddPictures(DtbsControler dtbsControler) {
        Collection collection = pictures.getPictures().entrySet();
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Map.Entry m = (Map.Entry) iterator.next();
            Bitmap bitmap = (Bitmap) m.getValue();
            String name = (String) m.getKey();
            dtbsControler.writeBitmapintoInnerFile(bitmap, name);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        flag = true;
    }

}

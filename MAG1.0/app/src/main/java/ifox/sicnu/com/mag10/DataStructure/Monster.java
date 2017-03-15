package ifox.sicnu.com.mag10.DataStructure;

import android.graphics.Bitmap;

/**
 * Created by Funchou Fu on 2017/3/7.
 * 属于怪物的特有属性：
 *
 * 2017年3月13日12:44:45 新增加经验值属性
 */
public class Monster extends Unit {
    public static final int BOSS = 0;
    public static final int NORMAL = 1;
    public static final int ELITE = 2;
    private int type;            //怪物的类型
    private int level;           //怪物的等级，BattleManager 可以根据怪物的等级和 怪物的类型，来给每一层合理的分配怪物资源
    private String introduce;
    private String name;
    private Bitmap bitmap;

    public int exp;

    public int getMonsterType() {
        return this.type;
    }

    public int getMonsterLevel() {
        return this.level;
    }

    public void setMonsterType(int type) {
        this.type = type;
    }

    public void setMonsterLevel(int level) {
        this.level = level;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return null;
    }
}
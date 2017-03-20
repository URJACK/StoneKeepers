package ifox.sicnu.com.mag10.Affix;

import ifox.sicnu.com.mag10.DataStructure.Equipment;

/**
 * Created by Funchou Fu on 2017/2/28.
 * 该类是Equipment对象的一个成员，用来给Equipment 附加各式各样的名字
 * 目前，词缀能够添加的属性有 基础三属性， 伤害三属性，防御三属性. 上限三属性
 */
public abstract class Affix {

    public String affixName;            //词缀的名字

    @Override
    public String toString() {
        return this.affixName;
    }

    public abstract void doWork(Equipment equipment);
}

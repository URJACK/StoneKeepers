package ifox.sicnu.com.mag10.DataStructure;

/**
 * Created by Funchou Fu on 2017/2/28.
 * 该类是Equipment对象的一个成员，用来给Equipment 附加各式各样的名字
 * 目前，词缀能够添加的属性有 基础三属性， 伤害三属性，防御三属性. 上限三属性
 */
public class Affix {
    public int atk;             //伤害
    public float hitrate;         //命中
    public float crit;            //爆击

    public int armor;           //可回复到的护甲
    public float dodge;           //闪避
    public float resistance;      //抗性

    public int maxHp;      //当前最大生命值
    public int maxMp;      //当前最大法力值
    public int maxPp;      //当前最大魂力值

    public String affixName;            //词缀的名字

    @Override
    public String toString() {
        return this.affixName;
    }
}

package ifox.sicnu.com.mag10.DataStructure;

import ifox.sicnu.com.mag10.DataStructure.Buff.Buff;

/**
 * Created by Funchou Fu on 2017/3/7.
 * 给目标穿上Buff，用该接口提供的方法来实现
 */
public interface UnitWearBuff {
    void wearBuff(Buff buff);       //增添Buff
    void dropBuff(Buff buff);       //删除Buff
}
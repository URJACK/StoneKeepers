package ifox.sicnu.com.mag10.Affix;

import ifox.sicnu.com.mag10.DataStructure.Equipment;

/**
 * Created by Funchou Fu on 2017/3/20.
 * 词缀:
 * 独狼 wolf:增加攻击(+3 再翻 1/4 倍)，增加爆击(+0.15)
 * 雄鹰 eagle:增加攻击，增加命中
 * 灰熊 grizzlies:增加生命，增加抗性
 * 灵猴 monkey:增加闪避
 * 巨蟹 cancer:增加护甲
 * 灵龟 turtle:增加生命，增加护甲
 * 风暴 storm:增加法力，增加闪避
 * 暗雷 darklightning:增加魂力，增加法力
 * 1 Resistance Crit Dodge Pp
 * 2 Attack Armor Hitrate Hp Mp
 */
public class AffixFactory {
    public static Affix createAffix(String name) {
        if (name.equals("wolf"))
            return createWolf();
        else if (name.equals("eagle"))
            return createEagle();
        else if (name.equals("grizzlies"))
            return createGrizzlies();
        else if (name.equals("monkey"))
            return createMonkey();
        else if (name.equals("cancer"))
            return createCancer();
        else if (name.equals("turtle"))
            return createTurtle();
        else if (name.equals("storm"))
            return createStorm();
        else if (name.equals("darklightning"))
            return createDarkLightning();
        return null;
    }

    public static Affix createAffix() {
        double m = Math.random();
        double l = ((double) 1) / 8;
        if (m < l)
            return createWolf();
        else if (m < 2 * l)
            return createEagle();
        else if (m < 3 * l)
            return createGrizzlies();
        else if (m < 4 * l)
            return createMonkey();
        else if (m < 5 * l)
            return createCancer();
        else if (m < 6 * l)
            return createTurtle();
        else if (m < 7 * l)
            return createStorm();
        else
            return createDarkLightning();
    }

    private static Affix createDarkLightning() {
        Affix affix = new Affix() {
            @Override
            public void doWork(Equipment equipment) {
                equipment.maxPp += 5;
                equipment.maxPp *= 1.4;
                equipment.maxMp += 3;
                equipment.maxMp *= 1.2;
            }
        };
        return affix;
    }

    private static Affix createStorm() {
        Affix affix = new Affix() {
            @Override
            public void doWork(Equipment equipment) {
                equipment.maxMp += 3;
                equipment.maxMp *= 1.2;
                equipment.dodge += 0.1;
            }
        };
        return affix;
    }

    private static Affix createTurtle() {
        Affix affix = new Affix() {
            @Override
            public void doWork(Equipment equipment) {
                equipment.maxHp += 2;
                equipment.maxHp *= 1.25;
                equipment.armor += 2;
                equipment.armor *= 1.25;
            }
        };
        return affix;
    }

    private static Affix createCancer() {
        Affix affix = new Affix() {
            @Override
            public void doWork(Equipment equipment) {
                equipment.armor += 3;
                equipment.armor *= 2;
            }
        };
        affix.affixName = "巨蟹";
        return affix;
    }

    private static Affix createMonkey() {
        Affix affix = new Affix() {
            @Override
            public void doWork(Equipment equipment) {
                equipment.dodge += 0.3;
            }
        };
        return affix;
    }

    private static Affix createGrizzlies() {
        Affix affix = new Affix() {
            @Override
            public void doWork(Equipment equipment) {
                equipment.maxHp += 3;
                equipment.maxHp *= 1.5;
                equipment.resistance += 0.2;
            }
        };
        affix.affixName = "灰熊";
        return affix;
    }

    private static Affix createEagle() {
        Affix affix = new Affix() {
            @Override
            public void doWork(Equipment equipment) {
                equipment.atk += 2;
                equipment.atk *= 1.2;
                equipment.hitrate += 0.2;
            }
        };
        affix.affixName = "雄鹰";
        return affix;
    }

    private static Affix createWolf() {
        Affix affix = new Affix() {
            @Override
            public void doWork(Equipment equipment) {
                equipment.atk += 3;
                equipment.atk *= 1.25;
                equipment.crit += 0.15;
            }
        };
        affix.affixName = "独狼";
        return affix;
    }
}

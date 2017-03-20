package ifox.sicnu.com.mag10.Data;

import java.util.ArrayList;

import ifox.sicnu.com.mag10.DataStructure.Hero;
import ifox.sicnu.com.mag10.Data.Herolist.Berserker;
import ifox.sicnu.com.mag10.Data.Herolist.Missionary;
import ifox.sicnu.com.mag10.Data.Herolist.Vampire;
import ifox.sicnu.com.mag10.Data.Herolist.Wizard;

/**
 * Created by Funchou Fu on 2017/2/24.
 * 英雄的faceid  即为找寻图片的名字，也为R.drawble.XXX，也是通过getHero(faceid) 所带入的参数
 */
public class Heroes {
    Pictures pictures;      //获取图片资源使用的类
    Skills skills;

    public Heroes(Pictures pictures, Skills skills) {
        this.skills = skills;
        this.pictures = pictures;
    }

    public Hero getHero(String heroName) {
        if (heroName.equals("斯微法")){
            return new Wizard(pictures);
        }else if (heroName.equals("弗拉基米尔")){
            return new Vampire(pictures);
        }else if (heroName.equals("赫拉克勒斯")){
            return new Berserker(pictures);
        }else if (heroName.equals("马可波罗")){
            return new Missionary(pictures);
        }
        return null;
    }

    public void IniHeroLine(ArrayList<Hero> herolines) {
        herolines.add(this.getHero("斯微法"));
        herolines.add(this.getHero("弗拉基米尔"));
        herolines.add(this.getHero("赫拉克勒斯"));
        herolines.add(this.getHero("马可波罗"));
    }

}

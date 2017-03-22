package ifox.sicnu.com.mag10.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ifox.sicnu.com.mag10.APP;
import ifox.sicnu.com.mag10.DataStructure.Player;

/**
 * Created by Funchou Fu on 2017/2/24.
 * 通过HeroBuff 和 选择的基础英雄，来决定玩家的初始属性
 * HeroBuff 是可以通过不断游玩进行提升的，使用sharedprefrence进行存储。
 * HeroBuff 同样也进行金钱的存储
 */
public class HeroBuff {
    private final String TAG = "StartActivity";
    private Context context;

    public int hp;
    public int mp;
    public int pp;
    public int atk;
    public int armor;
    public int money;
    //-----------------------------------------------------
    public List<String> buffer_name;
    public List<String> buffer_worlds;


    /*在启动APP 时，进行对HeroBuff进行初始化数据*/
    public HeroBuff(Context context) {
        this.context = context;
        SharedPreferences sp = context.getSharedPreferences("herobuff", Context.MODE_PRIVATE);
        hp = sp.getInt("hp", 0);
        mp = sp.getInt("mp", 0);
        pp = sp.getInt("pp", 0);
        atk = sp.getInt("atk", 0);
        armor = sp.getInt("armor", 0);
        money = sp.getInt("money", 30);

        Log.i(TAG, String.format("hp %d mp %d pp %d atk %d armor %d money %d", hp, mp, pp, atk, armor, money));

        buffer_name = new ArrayList<>();
        buffer_worlds = new ArrayList<>();
        initworlds();

    }

    private void initworlds() {
        buffer_name.add("hp");
        buffer_name.add("mp");
        buffer_name.add("pp");
        buffer_name.add("atk");
        buffer_name.add("armor");
        buffer_worlds.add("让英雄强化此属性");
        buffer_worlds.add("让英雄强化此属性");
        buffer_worlds.add("让英雄强化此属性");
        buffer_worlds.add("让英雄强化此属性");
        buffer_worlds.add("让英雄强化此属性");
    }

    /*当自身属性发生变化时，通过该方法来保留已经变化的属性*/
    public void saveValue() {
        Player player = ((APP) context.getApplicationContext()).getPlayer();
        SharedPreferences sp = context.getSharedPreferences("herobuff", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("hp", hp);
        editor.putInt("mp", mp);
        editor.putInt("pp", pp);
        editor.putInt("atk", atk);
        editor.putInt("armor", armor);
        editor.putInt("money", player.money);
        Toast.makeText(context, "你已经成功的存储了数据到本地", Toast.LENGTH_SHORT).show();
        editor.commit();
    }

}

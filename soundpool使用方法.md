# 使用SoundPool播放音效
——如果应用程序经常需要播放密集，短促的音效。这时还用Mediaplayer就显得不合适。
——MediaPlayer的缺点：资源占用量较高，延迟时间较长不支持多个音频同时播放。
——SoundPool使用音效池来播放一些较短的声音片段，它的优势资源占用量低和反应延迟小。
## 创建对象:new SoundPool(num,stream,0)
        第一个参数指定支持多少个声音
        第二个参数指定声音类型
        第三个参数指定声音品质，还没有启作用
        如：soundPool = new SoundPool(3,AudioManager.STREAM_MUSIC, 0);
        在android21的版本建议使用SoundPool中的内部类来创建：
        SoundPool pool=new SoundPool.Builder().setMaxStreams(3).build();
## 添加音频:load()加载声音，最好使用hashMap来管理：HashMap<Integer,Integer>
         load(Context context,int resId,int priority)
         load(String path,int priority)priority播放声音的优先级,目前没有启作用
         如：int id=soundPool.load(this,R.raw.nudge,1)
                map.put(1,soundPool.load(this,R.raw.nudge,1));
               返回的该声音的ID，之后就根据该ID来播放指定声音
## 播放:play(int,float,float,int,int,float)
          第一个参数指定播放哪个声音
          第二三个参数指定左右音量
          第四个参数播放声音的优先级，值越大越优先播放，0最小
          第五个参数是否循环，0不循环，-1循环
          第六个参数播放的比率，1为正常的，
          如：soundPool.play(pools.get(1), 1, 1, 0,0,1);
       暂停:pause()
       停止:stop()
       释放资源:release();
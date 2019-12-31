# 页面水印 类：WaterMarkPageBg
最近公司发布保护个人隐私，内部公共信息不能外传的命令，因此做了一个水印的功能，分为页面水印以及图片水印两种</br>
1.页面水印</br>
页面水印封装代码在WaterMarkPageBg类里面:</br>
  调用方法为：</br>
        //当前日期</br>
        SimpleDateFormat createTimeSdf1 = new SimpleDateFormat("yyyy-MM-dd");</br>
        //创建一个多行显示list</br>
        final List<String> labels = new ArrayList<>();</br>
        labels.add("用户名：绿帽三");</br>
        labels.add("日期："+ createTimeSdf1.format(new Date()));</br>
        labels.add("私人非开放信息页面");</br>
        //页面背景增加水印案例（可扩增可控属性，水印字体颜色，背景字体颜色等）</br>
        findViewById(R.id.bg).setBackgroundDrawable(new WaterMarkPageBg(MainActivity.this,labels,-30,13));</br>
      
        放在方法里面或者实例方法里就可使用，属性可扩增。
        
      


# 图片水印 类：WaterMarkPictureBg
2.图片水印</br>
图片水印封装代码在WaterMarkPictureBg类里面:</br>
  调用方法为：</br>
        //当前日期</br>
        SimpleDateFormat createTimeSdf1 = new SimpleDateFormat("yyyy-MM-dd");</br>
        //创建一个多行显示list</br>
        final List<String> labels = new ArrayList<>();</br>
        labels.add("用户名：绿帽三");</br>
        labels.add("日期："+ createTimeSdf1.format(new Date()));</br>
        labels.add("私人非开放信息页面");</br>
         //图片增加水印案例</br>
WaterMarkPictureBg.merge(MainActivity.this,labels,-30,14,"#000000","/mnt/sdcard/DCIM/TL/FQD","201911.png","/mnt/sdcard/DCIM/TL/FQD","201912.png");

        放在方法里面或者实例方法里就可使用，属性可扩增。

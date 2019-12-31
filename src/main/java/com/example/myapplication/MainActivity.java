package com.example.myapplication;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button bg_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态获取权限存储权限
        verifyStoragePermissions(this);
        //当前日期
        SimpleDateFormat createTimeSdf1 = new SimpleDateFormat("yyyy-MM-dd");
        //创建一个多行显示list
        final List<String> labels = new ArrayList<>();
        labels.add("用户名：绿帽三");
        labels.add("日期："+ createTimeSdf1.format(new Date()));
        labels.add("私人非开放信息页面");
        //页面背景增加水印案例（可扩增可控属性，水印字体颜色，背景字体颜色等）
        findViewById(R.id.bg).setBackgroundDrawable(new WaterMarkPageBg(MainActivity.this,labels,-30,13));
        bg_tv = findViewById(R.id.bg_tv);
        bg_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //图片增加水印案例
                WaterMarkPictureBg.merge(MainActivity.this,labels,-30,14,"#000000","/mnt/sdcard/DCIM/TL/FQD","201912241008205.png","/mnt/sdcard/DCIM/TL/FQD","201912301008205.png");

            }
        });


    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {

            "android.permission.READ_EXTERNAL_STORAGE",

            "android.permission.WRITE_EXTERNAL_STORAGE"};

    public static void verifyStoragePermissions(Activity activity) {

        try {

            //检测是否有写的权限

            int permission = ActivityCompat.checkSelfPermission(activity,

                    "android.permission.WRITE_EXTERNAL_STORAGE");

            if (permission != PackageManager.PERMISSION_GRANTED) {

                // 没有写的权限，去申请写的权限，会弹出对话框

                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}

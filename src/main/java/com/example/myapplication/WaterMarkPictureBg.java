package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 给图片增加水印
 * 2019-12-30
 * chenjc
 */
public class WaterMarkPictureBg {

    /**
     * 保存图片到文件File。
     *
     * @param context     activity上下文引用
     * @param labels    显示的水印内容（多层显示不同信息）
     * @param degress  倾斜角度（-30）
     * @param fontSize 显示字体大小单位sp
     * @param fontColor 需要加水印的图片原路径
     * @param inPath 需要加水印的图片原路径
     * @param inFilename 需要加水印的图片名称（全称 如：pic.jpg）
     * @param outPath 加水印后的图片保存路径
     * @param outFilename  加水印的输出图片名称（全称 如：pic1.jpg）
     *
     */
    public static void merge(final Context context, final List<String> labels, final int degress, final int fontSize,final String fontColor, final String inPath, final String inFilename, final String outPath, final String outFilename) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                File zhang = new File(inPath, inFilename);

                try {
                    Bitmap bitmap1 = BitmapFactory.decodeStream(new FileInputStream(zhang));

                    File zhangphil = new File(outPath, outFilename);
                    if (!zhangphil.exists())
                        zhangphil.createNewFile();
                    SimpleDateFormat createTimeSdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    Bitmap bitmap2 = addTimeFlag(context,labels,degress,fontSize,fontColor,bitmap1);
                    save(bitmap2, zhangphil, Bitmap.CompressFormat.JPEG, true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 初始化构造
     * @param labels 水印文字列表 多行显示修改content为list即可
     * @param degress 水印角度
     * @param fontSize 水印文字大小
     */
    private static Bitmap addTimeFlag(Context context, List<String> labels, int degress, int fontSize, String fontColor, Bitmap src) {
// 获取原始图片与水印图片的宽与高    
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        // 往位图中开始画入src原始图片    
        canvas.drawBitmap(src, 0, 0, null);
        //添加文字  
        Paint paint = new Paint();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sdf.format(new Date(System.currentTimeMillis()));
        paint.setColor(Color.parseColor(fontColor));
        paint.setTextSize(100);

        paint.setAntiAlias(true);
        paint.setTextSize(sp2px(context,fontSize));
        canvas.save();
        canvas.rotate(degress);
        float textWidth = paint.measureText(labels.get(0));
        int index = 0;
        for (int positionY = height / 10; positionY <= height; positionY += height / 10+80) {
            float fromX = -width + (index++ % 2) * textWidth;
            for (float positionX = fromX; positionX < width; positionX += textWidth * 2) {
                int spacing  = 0;//间距
                for(String label:labels){
                    canvas.drawText(label, positionX, positionY+spacing, paint);
                    spacing = spacing+50;
                }

            }
        }
        canvas.restore();
        return newBitmap;

    }


    /**
     * 保存图片到文件File。
     *
     * @param src     源图片
     * @param file    要保存到的文件
     * @param format  格式
     * @param recycle 是否回收
     * @return true 成功 false 失败
     */
    public static boolean save(Bitmap src, File file, Bitmap.CompressFormat format, boolean recycle) {
        if (isEmptyBitmap(src))
            return false;

        OutputStream os;
        boolean ret = false;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            ret = src.compress(format, 100, os);
            if (recycle && !src.isRecycled())
                src.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * Bitmap对象是否为空。
     */
    public static boolean isEmptyBitmap(Bitmap src) {
        return src == null || src.getWidth() == 0 || src.getHeight() == 0;
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}

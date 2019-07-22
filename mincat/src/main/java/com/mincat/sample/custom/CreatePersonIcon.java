package com.mincat.sample.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

/**
 * @author Gin
 * @描述  自定义一个圆形头像
 */

public class CreatePersonIcon {

    private static Canvas canvas;
    private static Bitmap bitmap;
    private static Paint paint;
    private static final int BITMAP_HEIGHT = 80;
    private static final int BITMAP_WIDTH = 80;
    private static final float TEXT_SIZE = 25.0f;


    public static Bitmap createPersonIcon(Context context, String username, int bg_color, int name_color) {


        //创建一个宽度和高度都是400、32位ARGB图
        bitmap = Bitmap.createBitmap(BITMAP_WIDTH, BITMAP_HEIGHT, Bitmap.Config.ARGB_8888);

        //初始化画布绘制的图像到icon上
        canvas = new Canvas(bitmap);

        //创建画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);

        //设置文字的大小
        paint.setTextSize(TEXT_SIZE);

        //文字的样式(加粗)
        paint.setTypeface(Typeface.DEFAULT_BOLD);

        //设置bitMap背景色
        paint.setColor(context.getResources().getColor(bg_color));

        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);

        canvas.drawOval(rectF, paint);

        // 设置文字颜色
        paint.setColor(context.getResources().getColor(name_color));

        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();

        int baseline = (rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2;

        // 下面这行是实现水平居中，drawText对应改为传入 rect.centerX()
        paint.setTextAlign(Paint.Align.CENTER);

        //将文字写入,这里面的（rect.centerX(), baseline）代表着文字在图层上的初始位置
        canvas.drawText(username, rect.centerX(), baseline, paint);

        //保存所有图层
//        canvas.save(canvas.ALL_SAVE_FLAG);

        canvas.restore();

        return bitmap;

    }


}

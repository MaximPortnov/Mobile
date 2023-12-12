package com.example.pr27;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class Draw2D extends View {

    private Paint mPaint = new Paint();
    private Rect mRect = new Rect();
    private Bitmap mBitmap;

    public Draw2D(Context context) {
        super(context);

        // Выводим значок из ресурсов
        Resources res = this.getResources();
        mBitmap = BitmapFactory.decodeResource(res, R.drawable.kitty);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // стиль Заливка
        mPaint.setStyle(Paint.Style.FILL);

        // закрашиваем холст белым цветом
        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);


        // Рисуем зелёный прямоугольник
        mPaint.setColor(Color.GREEN);
        //  canvas.drawRect(20, 650, 950, 680, mPaint);
        canvas.drawRect(0, canvas.getHeight() - 250, width, height, mPaint);

        // Рисуем текст
        canvas.save();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(100);
//        canvas.rotate(-60);
        canvas.rotate(-60, 30, height - 700);
        canvas.drawText("произведение мирового искусства", 30, height - 700, mPaint);
//        canvas.save();
        canvas.restore();
        int x = width - 170;
        int y = 190;
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(300, 300, 200, mPaint);
        canvas.save();
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.YELLOW);

        float centerX = 300;
        float centerY = 300;
        float radius = 200;

        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(i * 30);
            float startX = centerX + (float) (Math.cos(angle) * radius);
            float startY = centerY + (float) (Math.sin(angle) * radius);
            float endX = centerX + (float) (Math.cos(angle) * (radius + 80));
            float endY = centerY + (float) (Math.sin(angle) * (radius + 80));

            canvas.drawLine(startX, startY, endX, endY, mPaint); // Рисуем лучик
            canvas.save();
        }

        canvas.restore();

        canvas.drawBitmap(mBitmap, 250, 1520, mPaint);
    }
}
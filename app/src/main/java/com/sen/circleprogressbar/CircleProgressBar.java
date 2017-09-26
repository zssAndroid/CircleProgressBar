package com.sen.circleprogressbar;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class CircleProgressBar extends View {
    // 画圆环的画笔
    private Paint ringPaint;
    // 画字体的画笔
    private Paint textPaint;
    //圆环颜色
    private int ringColor;
    // 字体颜色
    private int textColor;
    // 圆环背景颜色
    private int ringBgColor;
    // 半径
    private float radius;
    // 圆环宽度
    private float strokeWidth;
    // 字的长度
    private float txtWidth;
    // 字的高度
    private float txtHeight;
    // 总进度
    private int totalProgress = 100;
    // 当前进度
    private int currentProgress;
    // 透明度
    private int alpha = 1;
    private int mCenterX;
    private int mCenterY;

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleProgressbar, 0, 0);
        radius = typeArray.getDimension(R.styleable.CircleProgressbar_radius, 80);
        strokeWidth = typeArray.getDimension(R.styleable.CircleProgressbar_strokeWidth, 10);
        ringColor = typeArray.getColor(R.styleable.CircleProgressbar_ringColor, 0xFF0000);
        ringBgColor = typeArray.getColor(R.styleable.CircleProgressbar_ringBgColor, 0xFF0000);
        textColor = typeArray.getColor(R.styleable.CircleProgressbar_textColor, 0xFFFFFF);
        typeArray.recycle();
    }

    private void initVariable() {
        ringPaint = new Paint();
        ringPaint.setAntiAlias(true);
        ringPaint.setDither(true);
        ringPaint.setColor(ringColor);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeCap(Paint.Cap.ROUND);
        ringPaint.setStrokeWidth(strokeWidth);
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(textColor);
        textPaint.setTextSize(radius / 2);
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        txtHeight = fm.descent + Math.abs(fm.ascent);
    }

    /**
     * 当layout大小变化后会回调次方法
     * 通过这方法获取宽高
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;//控宽的中心点
        mCenterY = h / 2;//控件高的中心点
//        //防止宽高不一致
//        int min = Math.min(mCenterX, mCenterY);
//        //半径
//        mRadius = min - mRoundWidth / 2;
//        //为画圆弧准备
//        mRectF = new RectF(mCenterX - mRadius, mCenterY - mRadius, mCenterX + mRadius, mCenterY + mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentProgress >= 0) {
//            ringPaint.setAlpha((int) (alpha + ((float) currentProgress / totalProgress) * 230));
            //先画北京圆环
            ringPaint.setColor(ringBgColor);
            int width = getWidth() / 2;
            int height = getHeight() / 2;
            canvas.drawCircle(width, height, radius, ringPaint);
            System.out.println("radius  " +radius);
            //画进度条
            ringPaint.setColor(ringColor);
            RectF oval = new RectF(width - radius, height - radius, width + radius, height + radius);
            canvas.drawArc(oval, 0, 0, false, ringPaint);
            canvas.drawArc(oval, -90, ((float) currentProgress / totalProgress) * 360, false, ringPaint);
            //画数字
            String txt = currentProgress + "%";
            txtWidth = textPaint.measureText(txt, 0, txt.length());
            canvas.drawText(txt, width - txtWidth / 2, height + txtHeight / 4, textPaint);
        }
    }

    public void setProgress(int progress) {
        currentProgress = progress;
        postInvalidate();
    }
}

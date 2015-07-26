package com.voyager.graphview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * 自定义控件——曲线视图
 * Created by wuhaojie on 2015/7/25.
 */
public class GraphView extends View {

    /**
     * 因变量显示格式，如：23.5
     */
    private static final String Y_VALUE_FORMAT = "0.#";
    /**
     * 水平方向上与 View边界的间距，单位：dip
     */
    private static final int VER_MARGIN = 25;
    /**
     * 竖直方向上与 View边界的间距，单位：dip
     */
    private static final int HOS_MARGIN = 8;
    /**
     * 坐标轴颜色
     */
    private static final String AXIS_LINE_COLOR = "#7fffffff";
    /**
     * 坐标轴宽度
     */
    private static final int AXIS_LINE_WIDTH = 2;
    private static final int X_VALUE_TEXTSIZE = 12;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 坐标点集合
     */
    private ArrayList<GraphItem> items = null;
    /**
     * View的高度
     */
    private int height = 0;
    /**
     * View的宽度
     */
    private int width = 0;
    /**
     * 水平间距，单位：px
     */
    private int hosMargin;
    /**
     * 竖直间距，单位：px
     */
    private int verMargin;
    private Paint paint;

    /**
     * 构造函数
     */
    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        height = getHeight();
        width = getWidth();
        hosMargin = DensityUtils.dip2px(context, HOS_MARGIN);
        verMargin = DensityUtils.dip2px(context, VER_MARGIN);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    /**
     * 返回坐标点集合
     */
    public ArrayList<GraphItem> getItems() {
        return items;
    }

    /**
     * 设置坐标点集合
     */
    public void setItems(ArrayList<GraphItem> items) {
        this.items = items;
    }

    /**
     * 绘制 View
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (items == null) {
            return;
        }
        initView();
        drawGraphLine(canvas);
        drawXValue(canvas);
    }

    /**
     * 绘制 x轴自变量
     */
    private void drawXValue(Canvas canvas) {
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(0);
        paint.setTextSize(DensityUtils.dip2px(context, X_VALUE_TEXTSIZE));
        int period = (width - 2 * hosMargin) / items.size(); //平均间隔
        int startX = hosMargin * 3;
        for (GraphItem item : items) {
            canvas.drawText(item.getxValue(), startX, verMargin / 2, paint);
            startX += period;
        }
    }

    /**
     * 绘制坐标轴
     */
    private void drawGraphLine(Canvas canvas) {
        paint.setColor(Color.parseColor(AXIS_LINE_COLOR));
        paint.setStrokeWidth(DensityUtils.dip2px(context, AXIS_LINE_WIDTH));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(hosMargin, verMargin, width - hosMargin, verMargin, paint);
        int verUpMargin = height - hosMargin; //在竖直方向与View上端边界的间距
        canvas.drawLine(hosMargin, verUpMargin, width - hosMargin, verUpMargin, paint);
    }

}

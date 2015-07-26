package com.voyager.graphview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
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
     * 竖直方向上与 View边界的间距，单位：dip
     */
    private static final int VER_MARGIN = 25;
    /**
     * 水平方向上与 View边界的间距，单位：dip
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
    /**
     * 自变量显示文字大小
     */
    private static final int X_VALUE_TEXTSIZE = 12;
    /**
     * 因变量极差均分的份数
     */
    private static final float COPYS = 6.0f;
    private static final int GRAPH_LINE_WIDTH = 3;
    private static final String GRAPH_LINE_COLOR = "#7fffffff";
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
    private ArrayList<Integer> xPoints;

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
        drawGraphAxis(canvas);
        drawXValue(canvas);
        drawYValue(canvas);
    }

    /**
     * 绘制 y轴因变量
     */
    private void drawYValue(Canvas canvas) {
        float yValue = 0;
        float max = items.get(0).getyValue(), min = items.get(0).getyValue();     //因变量的最值
        for (GraphItem item : items) {
            yValue = item.getyValue();
            max = yValue > max ? yValue : max;
            min = yValue < min ? yValue : min;
        }
        float range = (max - min) > 0 ? (max - min) : COPYS;     //极差
        max = max + range / COPYS;
        min = min - range / COPYS;
        Point[] points = getAllPoints(xPoints, max, min, height - 2 * verMargin - hosMargin, verMargin);
        paint.setStrokeWidth(GRAPH_LINE_WIDTH);
        paint.setColor(Color.parseColor(GRAPH_LINE_COLOR));
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < points.length - 1; i++) {
            canvas.drawLine(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y, paint);
        }
    }

    /**
     * 获取所有坐标点
     *
     * @param xPoints     自变量值集合
     * @param max         因变量最大值
     * @param min         因变量最小值
     * @param graphHeight 图像高度范围
     * @param verMargin   图像最高点
     * @return 返回点阵集合
     */
    private Point[] getAllPoints(ArrayList<Integer> xPoints, float max, float min, int graphHeight, int verMargin) {
        Point[] points = new Point[items.size()];
        int yRealHeight;
        for (int i = 0; i < items.size(); i++) {
            yRealHeight = (int) (graphHeight + verMargin - graphHeight * (items.get(i).getyValue() - min) / (max - min));
            points[i] = new Point(xPoints.get(i), yRealHeight);
        }
        return points;
    }

    /**
     * 绘制 x轴自变量
     */
    private void drawXValue(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(0);
        paint.setTextSize(DensityUtils.dip2px(context, X_VALUE_TEXTSIZE));
        paint.setTextAlign(Paint.Align.CENTER);
        int period = (width - 2 * hosMargin) / items.size(); //平均间隔
        int startX = hosMargin * 3;
        xPoints = new ArrayList<>();
        for (GraphItem item : items) {
            xPoints.add(startX);
            canvas.drawText(item.getxValue(), startX, verMargin / 2, paint);
            startX += period;
        }
    }

    /**
     * 绘制坐标轴
     */
    private void drawGraphAxis(Canvas canvas) {
        paint.setColor(Color.parseColor(AXIS_LINE_COLOR));
        paint.setStrokeWidth(DensityUtils.dip2px(context, AXIS_LINE_WIDTH));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(hosMargin, verMargin, width - hosMargin, verMargin, paint);
        int verUpMargin = height - hosMargin - verMargin; //在竖直方向与View上端边界的间距
        canvas.drawLine(hosMargin, verUpMargin, width - hosMargin, verUpMargin, paint);
    }

}

package com.voyager.graphview;

import android.content.Context;
import android.graphics.Canvas;
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

    /**
     * 构造函数
     */
    public GraphView(Context context, AttributeSet attrs, ArrayList<GraphItem> items) {
        super(context, attrs);
        initView(context, attrs, items);
    }

    /**
     * 初始化视图
     */
    private void initView(Context context, AttributeSet attrs, ArrayList<GraphItem> items) {
        this.context = context;
        this.items = items;
        height = getHeight();
        width = getWidth();
        hosMargin = DensityUtils.dip2px(context, HOS_MARGIN);
        verMargin = DensityUtils.dip2px(context, VER_MARGIN);
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

    }
}

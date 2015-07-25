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
     * 上下文
     */
    private Context context;
    /**
     * 坐标点集合
     */
    private ArrayList<GraphItem> items = null;

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

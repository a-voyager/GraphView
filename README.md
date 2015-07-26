# GraphView
自定义控件——天气温度折线图
>>
# 效果图
![image](https://github.com/w19961009/GraphView/raw/master/screenshots/QQ截图20150726150847.jpg "效果图")
![image](https://github.com/w19961009/GraphView/raw/master/screenshots/QQ截图20150726150955.jpg "效果图")
# 使用
## 布局文件
### <com.voyager.graphview.GraphView
            android:id="@+id/my_gv_main"
            android:layout_width="fill_parent"
            android:layout_height="250dip"
            android:layout_centerInParent="true" />
## JAVA代码
###         ArrayList<GraphItem> items = new ArrayList<GraphItem>();
            items.add(new GraphItem("昨天", 21.0f));
            items.add(new GraphItem("今天", 30.0f));
            items.add(new GraphItem("明天", 33.0f));
            items.add(new GraphItem("后天", 26.0f));

            GraphView my_gv_main = (GraphView) findViewById(R.id.my_gv_main);
            my_gv_main.setItems(items);
# 备注
Created by wuhaojie on 2015/7/25.
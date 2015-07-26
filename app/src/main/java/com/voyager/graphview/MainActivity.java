package com.voyager.graphview;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * @author wuhaojie
 */
public class MainActivity extends Activity {

    private GraphView my_gv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<GraphItem> items = new ArrayList<GraphItem>();
        items.add(new GraphItem("昨天", 37.0f));
        items.add(new GraphItem("今天", 37.0f));
        items.add(new GraphItem("明天", 37.0f));
        items.add(new GraphItem("后天", 37.0f));

        my_gv_main = (GraphView) findViewById(R.id.my_gv_main);
        my_gv_main.setItems(items);
    }

}

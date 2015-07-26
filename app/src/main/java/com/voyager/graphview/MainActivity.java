package com.voyager.graphview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @author wuhaojie
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private GraphView my_gv_main;
    private Button btn_add;
    private EditText et_data;
    private ArrayList<GraphItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<GraphItem>();
        items.add(new GraphItem("昨天", 21.0f));
        items.add(new GraphItem("今天", 30.0f));
        items.add(new GraphItem("明天", 33.0f));
        items.add(new GraphItem("后天", 26.0f));

        my_gv_main = (GraphView) findViewById(R.id.my_gv_main);
        et_data = (EditText) findViewById(R.id.et_data);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

        my_gv_main.setItems(items);
    }

    @Override
    public void onClick(View v) {
        String data = et_data.getText().toString().trim();
        if (data.isEmpty()) {
            Toast.makeText(MainActivity.this, "请输入数据", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] points = data.split(";");
        items.add(new GraphItem(points[0], Float.parseFloat(points[1])));
        my_gv_main.invalidate();
    }
}

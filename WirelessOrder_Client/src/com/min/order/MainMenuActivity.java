package com.min.order;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class MainMenuActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("���б����߶���ϵͳ-���˵�");
        setContentView(R.layout.main_menu);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        //gridview.setAdapter(new ImageAdapter(this));
    }
}

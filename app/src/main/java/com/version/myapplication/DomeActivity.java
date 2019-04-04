package com.version.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.version.myapplication.xuanhao.Pick_Activity;

public class DomeActivity extends Activity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dome);
        Button btn_city = findViewById(R.id.btn_city);

        btn_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button btn_qxc = findViewById(R.id.btn_qxc);
        btn_qxc.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                 intent = new Intent(DomeActivity.this, Pick_Activity.class);
                startActivity(intent);
            }
        });

    }
}

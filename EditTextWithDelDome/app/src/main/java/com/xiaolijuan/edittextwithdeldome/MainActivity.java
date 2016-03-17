package com.xiaolijuan.edittextwithdeldome;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.et_phoneNumber);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(userName.getText().toString())){
                    Toast.makeText(getApplicationContext(),"手机号码为空",Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),userName.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

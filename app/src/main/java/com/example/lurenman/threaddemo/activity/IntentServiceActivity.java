package com.example.lurenman.threaddemo.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.threaddemo.R;
import com.example.lurenman.threaddemo.service.MyIntentService;

/**
 * 创建日期：2019/5/17
 * 作者:baiyang
 */
public class IntentServiceActivity extends BaseNewActivity {

    private Button btn_test;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "IntentService";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_common;
    }

    @Override
    protected void initView() {
        btn_test = (Button) findViewById(R.id.btn_test);

    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initListenter() {
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test1();
            }
        });
    }

    private void test1() {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("name", "namei");
        intent.putExtra("age", 20);
        startService(intent);
    }
}

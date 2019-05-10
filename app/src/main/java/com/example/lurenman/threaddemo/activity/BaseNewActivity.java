package com.example.lurenman.threaddemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public abstract class BaseNewActivity extends AppCompatActivity {
    protected Context mContext;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle(getActionBarTitle());
        setContentView(getLayoutResId());
        initView();
        initVariables();
        initListenter();
        loadData();
    }


    protected abstract @NonNull
    String getActionBarTitle();

    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected abstract void initVariables();

    protected void loadData() {

    }

    protected void initListenter() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

}

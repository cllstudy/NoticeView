package com.cllstudy.androidclassutils.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cllstudy.androidclassutils.view.SlidingLayout;


/**
 * 侧滑退出当前页面
 */

public abstract class SlidingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableSliding()) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }
    }

    /**
     * 是否开启侧滑退出
     * @return
     */
    protected boolean enableSliding() {
        return true;
    }
}

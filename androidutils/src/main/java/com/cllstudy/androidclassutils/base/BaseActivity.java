package com.cllstudy.androidclassutils.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.cllstudy.androidclassutils.utils.ToastUtil;
import com.cllstudy.androidclassutils.view.IBaseView;
import com.gyf.barlibrary.ImmersionBar;
import com.mingle.widget.ShapeLoadingDialog;


/**
 * 不带返回按钮的baseActivity,
 */
public abstract class BaseActivity extends SlidingActivity implements IBaseView {

    protected ImmersionBar mImmersionBar;
    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            //初始化沉浸式
            if (isImmersionBarEnabled())
                initImmersionBar();
            initView(savedInstanceState);
            initData();
        }
    }

    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    public abstract void initView(@Nullable Bundle savedInstanceState);

    /**
     * 数据处理
     */
    public abstract void initData();


    @Override
    public void toActivity(Class className) {
        Intent toItt = new Intent(this, className);
        startActivity(toItt);
    }

    @Override
    public void toActivity(Class className, String key, Object value) {
        Intent toItt = new Intent(this, className);
        toItt.putExtra(key, (Parcelable) value);
        startActivity(toItt);
    }

    @Override
    public void activityFinish() {
        this.finish();
    }

    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }
    @Override
    public void toast(String msg) {
        ToastUtil.toast(this, msg);
    }

    @Override
    public void toast(@StringRes int stringId) {
        ToastUtil.toast(this, getString(stringId));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.imm = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }
    @Override
    public void showWaitDialog(String msg) {
        freshDialog(this, msg);
    }
    @Override
    public void dismissWaitDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    ShapeLoadingDialog dialog;
    private void freshDialog(Context context, String msg) {
        if (dialog != null) {
            dialog = null;
        }
        dialog = new ShapeLoadingDialog.Builder(context).loadText(msg).build();
        dialog.show();
    }

}

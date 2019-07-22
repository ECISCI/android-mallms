package com.mincat.mobilemallmanager;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.fra.ManagementFra;
import com.mincat.mobilemallmanager.fra.PunchClockFra;
import com.mincat.mobilemallmanager.fra.ImFra;
import com.mincat.mobilemallmanager.fra.SettingFra;
import com.mincat.sample.manager.MinCatBaseRequest;
import com.mincat.sample.utils.AppManager;
import com.mincat.sample.utils.Constants;
import com.mincat.sample.utils.T;

/**
 * @author Gin
 * @描述 自定义主页
 */
public class MainHomeAct extends MinCatBaseRequest implements RadioGroup.OnCheckedChangeListener {

    private static String mHomePage = MainHomeAct.class.getSimpleName();

    private long exitTimeMillis = System.currentTimeMillis();

    private ManagementFra mManagementFra;
    private PunchClockFra mPunchClockFra;
    private ImFra mImFra;
    private SettingFra mSettingFra;

    private FragmentManager fm;
    private FragmentTransaction transaction;
    private RadioGroup mRadioButtonRg;
    private FragmentTransaction transaction1;
    private RadioButton mRadioMain, mRadioController, mRadioFunction, mMy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        initView();

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            mPunchClockFra = new PunchClockFra();
            fragmentManager.beginTransaction().replace(R.id.fl, mPunchClockFra, mHomePage).commit();
        }
    }


    // 初始化控件
    public void initView() {

        mRadioButtonRg = getId(R.id.r_g);
        mRadioButtonRg.setOnCheckedChangeListener(this);
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();

        mManagementFra = (ManagementFra) fm.findFragmentByTag(mHomePage);
        mPunchClockFra = (PunchClockFra) fm.findFragmentByTag("mPunchClockFra");
        mImFra = (ImFra) fm.findFragmentByTag("mImFra");
        mSettingFra = (SettingFra) fm.findFragmentByTag("mSettingFra");

        mRadioMain = getId(R.id.r_management);
        mRadioController = getId(R.id.r_punch_clock);
        mRadioFunction = getId(R.id.r_im);
        mMy = getId(R.id.r_setting);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction1 = fm.beginTransaction();
        if (mManagementFra != null) {
            transaction1.hide(mManagementFra);
        }
        if (mPunchClockFra != null) {
            transaction1.hide(mPunchClockFra);
        }
        if (mImFra != null) {
            transaction1.hide(mImFra);
        }
        if (mSettingFra != null) {
            transaction1.hide(mSettingFra);
        }


        if (checkedId == R.id.r_management) {
            if (mManagementFra == null) {
                mManagementFra = new ManagementFra();
                transaction1.add(R.id.fl, mManagementFra, mHomePage);
            } else {
                transaction1.show(mManagementFra);
            }

        } else if (checkedId == R.id.r_punch_clock) {
            if (mPunchClockFra == null) {
                mPunchClockFra = new PunchClockFra();

                transaction1.add(R.id.fl, mPunchClockFra, "mPunchClockFra");
            } else {
                transaction1.show(mPunchClockFra);
            }

        } else if (checkedId == R.id.r_im) {
            if (mImFra == null) {
                mImFra = new ImFra();
                transaction1.add(R.id.fl, mImFra, "mImFra");
            } else {
                transaction1.show(mImFra);
            }

        } else if (checkedId == R.id.r_setting) {
            if (mSettingFra == null) {
                mSettingFra = new SettingFra();
                transaction1.add(R.id.fl, mSettingFra, "mSettingFra");
            } else {
                transaction1.show(mSettingFra);
            }
        }
        transaction1.commit();
    }


    @Override
    public void onNetRequest() {

    }

    @Override // 点击事件
    public void onClick(View view) {

    }

    @Override
    public String assembleRequestParam() {
        return null;
    }


    @Override
    public void onHandleResponsePost(String response, String sign) {

    }

    @Override
    public void onHandleResponseGet(String response, String sign) {

    }

    @Override
    public void errorListener(VolleyError error, String sign) {

    }

    // 再按一次退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - exitTimeMillis == Constants.ZERO || currentTimeMillis - exitTimeMillis > Constants.EXIT_CONTINUED) {
                exitTimeMillis = System.currentTimeMillis();
                T.showShort(this, Constants.EXIT);
                return false;
            } else {
                finish();
                AppManager.getAppManager().AppExit(this);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}

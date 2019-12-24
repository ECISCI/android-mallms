package com.mincat.mobilemallmanager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.VolleyError;
import com.mincat.mobilemallmanager.fra.ManagementFra;
import com.mincat.mobilemallmanager.fra.HomeFra;
import com.mincat.mobilemallmanager.fra.MessageFra;
import com.mincat.mobilemallmanager.fra.MyFra;
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
    private HomeFra mHomeFra;
    private MessageFra mMessageFra;
    private MyFra mMyFra;

    private FragmentManager fm;
    private FragmentTransaction transaction;
    private RadioGroup mRadioButtonRg;
    private FragmentTransaction transaction1;
    private RadioButton mRadioManage, mRadioMain, mRadioMessage, mMy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        initView();

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            mHomeFra = new HomeFra();
            fragmentManager.beginTransaction().replace(R.id.fl, mHomeFra, mHomePage).commit();
        }
    }


    // 初始化控件
    public void initView() {

        mRadioButtonRg = getId(R.id.r_g);
        mRadioButtonRg.setOnCheckedChangeListener(this);
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();

        mManagementFra = (ManagementFra) fm.findFragmentByTag(mHomePage);
        mHomeFra = (HomeFra) fm.findFragmentByTag("mPunchClockFra");
        mMessageFra = (MessageFra) fm.findFragmentByTag("mImFra");
        mMyFra = (MyFra) fm.findFragmentByTag("mSettingFra");

        mRadioManage = getId(R.id.r_management);
        mRadioMain = getId(R.id.r_home);
        mRadioMessage = getId(R.id.r_im);
        mMy = getId(R.id.r_setting);

        initRButtonSize(mRadioMain,R.drawable.home);
        initRButtonSize(mRadioManage,R.drawable.manage);
        initRButtonSize(mRadioMessage,R.drawable.message);
        initRButtonSize(mMy,R.drawable.my);

    }

    private void initRButtonSize(RadioButton button,int drawable) {

        //定义底部标签图片大小和位置
        Drawable drawable_news = getResources().getDrawable(drawable);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news.setBounds(0, 0, 85, 85);
        //设置图片在文字的哪个方向
        button.setCompoundDrawables(null, drawable_news, null, null);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction1 = fm.beginTransaction();
        if (mManagementFra != null) {
            transaction1.hide(mManagementFra);
        }
        if (mHomeFra != null) {
            transaction1.hide(mHomeFra);
        }
        if (mMessageFra != null) {
            transaction1.hide(mMessageFra);
        }
        if (mMyFra != null) {
            transaction1.hide(mMyFra);
        }


        if (checkedId == R.id.r_management) {
            if (mManagementFra == null) {
                mManagementFra = new ManagementFra();
                transaction1.add(R.id.fl, mManagementFra, mHomePage);
            } else {
                transaction1.show(mManagementFra);
            }

        } else if (checkedId == R.id.r_home) {
            if (mHomeFra == null) {
                mHomeFra = new HomeFra();

                transaction1.add(R.id.fl, mHomeFra, "mPunchClockFra");
            } else {
                transaction1.show(mHomeFra);
            }

        } else if (checkedId == R.id.r_im) {
            if (mMessageFra == null) {
                mMessageFra = new MessageFra();
                transaction1.add(R.id.fl, mMessageFra, "mImFra");
            } else {
                transaction1.show(mMessageFra);
            }

        } else if (checkedId == R.id.r_setting) {
            if (mMyFra == null) {
                mMyFra = new MyFra();
                transaction1.add(R.id.fl, mMyFra, "mSettingFra");
            } else {
                transaction1.show(mMyFra);
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

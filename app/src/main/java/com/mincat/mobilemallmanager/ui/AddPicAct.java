package com.mincat.mobilemallmanager.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;
import com.mincat.mobilemallmanager.MinCatBaseRequest;
import com.mincat.mobilemallmanager.R;
import com.mincat.mobilemallmanager.adapter.SelectPicAdapter;
import com.mincat.mobilemallmanager.adapter.SelectPicLayoutManager;
import com.mincat.sample.custom.inter.PicSelectPopuCallBack;
import com.mincat.sample.custom.popu.CustomPicSelect;
import com.mincat.sample.utils.T;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class AddPicAct extends MinCatBaseRequest implements SelectPicAdapter.OnItemClickListener {


    private static final String TAG = AddPicAct.class.getSimpleName();

    public static final int RESPONSE_CODE_MAIN_PIC = 0x7001;

    public static final int RESPONSE_CODE_DESC_PIC = 0x7002;

    public static final String RESPONSE_MAIN_PIC = "mainPic";
    public static final String RESPONSE_DESC_PIC = "descPic";

    /**
     * 设定可以选择图片的数量
     */
    private int maxSelectNum = 3;

    private List<LocalMedia> selectList = new ArrayList<>();

    private SelectPicAdapter mPicSelectAdapter;

    private RecyclerView mRecyclerView;

    private List<LocalMedia> images;

    private AppCompatImageButton mIconCloseAct;

    private SelectPicLayoutManager mPicSelectLayoutManager;

    private List<String> picPaths = new ArrayList<>();

    private Button mBtnSave;

    private String strExtraValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_pic);

        mRecyclerView = findViewById(R.id.recycler);

        initView();
    }


    @Override
    public void initView() {

        mPicSelectLayoutManager = new SelectPicLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mPicSelectLayoutManager);


        mPicSelectAdapter = new SelectPicAdapter(this, onAddPicClickListener);
        mPicSelectAdapter.setList(selectList);
        mPicSelectAdapter.setSelectMax(maxSelectNum);
        mPicSelectAdapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(mPicSelectAdapter);

        mIconCloseAct = getId(R.id.icon_close_act);
        mIconCloseAct.setOnClickListener(this);

        mBtnSave = getId(R.id.btn_save);
        mBtnSave.setOnClickListener(this);


        Intent intent = getIntent();

        strExtraValue = intent.getStringExtra(AddGoodsAct.REQUEST_CODE_MULT_PIC_KEY);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.icon_close_act:

                this.finish();
                break;


            case R.id.btn_save:


                if (picPaths.size() == 0) {

                    T.showShort(this, "请选择要保存的图片");
                    return;

                } else {
                    Intent intent = new Intent();

                    if (strExtraValue.equals(AddGoodsAct.REQUEST_CODE_MULT_PIC_VALUE_MAIN)) {

                        setResult(intent, picPaths, RESPONSE_MAIN_PIC, RESPONSE_CODE_MAIN_PIC);

                    } else if (strExtraValue.equals(AddGoodsAct.REQUEST_CODE_MULT_PIC_VALUE_DESC)) {

                        setResult(intent, picPaths, RESPONSE_DESC_PIC, RESPONSE_CODE_DESC_PIC);
                    }

                    this.finish();
                }

                break;
        }
    }

    private void setResult(Intent intent, List<String> picPaths, String extraKey, int resultCode) {

        intent.putExtra(extraKey, (Serializable) picPaths);
        setResult(resultCode, intent);
    }

    private SelectPicAdapter.onAddPicClickListener onAddPicClickListener = new SelectPicAdapter.onAddPicClickListener() {

        @SuppressLint("CheckResult")
        @Override
        public void onAddPicClick() {
            //获取写的权限
            RxPermissions rxPermission = new RxPermissions(AddPicAct.this);
            rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) {
                            if (permission.granted) {// 用户已经同意该权限

                                showPop(); // 1.弹出选择和拍照的dialog

                                // CustomPicSelect.showAlbum(); // 2.接进入相册，但是 是有拍照得按钮的
                            } else {

                                T.showShort(AddPicAct.this, "您已拒绝权限");
                            }
                        }
                    });
        }
    };


    // 显示图片选择弹出框
    private void showPop() {

        CustomPicSelect.showPicSelectPopu(this, this.findViewById(R.id.gerenzhongxin), new PicSelectPopuCallBack() {
            @Override
            public void clickAlbum() {
                //相册
                PictureSelector.create(AddPicAct.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(maxSelectNum)
                        .minSelectNum(1)
                        .imageSpanCount(4)
                        .selectionMode(PictureConfig.MULTIPLE)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }

            @Override
            public void clickPhotograph() {
                //拍照
                PictureSelector.create(AddPicAct.this)
                        .openCamera(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == PictureConfig.CHOOSE_REQUEST) {// 图片选择结果回调

                images = PictureSelector.obtainMultipleResult(data);
                selectList.addAll(images);

                //selectList = PictureSelector.obtainMultipleResult(data);

                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                mPicSelectAdapter.setList(selectList);

                mPicSelectAdapter.notifyDataSetChanged();

                for (int p = 0; p < images.size(); p++) {

                    picPaths.add(images.get(p).getPath());
                }
            }
        }
    }


    @Override
    public void onNetRequest() {

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


    @Override
    public void onItemClick(int position, View v) {
        if (selectList.size() > 0) {
            LocalMedia media = selectList.get(position);
            String pictureType = media.getPictureType();
            int mediaType = PictureMimeType.pictureToVideo(pictureType);
            switch (mediaType) {
                case 1:
                    // 预览图片 可自定长按保存路径
                    //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                    PictureSelector.create(AddPicAct.this).externalPicturePreview(position, selectList);
                    break;
                case 2:
                    // 预览视频
                    PictureSelector.create(AddPicAct.this).externalPictureVideo(media.getPath());
                    break;
                case 3:
                    // 预览音频
                    PictureSelector.create(AddPicAct.this).externalPictureAudio(media.getPath());
                    break;
            }
        }
    }
}

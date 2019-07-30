package com.mincat.sample.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.mincat.sample.R;
import com.mincat.sample.utils.SystemUtils;


/**
 * @author Gin
 * @描述 自定义警告dialog
 */
public class CustomWarnDialog extends Dialog implements OnClickListener {

    private Context context;
    private Button cancel;
    private Button btn_go;
    private OnDialogClickListener onDialogClickListener;
    private TextView title;
    private TextView main_title;
    private TextView message;

    public CustomWarnDialog(Context context, String title, String message, String cancel, String goString) {
        super(context, R.style.public_dialog);

        this.context = context;
        dialogInit();
        this.main_title.setText(Html.fromHtml(title));
        if (!message.equals("")) {
            this.message.setText(Html.fromHtml(message));
        } else {
            this.message.setVisibility(View.GONE);
        }
        if (goString.equals("")) {
            this.cancel.setBackgroundResource(R.drawable.btn_dialog_center);
            this.btn_go.setVisibility(View.GONE);
        } else {
            this.btn_go.setText(goString);
        }
        this.cancel.setText(cancel);
    }

    public CustomWarnDialog(Context context, String mainTitle, String title, String message, String cancle, String goString) {
        super(context, R.style.public_dialog);
        this.context = context;
        dialogInit();
        this.main_title.setText(mainTitle);
        this.title.setVisibility(View.GONE);
        if (!message.equals("")) {
            this.message.setText(message);
        } else {
            this.message.setVisibility(View.GONE);
        }
        this.cancel.setText(cancle);
        if (goString.equals("")) {
            this.btn_go.setVisibility(View.GONE);
        } else {
            this.btn_go.setText(goString);
        }
    }

    public CustomWarnDialog(Context context) {
        super(context);
        this.context = context;
        dialogInit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void dialogInit() {
        setContentView(R.layout.dialog_warn);
        if (context instanceof OnDialogClickListener) {
            onDialogClickListener = (OnDialogClickListener) context;
        }
        setCanceledOnTouchOutside(false);
        init();
        addListener();
    }

    private void setDialogWidthAndHeight() {
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = SystemUtils.getDeviceWidth(context) - SystemUtils.dip2px(context, 40);
        lp.height = LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }


    protected void init() {
        title = findViewById(R.id.alertTitle);
        main_title = findViewById(R.id.main_title);
        message = findViewById(R.id.message);
        cancel = findViewById(R.id.btn_left);
        btn_go = findViewById(R.id.btn_right);
        btn_go.setVisibility(View.VISIBLE);
    }

    protected void addListener() {
        cancel.setOnClickListener(this);
        btn_go.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        cancel();
        int id = v.getId();
        if (id == R.id.btn_left) {
            if (onDialogClickListener != null) {
                onDialogClickListener.dialogLeft();
            }
        } else if (id == R.id.btn_right) {
            if (onDialogClickListener != null) {
                onDialogClickListener.dialogRight();
            }
        }
    }

    public interface OnDialogClickListener {
        void dialogLeft();

        void dialogRight();
    }

    public OnDialogClickListener getOnDialogClickListener() {
        return onDialogClickListener;
    }

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }


}

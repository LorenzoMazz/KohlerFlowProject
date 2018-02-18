package project.kohler.com.kholer_project.CC;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import project.kohler.com.kholer_project.R;


public class CustomAlertDialog extends Dialog {

    private Activity activity;
    private TextView txt_title, txt_desc;
    private Button btn_left, btn_right;
    private View space_btn;

    public CustomAlertDialog(Context context) {
        super(context);
        initialize();
    }

    public CustomAlertDialog(Context context, int theme) {
        super(context, theme);
        initialize();
    }

    protected CustomAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initialize();
    }

    public void initialize() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.custom_alert_dialog);

        txt_title = findViewById(R.id.custom_alert_dialog_txt_title);
        txt_desc = findViewById(R.id.custom_alert_dialog_txt_description);
        btn_left = findViewById(R.id.custom_alert_dialog_txt_btn_left);
        btn_right = findViewById(R.id.custom_alert_dialog_txt_btn_right);
        space_btn = findViewById(R.id.custom_alert_dialog_btn_space);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout((int) (C_F.getScreenWidth(getContext())/1.2), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public void setData(String title, String description, String btnLeft, String btnRight,
                        View.OnClickListener clickListenerLeft,
                        View.OnClickListener clickListenerRight) {

        txt_title.setText(title);
        txt_desc.setText(description);
        btn_left.setOnClickListener(clickListenerLeft);
        btn_left.setText(btnLeft);
        if(clickListenerRight == null){
            btn_right.setVisibility(View.GONE);
            space_btn.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((C_F.getScreenWidth(getContext()) / 5), ViewGroup.LayoutParams.WRAP_CONTENT);
            btn_left.setLayoutParams(params);
        }
        else{
            btn_right.setText(btnRight);
            btn_right.setOnClickListener(clickListenerRight);
        }
    }

    @Override
    public void show() {
        super.show();
    }
}

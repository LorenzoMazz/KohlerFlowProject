package project.kohler.com.kholer_project.Frag_dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.C_F;
import project.kohler.com.kholer_project.Data.Note;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.R;


/**
 * Created by Lorenzo Mazzoni on 13/12/2017.
 */

public class Frag_Note_Detail extends DialogFragment {

    private TextView txt_msg;
    private ImageView img_close;

    private Note message;
    private User user;


    public static Frag_Note_Detail newInstance(Note message) {
        Frag_Note_Detail f = new Frag_Note_Detail();
        Bundle args = new Bundle();
        args.putSerializable("message", message);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = (Note) getArguments().getSerializable("message");
        user = ((App) getActivity().getApplicationContext()).getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.frag_pop_up_message, container, false);
        txt_msg = v.findViewById(R.id.popup_message_txt);
        img_close = v.findViewById(R.id.popup_message_img_close);

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        txt_msg.setText(C_F.fromHtml(message.getMessage()));

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width;
            int height = (int) (C_F.getScreenHeight(getActivity()) / 1.3);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setWindowAnimations(R.style.dialog_animation_fade);
            width = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

}

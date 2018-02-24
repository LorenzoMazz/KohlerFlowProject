package project.kohler.com.kholer_project.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import project.kohler.com.kholer_project.Activities.Act_main_project;
import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.List_Adapter.ActiveProjectAdapter;
import project.kohler.com.kholer_project.R;
import project.kohler.com.kholer_project.RecycleViewListeners.ClickListenerR;
import project.kohler.com.kholer_project.RecycleViewListeners.RecycleViewTouchListener;

import static android.view.View.GONE;

/**
 * Created by Lorenzo on 15/02/2018.
 */

public class Frag_plat extends Fragment implements View.OnClickListener{

    private Activity activity;
    private User user;

    private LinearLayout linearLayoutCheckDesign;
    private LinearLayout linearLayoutCheckCali;
    private LinearLayout linearLayoutCheckApliT1;
    private LinearLayout linearLayoutCheckSper;
    private LinearLayout linearLayoutCheckLotT1;
    private LinearLayout linearLayoutCheckQualiT1;

    private LinearLayout linearLayoutCheckApliT2;
    private LinearLayout linearLayoutCheckQualiT2;

    private LinearLayout linearLayoutCheckQualiT3;
    private LinearLayout linearLayoutCheckLotT3;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        user = ((App)activity.getApplicationContext()).getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_plat, container, false);

        linearLayoutCheckDesign = v.findViewById(R.id.checkDesign);
        linearLayoutCheckCali = v.findViewById(R.id.checkCali);
        linearLayoutCheckApliT1 = v.findViewById(R.id.checkApliT1);
        linearLayoutCheckSper = v.findViewById(R.id.checkSper);
        linearLayoutCheckLotT1 = v.findViewById(R.id.checkLotT1);
        linearLayoutCheckQualiT1 = v.findViewById(R.id.checkQualiT1);

        linearLayoutCheckApliT2 = v.findViewById(R.id.checkApliT2);
        linearLayoutCheckQualiT2 = v.findViewById(R.id.checkQualiT2);

        linearLayoutCheckQualiT3 = v.findViewById(R.id.checkQualiT3);
        linearLayoutCheckLotT3 = v.findViewById(R.id.checkLotT3);

        linearLayoutCheckDesign.setOnClickListener(this);
        linearLayoutCheckCali.setOnClickListener(this);
        linearLayoutCheckApliT1.setOnClickListener(this);
        linearLayoutCheckSper.setOnClickListener(this);
        linearLayoutCheckLotT1.setOnClickListener(this);
        linearLayoutCheckQualiT1.setOnClickListener(this);

        linearLayoutCheckApliT2.setOnClickListener(this);
        linearLayoutCheckQualiT2.setOnClickListener(this);

        linearLayoutCheckQualiT3.setOnClickListener(this);
        linearLayoutCheckLotT3.setOnClickListener(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    private void chanegAlpha(LinearLayout linearLayout) {
        if(linearLayout.getAlpha() == (float)0.3){
            linearLayout.setAlpha((float)1);
        }
        else if(linearLayout.getAlpha() == (float)1){
            linearLayout.setAlpha((float)0.3);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkDesign:
                chanegAlpha(linearLayoutCheckDesign);
                break;
            case R.id.checkCali:
                chanegAlpha(linearLayoutCheckCali);
                break;
            case R.id.checkApliT1:
                chanegAlpha(linearLayoutCheckApliT1);
                break;
            case R.id.checkSper:
                chanegAlpha(linearLayoutCheckSper);
                break;
            case R.id.checkLotT1:
                chanegAlpha(linearLayoutCheckLotT1);
                break;
            case R.id.checkQualiT1:
                chanegAlpha(linearLayoutCheckQualiT1);
                break;
            case R.id.checkApliT2:
                chanegAlpha(linearLayoutCheckApliT2);
                break;
            case R.id.checkQualiT2:
                chanegAlpha(linearLayoutCheckQualiT2);
                break;
            case R.id.checkQualiT3:
                chanegAlpha(linearLayoutCheckQualiT3);
                break;
            case R.id.checkLotT3:
                chanegAlpha(linearLayoutCheckLotT3);
                break;
        }
    }
}

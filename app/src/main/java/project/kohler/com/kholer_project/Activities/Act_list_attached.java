package project.kohler.com.kholer_project.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.C_F_APP;
import project.kohler.com.kholer_project.Data.Allegato;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.List_Adapter.RecycleViewListAllegatiAdapter;
import project.kohler.com.kholer_project.List_Adapter.RecycleViewListDepartmentAdapter;
import project.kohler.com.kholer_project.R;
import project.kohler.com.kholer_project.RecycleViewListeners.ClickListenerR;
import project.kohler.com.kholer_project.RecycleViewListeners.RecycleViewTouchListener;

public class Act_list_attached extends AppCompatActivity {

    private RecycleViewListAllegatiAdapter recycleViewDipartimentiAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private User user;
    private Activity activity;
    private TextView title;
    private ImageView back;
    private String titleS;
    private ArrayList<Allegato> allegati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_attached);
        activity = this;
        user = ((App) activity.getApplicationContext()).getUser();
        titleS = getIntent().getStringExtra("title");
        allegati =  (ArrayList<Allegato>)(getIntent().getSerializableExtra("allegati"));

        recyclerView = findViewById(R.id.recycleViewAttached);
        swipeRefreshLayout = findViewById(R.id.swipeLayoutAttached);
        title = findViewById(R.id.textView_title_toolbar);
        back = findViewById(R.id.btn_back);

        title.setText("Sezione Allegati " + titleS);


        mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        user = ((App) activity.getApplicationContext()).getUser();
        setUpAdapter();

        recyclerView.addOnItemTouchListener(new RecycleViewTouchListener(activity, recyclerView, new ClickListenerR.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                C_F_APP.showAlertDialogForError(activity, getString(R.string.showAttachedNotImplemented) +
                        "\nImpossibile visualizzare " + allegati.get(position).getName());
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.dark_blue));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setUpAdapter();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void setUpAdapter() {

        recycleViewDipartimentiAdapter = new RecycleViewListAllegatiAdapter(activity,allegati);
        recyclerView.setAdapter(recycleViewDipartimentiAdapter);
        swipeRefreshLayout.setRefreshing(false);
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
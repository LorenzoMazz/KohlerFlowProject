package project.kohler.com.kholer_project.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import project.kohler.com.kholer_project.Activities.Act_list_attached;
import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.C_F_APP;
import project.kohler.com.kholer_project.Data.Allegato;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.List_Adapter.RecycleViewListDepartmentAdapter;
import project.kohler.com.kholer_project.R;
import project.kohler.com.kholer_project.RecycleViewListeners.ClickListenerR;
import project.kohler.com.kholer_project.RecycleViewListeners.RecycleViewTouchListener;

/**
 * Created by Lorenzo on 16/02/2018.
 */

public class Frag_Attached extends Fragment {

    private Activity activity;
    private LinearLayout emptyListImage;
    private RecycleViewListDepartmentAdapter recycleViewDipartimentiAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button btnAddAttached;

    private User user;
    private Project project;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = getActivity();
        user = ((App)activity.getApplicationContext()).getUser();
        project = ((App) activity.getApplicationContext()).getCurrenteProject();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_attached, container, false);
        recyclerView = v.findViewById(R.id.recycleViewMessages);
        emptyListImage = v.findViewById(R.id.ViewEmptyListMessages);
        swipeRefreshLayout = v.findViewById(R.id.swipeLayoutMessages);
        btnAddAttached = v.findViewById(R.id.button_add_attached);


        //mLayoutManager = new LinearLayoutManager(activity);
        GridLayoutManager layoutManager = new GridLayoutManager(activity, 3);
        recyclerView.setLayoutManager(layoutManager);

        user = ((App) activity.getApplicationContext()).getUser();
        setUpAdapter();

        recyclerView.addOnItemTouchListener(new RecycleViewTouchListener(activity, recyclerView, new ClickListenerR.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(activity, Act_list_attached.class);
                i.putExtra("title", C_F_APP.getAllDipartimenti().get(position));
                i.putExtra("allegati", project.getAttachedFromDepartment(project.getDipartimenti().get(position).getNome()));
                startActivity(i);
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

        btnAddAttached.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C_F_APP.showAlertDialogForError(activity, getString(R.string.addNewAttachedNotImplemented));
            }
        });

        return v;
    }

    private void setUpAdapter() {

        recycleViewDipartimentiAdapter = new RecycleViewListDepartmentAdapter(getActivity(), project.getDipartimenti());
        recyclerView.setAdapter(recycleViewDipartimentiAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void showEmptyPage() {
        emptyListImage.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void hideEmptyPage() {
        emptyListImage.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
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
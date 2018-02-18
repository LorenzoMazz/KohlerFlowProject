package project.kohler.com.kholer_project.Fragments;

import android.app.Activity;
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

import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.Data.Chat;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.List_Adapter.RecycleViewMessagesAdapter;
import project.kohler.com.kholer_project.R;

/**
 * Created by Lorenzo on 16/02/2018.
 */

public class Frag_Messages extends Fragment {

    private Activity activity;
    private LinearLayout emptyListImage;
    private RecycleViewMessagesAdapter recycleViewMessagesAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    private User user;
    private Project project;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = getActivity();
        user = ((App) activity.getApplicationContext()).getUser();
        project = ((App) activity.getApplicationContext()).getCurrenteProject();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_messages, container, false);
        recyclerView = v.findViewById(R.id.recycleViewMessages);
        emptyListImage = v.findViewById(R.id.ViewEmptyListMessages);
        swipeRefreshLayout = v.findViewById(R.id.swipeLayoutMessages);


        mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);

        user = ((App) activity.getApplicationContext()).getUser();


        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.dark_blue));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDataMessagesCustom();
            }
        });

        return v;
    }

    private void setUpAdapter(ArrayList<Chat> messages) {
        if (messages.size() == 0) {
            showEmptyPage();
        } else {
            hideEmptyPage();
            recycleViewMessagesAdapter = new RecycleViewMessagesAdapter(getActivity(), messages);
            recyclerView.setAdapter(recycleViewMessagesAdapter);
        }
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


    private void loadDataMessagesCustom() {

        setUpAdapter(project.getListaChatProgetto());
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataMessagesCustom();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}

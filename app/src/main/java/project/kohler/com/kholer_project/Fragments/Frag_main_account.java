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
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import project.kohler.com.kholer_project.Activities.Act_compilation_commercial;
import project.kohler.com.kholer_project.Activities.Act_main_project;
import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.CONF;
import project.kohler.com.kholer_project.CC.C_F_APP;
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

public class Frag_main_account extends Fragment {

    private Activity activity;

    private Button addNewProject;
    private LinearLayout bottom;
    private RecyclerView activeProjectList;
    private LinearLayoutManager linearLayoutManager;

    private LinearLayout empty_list_actie_project;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<Project> activeProjects;
    private User user;


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
        View v = inflater.inflate(R.layout.frag_main_account, container, false);


        addNewProject = v.findViewById(R.id.btn_add_new_project);
        activeProjectList = v.findViewById(R.id.list_active_project);
        empty_list_actie_project = v.findViewById(R.id.empty_list_actie_project);
        swipeRefreshLayout = v.findViewById(R.id.swipe_layout_active_project);
        bottom = v.findViewById(R.id.bottom);

        linearLayoutManager = new LinearLayoutManager(activity);
        activeProjectList.setLayoutManager(linearLayoutManager);

        loadPage();

        /*activeProjectList.addOnItemTouchListener(new RecycleViewTouchListener(activity, activeProjectList, new ClickListenerR.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(activity, Act_main_project.class);
                ((App) activity.getApplicationContext()).setCurrenteProject(activeProjects.get(position));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));*/

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.dark_blue));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadActiveProject();
                setUpAdapter();
            }
        });

        addNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent complilation = new Intent(activity, Act_compilation_commercial.class);
                complilation.putExtra("fromProject", false);
                startActivity(complilation);
            }
        });

        return v;
    }

    private void loadPage() {
        if(!user.getDipartimento().equals(CONF.commerciale)){
            bottom.setVisibility(GONE);
        }
        loadActiveProject();
        if (activeProjects != null) {
            setUpAdapter();
        }
        else{
            showEmptyPage();
        }
    }

    private void showEmptyPage() {
        empty_list_actie_project.setVisibility(View.VISIBLE);
        activeProjectList.setVisibility(GONE);
    }

    private void hideEmptyPage() {
        empty_list_actie_project.setVisibility(View.GONE);
        activeProjectList.setVisibility(View.VISIBLE);
    }

    private void setUpAdapter() {
        if (activeProjects.size() == 0) {
            showEmptyPage();
        } else {
            hideEmptyPage();
            ActiveProjectAdapter activeProjectAdapter = new ActiveProjectAdapter(activity,this, activeProjects);
            activeProjectList.setAdapter(activeProjectAdapter);
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    private void loadActiveProject() {
        activeProjects = C_F_APP.getVisibleProject(activity, user.getTemp(), user.getDipartimento());
    }

    public void loadProject(int position){
        Intent i = new Intent(activity, Act_main_project.class);
        ((App) activity.getApplicationContext()).setCurrenteProject(activeProjects.get(position));
        startActivity(i);
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

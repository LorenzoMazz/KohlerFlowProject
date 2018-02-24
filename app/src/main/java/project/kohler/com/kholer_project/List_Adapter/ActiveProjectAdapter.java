package project.kohler.com.kholer_project.List_Adapter;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;

import at.grabner.circleprogress.CircleProgressView;
import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.CONF;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.Fragments.Frag_main_account;
import project.kohler.com.kholer_project.Fragments.Frag_old_projects;
import project.kohler.com.kholer_project.R;

/**
 * Created by Lorenzo Mazzoni on 27/11/2017.
 */

public class ActiveProjectAdapter extends RecyclerView.Adapter<ActiveProjectAdapter.ViewHolder> {


    private Activity activity;
    private ArrayList<Project> activeProject;
    private User user;
    private Frag_old_projects frag_old_projects;
    private Frag_main_account frag_main_account;
    private Fragment fragment;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView progress;
        TextView date;
        CircleProgressView progressView;
        ImageView temp;
        ImageView iconProject;
        ImageView iconAlert;
        LinearLayout linearLayoutProject;
        LinearLayout linearLayoutEnableDisable;
        TextView enableOrDisableProject;
        Switch switchProjectActive;
        ImageView imageViewLock;


        public ViewHolder(View v) {
            super(v);


            title = v.findViewById(R.id.text_active_project);
            date = v.findViewById(R.id.text_active_project4);
            progress = v.findViewById(R.id.text_percet_state);
            progressView = v.findViewById(R.id.circleProgress_state_project);
            temp = v.findViewById(R.id.image_icon_temp);
            iconProject = v.findViewById(R.id.image_icon_project);
            iconAlert = v.findViewById(R.id.imageIconAlert);
            linearLayoutProject = v.findViewById(R.id.layOutProject);
            linearLayoutEnableDisable = v.findViewById(R.id.layOutEnableDisable);
            enableOrDisableProject = v.findViewById(R.id.textView_Attiva_o_Disattiva_Progetto);
            switchProjectActive = v.findViewById(R.id.switchAttivazione_O_disattivazione_rpogetto);
            imageViewLock = v.findViewById(R.id.imageViewLock);

        }
    }

    public ActiveProjectAdapter(Activity activity, Fragment fragment, ArrayList<Project> activeProject) {
        this.activity = activity;
        this.activeProject = activeProject;
        this.user = ((App) activity.getApplicationContext()).getUser();
        this.fragment = fragment;
    }

    public ActiveProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_active_project, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(final ActiveProjectAdapter.ViewHolder holder, final int position) {

        final Project active_project = activeProject.get(position);
        holder.title.setText(active_project.getName());
        holder.date.setText(active_project.getDateStart());

        holder.progress.setText(active_project.getProgress()+"%");
        holder.progressView.setValueAnimated(active_project.getProgress(), 300);

        if(active_project.isRejected() && (active_project.getActualTemp() == user.getTemp())){
            holder.iconAlert.setVisibility(View.VISIBLE);
        }
        else {
            holder.iconAlert.setVisibility(View.GONE);
        }

        if(!user.getDipartimento().equals(CONF.piattaforma)){
            holder.linearLayoutEnableDisable.setVisibility(View.GONE);
        }
        else{
            if(active_project.isActive()){
                holder.switchProjectActive.setChecked(true);
                holder.enableOrDisableProject.setText(activity.getString(R.string.disableProject));
            }
            else{
                holder.switchProjectActive.setChecked(false);
                holder.enableOrDisableProject.setText(activity.getString(R.string.activeProject));
            }
        }

        changeProjectState(active_project, holder);

        holder.switchProjectActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                ((App) activity.getApplicationContext()).setProjectActive(isChecked, active_project.getName());
                active_project.setActive(isChecked);
                changeProjectState(active_project, holder);

                if(isChecked){
                    holder.enableOrDisableProject.setText(activity.getString(R.string.disableProject));
                }
                else{
                    holder.enableOrDisableProject.setText(activity.getString(R.string.activeProject));
                }
            }
        });

        holder.switchProjectActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.linearLayoutProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (active_project.isActive()) {
                    if (fragment instanceof Frag_old_projects) {
                        frag_old_projects = (Frag_old_projects) fragment;
                        frag_old_projects.loadProject(position);

                    } else if (fragment instanceof Frag_main_account) {
                        frag_main_account = (Frag_main_account) fragment;
                        frag_main_account.loadProject(position);
                    }
                }
            }
        });

        switch (active_project.getActualTemp()){
            case CONF.T1:
                if(Build.VERSION.SDK_INT>=21) {
                    holder.temp.setImageDrawable(activity.getDrawable(R.drawable.ic_shortcut_t1));
                }
                else {
                    holder.temp.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_shortcut_t1));
                }
                break;
            case  CONF.T2:
                if(Build.VERSION.SDK_INT>=21) {
                    holder.temp.setImageDrawable(activity.getDrawable(R.drawable.ic_shortcut_t2));
                }
                else {
                    holder.temp.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_shortcut_t2));
                }
                break;
            case  CONF.T3:
                if(Build.VERSION.SDK_INT>=21) {
                    holder.temp.setImageDrawable(activity.getDrawable(R.drawable.ic_shortcut_t3));
                }
                else {
                    holder.temp.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_shortcut_t3));
                }
                break;
            case CONF.T_Commerciale:
                if(active_project.isRejected() && (active_project.getActualTemp() == user.getTemp())){
                    if (Build.VERSION.SDK_INT >= 21) {
                        holder.temp.setImageDrawable(activity.getDrawable(R.drawable.ic_temp_c_red));
                    } else {
                        holder.temp.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_temp_c_red));
                    }
                }
                else {
                    if (Build.VERSION.SDK_INT >= 21) {
                        holder.temp.setImageDrawable(activity.getDrawable(R.drawable.ic_temp_c_blue));
                    } else {
                        holder.temp.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_temp_c_blue));
                    }
                }
                break;
            case CONF.T_Prevendita:
                if(active_project.isRejected() && (active_project.getActualTemp() == user.getTemp())){
                    if (Build.VERSION.SDK_INT >= 21) {
                        holder.temp.setImageDrawable(activity.getDrawable(R.drawable.ic_temp_prev_red));
                    } else {
                        holder.temp.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_temp_prev_red));
                    }
                }
                else {
                    if (Build.VERSION.SDK_INT >= 21) {
                        holder.temp.setImageDrawable(activity.getDrawable(R.drawable.ic_temp_prev_blue));
                    } else {
                        holder.temp.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_temp_prev_blue));
                    }
                }
                break;
            case CONF.T_Piattaforma:
                if(active_project.isRejected() && (active_project.getActualTemp() == user.getTemp())){
                    if (Build.VERSION.SDK_INT >= 21) {
                        holder.temp.setImageDrawable(activity.getDrawable(R.drawable.ic_temp_piat_red));
                    } else {
                        holder.temp.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_temp_piat_red));
                    }
                }
                else {
                    if (Build.VERSION.SDK_INT >= 21) {
                        holder.temp.setImageDrawable(activity.getDrawable(R.drawable.ic_temp_piat_blue));
                    } else {
                        holder.temp.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_temp_piat_blue));
                    }
                }
                break;
        }

    }

    private void changeProjectState(Project active_project, ViewHolder holder) {
        if(active_project.isActive()){
            enableProject(holder);
        }
        else{
            disableProject(holder);
        }
    }

    private void disableProject(ViewHolder holder) {
        holder.imageViewLock.setVisibility(View.VISIBLE);
        holder.linearLayoutProject.setClickable(false);
        holder.linearLayoutProject.setFocusable(false);
        holder.linearLayoutProject.setAlpha((float)0.3);
    }

    private void enableProject(ViewHolder holder) {
        holder.imageViewLock.setVisibility(View.GONE);
        holder.linearLayoutProject.setClickable(true);
        holder.linearLayoutProject.setFocusable(true);
        holder.linearLayoutProject.setAlpha((float)1);
    }


    @Override
    public int getItemCount() {
        return activeProject.size();
    }

}

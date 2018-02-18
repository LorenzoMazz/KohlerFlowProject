package project.kohler.com.kholer_project.List_Adapter;

import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import at.grabner.circleprogress.CircleProgressView;
import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.CONF;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.R;

/**
 * Created by Lorenzo Mazzoni on 27/11/2017.
 */

public class ActiveProjectAdapter extends RecyclerView.Adapter<ActiveProjectAdapter.ViewHolder> {


    private Activity activity;
    private ArrayList<Project> activeProject;
    private User user;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView progress;
        TextView date;
        CircleProgressView progressView;
        ImageView temp;
        ImageView iconProject;
        ImageView iconAlert;


        public ViewHolder(View v) {
            super(v);


            title = v.findViewById(R.id.text_active_project);
            date = v.findViewById(R.id.text_active_project4);
            progress = v.findViewById(R.id.text_percet_state);
            progressView = v.findViewById(R.id.circleProgress_state_project);
            temp = v.findViewById(R.id.image_icon_temp);
            iconProject = v.findViewById(R.id.image_icon_project);
            iconAlert = v.findViewById(R.id.imageIconAlert);

        }
    }

    public ActiveProjectAdapter(Activity activity, ArrayList<Project> activeProject) {
        this.activity = activity;
        this.activeProject = activeProject;
        this.user = ((App) activity.getApplicationContext()).getUser();

    }

    public ActiveProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_active_project, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(ActiveProjectAdapter.ViewHolder holder, final int position) {

        Project active_project = activeProject.get(position);
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




    @Override
    public int getItemCount() {
        return activeProject.size();
    }

}

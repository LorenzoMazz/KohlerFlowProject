package project.kohler.com.kholer_project.List_Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.Data.Dipartimento;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.R;


/**
 * Created by Lorenzo Mazzoni on 27/11/2017.
 */

public class RecycleViewListDepartmentAdapter extends RecyclerView.Adapter<RecycleViewListDepartmentAdapter.ViewHolder> {

    private Activity activity;
    private ArrayList<Dipartimento> dipartimenti;

    private User user;
    private Project project;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDate;
        ImageView revision;
        ImageView iconDip;

        public ViewHolder(View v) {
            super(v);

            textViewDate = v.findViewById(R.id.text_document_title);
            revision = v.findViewById(R.id.imageViewRevision);
            iconDip = v.findViewById(R.id.imageViewIconDepartment);
        }
    }

    public RecycleViewListDepartmentAdapter(Activity activity, ArrayList<Dipartimento> dipartimenti) {
        this.activity = activity;
        this.dipartimenti = dipartimenti;
        user = ((App) this.activity.getApplicationContext()).getUser();
        project = ((App) this.activity.getApplicationContext()).getCurrenteProject();

    }

    public RecycleViewListDepartmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_document, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(RecycleViewListDepartmentAdapter.ViewHolder holder, final int position) {

        holder.iconDip.setImageResource(dipartimenti.get(position).getIcon());

        if(user.getDipartimento().equals(dipartimenti.get(position))){
            holder.textViewDate.setText("Visualizza i tuoi allegati");
        }
        else {
            holder.textViewDate.setText("Allegati " + dipartimenti.get(position).getNome());
        }
        if(project.isRevisionedFromDepartment(dipartimenti.get(position).getNome())){
            holder.revision.setVisibility(View.GONE);
        }
        else{
            holder.revision.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return  dipartimenti.size();
    }
}

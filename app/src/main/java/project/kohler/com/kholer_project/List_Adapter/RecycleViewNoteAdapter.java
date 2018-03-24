package project.kohler.com.kholer_project.List_Adapter;

import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.CONF;
import project.kohler.com.kholer_project.CC.C_F;
import project.kohler.com.kholer_project.Data.Note;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.Frag_dialog.Frag_Note_Detail;
import project.kohler.com.kholer_project.R;


/**
 * Created by Lorenzo Mazzoni on 27/11/2017.
 */

public class RecycleViewNoteAdapter extends RecyclerView.Adapter<RecycleViewNoteAdapter.ViewHolder> {

    private FragmentActivity myActivity;
    private ArrayList<Note> notes;

    private User user;
    private Project project;

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView textViewDescription;
        TextView textViewSender;
        ImageView imageViewWarning;
        Button buttonSeeMore;



        public ViewHolder(View v) {
            super(v);


            textViewDescription = v.findViewById(R.id.textViewTextMessage);
            textViewSender = v.findViewById(R.id.textViewSender);
            buttonSeeMore = v.findViewById(R.id.buttonSeeMore);
            imageViewWarning = v.findViewById(R.id.imageWarning);



        }
    }

    public RecycleViewNoteAdapter(FragmentActivity mActivity, ArrayList<Note> notes) {
        myActivity = mActivity;
        this.notes = notes;
        user = ((App) myActivity.getApplicationContext()).getUser();
        project = ((App) myActivity.getApplicationContext()).getCurrenteProject();

    }

    public RecycleViewNoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_note, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(RecycleViewNoteAdapter.ViewHolder holder, final int position) {
        final Note note = notes.get(position);
        holder.textViewDescription.setText(C_F.fromHtml(note.getMessage()).toString());
        holder.textViewSender.setText(note.getSender());
        if (note.isRead()) {
            holder.textViewDescription.setAlpha(0.3f);
            holder.textViewSender.setAlpha(0.3f);
        } else {
            holder.textViewDescription.setAlpha(1f);
            holder.textViewSender.setAlpha(1f);
        }

        if(note.isWarning() && (project.getActualTemp() == user.getTemp())){
            holder.imageViewWarning.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= 21) {
                holder.buttonSeeMore.setBackground(myActivity.getDrawable(R.drawable.btn_log_red));
            } else {
                holder.buttonSeeMore.setBackground(myActivity.getResources().getDrawable(R.drawable.btn_log_red));
            }
        }
        else  if(note.isNotificationG2() && ((user.getDipartimento().getNome().equals(CONF.applicazioneT2)) || (user.getDipartimento().getNome().equals(CONF.piattaforma)))){
            holder.imageViewWarning.setVisibility(View.GONE);
                if (Build.VERSION.SDK_INT >= 21) {
                    holder.buttonSeeMore.setBackground(myActivity.getDrawable(R.drawable.btn_log_green));
                } else {
                    holder.buttonSeeMore.setBackground(myActivity.getResources().getDrawable(R.drawable.btn_log_green));
                }
            }
        else{
            holder.imageViewWarning.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= 21) {
                holder.buttonSeeMore.setBackground(myActivity.getDrawable(R.drawable.btn_log_blue));
            } else {
                holder.buttonSeeMore.setBackground(myActivity.getResources().getDrawable(R.drawable.btn_log_blue));
            }
        }






        holder.buttonSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frag_Note_Detail dialog = Frag_Note_Detail.newInstance(note);
                dialog.show(myActivity.getSupportFragmentManager(), null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}

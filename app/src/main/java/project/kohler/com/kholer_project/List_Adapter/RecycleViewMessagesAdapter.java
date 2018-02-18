package project.kohler.com.kholer_project.List_Adapter;

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
import project.kohler.com.kholer_project.CC.C_F;
import project.kohler.com.kholer_project.Data.Chat;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.Frag_dialog.Frag_Message_Detail;
import project.kohler.com.kholer_project.R;


/**
 * Created by Lorenzo Mazzoni on 27/11/2017.
 */

public class RecycleViewMessagesAdapter extends RecyclerView.Adapter<RecycleViewMessagesAdapter.ViewHolder> {

    private FragmentActivity myActivity;
    private ArrayList<Chat> messages;

    User user;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDate;
        TextView textViewDescription;
        TextView textViewSender;
        Button buttonSeeMore;
        ImageView imageViewIcon;


        public ViewHolder(View v) {
            super(v);


            textViewDate = v.findViewById(R.id.textViewMessageDate);
            textViewDescription = v.findViewById(R.id.textViewTextMessage);
            textViewSender = v.findViewById(R.id.textViewSender);
            buttonSeeMore = v.findViewById(R.id.buttonSeeMore);
            imageViewIcon = v.findViewById(R.id.imageViewMessageIcon);


        }
    }

    public RecycleViewMessagesAdapter(FragmentActivity mActivity, ArrayList<Chat> messages) {
        myActivity = mActivity;
        this.messages = messages;
        user = ((App) myActivity.getApplicationContext()).getUser();

    }

    public RecycleViewMessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_message, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(RecycleViewMessagesAdapter.ViewHolder holder, final int position) {
        final Chat message = messages.get(position);
        holder.textViewDate.setText(message.getLatsUpdateDate());
        holder.textViewDescription.setText(C_F.fromHtml(message.getLastMessage()).toString());
        holder.textViewSender.setText(message.getDipartimento());
        if (message.isRead()) {
            holder.textViewDescription.setAlpha(0.3f);
            holder.textViewSender.setAlpha(0.3f);
            holder.textViewDate.setAlpha(0.3f);
            holder.imageViewIcon.setAlpha(0.3f);
        } else {
            holder.textViewDescription.setAlpha(1f);
            holder.textViewSender.setAlpha(1f);
            holder.textViewDate.setAlpha(1f);
            holder.imageViewIcon.setAlpha(1f);
        }

        holder.buttonSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frag_Message_Detail dialog = Frag_Message_Detail.newInstance(message);
                dialog.show(myActivity.getSupportFragmentManager(), null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}

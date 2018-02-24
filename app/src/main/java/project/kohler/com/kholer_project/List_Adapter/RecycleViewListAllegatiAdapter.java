package project.kohler.com.kholer_project.List_Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.Data.Allegato;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.R;


/**
 * Created by Lorenzo Mazzoni on 27/11/2017.
 */

public class RecycleViewListAllegatiAdapter extends RecyclerView.Adapter<RecycleViewListAllegatiAdapter.ViewHolder> {

    private Activity myActivity;
    private ArrayList<Allegato> allegati;


    User user;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDocumentTitle;
        TextView textViewLastUpdate;

        public ViewHolder(View v) {
            super(v);

            textViewDocumentTitle = v.findViewById(R.id.text_document_title);
            textViewLastUpdate = v.findViewById(R.id.textViewUlltimoAggiornamento);
        }
    }

    public RecycleViewListAllegatiAdapter(Activity mActivity, ArrayList<Allegato> allegati) {
        myActivity = mActivity;
        this.allegati = allegati;
        user = ((App) myActivity.getApplicationContext()).getUser();

    }

    public RecycleViewListAllegatiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_attached, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(RecycleViewListAllegatiAdapter.ViewHolder holder, final int position) {

            holder.textViewDocumentTitle.setText(allegati.get(position).getName());
            holder.textViewLastUpdate.setText(allegati.get(position).getUltimoAggiornamento() + allegati.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return  allegati.size();
    }
}

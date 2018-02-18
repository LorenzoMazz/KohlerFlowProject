package project.kohler.com.kholer_project.RecycleViewListeners;

import android.view.View;

/**
 * Created by Lorenzo Gaming on 25/08/2017.
 */

public class ClickListenerR {

    public interface ClickListener {
        public void onClick(View view, int position);
        public void onLongClick(View view,int position);
    }
}

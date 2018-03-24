package project.kohler.com.kholer_project.CC;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;

import project.kohler.com.kholer_project.Data.Dipartimento;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.R;

/**
 * Created by Lorenzo on 13/02/2018.
 */

public class C_F_APP {

    public static void showAlertDialogForError(Activity activity, String message) {
        final CustomAlertDialog dialog = new CustomAlertDialog(activity);
        dialog.setActivity(activity);
        View.OnClickListener listenerLeft = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        };

        dialog.setData(activity.getString(R.string.warning), message,
                activity.getString(R.string.ok), null,
                listenerLeft, null);
        dialog.show();
    }

    public static ArrayList<String> getAllDipartimenti(){
        ArrayList<String> dipartimenti = new ArrayList<>();
        dipartimenti.add("Commerciale");
        dipartimenti.add("Prevendita");
        dipartimenti.add("Design");
        dipartimenti.add("Qualità");
        dipartimenti.add("Sper");
        dipartimenti.add("Applicazione");
        dipartimenti.add("LOT");
        dipartimenti.add("Produzione");
        dipartimenti.add("Calibrazione");
        return dipartimenti;
    }

    public static ArrayList<Project> getVisibleProject(Activity activity, int tempDip, Dipartimento dipartimento) {
        ArrayList<Project> activeProject = ((App) activity.getApplicationContext()).getActiveProjects();
        ArrayList<Project> visibleProject = new ArrayList<>();
        for(Project project: activeProject){
            if(project.isVisible(tempDip, dipartimento)){
                visibleProject.add(project);
            }
        }
        return visibleProject;
    }


}

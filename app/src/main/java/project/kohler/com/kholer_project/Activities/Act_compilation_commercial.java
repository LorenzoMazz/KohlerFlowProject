package project.kohler.com.kholer_project.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.CONF;
import project.kohler.com.kholer_project.CC.C_F_APP;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.R;

public class Act_compilation_commercial extends AppCompatActivity {

    private Activity activity;
    private User user;

    private ImageView back;
    private TextView titlePage;
    private LinearLayout btn_riquadro;
    private Button createProject;
    private boolean fromProjectActive;
    private Project project;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_compilation_commercial);

        activity = this;
        user = ((App)activity.getApplicationContext()).getUser();
        project = ((App) activity.getApplicationContext()).getCurrenteProject();

        fromProjectActive = getIntent().getBooleanExtra("fromProject", true);

        back = findViewById(R.id.image_back_compilation);
        btn_riquadro = findViewById(R.id.btn_riquadro);
        createProject = findViewById(R.id.button_create_project);
        titlePage = findViewById(R.id.textViewTitleCompilation);

        checkButtons();
        if (fromProjectActive){
            titlePage.setText(project.getName());
            createProject.setVisibility(View.INVISIBLE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_riquadro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent subComplilation = new Intent(activity, Act_sub_compilation_commercial.class);
                subComplilation.putExtra("title", "RIQUADRO");
                startActivity(subComplilation);
            }
        });

        createProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C_F_APP.showAlertDialogForError(Act_compilation_commercial.this, getString(R.string.creation_complete));
            }
        });
    }

    private void checkButtons() {
        if(!user.getDipartimento().equals(CONF.commerciale)){
            createProject.setVisibility(View.INVISIBLE);
        }
    }
}

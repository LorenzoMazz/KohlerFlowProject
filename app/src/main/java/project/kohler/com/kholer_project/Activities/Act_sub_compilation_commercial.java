package project.kohler.com.kholer_project.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.CONF;
import project.kohler.com.kholer_project.CC.C_F_APP;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.R;

public class Act_sub_compilation_commercial extends AppCompatActivity {

    private Activity activity;
    private User user;
    private Project project;
    private boolean fromProjectActive;

    private ImageView back;
    private Button save;
    private TextView title;
    private String titleText;

    private EditText editTextTypeM;
    private EditText editTextClient;
    private EditText editTextTypeApp;
    private EditText editTextBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sub_compilation_commercial);

        activity = this;
        user = ((App)activity.getApplicationContext()).getUser();
        project = ((App) activity.getApplicationContext()).getCurrenteProject();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        fromProjectActive = getIntent().getBooleanExtra("fromProject", true);
        titleText = getIntent().getStringExtra("title");

        title = findViewById(R.id.text_title_sub);
        back = findViewById(R.id.image_back_compilation);
        save = findViewById(R.id.button_save_info);
        editTextTypeM = findViewById(R.id.editTextTypeMachine);
        editTextClient = findViewById(R.id.editTextClient);
        editTextTypeApp = findViewById(R.id.editTextTypeApplication);
        editTextBrand = findViewById(R.id.editTextBrand);

        title.setText(titleText);

        checkButtons();
        if (fromProjectActive && project!=null){
            title.setText(titleText + " "+ project.getName());
        }
        else{
            title.setText(titleText);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                C_F_APP.showAlertDialogForError(Act_sub_compilation_commercial.this, getString(R.string.data_saved));
            }
        });

    }

    private void checkButtons() {
        if(!user.getDipartimento().equals(CONF.commerciale)){
            save.setVisibility(View.INVISIBLE);
            editTextClient.setEnabled(false);
            editTextClient.setText("Informazioni Cliente " + project.getName());
            editTextTypeApp.setEnabled(false);
            editTextTypeApp.setText("Informazioni tipo Applicazione" + project.getName());
            editTextTypeM.setEnabled(false);
            editTextTypeM.setText("Informazioni Tipo Macchina" + project.getName());
            editTextBrand.setEnabled(false);
            editTextBrand.setText("Informazioni Marchio" + project.getName());
        }
    }
}

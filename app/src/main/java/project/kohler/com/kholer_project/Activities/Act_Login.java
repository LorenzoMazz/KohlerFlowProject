package project.kohler.com.kholer_project.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.CONF;
import project.kohler.com.kholer_project.CC.C_F;
import project.kohler.com.kholer_project.CC.C_F_APP;
import project.kohler.com.kholer_project.Data.Dipartimento;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.R;

public class Act_Login extends AppCompatActivity {

    public static AppCompatActivity act;
    private Activity activity;

    private RelativeLayout relativeLayout;
    private EditText editTextEmail;
    private EditText editTextPsswd;
    private TextView psswdForgot;
    private Button buttonLogin;
    private Button buttonRegister;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        act = this;
        activity = this;
        fm = getSupportFragmentManager();

        editTextPsswd = findViewById(R.id.edit_password);
        editTextEmail = findViewById(R.id.edit_email);
        psswdForgot = findViewById(R.id.textPsswdForgot);
        buttonLogin = findViewById(R.id.button_login);
        buttonRegister = findViewById(R.id.button_register);
        relativeLayout = findViewById(R.id.total);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String passw = editTextPsswd.getText().toString();
                if (!email.isEmpty()) {
                    switchLog(email);
                } else {
                    C_F_APP.showAlertDialogForError(Act_Login.this, getString(R.string.please_provide_student_code_password));
                }
            }
        });

        psswdForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                C_F_APP.showAlertDialogForError(Act_Login.this, getString(R.string.you_forgot_the_password));
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                C_F_APP.showAlertDialogForError(Act_Login.this, getString(R.string.you_will_be_regitered));
            }
        });
    }

    private void switchLog(String email) {
        Intent i = new Intent(activity, Act_home.class);

        if(email.equals("c"))
        {
            ((App) activity.getApplicationContext()).setUser(new User(0, "Mario Rossi",new Dipartimento( CONF.commerciale, CONF.ic_commerciale), CONF.T_Commerciale));
            startActivity(i);
            finish_();
        }
        else if(email.equals("pr")){
            ((App) activity.getApplicationContext()).setUser(new User(1, "Claudia Sergi",new Dipartimento(CONF.prevendita, CONF.ic_prevendita), CONF.T_Prevendita));
            startActivity(i);
            finish_();
        }
        else if(email.equals("pi")){
            ((App) activity.getApplicationContext()).setUser(new User(2, "Francesca Ferrari",new Dipartimento(CONF.piattaforma, CONF.ic_piattaforma), CONF.T_Piattaforma));
            startActivity(i);
            finish_();
        }
        else if(email.equals("d")){
            ((App) activity.getApplicationContext()).setUser(new User(3, "Marco Ruspa",new Dipartimento( CONF.design, CONF.ic_design), CONF.T1));
            startActivity(i);
            finish_();
        }
        else if(email.equals("a")){
            ((App) activity.getApplicationContext()).setUser(new User(4, "Luca Zeta",new Dipartimento( CONF.applicazioneT2, CONF.ic_applicazioneT2), CONF.T2));
            startActivity(i);
            finish_();
        }
        else if(email.equals("q")){
            ((App) activity.getApplicationContext()).setUser(new User(5, "Andrea Bendetto",new Dipartimento(CONF.qualitaT3, CONF.ic_qualitaT3), CONF.T3));
            startActivity(i);
            finish_();
        }
        else if(email.equals("foto")){
            try {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) , "foto_sfondo2.png");
                FileOutputStream output = new FileOutputStream( file);
                Bitmap bitmap = C_F.viewToBitmap(relativeLayout);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                output.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else{
            C_F_APP.showAlertDialogForError(Act_Login.this, getString(R.string.please_provide_student_code_password));
        }
    }

    private void finish_() {
        //finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
    }
}

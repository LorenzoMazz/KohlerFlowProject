package project.kohler.com.kholer_project.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.R;
import android.os.Handler;
import android.widget.ImageView;

public class Act_Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    public static AppCompatActivity act;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);

        act = this;
        activity = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((App)activity.getApplicationContext()).initializeActiveProjects();
                Intent intent = new Intent(activity, Act_Login.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

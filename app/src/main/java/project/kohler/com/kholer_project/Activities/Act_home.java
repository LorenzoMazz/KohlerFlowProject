package project.kohler.com.kholer_project.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.C_F_APP;
import project.kohler.com.kholer_project.CC.CustomAlertDialog;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.Fragments.Frag_main_account;
import project.kohler.com.kholer_project.Fragments.Frag_old_projects;
import project.kohler.com.kholer_project.R;


public class Act_home extends AppCompatActivity implements View.OnClickListener{

    public static final int FRAG_HOME = 0;
    public static final int FRAG_STORICO = 1;
    public static final int FRAG_MAIL = 2;
    public static final int FRAG_AIUTO = 3;
    public static final int FRAG_IMPOSTAZIONI= 4;
    public static final int FRAG_LOGOUT = 5;

    private int cur_fragment = FRAG_HOME;

    private Activity activity;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private RelativeLayout layout_drawer;
    private ActionBarDrawerToggle drawerToggle;
    private FrameLayout container;
    private LinearLayout layoutHome, layoutStorico, layoutMail, layoutAiuto, layoutImpostazioni, layoutLougOut;
    private TextView text_nome_dipartimento;
    private TextView textViewNameWorker;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);

        activity = this;
        user = ((App)activity.getApplicationContext()).getUser();

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        layout_drawer = findViewById(R.id.start_layout_drawer);
        container = findViewById(R.id.content_frame);
        text_nome_dipartimento = findViewById(R.id.textViewNomeDipartimento);
        layoutHome = findViewById(R.id.home);
        layoutStorico = findViewById(R.id.storico);
        layoutMail = findViewById(R.id.mail);
        layoutAiuto = findViewById(R.id.help);
        layoutImpostazioni = findViewById(R.id.settings);
        layoutLougOut = findViewById(R.id.logout);
        textViewNameWorker = findViewById(R.id.textViewNameWorker);


        layoutImpostazioni.setAlpha((float)0.2);
        layoutAiuto.setAlpha((float)0.2);
        layoutMail.setAlpha((float)0.2);


        text_nome_dipartimento.setText(user.getDipartimento().getNome());
        textViewNameWorker.setText(user.getName());

        layoutHome.setOnClickListener(this);
        layoutStorico.setOnClickListener(this);
        layoutMail.setOnClickListener(this);
        layoutAiuto.setOnClickListener(this);
        layoutImpostazioni.setOnClickListener(this);
        layoutLougOut.setOnClickListener(this);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if(savedInstanceState == null) {
            setFragment();
        }

    }

    public void setFragment() {
        switch (cur_fragment) {
            case FRAG_HOME:
                getSupportFragmentManager().beginTransaction().replace(container.getId(), new Frag_main_account(), getString(R.string.app_name)).commitAllowingStateLoss();
                break;
            case FRAG_STORICO:
                getSupportFragmentManager().beginTransaction().replace(container.getId(), new Frag_old_projects(), getString(R.string.app_name)).commitAllowingStateLoss();
                break;
            case FRAG_MAIL:
                getSupportFragmentManager().beginTransaction().replace(container.getId(), new Frag_main_account(), getString(R.string.app_name)).commitAllowingStateLoss();
                break;
            case FRAG_AIUTO:
                getSupportFragmentManager().beginTransaction().replace(container.getId(), new Frag_main_account(), getString(R.string.app_name)).commitAllowingStateLoss();
                break;
            case FRAG_IMPOSTAZIONI:
                getSupportFragmentManager().beginTransaction().replace(container.getId(), new Frag_main_account(), getString(R.string.app_name)).commitAllowingStateLoss();
                break;
        }
        if (drawerLayout.isDrawerOpen(layout_drawer)) {
            drawerLayout.closeDrawer(layout_drawer);
        }
        changeAlpha();
    }

    private void changeAlpha() {
        switch (cur_fragment) {
            case FRAG_HOME:
                layoutHome.setAlpha((float) 1);
                layoutStorico.setAlpha((float) 0.2);
                break;
            case FRAG_STORICO:
                layoutHome.setAlpha((float) 0.2);
                layoutStorico.setAlpha((float) 1);
                break;
        }

        if (drawerLayout.isDrawerOpen(layout_drawer)) {
            drawerLayout.closeDrawer(layout_drawer);
        }
    }


    private void LogOut() {
        Intent mainIntent = new Intent(this, Act_Login.class);
        startActivity(mainIntent);
        finish();
    }

    public void showLogoutDialog() {
        final CustomAlertDialog dialog = new CustomAlertDialog(this);
        dialog.setActivity(this);
        View.OnClickListener listenerLeft = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogOut();
                dialog.dismiss();
            }
        };

        View.OnClickListener listenerRight = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        };

        dialog.setData(getString(R.string.warning), getString(R.string.really_wish_logout),
                getString(R.string.yes), getString(R.string.no),
                listenerLeft, listenerRight);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == layoutHome.getId()) {
            if (cur_fragment != FRAG_HOME) {
                cur_fragment = FRAG_HOME;
                setFragment();
            } else {
                drawerLayout.closeDrawer(layout_drawer);
            }
        }
        else  if (v.getId() == layoutStorico.getId()) {
            if (cur_fragment != FRAG_STORICO) {
                cur_fragment = FRAG_STORICO;
                setFragment();
            } else {
                drawerLayout.closeDrawer(layout_drawer);
            }
        }
        else  if (v.getId() == layoutMail.getId()) {
            C_F_APP.showAlertDialogForError(activity, getString(R.string.function_not_enable));
           /* if (cur_fragment != FRAG_MAIL) {
                cur_fragment = FRAG_MAIL;
                setFragment();
            } else {
                drawerLayout.closeDrawer(layout_drawer);
            }*/
        }
        else  if (v.getId() == layoutAiuto.getId()) {
            C_F_APP.showAlertDialogForError(activity, getString(R.string.function_not_enable));
           /* if (cur_fragment != FRAG_AIUTO) {
                cur_fragment = FRAG_AIUTO;
                setFragment();
            } else {
                drawerLayout.closeDrawer(layout_drawer);
            }*/
        }
        else  if (v.getId() == layoutImpostazioni.getId()) {
            C_F_APP.showAlertDialogForError(activity, getString(R.string.function_not_enable));
          /*  if (cur_fragment != FRAG_IMPOSTAZIONI) {
                cur_fragment = FRAG_IMPOSTAZIONI;
                setFragment();
            } else {
                drawerLayout.closeDrawer(layout_drawer);
            }*/
        }
        else  if (v.getId() == layoutLougOut.getId()) {
            showLogoutDialog();
        }
    }

    @Override
    public void onBackPressed() {
        if (cur_fragment == FRAG_HOME) {
            super.onBackPressed();
        } else {
            cur_fragment = FRAG_HOME;
            setFragment();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

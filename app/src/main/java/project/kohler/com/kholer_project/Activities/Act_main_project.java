package project.kohler.com.kholer_project.Activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nex3z.notificationbadge.NotificationBadge;

import project.kohler.com.kholer_project.CC.App;
import project.kohler.com.kholer_project.CC.CONF;
import project.kohler.com.kholer_project.CC.C_F;
import project.kohler.com.kholer_project.CC.C_F_APP;
import project.kohler.com.kholer_project.CC.CustomAlertDialog;
import project.kohler.com.kholer_project.CC.CustomViewPager;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.Data.User;
import project.kohler.com.kholer_project.Fragments.Frag_Attached;
import project.kohler.com.kholer_project.Fragments.Frag_Messages;
import project.kohler.com.kholer_project.Fragments.Frag_Note;
import project.kohler.com.kholer_project.List_Adapter.Frags_Adapter;
import project.kohler.com.kholer_project.R;


public class Act_main_project extends AppCompatActivity {

    private TabLayout tabLayout;
    private TextView saveInfo;
    private ImageView back;
    private TextView textViewClientName;
    private TextView textViewProjectTitle;
    private LinearLayout linearLayoutInfo;
    private ImageView btnRejectProject;

    private CustomViewPager viewPagerFragments;
    private Frags_Adapter frags_adapter;

    private int imageResources[] = {R.drawable.ic_assignment_white_24dp, R.drawable.ic_event_note_white_24dp, R.drawable.ic_message_white_24dp};
    int currentPosition;

    private Activity activity;
    private Project project;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main_project);

        activity = this;
        project = ((App) activity.getApplicationContext()).getCurrenteProject();
        user = ((App)activity.getApplicationContext()).getUser();

        tabLayout = findViewById(R.id.tabHome);
        viewPagerFragments = findViewById(R.id.flContentHome);
        saveInfo = findViewById(R.id.btn_save);
        back = findViewById(R.id.btn_back);
        textViewClientName = findViewById(R.id.textViewClientName);
        textViewProjectTitle = findViewById(R.id.text_title_project);
        linearLayoutInfo = findViewById(R.id.linearLayoutInfo);
        btnRejectProject = findViewById(R.id.btn_reject);

        if(user.getTemp() == CONF.T_Commerciale || project.getActualTemp()!=user.getTemp()){
            btnRejectProject.setVisibility(View.GONE);
        }

        textViewClientName.setText(project.getClientName());
        textViewProjectTitle.setText(project.getName());

        currentPosition = 0;

        frags_adapter = new Frags_Adapter(getSupportFragmentManager());

        frags_adapter.addFragment(new Frag_Attached(), getString(R.string.documents));
        frags_adapter.addFragment(new Frag_Note(), getString(R.string.notes));
        frags_adapter.addFragment(new Frag_Messages(), getString(R.string.chat));



        viewPagerFragments.setAdapter(frags_adapter);
        viewPagerFragments.setOffscreenPageLimit(4);

        tabLayout.setupWithViewPager(viewPagerFragments);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);


        InitilizeTabs(0);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ChangeAlpha(tab, (float) 1);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ChangeAlpha(tab, (float) 0.5);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        int notification = project.getUreadMessages();
        setNotificationMessage(notification);

        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(project.isRevisionedFromDepartment(user.getDipartimento())) {
                    showLogoutDialog();
                }
                else{
                    C_F_APP.showAlertDialogForError(activity,getString(R.string.youMustRevisionTheProject));
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        linearLayoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent complilation = new Intent(activity, Act_compilation_commercial.class);
                complilation.putExtra("fromProject", true);
                startActivity(complilation);
            }
        });

        btnRejectProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sender = user.getDipartimento();
                String reciver = "";
               switch (user.getTemp()){
                   case CONF.T_Prevendita:
                       reciver = "Commerciale";
                       break;
                   case CONF.T_Piattaforma:
                       reciver = "Prevendita";
                       break;
                   case CONF.T1:
                       reciver = "Piattaforma";
                       break;
                   case CONF.T2:
                       reciver = "Piattaforma";
                       break;
                   case CONF.T3:
                       reciver = "Piattaforma";
                       break;
               }
                   C_F_APP.showAlertDialogForError(activity, "Questo progetto è stato respinto ed è stato inviato da " + sender + " a " + reciver);
            }
        });

    }

    public void showLogoutDialog() {
        final CustomAlertDialog dialog = new CustomAlertDialog(this);
        dialog.setActivity(this);
        View.OnClickListener listenerLeft = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                C_F_APP.showAlertDialogForError(activity, getString(R.string.save_file_option_dsable));
            }
        };

        View.OnClickListener listenerRight = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        };

        dialog.setData(getString(R.string.warning), getString(R.string.really_wish_save),
                getString(R.string.yes), getString(R.string.no),
                listenerLeft, listenerRight);
        dialog.show();
    }


    private void setNotificationMessage(int notification) {
        RelativeLayout layout = (RelativeLayout) tabLayout.getTabAt(2).getCustomView();
        ImageView imageViewIcon = layout.findViewById(R.id.iconTab);
        NotificationBadge notificationBadge = layout.findViewById(R.id.badge);
        TextView title = layout.findViewById(R.id.title_sectioon_project);
        notificationBadge.setNumber(notification);
        imageViewIcon.setImageResource(imageResources[2]);

        if (currentPosition != 2) {
            imageViewIcon.setAlpha((float) (0.5));
            notificationBadge.setAlpha((float) (0.5));
            title.setAlpha((float) (0.5));
        } else {
            imageViewIcon.setAlpha((float) (1));
            notificationBadge.setAlpha((float) (1));
            title.setAlpha((float) (1));
        }
    }

    private void InitilizeTabs(int starterPosition) {
        TabLayout.LayoutParams params = new FrameLayout.LayoutParams(C_F.getScreenWidth(activity)/6, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            RelativeLayout layout = (RelativeLayout) LayoutInflater.from(activity).inflate(R.layout.tab_project, null);

            ImageView imageViewIcon = layout.findViewById(R.id.iconTab);
            TextView title = layout.findViewById(R.id.title_sectioon_project);

            NotificationBadge notificationBadge = layout.findViewById(R.id.badge);

            if(i == 1) {
                if(project.hasWarningNote() && project.getActualTemp()== user.getTemp()) {
                    imageViewIcon.setImageResource(R.drawable.ic_warning_red_24dp);
                }
                else{
                    imageViewIcon.setImageResource(imageResources[i]);
                }
            }
            else{
                imageViewIcon.setImageResource(imageResources[i]);
            }
            title.setText(frags_adapter.getPageTitle(i));

            if (i != starterPosition) {
                imageViewIcon.setAlpha((float) (0.5));
                notificationBadge.setAlpha((float) (0.5));
                title.setAlpha((float) (0.5));
            } else {
                imageViewIcon.setAlpha((float) (1));
                notificationBadge.setAlpha((float) (1));
                title.setAlpha((float) (1));
            }
            tabLayout.getTabAt(i).setCustomView(layout);
        }
        viewPagerFragments.setCurrentItem(0);
        tabLayout.getLayoutParams().height = (int)(C_F.getScreenHeight(activity)/8.5);
    }

    private void ChangeAlpha(TabLayout.Tab tab, float value) {

        if (value == 1) {
            currentPosition = tab.getPosition();
        }

        RelativeLayout layout = (RelativeLayout) tab.getCustomView();
        ImageView imageViewIcon = (ImageView) layout.findViewById(R.id.iconTab);
        NotificationBadge notificationBadge = layout.findViewById(R.id.badge);
        TextView title = layout.findViewById(R.id.title_sectioon_project);
        imageViewIcon.setAlpha(value);
        notificationBadge.setAlpha(value);
        title.setAlpha(value);
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

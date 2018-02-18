package project.kohler.com.kholer_project.CC;

import android.app.Application;

import java.util.ArrayList;

import project.kohler.com.kholer_project.Data.Allegato;
import project.kohler.com.kholer_project.Data.Chat;
import project.kohler.com.kholer_project.Data.Note;
import project.kohler.com.kholer_project.Data.Project;
import project.kohler.com.kholer_project.Data.User;


/**
 * Created by Lorenzo Mazzoni on 31/10/2017.
 */

public class App extends Application {

    private User user = null;
    private ArrayList<Project> activeProjects;
    private ArrayList<Project> oldProjects;
    private Project currenteProject;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ArrayList<Project> getOldProjects() {
        return oldProjects;
    }

    public void setOldProjects(ArrayList<Project> oldProjects) {
        this.oldProjects = oldProjects;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Project> getActiveProjects() {
        activeProjects = new ArrayList<>();


        Project p = new Project("Progetto 1", "Nome cliente 1", 0, CONF.T_Commerciale,  "16 Febbraio 2018", true);

        // public Chat(int id, String LastMessage, String dipartimento, String latsUpdateDate, boolean read){
        p.addChat(new Chat(0,"Ultimo messaggio inviato da Prevendita", CONF.prevendita, "20/02/2018   15:52", false ));

        //public Allegato(String name, String dipartimento){
        p.addAllegato(new Allegato("Allegato 1 Commerciale", CONF.commerciale));
        p.addAllegato(new Allegato("Allegato 2 Commerciale", CONF.commerciale));
        p.addAllegato(new Allegato("Allegato 1 Prevendita", CONF.prevendita));
        p.addAllegato(new Allegato("Allegato 2 Prevendita", CONF.prevendita));
        p.addAllegato(new Allegato("Allegato 3 Prevendita", CONF.prevendita));
        //public Note(int id, String message, String sender, String date, boolean read, boolean warning){
        p.addNote(new Note(0, "Motivazione respinta progetto", CONF.prevendita, "21/2/02018", false, true));
        p.addNote(new Note(0, "Nota 1 prevendita", CONF.prevendita, "18/02/2018", false, false));
        p.addNote(new Note(0, "Nota 2 prevendita", CONF.prevendita, "19/02/2018", true, false));
        p.addNote(new Note(0, "Nota 1 commerciale", CONF.commerciale, "17/02/2018", true, false));

        //dipartimenti interessati
        p.addDipartimento(CONF.commerciale);
        p.addDipartimento(CONF.prevendita);

        activeProjects.add(p);

        Project p2 = new Project("Progetto 2", "Nome cliente 2", 50, CONF.T2,  "20 Dicembre 2017", false);

        p2.addChat(new Chat(0,"Ultimo messaggio inviato da Prevendita", CONF.prevendita, "20/02/2018   15:52", false ));
        p2.addChat(new Chat(0,"Ultimo messaggio inviato da Commerciale", CONF.commerciale, "22/12/2017   17:52", false ));
        p2.addChat(new Chat(0,"Ultimo messaggio inviato da Piattaforma", CONF.piattaforma, "27/01/2018   12:59", false ));

        p2.addAllegato(new Allegato("Allegato 1 Commerciale", CONF.commerciale));
        p2.addAllegato(new Allegato("Allegato 1 Prevendita", CONF.prevendita));
        p2.addAllegato(new Allegato("Allegato 1 Piattaforma", CONF.piattaforma));
        p2.addAllegato(new Allegato(CONF.revsione  , CONF.design));
        p2.addAllegato(new Allegato("Allegato 2 Design", CONF.design));
        p2.addAllegato(new Allegato(CONF.revsione, CONF.sper));
        p2.addAllegato(new Allegato("Allegato 2 Sper", CONF.sper));
        p2.addAllegato(new Allegato("Allegato 1 Applicazione", CONF.applicazione));


        p2.addNote(new Note(0, "Nota 1 commerciale", CONF.commerciale, "01/01/2018", false, false));
        p2.addNote(new Note(0, "Nota 1 prevendita", CONF.prevendita, "12/01/2018", false, false));
        p2.addNote(new Note(0, "Nota 1 piattaforma", CONF.piattaforma, "15/01/2018", false, false));
        p2.addNote(new Note(0, "Nota 1 design", CONF.design, "02/2/2018", true, false));
        p2.addNote(new Note(0, "Nota 1 sper", CONF.sper, "14/02/2018", true, false));

        p2.addDipartimento(CONF.commerciale);
        p2.addDipartimento(CONF.prevendita);
        p2.addDipartimento(CONF.piattaforma);
        p2.addDipartimento(CONF.design);
        p2.addDipartimento(CONF.sper);
        p2.addDipartimento(CONF.applicazione);

        activeProjects.add(p2);

        Project p3 = new Project("Progetto 3", "Nome cliente 3", 20, CONF.T1,  "16 Dicembre 2017", false);

        p3.addChat(new Chat(0,"Ultimo messaggio inviato da Prevendita", CONF.prevendita, "20/02/2018   15:52", false ));
        p3.addChat(new Chat(0,"Ultimo messaggio inviato da Commerciale", CONF.commerciale, "22/12/2017   17:52", false ));
        p3.addChat(new Chat(0,"Ultimo messaggio inviato da Piattaforma", CONF.piattaforma, "27/01/2018   12:59", false ));

        p3.addAllegato(new Allegato("Allegato 1 Commerciale", CONF.commerciale));
        p3.addAllegato(new Allegato("Allegato 1 Prevendita", CONF.prevendita));
        p3.addAllegato(new Allegato("Allegato 1 Piattaforma", CONF.piattaforma));
        p3.addAllegato(new Allegato("Allegato 1 Design", CONF.design));
        p3.addAllegato(new Allegato("Allegato 2 Design", CONF.design));


        p3.addNote(new Note(0, "Nota 1 commerciale", CONF.commerciale, "01/01/2018", false, false));
        p3.addNote(new Note(0, "Nota 1 prevendita", CONF.prevendita, "12/01/2018", false, false));
        p3.addNote(new Note(0, "Nota 1 piattaforma", CONF.piattaforma, "15/01/2018", false, false));
        p3.addNote(new Note(0, "Nota 1 design", CONF.design, "02/2/2018", true, false));

        p3.addDipartimento(CONF.commerciale);
        p3.addDipartimento(CONF.prevendita);
        p3.addDipartimento(CONF.piattaforma);
        p3.addDipartimento(CONF.design);

        activeProjects.add(p3);


        Project p4 = new Project("Progetto 4", "Nome cliente 4", 0, CONF.T_Prevendita,  "08 Novembre 2017", true);

        p4.addChat(new Chat(0,"Ultimo messaggio inviato da Prevendita", CONF.prevendita, "20/02/2018   15:52", false ));
        p4.addChat(new Chat(0,"Ultimo messaggio inviato da Commerciale", CONF.commerciale, "22/12/2017   17:52", false ));
        p4.addChat(new Chat(0,"Ultimo messaggio inviato da Piattaforma", CONF.piattaforma, "27/01/2018   12:59", false ));

        p4.addAllegato(new Allegato("Allegato 1 Commerciale", CONF.commerciale));
        p4.addAllegato(new Allegato("Allegato 1 Prevendita", CONF.prevendita));
        p4.addAllegato(new Allegato("Allegato 1 Piattaforma", CONF.piattaforma));


        p4.addNote(new Note(0, "Motivazione respinta progetto", CONF.piattaforma, "15/01/2018", false, true));
        p4.addNote(new Note(0, "Nota 1 commerciale", CONF.commerciale, "01/01/2018", false, false));
        p4.addNote(new Note(0, "Nota 1 prevendita", CONF.prevendita, "12/01/2018", false, false));



        p4.addDipartimento(CONF.commerciale);
        p4.addDipartimento(CONF.prevendita);
        p4.addDipartimento(CONF.piattaforma);


        activeProjects.add(p4);

        Project p5 = new Project("Progetto 5", "Nome cliente 5", 0, CONF.T_Piattaforma,  "07 Novembre 2017", true); //respinto da design

        p5.addChat(new Chat(0,"Ultimo messaggio inviato da Prevendita", CONF.prevendita, "20/02/2018   15:52", false ));
        p5.addChat(new Chat(0,"Ultimo messaggio inviato da Commerciale", CONF.commerciale, "22/12/2017   17:52", false ));
        p5.addChat(new Chat(0,"Ultimo messaggio inviato da Piattaforma", CONF.piattaforma, "27/01/2018   12:59", false ));

        p5.addAllegato(new Allegato("Allegato 1 Commerciale", CONF.commerciale));
        p5.addAllegato(new Allegato("Allegato 1 Prevendita", CONF.prevendita));
        p5.addAllegato(new Allegato("Allegato 1 Piattaforma", CONF.piattaforma));



        p5.addNote(new Note(0, "Motivazione respinta progetto", CONF.design, "01/01/2018", false, true));
        p5.addNote(new Note(0, "Nota 1 commerciale", CONF.commerciale, "01/01/2018", false, false));
        p5.addNote(new Note(0, "Nota 1 prevendita", CONF.prevendita, "12/01/2018", false, false));
        p5.addNote(new Note(0, "Nota 1 piattaforma", CONF.piattaforma, "15/01/2018", false, false));



        p5.addDipartimento(CONF.commerciale);
        p5.addDipartimento(CONF.prevendita);
        p5.addDipartimento(CONF.piattaforma);
        p5.addDipartimento(CONF.design);


        activeProjects.add(p5);

        return this.activeProjects;
    }

    public Project getCurrenteProject() {
        return currenteProject;
    }

    public void setActiveProjects(ArrayList<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }

    public void setCurrenteProject(Project currenteProject) {
        this.currenteProject = currenteProject;
    }
}

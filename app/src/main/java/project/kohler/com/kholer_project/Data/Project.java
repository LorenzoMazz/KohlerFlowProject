package project.kohler.com.kholer_project.Data;

import java.io.Serializable;
import java.util.ArrayList;

import project.kohler.com.kholer_project.CC.CONF;

/**
 * Created by Lorenzo on 15/02/2018.
 */

public class Project implements Serializable{

    private String name;
    private String clientName;
    private int progress;
    private int actualTemp;
    private String dateStart;
    private String dateEnd;

    private ArrayList<Note> listaNoteProgetto;
    private ArrayList<Chat> listaChatProgetto;
    private ArrayList<Allegato> listAllegati;
    private ArrayList<String> dipartimenti;

    private boolean rejected;
    public boolean active = true;

    public Project(String name, String clientName, int progress, int actualTemp, String dateStart, boolean rejected){
        this.name = name;
        this.clientName = clientName + " "+ name;
        this.progress = progress;
        this.actualTemp = actualTemp;
        this.dateStart = dateStart;
        this.rejected = rejected;
        this.dipartimenti = new ArrayList<>();
        this.listAllegati = new ArrayList<>();
        this.listaChatProgetto = new ArrayList<>();
        this.listaNoteProgetto = new ArrayList<>();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<String> getDipartimenti() {
        return dipartimenti;
    }

    public void setDipartimenti(ArrayList<String> dipartimenti) {
        this.dipartimenti = dipartimenti;
    }

    public void addAllegato(Allegato allegato){
        this.listAllegati.add(allegato);
    }

    public void addChat(Chat chat){
        this.listaChatProgetto.add(chat);
    }

    public void addNote(Note note){
        this.listaNoteProgetto.add(note);
    }

    public void addDipartimento(String dipartimento){ this.dipartimenti.add(dipartimento);}

    public ArrayList<Allegato> getListAllegati() {
        return listAllegati;
    }

    public void setListAllegati(ArrayList<Allegato> listAllegati) {
        this.listAllegati = listAllegati;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public ArrayList<Chat> getListaChatProgetto() {
        return listaChatProgetto;
    }

    public void setListaChatProgetto(ArrayList<Chat> listaChatProgetto) {
        this.listaChatProgetto = listaChatProgetto;
    }

    public ArrayList<Note> getListaNoteProgetto() {
        return listaNoteProgetto;
    }

    public void setListaNoteProgetto(ArrayList<Note> listaNoteProgetto) {
        this.listaNoteProgetto = listaNoteProgetto;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public int getActualTemp() {
        return actualTemp;
    }

    public void setActualTemp(int actualTemp) {
        this.actualTemp = actualTemp;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisible(int tempDip, String dipartimento) {
        if(dipartimento.equals(CONF.commerciale) || dipartimento.equals(CONF.piattaforma) || dipartimento.equals(CONF.prevendita)) {
            return (this.actualTemp >= tempDip && this.dipartimenti.contains(dipartimento));
        }
        else{
            return (this.actualTemp == tempDip && this.dipartimenti.contains(dipartimento));
        }
    }

    public ArrayList<Allegato> getAttachedFromDepartment(String s) {
        ArrayList<Allegato> allegati = new ArrayList<>();
        for(Allegato allegato: this.listAllegati){
            if (allegato.getDipartimento().equals(s)){
                allegati.add(allegato);
            }
        }
        return  allegati;
    }

    public int getUreadMessages() {
        int num = 0;
        for(Chat chat: listaChatProgetto){
            if(!chat.isRead()){
                num++;
            }
        }
        return num;
    }

    public boolean hasWarningNote() {
        for(Note n: listaNoteProgetto){
            if(n.isWarning())
                return true;
        }
        return  false;
    }

    public boolean isRevisionedFromDepartment(String s) {
        for(Allegato allegato: this.listAllegati){
            if (allegato.getDipartimento().equals(s) && allegato.getName().equals(CONF.revsione)){
                return  true;
            }
        }
        return false;
    }
}

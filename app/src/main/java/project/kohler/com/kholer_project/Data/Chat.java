package project.kohler.com.kholer_project.Data;

import java.io.Serializable;

/**
 * Created by Lorenzo Mazzoni on 12/12/2017.
 */

public class Chat implements Serializable {

    private int id;
    private String LastMessage;
    private String dipartimento;
    private String latsUpdateDate;
    private boolean read;

    public Chat(int id, String LastMessage, String dipartimento, String latsUpdateDate, boolean read){
        this.id = id;
        this.LastMessage = LastMessage;
        this.dipartimento = dipartimento;
        this.latsUpdateDate = latsUpdateDate;
        this.read = read;
    }

    public void setLatsUpdateDate(String latsUpdateDate) {
        this.latsUpdateDate = latsUpdateDate;
    }

    public String getLatsUpdateDate() {
        return latsUpdateDate;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getId() {
        return id;
    }

    public String getLastMessage() {
        return LastMessage;
    }

    public String getDipartimento() {
        return dipartimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastMessage(String lastMessage) {
        this.LastMessage = lastMessage;
    }

    public void setDipartimento(String dipartimento) {
        this.dipartimento = dipartimento;
    }
}

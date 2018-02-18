package project.kohler.com.kholer_project.Data;

import java.io.Serializable;

/**
 * Created by Lorenzo Mazzoni on 12/12/2017.
 */

public class Note implements Serializable {

    private int id;
    private String message;
    private String sender;
    private String date;
    private boolean read;
    private boolean warning;

    public Note(int id, String message, String sender, String date, boolean read, boolean warning){
        this.id = id;
        this.message = message;
        this.sender = sender;
        this.date = date;
        this.read = read;
        this.warning = warning;
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
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

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}

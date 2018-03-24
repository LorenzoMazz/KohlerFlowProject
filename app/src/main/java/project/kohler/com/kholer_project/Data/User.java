package project.kohler.com.kholer_project.Data;

import java.io.Serializable;

/**
 * Created by Lorenzo Mazzoni on 28/11/2017.
 */

public class User implements Serializable {

    private int idWorker;
    private String name;
    private Dipartimento dipartimento;
    private int temp;


    public User( int idStudent, String name, Dipartimento dipartimento, int temp){
        this.idWorker = idStudent;
        this.name = name;
        this.dipartimento = dipartimento;
        this.temp = temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getTemp() {
        return temp;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public Dipartimento getDipartimento() {
        return dipartimento;
    }

    public String getName() {
        return name;
    }

    public void setDipartimento(Dipartimento dipartimento) {
        this.dipartimento = dipartimento;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public void setName(String name) {
        this.name = name;
    }

}

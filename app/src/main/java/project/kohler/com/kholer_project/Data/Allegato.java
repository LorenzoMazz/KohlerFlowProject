package project.kohler.com.kholer_project.Data;

import java.io.Serializable;

/**
 * Created by Lorenzo on 17/02/2018.
 */

public class Allegato implements Serializable{

    private String name;
    private String dipartimento;
    private String ultimoAggiornamento = "Data ultimo aggiornamento ";

    public Allegato(String name, String dipartimento){
        this.name = name;
        this.dipartimento = dipartimento;
    }

    public String getUltimoAggiornamento() {
        return ultimoAggiornamento;
    }

    public void setUltimoAggiornamento(String ultimoAggiornamento) {
        this.ultimoAggiornamento = ultimoAggiornamento;
    }

    public String getName() {
        return name;
    }

    public String getDipartimento() {
        return dipartimento;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDipartimento(String dipartimento) {
        this.dipartimento = dipartimento;
    }
}

package project.kohler.com.kholer_project.Data;

/**
 * Created by Lorenzo on 25/02/2018.
 */

public class Dipartimento {

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    private String nome;
    private int icon;

    public Dipartimento(String nome, int icon){
        this.nome = nome;
        this.icon = icon;
    }
}

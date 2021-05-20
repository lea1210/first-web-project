package de.hsrm.mi.web.projekt.foto;
import java.io.Serializable;

public class Adresse implements Serializable{
    private String display_name;

    public Adresse(){
    
    }

    public String getDisplay_name() {
        return this.display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }


}

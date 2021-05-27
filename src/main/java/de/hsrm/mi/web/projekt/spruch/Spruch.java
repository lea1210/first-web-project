package de.hsrm.mi.web.projekt.spruch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Spruch {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private String name;

    private String text;

    private int anspruch;

    public Spruch(){
        name = "";
        text = "";
        anspruch = 0;
    }

    public Spruch(String name, String text, int anspruch){
        this.name = name;
        this.text = text;
        this.anspruch = anspruch;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return this.version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAnspruch() {
        return this.anspruch;
    }

    public void setAnspruch(int anspruch) {
        this.anspruch = anspruch;
    }

    
}

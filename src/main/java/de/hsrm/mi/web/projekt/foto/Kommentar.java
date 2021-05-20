package de.hsrm.mi.web.projekt.foto;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;


@Entity
public class Kommentar {

   

    @GeneratedValue
    @Id
    private long id;

    @Version
    private long version;

    @ManyToOne
    private Foto aktFoto;

    private String autor;
    private LocalDateTime zeitpunkt;
    private String text;

    public Kommentar(){
        autor = "";
        text="";
        zeitpunkt = LocalDateTime.now();

    }

    public Kommentar(String autor, String text){
        this.autor = autor;
        this.text = text;
        zeitpunkt = LocalDateTime.now();

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

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDateTime getZeitpunkt() {
        return this.zeitpunkt;
    }

    public void setZeitpunkt(LocalDateTime zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Kommentar)) {
            return false;
        }
        Kommentar kommentar = (Kommentar) o;
        return id == kommentar.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, aktFoto, autor, zeitpunkt, text);
    }

    
}

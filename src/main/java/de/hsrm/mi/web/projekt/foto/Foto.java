package de.hsrm.mi.web.projekt.foto;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Foto {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version;

    @NotBlank(message = "{leeresFeld.fehler}")
    private String mimetype;

    @Size(min=3,message="{anzahlZeichenDateiName.fehler} {min} ")
    private String dateiname;
    private String ort;

    @Past(message="{datumZukunft.fehler}")
    private LocalDateTime zeitstempel;
    private double geolaenge;
    private double geobreite;

    @Lob
    private byte[] fotodaten;

    public Foto(){
        mimetype = "";
        dateiname = "";
        ort = "";
        zeitstempel = LocalDateTime.MIN;  
    }

    public Foto(String dateiname, String mimetype, byte[] fotodaten){
        this.mimetype = mimetype;
        this.dateiname = dateiname;
        this.fotodaten = fotodaten;
        this.ort = "";
        this.zeitstempel = LocalDateTime.MIN;  
    }


    public String getMimetype() {
        return this.mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getDateiname() {
        return this.dateiname;
    }

    public void setDateiname(String dateiname) {
        this.dateiname = dateiname;
    }

    public String getOrt() {
        return this.ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public LocalDateTime getZeitstempel() {
        return this.zeitstempel;
    }

    public void setZeitstempel(LocalDateTime zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public double getGeolaenge() {
        return this.geolaenge;
    }

    public void setGeolaenge(double geolaenge) {
        this.geolaenge = geolaenge;
    }

    public double getGeobreite() {
        return this.geobreite;
    }

    public void setGeobreite(double geobreite) {
        this.geobreite = geobreite;
    }

    public byte[] getFotodaten() {
        return this.fotodaten;
    }

    public void setFotodaten(byte[] fotodaten) {
        this.fotodaten = fotodaten;
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

   

    @Override
    public boolean equals(Object o) {
        
        if (!(o instanceof Foto)) {
            return false;
        }

        Foto foto = (Foto) o;

        return Objects.equals(dateiname, foto.dateiname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mimetype, dateiname, ort, zeitstempel, geolaenge, geobreite, fotodaten);
    }

    @Override
    public String toString() {
        return "{" +
            " mimetype='" + getMimetype() + "'" +
            ", dateiname='" + getDateiname() + "'" +
            ", ort='" + getOrt() + "'" +
            ", zeitstempel='" + getZeitstempel() + "'" +
            ", geolaenge='" + getGeolaenge() + "'" +
            ", geobreite='" + getGeobreite() + "'" +
            ", fotodaten='" + getFotodaten() + "'" +
            "}";
    }

    
}

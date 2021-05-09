package de.hsrm.mi.web.projekt.sichtung;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import de.hsrm.mi.web.projekt.validierung.Siebzehnhaft;


public class Sichtung {
    @Size(min=3,message="{anzahlZeichenName.fehler} {min} ")
    @NotBlank(message = "{leeresFeld.fehler}")
    private String name;

    @NotBlank(message = "{leeresFeld.fehler}")
    private String ort;

    @NotNull(message = "{leeresFeld.fehler}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datum;

    @Size(max=80,message="{anzahlZeichenBeschreibung.fehler}{max}")
    @NotBlank(message = "{leeresFeld.fehler}")
    @Siebzehnhaft(value="17",message="{siebzehnhaft.fehler}")
    private String beschreibung;

    public Sichtung(){
        name = "";
        ort="";
        beschreibung="";
        datum = LocalDate.of(1970, 01, 01);
    }

    public Sichtung(String name, String ort, LocalDate datum, String beschreibung){
        this.name = name;
        this.ort = ort;
        this.datum = datum;
        this.beschreibung = beschreibung;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", ort='" + getOrt() + "'" +
            ", datum='" + getDatum() + "'" +
            ", beschreibung='" + getBeschreibung() + "'" +
            "}";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrt() {
        return this.ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public LocalDate getDatum() {
        return this.datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }  
}

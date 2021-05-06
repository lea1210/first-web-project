package de.hsrm.mi.web.projekt.sichtung;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes(names = {"meinesichtungen"})
public class SichtungController {
    Logger logger = LoggerFactory.getLogger(SichtungController.class);

    @ModelAttribute("meinesichtungen")
    public void initListe(Model m){
        ArrayList<Sichtung> meineSichtis = new ArrayList<Sichtung>();
        meineSichtis.add(new Sichtung("lelo", "Wiesbaden", LocalDate.of(2021,04,12),"17 gelbe Autos "));
        meineSichtis.add(new Sichtung("heinz", "Limburg", LocalDate.of(2021,02,11),"katze mit 17 augen "));
        meineSichtis.add(new Sichtung("horst", "Hamburg", LocalDate.of(2021,01,01),"17 Asse gezogen. "));

        logger.info("Default-Sichtungen hinzugefuegt.");

        m.addAttribute("meinesichtungen", meineSichtis);
    }

    @GetMapping("/sichtung/meine")
    public String sichtung_get(Model m){
        return "sichtung/meine/liste";
    }

    @GetMapping("/sichtung/meine/neu")
    public String sichtung_getNeu(Model m){
        m.addAttribute("meinesichtungform", new Sichtung());
        return "sichtung/meine/bearbeiten";
    }

    @GetMapping("/sichtung/meine/{index}")
    public String sichtung_getEdit(Model m,@ModelAttribute("meinesichtungen") ArrayList<Sichtung> meineSichtungen,@ModelAttribute("meinesichtungform") Sichtung meineSichtungForm,@PathVariable("index") int index){
        //meineSichtungForm = meineSichtungen.get(index-1);
        String myName = meineSichtungen.get(index).getName();
        String myOrt = meineSichtungen.get(index).getOrt();
        String myBeschreibung = meineSichtungen.get(index).getBeschreibung();
        LocalDate myDatum = meineSichtungen.get(index).getDatum();
        meineSichtungForm = new Sichtung(myName, myOrt ,myDatum, myBeschreibung );
        meineSichtungen.remove(index-1);
        m.addAttribute("meinesichtungform", meineSichtungForm);
      
        return "sichtung/meine/bearbeiten";
    }

    @GetMapping("/sichtung/meine/{index}/del")
    public String sichtung_getDelete(Model m,@ModelAttribute("meinesichtungen") ArrayList<Sichtung> meineSichtungen, @PathVariable("index") int index){
        meineSichtungen.remove(index);
        logger.info("ausgewaehlte Sichtung gelöscht");
        return "redirect:/sichtung/meine";
    }

    @PostMapping("/sichtung/meine/neu")
    public String sichtung_postNeu(Model m, @Valid @ModelAttribute("meinesichtungform") Sichtung meineSichtungForm, BindingResult sichtungformErrors, @ModelAttribute("meinesichtungen") ArrayList<Sichtung> meineSichtungen){

        //wenn Validierungsfehler auftreten auf der gleichen Seite bleiben
        if(sichtungformErrors.hasErrors()){
            logger.info("Validierungsfehler im Formular.");
            return "sichtung/meine/bearbeiten";
        }

        //wenn keine Validierungsfehler auftreten Sichtung hinzufuegen und redirect zur Uebersicht 
        meineSichtungen.add(meineSichtungForm);
        logger.info("Neue Sichtung hinzugefuegt, leite zurueck zur Uebersicht");
        return "redirect:/sichtung/meine";

    }
    
}

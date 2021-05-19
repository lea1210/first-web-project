package de.hsrm.mi.web.projekt.foto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class FotoController {
    Logger logger = LoggerFactory.getLogger(FotoController.class);
    @Autowired FotoService fbservice;

    @ModelAttribute("fotos")
    public void initListe(Model m){
        List<Foto> meineFotos = new ArrayList<Foto>();

        m.addAttribute("fotos", meineFotos);
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> foto_getId(Model m, @PathVariable("id") long id){
        logger.info("----------------------------------------");
        logger.info("GetId erreicht.");

        Optional<Foto> optFoto = fbservice.fotoAbfragenNachId(id);

        if (optFoto.isPresent()){
            logger.info("Es gibt ein Foto");
            Foto foto = optFoto.get();

            return ResponseEntity.ok()
                .header("Content-Type", foto.getMimetype())
                .body(foto.getFotodaten());
        }else{
            logger.info("Kein Foto vorhanden.");
            return null;
        }

        
    }
    @GetMapping("/foto/{id}/del")
    public String foto_getDelete(Model m, @PathVariable("id") long id){
        fbservice.loescheFoto(id);
        logger.info("Foto mit folgender Id gelöscht: " + id);
        return "redirect:/foto";
    }

    @GetMapping("/foto")
    public String foto_get(Model m, @ModelAttribute("fotos") ArrayList<Foto> fotos){

        fotos.addAll(fbservice.alleFotosNachZeitstempelSortiert());
        logger.info("Fotos wurden der Liste hizugefuegt.");
        m.addAttribute("fotos", fotos);

        return "foto/liste";
       
    }

    @PostMapping("/foto")
    public String foto_post(Model m, @RequestParam("datei") MultipartFile datei, @ModelAttribute("fotos") ArrayList<Foto> fotos ){
        String dateiname = datei.getOriginalFilename();
        String mimetype = datei.getContentType();
        byte[] fotodaten = null;
        try {
            fotodaten = datei.getBytes();
        } catch (IOException e) {
            logger.info("Datei konnte nicht geladen werden.");
            e.printStackTrace();
        }

        Foto newFoto = new Foto(dateiname, mimetype, fotodaten);

        if(datei.getSize()>= 17){
            fbservice.fotoAbspeichern(newFoto);
            logger.info("Foto wurde abgespeichert");
        }

        fotos.addAll(fbservice.alleFotosNachZeitstempelSortiert());
        logger.info("Fotos wurden der Liste hizugefuegt.");
        m.addAttribute("fotos", fotos);

        return "foto/liste";
        
    }
    
}

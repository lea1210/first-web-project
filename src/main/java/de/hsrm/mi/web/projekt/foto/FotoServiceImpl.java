package de.hsrm.mi.web.projekt.foto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hsrm.mi.web.projekt.messaging.FotoMessage;
import de.hsrm.mi.web.projekt.utils.FotoBearbeitungService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional
@Service
public class FotoServiceImpl implements FotoService{
    @Autowired SimpMessagingTemplate broker;
    @Autowired FotoBearbeitungService fbservice;
    @Autowired FotoRepository fotorep;
    @Autowired AdressService adressService;
    Logger logger = LoggerFactory.getLogger(FotoServiceImpl.class);

    @Override
    public Foto fotoAbspeichern(Foto foto) {
        fbservice.aktualisiereMetadaten(foto);
        fbservice.orientiereFoto(foto);

        //wenn ein entsprechender Ort vorhanden ist wird dieser gesetzt
        Optional <String>  ort = adressService.findeAdresse(foto.getGeobreite(), foto.getGeolaenge());
        if(ort.isPresent()){
            logger.info("Es gibt einen Ort: " + ort.get());
            foto.setOrt(ort.get());
        }

        Foto managedFoto = fotorep.save(foto);

        //sendet FotoMessage mit Operationswert FOTO_GESPEICHERT
        FotoMessage fMessage = new FotoMessage();
        fMessage.setOperation(FotoMessage.FOTO_GESPEICHERT); 
        broker.convertAndSend("/topic/foto", fMessage);
        logger.info("---------------------------------------------------------------------------------------------------");
        logger.info("Operation Foto_Gespeichrt an Topic Foto gesendet");

        return managedFoto;
    }

    @Override
    public Optional<Foto> fotoAbfragenNachId(Long id) {
        Optional<Foto> foundFoto = fotorep.findById(id);
        return foundFoto;
    }

    @Override
    public List<Foto> alleFotosNachZeitstempelSortiert() {
        List<Foto> foundFotos = fotorep.findAll(Sort.by("zeitstempel"));
        return foundFotos;
    }

    @Override
    public void loescheFoto(Long id) {
        fotorep.deleteById(id);

        //sendet FotoMessage mit Operationswert FOTO_GELOESCHT 
        FotoMessage fMessage = new FotoMessage();
        fMessage.setOperation(FotoMessage.FOTO_GELOESCHT); 
        broker.convertAndSend("/topic/foto", fMessage);
        logger.info("---------------------------------------------------------------------------------------------------");
        logger.info("Operation Foto_Geloescht an Topic Foto gesendet");

    }

    @Override
    public void fotoKommentieren(long id, String autor, String kommentar) throws NoSuchElementException{
        Optional<Foto> foundFoto = fotorep.findById(id);
        if(foundFoto.isPresent()){
            Foto actFoto = foundFoto.get();
            Kommentar actKom = new Kommentar(autor, kommentar);
            actFoto.getKommentare().add(actKom);
            fotorep.save(actFoto);
        }else{
            throw new NoSuchElementException();
        }
        
    }

    @Override
    public List<Kommentar> alleKommentareFuerFoto(long fotoid) throws NoSuchElementException{
        Optional<Foto> foundFoto = fotorep.findById(fotoid);
        if(foundFoto.isPresent()){
            Foto actFoto = foundFoto.get();
            return actFoto.getKommentare();
        }else{
            throw new NoSuchElementException();
            
        }
    }

    @Override
    public void fotoKommentarLoeschen(long fotoid, long kid) throws NoSuchElementException{
        boolean falscheKId = true;
        Optional<Foto> foundFoto = fotorep.findById(fotoid);
        if(foundFoto.isPresent()){
            Foto actFoto = foundFoto.get();
            List<Kommentar> kommentare = actFoto.getKommentare();
            for(int i = 0; i<kommentare.size(); i++){
                if(kommentare.get(i).getId() == kid){
                    kommentare.remove(i);
                    falscheKId = false;
                }
            }
            if(falscheKId){
                throw new NoSuchElementException();
            }
        
        }else{
            throw new NoSuchElementException();
            
        }
    }

    
}

package de.hsrm.mi.web.projekt.foto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.utils.FotoBearbeitungService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FotoServiceImpl implements FotoService{
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
    }
    
}

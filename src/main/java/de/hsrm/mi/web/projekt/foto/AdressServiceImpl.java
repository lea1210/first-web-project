package de.hsrm.mi.web.projekt.foto;

import java.net.URI;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdressServiceImpl implements AdressService{
    Logger logger = LoggerFactory.getLogger(AdressServiceImpl.class);

    @Override
    public Optional<String> findeAdresse(double geobreite, double geolaenge) {
        RestTemplate resttemplate = new RestTemplate();
        Adresse adress = resttemplate.getForObject(URI.create("https://nominatim.openstreetmap.org/reverse?lat="+ geobreite + "&lon=" + geolaenge + "&format=json"),Adresse.class);
        
        //wenn Displayname durch RestTemplate initialisert wurde, wird es in optResult gelegt, sonst null
        Optional<String> optResult = Optional.ofNullable(adress.getDisplay_name());
        
        return optResult;
    }


    
}

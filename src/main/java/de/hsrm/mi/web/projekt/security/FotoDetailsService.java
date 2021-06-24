package de.hsrm.mi.web.projekt.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FotoDetailsService implements UserDetailsService{
    @Autowired private FotoUserRepository fotoUserRepository;
    @Autowired PasswordEncoder pwenc;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Passwort-Encoder zum erschlüsseln d. Passworts für Vergleich

        // Schritt 1: notwendige Daten zu Login-Name 'username' besorgen, z.B. aus Datenbank
        Optional<FotoUser> user = fotoUserRepository.findById(username);
        FotoUser realUser = null;
        if(user ==null) {
          throw new UsernameNotFoundException(username); 
        }else{
            realUser = user.get();
        }
        // Schritt 2: Spring 'User'-Objekt mit relevanten Daten für 'username' zurückgeben
        return org.springframework.security.core.userdetails.User
            .withUsername(username)
            .password(pwenc.encode(realUser.getPassword()))
            .roles() 
            .build();
    }
    
}

package de.hsrm.mi.web.projekt.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes(names = {"loggedinusername"})
public class LoginController{
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ModelAttribute("loggedinusername")
    public void setLoggedInUser(Model m){
        String loggedIn = "";
        m.addAttribute("loggedinusername", loggedIn);
    }
    

    @GetMapping("/login")
    public String login_get(Model m){
        return "login";
    }

    @PostMapping("/login")
    public String login_post(Model m, @RequestParam String username, @RequestParam String password, @ModelAttribute("loggedinusername") String loggedinuser){
        String correctPassword = username + username.length();
        logger.info("Korrektes Passwort: " + correctPassword);

        if (password.equals(correctPassword)){
            loggedinuser = username;
            m.addAttribute("loggedinusername", username);
            logger.info("Passwort korrekt");
            return "redirect:/sichtung/meine";
        }else{
            loggedinuser = "";
            m.addAttribute("loggedinusername", "");
            logger.info("Passwort falsch");
            m.addAttribute("username", username);
            m.addAttribute("correctPassword", correctPassword);
            return "login";
        }

        
    }
}
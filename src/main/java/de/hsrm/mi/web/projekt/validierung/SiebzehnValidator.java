package de.hsrm.mi.web.projekt.validierung;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SiebzehnValidator implements ConstraintValidator<Siebzehnhaft, String>{
    Logger logger = LoggerFactory.getLogger(SiebzehnValidator.class);
    protected String siebzehn;

    @Override
    public void initialize(Siebzehnhaft annotationSiebzehnhaft){
        this.siebzehn = annotationSiebzehnhaft.value();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        logger.info("value wert: "  + value);
        if(value==null || value == "" || value == " "){
            return false;
        } 
        return (value.contains("17") ||value.contains("siebzehn") ||value.contains("Siebzehn "));
    }


}
package de.hsrm.mi.web.projekt.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import de.hsrm.mi.web.projekt.foto.Foto;
import de.hsrm.mi.web.projekt.foto.FotoService;
import de.hsrm.mi.web.projekt.foto.Kommentar;

@RestController
@RequestMapping("/api")
public class FotoRestController {
    @Autowired FotoService fservice;
    

    @GetMapping("/foto")
    public List<Foto> fotoRest_getFoto(Model m){
        return fservice.alleFotosNachZeitstempelSortiert();
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> foto_getId(Model m, @PathVariable("id") long id){

        Optional<Foto> optFoto = fservice.fotoAbfragenNachId(id);

        if (optFoto.isPresent()){
            Foto foto = optFoto.get();

            return ResponseEntity.ok()
                .header("Content-Type", foto.getMimetype())
                .body(foto.getFotodaten());
        }else{
            return null;
        }
    }

    @DeleteMapping("/foto/{id}")
    public void fotoRest_delFoto(Model m, @PathVariable("id")long id){
        fservice.loescheFoto(id);
    }

    @DeleteMapping("/foto/{id}/kommentar/{kid}")
    public void fotoRest_delKom(Model m, @PathVariable("id")long id,@PathVariable("kid")long kid ){
        fservice.fotoKommentarLoeschen(id, kid);
    }

    @GetMapping("/foto/{id}/kommentar")
    public List<Kommentar>  fotoRest_getKommentar(Model m, @PathVariable("id")long id){
        return fservice.alleKommentareFuerFoto(id);
    }

}

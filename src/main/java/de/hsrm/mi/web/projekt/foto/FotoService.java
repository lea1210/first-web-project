package de.hsrm.mi.web.projekt.foto;

import java.util.List;
import java.util.Optional;

public interface FotoService{
    public Foto fotoAbspeichern(Foto foto);
    public Optional<Foto> fotoAbfragenNachId(Long Id);
    public List<Foto> alleFotosNachZeitstempelSortiert();
    public void loescheFoto(Long id);

}
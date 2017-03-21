package co.com.ias.service;

import co.com.ias.model.Ave;
import co.com.ias.model.Zona;

import java.util.List;

/**
 * Created by Marlon Olaya on 20/03/2017.
 */
public interface PersistenceService {

    public List<Zona> getZonas();
    public List<Ave> getAves();
    public List<Ave> search(String name, String zona);
    public Ave addAve(Ave ave);
    public Ave updateAve(Ave ave);
    public void deleteAve(Integer id);
}

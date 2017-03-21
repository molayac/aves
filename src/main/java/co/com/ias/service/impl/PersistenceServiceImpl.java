package co.com.ias.service.impl;

import co.com.ias.model.Ave;
import co.com.ias.model.Pais;
import co.com.ias.model.Zona;
import co.com.ias.repository.AveRepository;
import co.com.ias.repository.PaisRepository;
import co.com.ias.repository.ZonaRepository;
import co.com.ias.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marlon Olaya on 20/03/2017.
 */
@Service
public class PersistenceServiceImpl implements PersistenceService{
    private AveRepository aveRepo;
    private PaisRepository paisRepo;
    private ZonaRepository zonaRepo;

    @Autowired
    public void setAveRepo(AveRepository aveRepo) {
        this.aveRepo = aveRepo;
    }
    @Autowired
    public void setPaisRepo(PaisRepository paisRepo) {
        this.paisRepo = paisRepo;
    }
    @Autowired
    public void setZonaRepo(ZonaRepository zonaRepo) {
        this.zonaRepo = zonaRepo;
    }

    @Override
    public List<Zona> getZonas() {
        return zonaRepo.findAll();
    }

    @Override
    public List<Ave> getAves() {
        return aveRepo.findAll();
    }

    @Override
    public List<Ave> search(String name, String zona) {
        if(zona == null || zona.isEmpty()){
            if(name==null || name.isEmpty())
                return aveRepo.findAll();
            return aveRepo.findByNameContaining(name);
        }else if(name == null || name.isEmpty()){
            return aveRepo.findByPaises_Zona_Id(Integer.parseInt(zona));
        }
        return aveRepo.findByNameAndZona(Integer.parseInt(zona), name);
    }

    @Override
    public Ave addAve(Ave ave) {
        List<Pais> paises = new ArrayList<Pais>();
        /*Encuentra los paises viejos y les pone el id, para que no se creen otra vez*/
        ave.getPaises().forEach(
                p ->{
                    if(paisRepo.findByDsNombreCount(p.getDsNombre())==0)
                        paises.add(p);
                    else
                        paises.add(paisRepo.findByDsNombre(p.getDsNombre()));
                });
        ave.setPaises(paises);
        return aveRepo.saveAndFlush(ave);
    }

    @Override
    public Ave updateAve(Ave ave) {
        return aveRepo.saveAndFlush(ave);
    }

    @Override
    public void deleteAve(Integer id) {
        aveRepo.delete(id);
    }
}

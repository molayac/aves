package co.com.ias.controller;

import co.com.ias.model.Ave;
import co.com.ias.model.Zona;

import co.com.ias.service.impl.PersistenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marlon Olaya on 18/03/2017.
 */
@RestController
@RequestMapping("/aves")
public class AppRestController {
    private PersistenceServiceImpl persistenceService;

    @Autowired
    public void setPersistenceService(PersistenceServiceImpl persistenceService) {
        this.persistenceService = persistenceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Ave> findItems() {
        List<Ave> aves = persistenceService.getAves();
        return aves;
    }

    /**
     * Este metodo permite crear nuevas aves, a su vez que crea el Pais.
     * @param ave Recibe un Json que contiene los datos del ave y el pais.
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Ave addItem(@RequestBody Ave ave) {
        ave.setId(null);
        return persistenceService.addAve(ave);
    }

    /**
     * Este metodo permite buscar por uno o los dos campos,
     * @param zona ID de zona a buscar
     * @param n Nombre A buscar en los comun y cientifico
     * @return Listado de Aves
     */
    @RequestMapping(value="search", method = RequestMethod.GET)
    public List<Ave> searchAvesByNameAndZone(@RequestParam(required=false) String zona, @RequestParam(required=false) String n) {
        return persistenceService.search(n, zona);
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Ave updateItem(@RequestBody Ave updatedItem, @PathVariable Integer id) {
        updatedItem.setId(id);
        return persistenceService.updateAve(updatedItem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable Integer id) {
        persistenceService.deleteAve(id);
    }

    @RequestMapping("/zonas")
    public List<Zona> getZonas(){
        return persistenceService.getZonas();
    }

}

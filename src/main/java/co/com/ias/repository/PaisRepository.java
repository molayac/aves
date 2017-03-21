package co.com.ias.repository;

import co.com.ias.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Marlon Olaya on 18/03/2017.
 */
public interface PaisRepository extends JpaRepository<Pais, Integer> {
    @Query(value ="SELECT count(*) FROM TONT_PAISES WHERE DSNOMBRE = ?1", nativeQuery = true)
    Integer findByDsNombreCount(String dsNombre);

    Pais findByDsNombre(String dsNombre);

}

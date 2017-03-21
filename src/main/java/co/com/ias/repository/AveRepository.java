package co.com.ias.repository;

/**
 * Created by Marlon Olaya on 18/03/2017.
 */
import co.com.ias.model.Ave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AveRepository extends JpaRepository<Ave, Integer> {

    public static final String findByZoneIdAndDsNombreLike = "SELECT * " +
            "FROM tont_aves a " +
            "LEFT JOIN tont_aves_pais ap ON a.cdave = ap.cdave " +
            "INNER JOIN tont_paises p ON ap.cdpais = p.cdpais " +
            "LEFT JOIN tont_zonas z ON p.cdzona = z.cdzona " +
            "where z.cdzona = ?1 AND (a.dsnombre_comun like %?2% OR a.dsnombre_cientifico like %?2%) ";
/*    public static final String findByZoneId = "SELECT * " +
            "FROM tont_aves a " +
            "LEFT JOIN tont_aves_pais ap ON a.cdave = ap.cdave " +
            "INNER JOIN tont_paises p ON ap.cdpais = p.cdpais " +
            "LEFT JOIN tont_zonas z ON p.cdzona = z.cdzona " +
            "where z.cdzona = ?1 ";
*/

    @Query(value ="Select * from tont_aves a where a.DSNOMBRE_COMUN like %?1% OR a.DSNOMBRE_CIENTIFICO like %?1%", nativeQuery = true)
    List<Ave> findByNameContaining(String partname);

    @Query(value= findByZoneIdAndDsNombreLike,nativeQuery = true)
    List<Ave> findByNameAndZona(Integer zona, String search);

    List<Ave> findByPaises_Zona_Id(Integer zona);


}

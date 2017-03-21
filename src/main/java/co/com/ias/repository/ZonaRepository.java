package co.com.ias.repository;

/**
 * Created by Marlon Olaya on 18/03/2017.
 */

import co.com.ias.model.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZonaRepository extends JpaRepository<Zona, Integer> {
}

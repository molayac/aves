package co.com.ias.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Marlon Olaya on 18/03/2017.
 */

@Entity
@Table(name="TONT_AVES")
public class Ave implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="CDAVE")
    private Integer id;
    @Column(name="DSNOMBRE_COMUN")
    private String dsNombreComun;
    @Column(name="DSNOMBRE_CIENTIFICO")
    private String dsNombreCientifico;

    @JoinTable(name = "TONT_AVES_PAIS",
            joinColumns={
                    @JoinColumn(name = "cdave", referencedColumnName = "cdave")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "cdpais", referencedColumnName = "cdpais")
            }
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Pais> paises;

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDsNombreComun() {
        return dsNombreComun;
    }

    public void setDsNombreComun(String dsNombreComun) {
        this.dsNombreComun = dsNombreComun;
    }

    public String getDsNombreCientifico() {
        return dsNombreCientifico;
    }

    public void setDsNombreCientifico(String dsNombreCientifico) {
        this.dsNombreCientifico = dsNombreCientifico;
    }
}
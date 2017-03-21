package co.com.ias.model;

/**
 * Created by Marlon Olaya on 18/03/2017.
 */
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name="TONT_PAISES")
public class Pais{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="CDPAIS")
    private Integer id;
    @Column(name="DSNOMBRE")
    @NotNull
    private String dsNombre;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy = "paises", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private List<Ave> aves;


    /*Allow to merge tables EAGER cause LAZY generate recursive loop infinite*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CDZONA")
    private Zona zona;


    public List<Ave> getAves() {
        return aves;
    }

    public void setAves(List<Ave> aves) {
        this.aves = aves;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDsNombre() {
        return dsNombre;
    }

    public void setDsNombre(String dsNombre) {
        this.dsNombre = dsNombre;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
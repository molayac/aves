package co.com.ias.model;

/**
 * Created by Marlon Olaya on 18/03/2017.
 */
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="TONT_ZONAS")
public class Zona {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="CDZONA")

    private Integer id;
    @Column(name="DSNOMBRE")
    @NotNull
    private String dsNombre;

    @OneToMany(mappedBy = "zona")
    private Set<Pais> pais;

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

    @JsonBackReference
    public Set<Pais> getPais() {
        return pais;
    }

    public void setPais(Set<Pais> pais) {
        this.pais = pais;
    }
}
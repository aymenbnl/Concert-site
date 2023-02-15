package pojo_MySql;

import javax.persistence.*;

@Entity
@Table(name = "Soiree")
@NamedQueries({
        @NamedQuery(name = "Soiree.FindAll", query = "select s from Soiree s"),
        @NamedQuery(name = "Soiree.FindById", query = "select s from Soiree s where s.idSoiree = :id"),
})
public class Soiree {
    @Id
    @Column(name = "id_soiree")
    private Integer idSoiree;

    @Column(name = "nom")
    private String nom;

    @Column(name = "id_salle")
    private Integer idSalle;

    public Integer getIdSoiree() {
        return this.idSoiree;
    }

    public void setIdSoiree(Integer idSoiree) {
        this.idSoiree = idSoiree;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getIdSalle() {
        return this.idSalle;
    }

    public void setIdSalle(Integer idSalle) {
        this.idSalle = idSalle;
    }
}

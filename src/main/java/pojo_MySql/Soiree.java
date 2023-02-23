package pojo_MySql;

import javax.persistence.*;

@Entity
@Table(name = "Soiree")
@NamedQueries({
        @NamedQuery(name = "Soiree.findAll", query = "select s from Soiree s"),
        @NamedQuery(name = "Soiree.findById", query = "select s from Soiree s where s.idSoiree = :id"),
})
public class Soiree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_soiree")
    private Integer idSoiree;

    @Column(name = "nom")
    private String nom;

    @JoinColumn(name = "id_salle", referencedColumnName = "id_salle")
    @ManyToOne
    private Salle salle;

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

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}

package pojo_MySql;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Concert")
@NamedQueries({
        @NamedQuery(name = "Concert.findAll", query = "select c from Concert c"),
        @NamedQuery(name = "Concert.findById", query = "select c from Concert c where c.idConcert = :id"),
})
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_concert")
    private Integer idConcert;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "heure_debut")
    private java.sql.Time heure_debut;

    @Column(name = "heure_fin")
    private java.sql.Time heure_fin;

    @JoinColumn(name = "id_groupe", referencedColumnName = "id_groupe")
    @ManyToOne
    private Groupe groupe;

    @JoinColumn(name = "id_soiree", referencedColumnName = "id_soiree")
    @ManyToOne
    private Soiree soiree;

    @OneToMany(mappedBy = "concert", cascade= CascadeType.REMOVE)
    private List<Ticket> listTicket;

    public Integer getIdConcert() {
        return this.idConcert;
    }

    public void setIdConcert(Integer idConcert) {
        this.idConcert = idConcert;
    }

    public java.sql.Date getDate() {
        return this.date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public java.sql.Time getHeureDebut() {
        return this.heure_debut;
    }

    public void setHeureDebut(java.sql.Time heure_debut) {
        this.heure_debut = heure_debut;
    }

    public java.sql.Time getHeureFin() {
        return this.heure_debut;
    }

    public void setHeureFin(java.sql.Time heureFin) {
        this.heure_fin = heureFin;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Soiree getSoiree() {
        return soiree;
    }

    public void setSoiree(Soiree soiree) {
        this.soiree = soiree;
    }

    @Override
    public String toString(){
        return this.idConcert + " " + this.date + " " + this.heure_debut + "=>" + this.heure_fin + " : " + this.listTicket;
    }
}

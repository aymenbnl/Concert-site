package pojo_MySql;

import javax.persistence.*;

@Entity
@Table(name = "Concert")
@NamedQueries({
        @NamedQuery(name = "Concert.findAll", query = "select c from Concert c"),
        @NamedQuery(name = "Concert.findById", query = "select c from Concert c where c.idConcert = :id"),
})
public class Concert {
    @Id
    @Column(name = "id_concert")
    private Integer idConcert;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "heure")
    private java.sql.Time heure;

    @Column(name = "duree_prev")
    private java.sql.Time dureePrev;

    @JoinColumn(name = "id_groupe", referencedColumnName = "id_groupe")
    @ManyToOne
    private Groupe groupe;

    @JoinColumn(name = "id_soiree", referencedColumnName = "id_soiree")
    @ManyToOne
    private Soiree soiree;

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

    public java.sql.Time getHeure() {
        return this.heure;
    }

    public void setHeure(java.sql.Time heure) {
        this.heure = heure;
    }

    public java.sql.Time getDureePrev() {
        return this.dureePrev;
    }

    public void setDureePrev(java.sql.Time dureePrev) {
        this.dureePrev = dureePrev;
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
}

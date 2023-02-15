package pojo_MySql;

import javax.persistence.*;

@Entity
@Table(name = "Concert")
@NamedQueries({
        @NamedQuery(name = "Concert.FindAll", query = "select c from Concert c"),
        @NamedQuery(name = "Concert.FindById", query = "select c from Concert c where c.idConcert = :id"),
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

    @Column(name = "id_groupe")
    private Integer idGroupe;

    @Column(name = "id_soiree")
    private Integer idSoiree;

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

    public Integer getIdGroupe() {
        return this.idGroupe;
    }

    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public Integer getIdSoiree() {
        return this.idSoiree;
    }

    public void setIdSoiree(Integer idSoiree) {
        this.idSoiree = idSoiree;
    }
}

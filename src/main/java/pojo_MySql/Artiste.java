package pojo_MySql;

import javax.persistence.*;

@Entity
@Table(name = "Artiste")
@NamedQueries({
        @NamedQuery(name = "Artiste.FindAll", query = "select a from Artiste a"),
        @NamedQuery(name = "Artiste.FindById", query = "select a from Artiste a where a.idArtiste = :id"),
})
public class Artiste {
    @Id
    @Column(name = "id_artiste")
    private Integer idArtiste;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "ville_origine")
    private String villeOrigine;

    @Column(name = "age")
    private Integer age;

    @JoinColumn(name = "id_groupe", referencedColumnName = "id_groupe")
    @ManyToOne
    private Groupe groupe;

    public Integer getIdArtiste() {
        return this.idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getVilleOrigine() {
        return this.villeOrigine;
    }

    public void setVilleOrigine(String villeOrigine) {
        this.villeOrigine = villeOrigine;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
}

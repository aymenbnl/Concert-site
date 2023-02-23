package pojo_MySql;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Groupe")
@NamedQueries({
        @NamedQuery(name = "Groupe.findAll", query = "select g from Groupe g"),
        @NamedQuery(name = "Groupe.findById", query = "select g from Groupe g where g.idGroupe = :id"),
})
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_groupe")
    private Integer idGroupe;

    @OneToMany(mappedBy = "groupe", cascade= CascadeType.REMOVE)
    private Set<Artiste> listArtiste;

    @OneToMany(mappedBy = "groupe", cascade= CascadeType.REMOVE)
    private Set<Concert> listConcert;
    
    public Groupe() {
	}

	public Integer getIdGroupe() {
        return this.idGroupe;
    }

    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public Set<Artiste> getListArtiste() {
        return listArtiste;
    }

    public void addListArtiste(Artiste artiste) {
        this.listArtiste.add(artiste);
    }

    public void delListArtiste(Artiste artiste) {
        this.listArtiste.remove(artiste);
    }

    public Set<Concert> getListConcert() {
        return listConcert;
    }

    public void addListConcert(Concert concert) {
        this.listConcert.add(concert);
    }

    public void delListConcert(Concert concert) {
        this.listArtiste.remove(concert);
    }
}

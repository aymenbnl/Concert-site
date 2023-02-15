package pojo_MySql;

import javax.persistence.*;

@Entity
@Table(name = "Groupe")
@NamedQueries({
        @NamedQuery(name = "Groupe.FindAll", query = "select g from Groupe g"),
        @NamedQuery(name = "Groupe.FindById", query = "select g from Groupe g where g.idGroupe = :id"),
})
public class Groupe {
    @Id
    @Column(name = "id_groupe")
    private Integer idGroupe;

    public Integer getIdGroupe() {
        return this.idGroupe;
    }

    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }
}

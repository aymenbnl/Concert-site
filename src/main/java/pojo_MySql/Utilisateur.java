package pojo_MySql;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Utilisateur")@NamedQueries({
        @NamedQuery(name = "Utilisateur.findAll", query = "select t from Utilisateur t"),
        @NamedQuery(name = "Utilisateur.findById", query = "select t from Utilisateur t where t.idUtilisateur = :id"),
        @NamedQuery(name = "Utilisateur.findByLoginAndPassword", query = "select a from Utilisateur a where a.identifiant = :login and a.motDePasse = :mdp"),
})
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;

    @Column(name = "identifiant")
    private String identifiant;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @OneToMany(mappedBy = "utilisateur", cascade= CascadeType.REMOVE)
    private Set<Ticket> listTicket;

    public Integer getIdUtilisateur() {
        return this.idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdentifiant() {
        return this.identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return this.motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void addListTicket(Ticket ticket) {
        this.listTicket.add(ticket);
    }

    public void delListTicket(Ticket ticket) {
        this.listTicket.remove(ticket);
    }
}

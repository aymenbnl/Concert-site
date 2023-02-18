package pojo_MySql;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {
    @Id
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;

    @Column(name = "identifiant")
    private String identifiant;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @OneToMany(mappedBy = "utilisateur")
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

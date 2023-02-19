package pojo_MySql;

import javax.persistence.*;

@Entity
@Table(name = "Ticket")@NamedQueries({
        @NamedQuery(name = "Ticket.findAll", query = "select t from Ticket t"),
        @NamedQuery(name = "Ticket.findById", query = "select t from Ticket t where t.idTicket = :id"),
})
public class Ticket {
    @Id
    @Column(name = "id_ticket")
    private Integer idTicket;

    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur")
    @ManyToOne
    private Utilisateur utilisateur;

    @JoinColumn(name = "id_concert", referencedColumnName = "id_concert")
    @ManyToOne
    private Concert concert;

    public Integer getIdTicket() {
        return this.idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}

package pojo_MySql;

import javax.persistence.*;

@Entity
@Table(name = "Ticket")@NamedQueries({
        @NamedQuery(name = "Ticket.FindAll", query = "select t from Ticket t"),
        @NamedQuery(name = "Ticket.FindById", query = "select t from Ticket t where t.idTicket = :id"),
})
public class Ticket {
    @Id
    @Column(name = "id_ticket")
    private Integer idTicket;

    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;

    @Column(name = "id_concert")
    private Integer idConcert;

    public Integer getIdTicket() {
        return this.idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getIdUtilisateur() {
        return this.idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdConcert() {
        return this.idConcert;
    }

    public void setIdConcert(Integer idConcert) {
        this.idConcert = idConcert;
    }
}

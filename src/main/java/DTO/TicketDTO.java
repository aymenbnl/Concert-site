package DTO;

import java.util.Objects;

public class TicketDTO {

    private Integer idTicket;
    private Integer idUtilisateur;
    private Integer idConcert;



    public TicketDTO() {
    }

    public TicketDTO(Integer idTicket, Integer idUtilisateur, Integer idConcert) {
        super();
        this.idTicket = idTicket;
        this.idUtilisateur = idUtilisateur;
        this.idConcert = idConcert;

    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdConcert() {
        return idConcert;
    }

    public void setIdConcert(Integer idConcert) {
        this.idConcert = idConcert;
    }


    @Override
    public int hashCode() {
        return Objects.hash(idTicket, idUtilisateur, idConcert);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TicketDTO other = (TicketDTO) obj;
        return Objects.equals(idTicket, other.idTicket) && Objects.equals(idUtilisateur, other.idUtilisateur)
                && Objects.equals(idConcert, other.idConcert) ;
    }

    @Override
    public String toString() {
        return "TicketDTO [idTicket =" + idTicket + ", idUtilisateur =" + idUtilisateur + ", idConcert=" + idConcert +"]";
    }

}

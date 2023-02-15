package pojo_MySql;

import javax.persistence.*;

@Entity
@Table(name = "Salle")
@NamedQueries({
    @NamedQuery(name = "Salle.FindAll", query = "select s from Salle s"),
    @NamedQuery(name = "Salle.FindById", query = "select s from Salle s where s.idSalle = :id"),
})
public class Salle {
    @Id
    @Column(name = "id_salle")
    private Integer idSalle;

    @Column(name = "nom")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "capacite")
    private Integer capacite;

    @Column(name = "nom_gest")
    private String nomGest;

    @Column(name = "prenom_gest")
    private String prenomGest;

    @Column(name = "association")
    private String association;

    public Integer getIdSalle() {
        return this.idSalle;
    }

    public void setIdSalle(Integer idSalle) {
        this.idSalle = idSalle;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getCapacite() {
        return this.capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public String getNomGest() {
        return this.nomGest;
    }

    public void setNomGest(String nomGest) {
        this.nomGest = nomGest;
    }

    public String getPrenomGest() {
        return this.prenomGest;
    }

    public void setPrenomGest(String prenomGest) {
        this.prenomGest = prenomGest;
    }

    public String getAssociation() {
        return this.association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }
}

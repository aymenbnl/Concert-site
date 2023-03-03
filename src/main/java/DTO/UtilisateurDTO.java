package DTO;

import java.util.Objects;

public class UtilisateurDTO {


    private Integer idUtilisateur;
    private String identifiant;
    private String motDePasse;

    public UtilisateurDTO() {
    }
    public UtilisateurDTO(Integer idUtilisateur, String identifiant, String motDePasse) {
        super();
        this.idUtilisateur = idUtilisateur;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String  getMotPasse() {
        return motDePasse;
    }

    public void setMotPasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, identifiant, motDePasse);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UtilisateurDTO other = (UtilisateurDTO) obj;
        return Objects.equals(identifiant, other.identifiant) && Objects.equals(idUtilisateur, other.idUtilisateur)
                && Objects.equals(motDePasse, other.motDePasse) ;
    }

    @Override
    public String toString() {
        return "UtilisateurDTO [idUtilisateur =" + idUtilisateur + ", identifiant =" + identifiant + ", mot de passe=" + motDePasse +"]";
    }

}

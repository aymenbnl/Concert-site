package DTO;

import java.util.Objects;

public class ArtisteDTO {
	
    private Integer idArtiste;

    private String pseudo;

    private String villeOrigine;

    private Integer age;

    private Integer idGroupe;
    
    

	public ArtisteDTO() {
	}

	public ArtisteDTO(Integer idArtiste, String pseudo, String villeOrigine, Integer age, Integer idGroupe) {
		super();
		this.idArtiste = idArtiste;
		this.pseudo = pseudo;
		this.villeOrigine = villeOrigine;
		this.age = age;
		this.idGroupe = idGroupe;
	}

	public Integer getIdArtiste() {
		return idArtiste;
	}

	public void setIdArtiste(Integer idArtiste) {
		this.idArtiste = idArtiste;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getVilleOrigine() {
		return villeOrigine;
	}

	public void setVilleOrigine(String villeOrigine) {
		this.villeOrigine = villeOrigine;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getIdGroupe() {
		return idGroupe;
	}

	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, idArtiste, idGroupe, pseudo, villeOrigine);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtisteDTO other = (ArtisteDTO) obj;
		return Objects.equals(age, other.age) && Objects.equals(idArtiste, other.idArtiste)
				&& Objects.equals(idGroupe, other.idGroupe) && Objects.equals(pseudo, other.pseudo)
				&& Objects.equals(villeOrigine, other.villeOrigine);
	}

	@Override
	public String toString() {
		return "ArtisteDTO [idArtiste=" + idArtiste + ", pseudo=" + pseudo + ", villeOrigine=" + villeOrigine + ", age="
				+ age + ", idGroupe=" + idGroupe + "]";
	}
    
    
}

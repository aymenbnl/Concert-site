package DTO;

import java.util.Objects;
import java.util.Set;

public class GroupeDTO {
	
    private Integer idGroupe;

    private Set<ArtisteDTO> listArtiste;

    private Set<Integer> listConcert;
    
    
	public GroupeDTO() {
	}

	public GroupeDTO(Integer idGroupe, Set<ArtisteDTO> listArtiste, Set<Integer> listConcert) {
		super();
		this.idGroupe = idGroupe;
		this.listArtiste = listArtiste;
		this.listConcert = listConcert;
	}

	public Integer getIdGroupe() {
		return idGroupe;
	}

	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}

	public Set<ArtisteDTO> getListArtiste() {
		return listArtiste;
	}

	public void setListArtiste(Set<ArtisteDTO> listArtiste) {
		this.listArtiste = listArtiste;
	}

	public Set<Integer> getListConcert() {
		return listConcert;
	}

	public void setListConcert(Set<Integer> listConcert) {
		this.listConcert = listConcert;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idGroupe, listArtiste, listConcert);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupeDTO other = (GroupeDTO) obj;
		return Objects.equals(idGroupe, other.idGroupe) && Objects.equals(listArtiste, other.listArtiste)
				&& Objects.equals(listConcert, other.listConcert);
	}

	@Override
	public String toString() {
		return "GroupeDTO [idGroupe=" + idGroupe + ", listArtiste=" + listArtiste + ", listConcert=" + listConcert
				+ "]";
	}
    
}

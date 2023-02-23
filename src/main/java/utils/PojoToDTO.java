package utils;

import java.util.HashSet;
import java.util.Set;

import DTO.ArtisteDTO;
import DTO.GroupeDTO;
import pojo_MySql.Artiste;
import pojo_MySql.Concert;
import pojo_MySql.Groupe;

public class PojoToDTO {
	public static GroupeDTO forGroupe(Groupe groupe) {
		GroupeDTO groupeDTO = new GroupeDTO();
		
		groupeDTO.setIdGroupe(groupe.getIdGroupe());
		
		Set<ArtisteDTO> artistes = new HashSet<>();
		for(Artiste a: groupe.getListArtiste()) {
			artistes.add(forArtiste(a));
		}
		groupeDTO.setListArtiste(artistes);
		
		Set<Integer> concerts = new HashSet<>();
		for(Concert c: groupe.getListConcert()) {
			concerts.add(c.getIdConcert());
		}
		groupeDTO.setListConcert(concerts);
		
		return groupeDTO;
	}
	
	public static ArtisteDTO forArtiste(Artiste artiste) {
		ArtisteDTO artisteDTO = new ArtisteDTO();
		
		artisteDTO.setIdArtiste(artiste.getIdArtiste());
		artisteDTO.setPseudo(artiste.getPseudo());
		artisteDTO.setVilleOrigine(artiste.getVilleOrigine());
		artisteDTO.setAge(artiste.getAge());
		artisteDTO.setIdGroupe(artiste.getGroupe().getIdGroupe());
		
		return artisteDTO;
	}
}

package utils;

import java.util.HashSet;
import java.util.Set;

import DTO.ArtisteDTO;
import DTO.GroupeDTO;
import DTO.TicketDTO;
import DTO.UtilisateurDTO;
import pojo_MySql.Artiste;
import pojo_MySql.Concert;
import pojo_MySql.Groupe;
import pojo_MySql.Ticket;
import pojo_MySql.Utilisateur;

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
	
	public static TicketDTO forTicket(Ticket ticket) {
		TicketDTO ticketDTO = new TicketDTO();

		ticketDTO.setIdTicket(ticket.getIdTicket());
		ticketDTO.setIdUtilisateur(ticket.getUtilisateur().getIdUtilisateur());
		ticketDTO.setIdConcert(ticket.getConcert().getIdConcert());

		return ticketDTO;
	}

	public static UtilisateurDTO forUtilisateur(Utilisateur utilisateur) {
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();

		utilisateurDTO.setIdUtilisateur(utilisateur.getIdUtilisateur());
		utilisateurDTO.setIdentifiant(utilisateur.getIdentifiant());
		utilisateurDTO.setMotPasse(utilisateur.getMotDePasse());

		return utilisateurDTO;
	}
}

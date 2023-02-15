package pojo_MongoDb;

import org.bson.types.ObjectId;

import java.util.Objects;

public class Image {
	private ObjectId id;
	
	private String nom;
	
	private String lien;
	
	private String date;
	
	private Auteur auteur;
	
	private Integer salle;
	
	private Integer concert;
	
	private Integer soiree;
	
	private Integer groupe;
	
	private Integer artiste;

	public Image() {
		super();
	}

	public Image(String nom, String lien, String date, Auteur auteur) {
		super();
		this.nom = nom;
		this.lien = lien;
		this.date = date;
		this.auteur = auteur;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public Integer getSalle() {
		return salle;
	}

	public void setSalle(Integer salle) {
		this.salle = salle;
	}

	public Integer getConcert() {
		return concert;
	}

	public void setConcert(Integer concert) {
		this.concert = concert;
	}

	public Integer getSoiree() {
		return soiree;
	}

	public void setSoiree(Integer soiree) {
		this.soiree = soiree;
	}

	public Integer getGroupe() {
		return groupe;
	}

	public void setGroupe(Integer groupe) {
		this.groupe = groupe;
	}

	public Integer getArtiste() {
		return artiste;
	}

	public void setArtiste(Integer artiste) {
		this.artiste = artiste;
	}

	@Override
	public int hashCode() {
		return Objects.hash(artiste, auteur, concert, date, groupe, id, lien, nom, salle, soiree);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return artiste == other.artiste && Objects.equals(auteur, other.auteur) && concert == other.concert
				&& Objects.equals(date, other.date) && groupe == other.groupe && Objects.equals(id, other.id)
				&& Objects.equals(lien, other.lien) && Objects.equals(nom, other.nom) && salle == other.salle
				&& soiree == other.soiree;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", nom=" + nom + ", lien=" + lien + ", date=" + date + ", auteur=" + auteur
				+ ", salle=" + salle + ", concert=" + concert + ", soiree=" + soiree + ", groupe=" + groupe
				+ ", artiste=" + artiste + "]";
	}
}

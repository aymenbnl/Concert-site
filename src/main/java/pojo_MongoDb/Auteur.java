package pojo_MongoDb;

import java.util.Objects;

public class Auteur {
	private String nom;
	private String prenom;
	private String description;
	
	public Auteur() {
		super();
	}

	public Auteur(String nom, String prenom, String description) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.description = description;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, nom, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auteur other = (Auteur) obj;
		return Objects.equals(description, other.description) && Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom);
	}

	@Override
	public String toString() {
		return "Auteur [nom=" + nom + ", prenom=" + prenom + ", description=" + description + "]";
	}
	
	
}

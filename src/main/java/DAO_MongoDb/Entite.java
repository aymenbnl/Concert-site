package DAO_MongoDb;

public enum Entite {
	salle("salle"), concert("concert"), soiree("soiree"), groupe("groupe"), artiste("artiste");

	public final String label;
	private Entite(String label) {
		this.label=label;
	}
}

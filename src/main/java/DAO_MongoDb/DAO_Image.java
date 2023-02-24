package DAO_MongoDb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import pojo_MongoDb.Image;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class DAO_Image {
	
	private MongoDatabase db;
	
	private String colImages = "images";
	
	public DAO_Image() throws MongoDAOException {
		this.db = MongoDBConnection.getDatabase();
	}
	
	/**
	 * 
	 * @return la liste des images contenues dans la base de donnees
	 * @throws MongoDAOException
	 */
	public List<Image> findImages() throws MongoDAOException {
		try {
			MongoCollection<Image> images = db.getCollection(colImages, Image.class);
			
			List<Image> listeImages = new ArrayList<>();
			for(Image img: images.find()) {
				listeImages.add(img);
			}
			
			return listeImages;
		} catch (Exception e) {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	/**
	 * 
	 * @param nom le nom de l'image
	 * @return une image de la base de donnees
	 * @throws MongoDAOException
	 */
	public Image findImage(String nom) throws MongoDAOException {
		try {
			MongoCollection<Image> images = db.getCollection(colImages, Image.class);
			return images.find(eq("nom", nom)).first();
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	public Image findImage(ObjectId id) throws MongoDAOException {
		try {
			MongoCollection<Image> images = db.getCollection(colImages, Image.class);
			return images.find(eq("_id", id)).first();
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	/**
	 * 
	 * @param entite l'entite (salle, concert, soiree, groupe ou artiste)
	 * @param idEntite l'identifiant de l'entite
	 * @return la liste des images de l'entite
	 * @throws MongoDAOException
	 */
	public List<Image> findImagesEntite(Entite entite, Integer idEntite) throws MongoDAOException {
		try {
			MongoCollection<Image> images = db.getCollection(colImages, Image.class);
			FindIterable<Image> lesImages = images.find(eq(entite.label, idEntite));
			
			List<Image> listeImages = new ArrayList<>();
			
			for(Image img: lesImages) {
				listeImages.add(img);
			}
			
			return listeImages;
		} catch (Exception e) {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	/**
	 * Cree une image
	 * 
	 * @param entite l'entite (salle, concert, soiree, groupe ou artiste)
	 * @param idEntite l'identifiant de l'entite
	 * @param data les donnees (nom, lien, date, auteur) qui vont etre recuperees pour creer l'image
	 * @throws MongoDAOException si une image ayant le même nom existe déjà ou en cas de problème technique
	 */
	public void createImageEntite(Entite entite,Integer idEntite, Image data) throws MongoDAOException {
		try {
			MongoCollection<Image> images = db.getCollection(colImages, Image.class);
			if(images.find(eq("nom", data.getNom())).first()==null) {
				data.setId(null);
				this.setEntiteImage(entite, idEntite, data);
				images.insertOne(data);			
			} else {
				throw new MongoDAOException("Creation image : une image ayant le même nom existe déjà");
			}
		} catch (Exception e) {
			if(!e.getClass().equals(MongoDAOException.class)) {
				throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * Cree une image
	 * @param data les donnees de l'image
	 * @throws MongoDAOException si une image avec le meme nom existe deja ou en cas de problème technique
	 */
	public void createImage(Image data) throws MongoDAOException {
		
		try {
			MongoCollection<Image> images = db.getCollection(colImages, Image.class);
			if(images.find(eq("nom", data.getNom())).first()==null) {
				images.insertOne(data);			
			} else {
				throw new MongoDAOException("Creation image : une image ayant le même nom existe déjà");
			}
		} catch (Exception e) {
			if(!e.getClass().equals(MongoDAOException.class)) {
				throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * Modifie les informations (nom, lien, date, auteur) d'une image.
	 * Ne modifie pas les informations sur l'entite.
	 * 
	 * @param nom le nom de l'image a modifier
	 * @param data les donnees a mettre a jour, celles qui ne sont pas nulles
	 * @throws MongoDAOException
	 */
	public void updateImage(String nom, Image data) throws MongoDAOException {
		try {
			MongoCollection<Image> images = db.getCollection(colImages, Image.class);
			
			List<Bson> updatesList = new ArrayList<>();
			if(data.getNom()!=null) updatesList.add(Updates.set("nom", data.getNom()));
			if(data.getLien()!=null) updatesList.add(Updates.set("lien", data.getLien()));
			if(data.getDate()!=null) updatesList.add(Updates.set("date", data.getDate()));
			if(data.getAuteur()!=null) {
				if(data.getAuteur().getNom()!=null) updatesList.add(Updates.set("auteur.nom", data.getAuteur().getNom()));
				if(data.getAuteur().getPrenom()!=null) updatesList.add(Updates.set("auteur.prenom", data.getAuteur().getPrenom()));
				if(data.getAuteur().getDescription()!=null) updatesList.add(Updates.set("auteur.description", data.getAuteur().getDescription()));
			}
			images.updateOne(eq("nom", nom), Updates.combine(updatesList));
		} catch (Exception e) {
			if(!e.getClass().equals(MongoDAOException.class)) {
				throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * Remplace une image par une autre
	 * @param nom le nom de l'image a remplacer
	 * @param data la nouvelle image
	 * @throws MongoDAOException si l'image a remplacer n'existe pas ou en cas de problème technique
	 */
	public void updateReplaceImage(String nom, Image data) throws MongoDAOException {
		try {
			MongoCollection<Image> images = db.getCollection(colImages, Image.class);
			if(images.find(eq("nom", data.getNom())).first()!=null) {
				images.replaceOne(Filters.eq("nom", nom), data);
			} else {
				throw new MongoDAOException("Remplacement image : l'image n'existe pas");
			}
		} catch (Exception e) {
			if(!e.getClass().equals(MongoDAOException.class)) {
				throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * Supprime une image.
	 * @param nom le nom de l'image
	 * @throws MongoDAOException
	 */
	public void deleteImage(String nom) throws MongoDAOException {
		try {
			MongoCollection<Image> images = db.getCollection(colImages, Image.class);
			images.deleteOne(eq("nom", nom));
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	public void deleteImage(ObjectId id) throws MongoDAOException {
		try {
			MongoCollection<Image> images = db.getCollection(colImages, Image.class);
			images.deleteOne(eq("_id", id));
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	/**
	 * Supprime les images d'une entite (salle, concert, soiree, groupe ou encore artiste)
	 * @param entite l'entite
	 * @param idEntite l'identifiant de l'entite
	 * @throws MongoDAOException
	 */
	public void deleteImagesEntite(Entite entite, Integer idEntite) throws MongoDAOException {
		try {
			MongoCollection<Image> images = db.getCollection(colImages, Image.class);
			images.deleteMany(eq(entite.label, idEntite));
		} catch (Exception e) {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	private void setEntiteImage(Entite entite,Integer idEntite, Image data) {
		if(entite.equals(Entite.salle)) {
			data.setSalle(idEntite);
			data.setConcert(null);
			data.setSoiree(null);
			data.setGroupe(null);
			data.setArtiste(null);
			
		} else if(entite.equals(Entite.concert)) {
			data.setSalle(null);
			data.setConcert(idEntite);
			data.setSoiree(null);
			data.setGroupe(null);
			data.setArtiste(null);
		} else if(entite.equals(Entite.soiree)) {
			data.setSalle(null);
			data.setConcert(null);
			data.setSoiree(idEntite);
			data.setGroupe(null);
			data.setArtiste(null);
		} else if(entite.equals(Entite.groupe)) {
			data.setSalle(null);
			data.setConcert(null);
			data.setSoiree(null);
			data.setGroupe(idEntite);
			data.setArtiste(null);
		} else { // artiste
			data.setSalle(null);
			data.setConcert(null);
			data.setSoiree(null);
			data.setGroupe(null);
			data.setArtiste(idEntite);
		}
		
	}
	
}

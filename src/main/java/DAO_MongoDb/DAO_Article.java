package DAO_MongoDb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import pojo_MongoDb.Article;
import pojo_MongoDb.Auteur;

public class DAO_Article {
	private MongoDatabase db;
	
	private String colArticles = "articles";
	
	public DAO_Article() throws MongoDAOException {
		this.db = MongoDBConnection.getDatabase();
	}
	
	/**
	 * 
	 * @return la liste des articles contenus dans la base de donnees
	 * @throws MongoDAOException
	 */
	public List<Article> findArtciles() throws MongoDAOException {
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
			
			List<Article> listeArticle = new ArrayList<>();
			for(Article art: articles.find()) {
				listeArticle.add(art);
			}
			
			return listeArticle;
		} catch (Exception e) {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	/**
	 * 
	 * @param nom le nom de l'article
	 * @return un article de la base de donnees
	 * @throws MongoDAOException
	 */
	public Article findArticle(String nom) throws MongoDAOException {
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
			return articles.find(eq("nom", nom)).first();
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	public Article findArticle(ObjectId id) throws MongoDAOException {
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
			return articles.find(eq("_id", id)).first();
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	/**
	 * 
	 * @param date la date
	 * @return la liste des articles parues a la date donnee
	 * @throws MongoDAOException
	 */
    public List<Article> findArticleByDate(String date) throws MongoDAOException {  
		try {
	    	MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
	        List<Article> listeArticles = new ArrayList<>();
	        articles.find(Filters.eq("date", date)).forEach((Article article) -> {
	        	listeArticles.add(article);
	        });
	        return listeArticles;
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
    }
    
    public List<Article> findByNameAndFirstNameAuteur(Auteur auteur) throws MongoDAOException {
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
	        List<Article> listeArticles = new ArrayList<>();
	        articles.find(Filters.and(eq("auteur.nom", auteur.getNom()), eq("auteur.prenom", auteur.getPrenom()))).forEach((Article article) -> {
	        	listeArticles.add(article);
	        });
	        return listeArticles;
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
    }
    
    /**
     * 
     * @param date la date
     * @return la liste des raticles parues a une date inferieure ou egale a la date donnee
     * @throws MongoDAOException
     */
    public List<Article> findArticleLessThanOrEqualDate(String date) throws MongoDAOException {  
		try {
	    	MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
	        List<Article> listeArticles = new ArrayList<>();
	        articles.find(Filters.lte("date", date)).forEach((Article article) -> {
	        	listeArticles.add(article);
	        });
	        return listeArticles;
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
    }
    
    /**
     * 
     * @param date la date
     * @return la liste des artices parues a une date strictement inferieure a la date donnee
     * @throws MongoDAOException
     */
    public List<Article> findArticleLessThanDate(String date) throws MongoDAOException {  
		try {
	    	MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
	        List<Article> listeArticles = new ArrayList<>();
	        articles.find(Filters.lt("date", date)).forEach((Article article) -> {
	        	listeArticles.add(article);
	        });
	        return listeArticles;
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
    }
    
    /**
     * 
     * @param date la date
     * @return la liste des articles parues a une date superieure ou egale a la date donnee
     * @throws MongoDAOException
     */
    public List<Article> findArticleGreaterThanOrEqualDate(String date) throws MongoDAOException {  
		try {
	    	MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
	        List<Article> listeArticles = new ArrayList<>();
	        articles.find(Filters.gte("date", date)).forEach((Article article) -> {
	        	listeArticles.add(article);
	        });
	        return listeArticles;
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
    }
    
    /**
     * 
     * @param date la date
     * @return la liste des articles parues a une date strictement inferieure a la date donnee
     * @throws MongoDAOException
     */
    public List<Article> findArticleGreaterThanDate(String date) throws MongoDAOException {  
		try {
	    	MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
	        List<Article> listeArticles = new ArrayList<>();
	        articles.find(Filters.gt("date", date)).forEach((Article article) -> {
	        	listeArticles.add(article);
	        });
	        return listeArticles;
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
    }
	
	/**
	 * 
	 * @param entite l'entite (salle, concert, soiree, groupe ou artiste)
	 * @param idEntite l'identifiant de l'entite
	 * @return la liste des articles de l'entite
	 * @throws MongoDAOException
	 */
	public List<Article> findArticlesEntite(Entite entite, Integer idEntite) throws MongoDAOException { 
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
			FindIterable<Article> lesArticles = articles.find(eq(entite.label, idEntite));
			
			List<Article> listeArticles = new ArrayList<>();
			
			for(Article art: lesArticles) {
				listeArticles.add(art);
			}
			
			return listeArticles;
		} catch (Exception e) {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	/**
	 * Cree un article
	 * 
	 * @param entite l'entite (salle, concert, soiree, groupe ou artiste)
	 * @param idEntite l'identifiant de l'entite
	 * @param data les donnees (nom, lien, date, auteur) qui vont etre recuperees pour creer l'article
	 * @throws MongoDAOException si un article ayant le même nom existe déjà ou en cas de problème technique
	 */
	public void createArticleEntite(Entite entite,Integer idEntite, Article data) throws MongoDAOException {
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
			if(articles.find(eq("nom", data.getNom())).first()==null) {
				data.setId(null);
				this.setEntiteArticle(entite, idEntite, data);
				articles.insertOne(data);			
			} else {
				throw new MongoDAOException("Creation article : un article ayant le même nom existe déjà");
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
	 * Cree un article
	 * @param data les donnees de l'article
	 * @throws MongoDAOException si un article avec le meme nom existe deja ou en cas de problème technique
	 */
	public void createArticle(Article data) throws MongoDAOException {
		
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
			if(articles.find(eq("nom", data.getNom())).first()==null) {
				articles.insertOne(data);			
			} else {
				throw new MongoDAOException("Creation article : un article ayant le même nom existe déjà");
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
	 * Modifie les informations (nom, lien, date, auteur) d'un article.
	 * Ne modifie pas les informations sur l'entite.
	 * 
	 * @param nom le nom de l'article a modifier
	 * @param data les donnees a mettre a jour, celles qui ne sont pas nulles
	 * @throws MongoDAOException
	 */
	public void updateArticle(String nom, Article data) throws MongoDAOException {
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
			
			List<Bson> updatesList = new ArrayList<>();
			if(data.getNom()!=null) updatesList.add(Updates.set("nom", data.getNom()));
			if(data.getLien()!=null) updatesList.add(Updates.set("lien", data.getLien()));
			if(data.getDate()!=null) updatesList.add(Updates.set("date", data.getDate()));
			if(data.getAuteur()!=null) {
				if(data.getAuteur().getNom()!=null) updatesList.add(Updates.set("auteur.nom", data.getAuteur().getNom()));
				if(data.getAuteur().getPrenom()!=null) updatesList.add(Updates.set("auteur.prenom", data.getAuteur().getPrenom()));
				if(data.getAuteur().getDescription()!=null) updatesList.add(Updates.set("auteur.description", data.getAuteur().getDescription()));
			}
			articles.updateOne(eq("nom", nom), Updates.combine(updatesList));
		} catch (Exception e) {
			if(!e.getClass().equals(MongoDAOException.class)) {
				throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * Remplace un article par une autre
	 * @param nom le nom de l'article a remplacer
	 * @param data le nouvel article
	 * @throws MongoDAOException si l'article a remplacer n'existe pas ou en cas de problème technique
	 */
	public void updateReplaceArticle(String nom, Article data) throws MongoDAOException {
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
			if(articles.find(eq("nom", data.getNom())).first()!=null) {
				articles.replaceOne(Filters.eq("nom", nom), data);
			} else {
				throw new MongoDAOException("Remplacement article : l'article n'existe pas");
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
	 * Supprime un article.
	 * @param nom le nom de l'article
	 * @throws MongoDAOException
	 */
	public void deleteArticle(String nom) throws MongoDAOException {
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
			articles.deleteOne(eq("nom", nom));
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	public void deleteArticle(ObjectId id) throws MongoDAOException {
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
			articles.deleteOne(eq("_id", id));
		} catch (Exception e)  {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	/**
	 * Supprime les articles d'une entite (salle, concert, soiree, groupe ou encore artiste)
	 * @param entite l'entite
	 * @param idEntite l'identifiant de l'entite
	 * @throws MongoDAOException
	 */
	public void deleteArticlesEntite(Entite entite, Integer idEntite) throws MongoDAOException {
		try {
			MongoCollection<Article> articles = db.getCollection(colArticles, Article.class);
			articles.deleteMany(eq(entite.label, idEntite));
		} catch (Exception e) {
			throw new MongoDAOException("Problème technique (" + e.getMessage() + ")");
		}
	}
	
	private void setEntiteArticle(Entite entite,Integer idEntite, Article data) {
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

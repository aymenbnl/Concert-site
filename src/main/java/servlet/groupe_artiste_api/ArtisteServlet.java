package servlet.groupe_artiste_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO_MySql.DAOException;
import DAO_MySql.DAO_Artiste;
import DAO_MySql.DAO_Groupe;
import DTO.ArtisteDTO;
import pojo_MySql.Artiste;
import pojo_MySql.Groupe;
import servlet.ServletUtils;
import utils.PojoToDTO;

/**
 * Servlet implementation class ArtisteServlet
 */
public class ArtisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAO_Artiste daoArtiste;
	private DAO_Groupe daoGroupe;
	private Gson gson;

    public void init() throws ServletException {

        try {
        	daoArtiste = new DAO_Artiste();
        	daoGroupe = new DAO_Groupe();
		} catch (DAOException e) {
			throw new ServletException(e);
		}
        gson = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
	    
		String pathInfo = request.getPathInfo();

		if(pathInfo == null) { // /groupe-artiste-api/artistes
			try {
				List<Artiste> artistes = daoArtiste.getArtistes();
				List<ArtisteDTO> artistesDTO = new ArrayList<>();
				for(Artiste a: artistes) {
					artistesDTO.add(PojoToDTO.forArtiste(a));
				}
				out.print(gson.toJson(artistesDTO));
			} catch (DAOException e) {
				throw new ServletException(e);
			}
		} else { // /groupe-artiste-api/artistes/{id}
			pathInfo = pathInfo.substring(1);
			Integer id = ServletUtils.getIdInPath(pathInfo);
			if(id!=null) {
				try {
					Artiste artiste = daoArtiste.find(id);
					out.print(gson.toJson(PojoToDTO.forArtiste(artiste)));
				} catch (NoResultException e) {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				} catch (DAOException e) {
					throw new ServletException(e);
				}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
		
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String pathInfo = request.getPathInfo();
		if(pathInfo == null) { // /groupe-artiste-api/artistes
			BufferedReader reader = request.getReader();
		    StringBuilder stringBuilder = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        stringBuilder.append(line);
		    }
		    
		    String body = stringBuilder.toString();
		    
		    ArtisteDTO artisteDTO = gson.fromJson(body, ArtisteDTO.class);
		    
		    Artiste artiste = new Artiste();
		    if((artisteDTO.getPseudo()==null || artisteDTO.getPseudo().trim().isEmpty()) 
		    		|| (artisteDTO.getVilleOrigine()==null || artisteDTO.getVilleOrigine().trim().isEmpty())
		    		|| artisteDTO.getAge()==null || artisteDTO.getIdGroupe()==null) {
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    } else {
		    	artiste.setPseudo(artisteDTO.getPseudo());
		    	artiste.setVilleOrigine(artisteDTO.getVilleOrigine());
		    	artiste.setAge(artisteDTO.getAge());
		    	
		    	Integer idGroupe = artisteDTO.getIdGroupe();
		    	Groupe groupe=null;
		    	
		    	try {
		    		response.setContentType("application/json");
		    		PrintWriter out = response.getWriter();
		    		
					groupe = daoGroupe.find(idGroupe);
					artiste.setGroupe(groupe);
					daoArtiste.create(artiste);
					Integer idArtisteCree = daoArtiste.getArtistes().stream().mapToInt(a -> a.getIdArtiste()).max().getAsInt();
					out.print(gson.toJson(PojoToDTO.forArtiste(daoArtiste.find(idArtisteCree))));

					response.setStatus(HttpServletResponse.SC_CREATED);
					out.flush();
				} catch (NoResultException e) {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				} catch (DAOException e) {
					if(e.getMessage().contains("ConstraintViolationException")) {
						response.setStatus(HttpServletResponse.SC_CONFLICT);
					} else {
						throw new ServletException(e);
					}
				}
		    }
		  
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	response.setHeader("Access-Control-Allow-Headers", "*");
    }
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String pathInfo = request.getPathInfo();

		if(pathInfo == null) { // /groupe-artiste-api/artistes
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else { // /groupe-artiste-api/artistes/{id}
			pathInfo = pathInfo.substring(1);
			Integer id = ServletUtils.getIdInPath(pathInfo);
			if(id!=null) {
				try {
					BufferedReader reader = request.getReader();
				    StringBuilder stringBuilder = new StringBuilder();
				    String line;
				    while ((line = reader.readLine()) != null) {
				        stringBuilder.append(line);
				    }
				    
				    String body = stringBuilder.toString();
				    
				    ArtisteDTO artisteDTO = gson.fromJson(body, ArtisteDTO.class);
				    
				    Artiste artiste = daoArtiste.find(id);
				    
				    if(artisteDTO.getPseudo()!=null && !artisteDTO.getPseudo().trim().isEmpty()) artiste.setPseudo(artisteDTO.getPseudo());
				    if(artisteDTO.getVilleOrigine()!=null && !artisteDTO.getVilleOrigine().trim().isEmpty()) artiste.setVilleOrigine(artisteDTO.getVilleOrigine());
				    if(artisteDTO.getAge()!=null) artiste.setAge(artisteDTO.getAge());
				    
				    if(artisteDTO.getIdGroupe()!=null) {
				    	if(artisteDTO.getIdGroupe()!=artiste.getGroupe().getIdGroupe()) {
				    		Groupe g = daoGroupe.find(artisteDTO.getIdGroupe());
				    		artiste.setGroupe(g);
				    	}
				    }
				    daoArtiste.create(artiste);
				    response.setStatus(HttpServletResponse.SC_OK);
				} catch (NoResultException e) {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				} catch (DAOException e) {
					if(e.getMessage().contains("ConstraintViolationException")) {
						response.setStatus(HttpServletResponse.SC_CONFLICT);
					} else {
						throw new ServletException(e);
					}
				}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String pathInfo = request.getPathInfo();
		if(pathInfo == null) { // /groupe-artiste-api/artistes
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			pathInfo = pathInfo.substring(1);
			Integer id = ServletUtils.getIdInPath(pathInfo);
			if(id!=null) { // /groupe-artiste-api/artistes/{id}
				try {
					Artiste artiste = daoArtiste.find(id);
					daoArtiste.delete(artiste);
					response.setStatus(HttpServletResponse.SC_OK);
				} catch (NoResultException e) {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				} catch (DAOException e) {
					throw new ServletException(e);
				}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
	}

}

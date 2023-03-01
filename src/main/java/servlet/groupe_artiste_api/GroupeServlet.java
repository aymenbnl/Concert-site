package servlet.groupe_artiste_api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.NoResultException;

import com.google.gson.Gson;

import DAO_MySql.DAOException;
import DAO_MySql.DAO_Groupe;
import DTO.GroupeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo_MySql.Groupe;
import servlet.ServletUtils;
import utils.PojoToDTO;

/**
 * Servlet implementation class GroupeServlet
 */
public class GroupeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAO_Groupe daoGroupe;
	private Gson gson;

    public void init() throws ServletException {

        try {
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

		if(pathInfo == null) { // /groupe-artiste-api/groupes
			try {
				List<Groupe> groupes = daoGroupe.getGroupe();
				List<GroupeDTO> groupesDTO = new ArrayList<>();
				for(Groupe g: groupes) {
					groupesDTO.add(PojoToDTO.forGroupe(g));
				}
				out.print(gson.toJson(groupesDTO));
			} catch (DAOException e) {
				throw new ServletException(e);
			}
		} else { // /groupe-artiste-api/groupes/{id}
			pathInfo = pathInfo.substring(1);
			Integer id = ServletUtils.getIdInPath(pathInfo);
			if(id!=null) {
				try {
					Groupe groupe = daoGroupe.find(id);
					out.print(gson.toJson(PojoToDTO.forGroupe(groupe)));
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
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		
		String pathInfo = request.getPathInfo();
		if(pathInfo == null) { // /groupe-artiste-api/groupes
			try { 
				Groupe g = new Groupe();
				daoGroupe.create(g);
				List<Groupe> groupes = daoGroupe.getGroupe();
				Integer idGroupeCree = groupes.stream().mapToInt(gr -> gr.getIdGroupe()).max().getAsInt();
				g=new Groupe();
				g.setIdGroupe(idGroupeCree);
				g.setListArtiste(new HashSet<>());
				g.setListConcert(new HashSet<>());
				out.print(gson.toJson(PojoToDTO.forGroupe(g)));
				response.setStatus(HttpServletResponse.SC_CREATED);
				
			} catch (DAOException e) {
				throw new ServletException(e);
			}
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		out.flush();
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String pathInfo = request.getPathInfo();
		if(pathInfo == null) { // /groupe-artiste-api/groupes
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			pathInfo = pathInfo.substring(1);
			Integer id = ServletUtils.getIdInPath(pathInfo);
			if(id!=null) { // /groupe-artiste-api/groupes/{id}
				try {
					Groupe groupe = daoGroupe.find(id);
					daoGroupe.delete(groupe);
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

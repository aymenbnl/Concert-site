package servlet.ticket_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import com.google.gson.Gson;

import DAO_MySql.DAOException;
import DAO_MySql.DAO_Ticket;
import DAO_MySql.DAO_Utilisateur;
import DTO.UtilisateurDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo_MySql.Admin;
import pojo_MySql.Ticket;
import pojo_MySql.Utilisateur;
import servlet.ServletUtils;
import utils.PojoToDTO;

/**

 Servlet implementation class UtilisateurServlet
 */
public class UtilisateurSevlet extends HttpServlet {
    private DAO_Utilisateur utilisateurDAO;

    private DAO_Ticket ticketDAO; ;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            utilisateurDAO = new DAO_Utilisateur();

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        gson = new Gson();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) { // /ticket-api/utilisateurs
            try {
                List<Utilisateur> utilisateurs = utilisateurDAO.getUtilisateurs();
                List<UtilisateurDTO> utilisateurDTOS = new ArrayList<>();
                for (Utilisateur utilisateur : utilisateurs) {
                    utilisateurDTOS.add(PojoToDTO.forUtilisateur(utilisateur));
                }
                out.print(gson.toJson(utilisateurDTOS));
            } catch (DAOException e) {
                throw new ServletException(e);
            }
        } else { // /ticket-api/utilisateurs/{id}
            pathInfo = pathInfo.substring(1);
            Integer id = ServletUtils.getIdInPath(pathInfo);
            if (id != null) {
                try {
                    Utilisateur utilisateur = utilisateurDAO.find(id);
                    out.print(gson.toJson(PojoToDTO.forUtilisateur(utilisateur)));
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
    
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	response.setHeader("Access-Control-Allow-Headers", "*");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");   	
    	response.setContentType("application/json");
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) { // /ticket-api/utilisateurs

            try {
                BufferedReader reader = request.getReader();
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                String body = stringBuilder.toString();

                UtilisateurDTO utilisateurDTO = gson.fromJson(body, UtilisateurDTO.class);
                System.out.println(utilisateurDTO);
                Utilisateur utilisateur = new Utilisateur();
                if ((utilisateurDTO.getIdentifiant() == null || utilisateurDTO.getIdentifiant().trim().isEmpty())
                        || (utilisateurDTO.getMotPasse() == null || utilisateurDTO.getMotPasse().trim().isEmpty())) {
                    //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    utilisateur.setIdentifiant(utilisateurDTO.getIdentifiant());
                    utilisateur.setMotDePasse(ServletUtils.hashPassword(utilisateurDTO.getMotPasse()));


                    try {
                        utilisateurDAO.create(utilisateur);
                        response.setStatus(HttpServletResponse.SC_CREATED);
                    } catch (NoResultException e) {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    } catch (DAOException e) {
                        if (e.getMessage().contains("ConstraintViolationException")) {
                            response.setStatus(HttpServletResponse.SC_CONFLICT);
                        } else {
                            throw new ServletException(e);
                        }
                    } 
                }
            } catch (Exception e) {
            	throw new ServletException(e);
            }
            } else{ // /ticket-api/utilisateurs/getByLoginAndPassword
    			PrintWriter out = response.getWriter();
    			
    			pathInfo = pathInfo.substring(1);
    			String[] pathVariables = pathInfo.split("/");
    			if(pathVariables.length == 1 && pathVariables[0].equals("getByLoginAndPassword")) { 
    				BufferedReader reader = request.getReader();
    			    StringBuilder stringBuilder = new StringBuilder();
    			    String line;
    			    while ((line = reader.readLine()) != null) {
    			        stringBuilder.append(line);
    			    }
    			    
    			    String body = stringBuilder.toString();
    			    
    			    Utilisateur user = gson.fromJson(body, Utilisateur.class);
    			    
    				try {
    					user = utilisateurDAO.findByLoginAndPassword(user.getIdentifiant(), ServletUtils.hashPassword(user.getMotDePasse()));
    					out.print(gson.toJson(user));
    					response.setStatus(HttpServletResponse.SC_OK);
    				} catch (NoResultException e) {
    					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    				} catch (Exception e) {
    					throw new ServletException(e);
    				}
    			} else {
    				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    			}
    			out.flush();
    		}
        }



        @Override
        protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	response.setHeader("Access-Control-Allow-Origin", "*");
        	String pathInfo = request.getPathInfo();
            if (pathInfo == null) { // /ticket-api/utilisateurs
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                pathInfo = pathInfo.substring(1);
                Integer id = ServletUtils.getIdInPath(pathInfo);
                if (id != null) { // /ticket-api/utilisateurs/{id}
                    try {

                        BufferedReader reader = request.getReader();
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line);
                        }

                        String body = stringBuilder.toString();

                        UtilisateurDTO utilisateurDTO = gson.fromJson(body, UtilisateurDTO.class);

                        Utilisateur utilisateur = utilisateurDAO.find(id);
                        if (utilisateur == null) {
                            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                            return;
                        }
                        List<Ticket> tickets = ticketDAO.getTickets();
                        for (Ticket ticket : tickets) {
                            if(ticket.getUtilisateur().getIdUtilisateur()==utilisateurDTO.getIdUtilisateur())
                            ticketDAO.delete(ticket);
                        }
                        utilisateurDAO.delete(utilisateur);
                        response.setStatus(HttpServletResponse.SC_OK);
                    } catch (DAOException e) {
                        throw new ServletException(e);
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            }
        }

}
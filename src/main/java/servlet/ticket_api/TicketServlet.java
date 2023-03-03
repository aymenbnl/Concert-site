package servlet.ticket_api;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import com.google.gson.Gson;

import DAO_MySql.DAOException;
import DAO_MySql.DAO_Concert;
import DAO_MySql.DAO_Ticket;
import DAO_MySql.DAO_Utilisateur;
import DTO.TicketDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo_MySql.Concert;
import pojo_MySql.Ticket;
import pojo_MySql.Utilisateur;
import servlet.ServletUtils;
import utils.PojoToDTO;

/**

 Servlet implementation class TicketServlet
 */
public class TicketServlet extends HttpServlet {
    private DAO_Ticket ticketDAO;
    private DAO_Utilisateur UtilisateurDAO;
    private DAO_Concert ConcertDAO;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ticketDAO = new DAO_Ticket();
            UtilisateurDAO = new DAO_Utilisateur();
            ConcertDAO = new DAO_Concert();
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

        if (pathInfo == null) { // /ticket-api/tickets
            try {
                List<Ticket> tickets = ticketDAO.getTickets();
                List<TicketDTO> ticketDTOs = new ArrayList<>();
                for (Ticket ticket : tickets) {
                    ticketDTOs.add(PojoToDTO.forTicket(ticket));
                }
                out.print(gson.toJson(ticketDTOs));
            } catch (DAOException e) {
                throw new ServletException(e);
            }
        } else { // /ticket-api/tickets/{id}
            pathInfo = pathInfo.substring(1);
            Integer id = ServletUtils.getIdInPath(pathInfo);
            if (id != null) {
                try {
                    Ticket ticket = ticketDAO.find(id);
                    out.print(gson.toJson(PojoToDTO.forTicket(ticket)));
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
        if (pathInfo == null) { // /ticket-api/tickets

            try {
                BufferedReader reader = request.getReader();
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                String body = stringBuilder.toString();

                TicketDTO ticketDTO = gson.fromJson(body, TicketDTO.class);

                Ticket ticket = new Ticket();
                List<Ticket> tickets = ticketDAO.getTickets();

                Integer idUtilisateur = ticketDTO.getIdUtilisateur();
                Utilisateur utilisateur = UtilisateurDAO.find(idUtilisateur);
                ticket.setUtilisateur(utilisateur);

                Integer idConcert = ticketDTO.getIdConcert();
                Concert concert = ConcertDAO.find(idConcert);
                ticket.setConcert(concert);

                ticketDAO.create(ticket);

                out.print(gson.toJson(PojoToDTO.forTicket(ticket)));
                response.setStatus(HttpServletResponse.SC_CREATED);

            }catch (NoResultException e) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            }catch (DAOException e) {
                throw new ServletException(e);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	String pathInfo = request.getPathInfo();
        if (pathInfo == null) { // /ticket-api/tickets
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            pathInfo = pathInfo.substring(1);
            Integer id = ServletUtils.getIdInPath(pathInfo);
            if (id != null) { // /ticket-api/tickets/{id}
                try {
                    Ticket ticket = ticketDAO.find(id);
                    ticketDAO.delete(ticket);
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



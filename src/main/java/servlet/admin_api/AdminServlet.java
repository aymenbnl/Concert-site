package servlet.admin_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.NoResultException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO_MySql.DAOException;
import DAO_MySql.DAO_Admin;
import pojo_MySql.Admin;
import servlet.ServletUtils;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAO_Admin daoAdmin;
	private Gson gson;
       
    public void init() throws ServletException {

        try {
        	daoAdmin = new DAO_Admin();
		} catch (DAOException e) {
			throw new ServletException(e);
		}
        gson = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
	    
		String pathInfo = request.getPathInfo();

		if(pathInfo == null) { // /admin-api/admins
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else { // /admin-api/admins/{id}
			pathInfo = pathInfo.substring(1);
			Integer id = ServletUtils.getIdInPath(pathInfo);
			if(id!=null) {
				try {
					Admin admin = daoAdmin.find(id);
					out.print(gson.toJson(admin));
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("post");
		String pathInfo = request.getPathInfo();
		if(pathInfo == null) { // /admin-api/admins
			BufferedReader reader = request.getReader();
		    StringBuilder stringBuilder = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        stringBuilder.append(line);
		    }
		    
		    String body = stringBuilder.toString();
		    
		    Admin admin = gson.fromJson(body, Admin.class);
		    admin.setIdAdmin(null);
		    if((admin.getLogin()==null || admin.getLogin().trim().isEmpty()) 
		    		|| (admin.getMotDePasse()==null || admin.getMotDePasse().trim().isEmpty())) {
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	
		    } else {
		    	try {
		    		admin.setMotDePasse(ServletUtils.hashPassword(admin.getMotDePasse()));
					daoAdmin.create(admin);
					response.setStatus(HttpServletResponse.SC_CREATED);
				} catch (Exception e) {
					if(e.getMessage().contains("ConstraintViolationException")) {
						response.setStatus(HttpServletResponse.SC_CONFLICT);
					} else {
						throw new ServletException(e);
					}
				}
		    }
		  
		} else { // /admin-api/admins/getByLoginAndPassword
			response.setContentType("application/json");
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
			    
			    Admin admin = gson.fromJson(body, Admin.class);
				System.err.println(admin.getLogin() + " " + admin.getMotDePasse());

				try {
					admin = daoAdmin.findByLoginAndPassword(admin.getLogin(), ServletUtils.hashPassword(admin.getMotDePasse()));
					out.print(gson.toJson(admin));
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
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String pathInfo = request.getPathInfo();
		if(pathInfo == null) { // /admin-api/admins
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			pathInfo = pathInfo.substring(1);
			Integer id = ServletUtils.getIdInPath(pathInfo);
			if(id!=null) { // /admin-api/admins/{id}
				try {
					Admin admin = daoAdmin.find(id);
					daoAdmin.delete(admin);
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

package com.seanjohn.nhl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seanjohn.nhl.business.Team;
import com.seanjohn.nhl.data.TeamIO;

/**
 * Servlet implementation class TeamsServlet
 */
@WebServlet({"/teams", "/Teams"})
public class TeamsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setHeader("X-Servlet-Name", getServletName());

	    //query db for a list of teams
	    //take list of teams and display their names and their coaches/management
	    //make team name a link on the jsp page that redirects 
	    //Team[] teams = new Team[8];
	    HttpSession session = request.getSession();
	    String user = (String)session.getAttribute("plaintextSQLUser");
	    String pass = (String)session.getAttribute("plaintextSQLPass");
	    
	    TeamIO teamIO = new TeamIO(user,pass);
	    ArrayList<Team> teams;
	    try {
			teams = teamIO.getTeams();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ServletContext context = this.getServletContext();
			context.log("getTeams", e);
			teams = new ArrayList<Team>();
		}
	    
	    request.setAttribute("teams", teams);
        String url = "/teams.jsp";
        ServletContext sc = getServletContext();
        sc.getRequestDispatcher(url).forward(request, response);
	}
}

/*
 *   Document   : TeamsServlet.java
 *   Created on : July 23, 2016
 *   Authors    : John Steel & Sean Coombes
 */

package com.seanjohn.nhl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seanjohn.nhl.business.Team;
import com.seanjohn.nhl.data.TeamHIO;

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
	    
	    //gets lists of teams from derby database via TeamIO class
	    TeamHIO teamIO = new TeamHIO();
	    List<Team> teams;
	    try {
			teams = teamIO.getTeams();
		} catch (Exception e) {
			ServletContext context = this.getServletContext();
			context.log(getServletName(), e);
			teams = new ArrayList<Team>();
		}
	    
	    //adds to team to an attribute to be used in the jsp
	    request.setAttribute("teams", teams);
        String url = "/teams.jsp";
        ServletContext sc = getServletContext();
        sc.getRequestDispatcher(url).forward(request, response);
	}
}

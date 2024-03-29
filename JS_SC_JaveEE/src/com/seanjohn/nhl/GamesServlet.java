/*
 *   Document   : GamesServlet.java
 *   Created on : July 23, 2016
 *   Authors    : John Steel & Sean Coombes
 */

package com.seanjohn.nhl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seanjohn.nhl.business.Game;
import com.seanjohn.nhl.data.ScheduleHIO;

/**
 * Servlet implementation class CompleteGamesServlet
 */
@WebServlet({"/completegames", "/CompleteGames", "/upcominggames", "/UpcomingGames"})
public class GamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setHeader("X-Servlet-Name", getServletName());
	    
	    String path = request.getRequestURI().substring(request.getContextPath().length());
	    boolean checkUpcoming = path.equalsIgnoreCase("/UpcomingGames");
	    ScheduleHIO sched = new ScheduleHIO();
	    List<Game> games;
	    try {
	    	if (checkUpcoming) {
				games = sched.getUpcomingGames();
	    	} else {
				games = sched.getCompletedGames();
	    	}
		} catch (SQLException e) {
			getServletContext().log(getServletName(), e);
			games = new ArrayList<Game>();
		}
	    
	    request.setAttribute("games", games);
	    request.setAttribute("title", checkUpcoming ? "Upcoming Games" : "Completed Games");
        String url = "/games.jsp";
        ServletContext sc = getServletContext();
        sc.getRequestDispatcher(url).forward(request, response);

	}
}

package com.seanjohn.nhl.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.seanjohn.nhl.business.*;

public class TeamIO extends GenericIO {

	public TeamIO(String username, String password) {
		super(username, password);
	}
	
	public Team getTeam(String teamID) throws SQLException {
		PreparedStatement ps = this.db.prepareStatement("SELECT TeamName FROM TEAM WHERE TEAMID = ?");
		ps.setString(0, teamID);
		ResultSet rs = ps.executeQuery();
		// Read & Print records
		while (rs.next()) {
			Team team = new Team();
			team.setTeamname(rs.getString(0));
			return team;
		}
		
		return null;
	}
	
	public ArrayList<Team> getTeams() throws SQLException {
        ResultSet rs = this.db
                .prepareStatement("SELECT t.TEAMID, t.TEAMNAME, " +
                                  "    s1.LASTNAME || ', ' || s1.FIRSTNAME as coach, " +
                                  "    s2.LASTNAME || ', ' || s2.FIRSTNAME as asstcoach, " +
                                  "    s3.LASTNAME || ', ' || s3.FIRSTNAME as trainer, " +
                                  "    s4.LASTNAME || ', ' || s4.FIRSTNAME as manager " +
                                  "FROM Team t " +
                                  "    LEFT JOIN STAFF s1 " +      
                                  "        ON t.HEADCOACH = s1.STAFFID " +      
                                  "    LEFT JOIN STAFF s2 " +      
                                  "        ON t.ASSTCOACH = s2.STAFFID " +      
                                  "    LEFT JOIN STAFF s3 " +      
                                  "        ON t.TRAINER = s3.STAFFID " +      
                                  "    LEFT JOIN STAFF s4 " +      
                                  "        ON t.MANAGER = s4.STAFFID")                                                                             
                .executeQuery();
        
        ArrayList<Team> teams = new ArrayList<Team>();
        
        // Read & Print records
        while (rs.next()) {
            Team team = new Team();
            team.setTeamname(rs.getString(1));
            team.setHeadcoach(rs.getString(2));
            team.setAsstcoach(rs.getString(3));
            team.setTrainer(rs.getString(4));
            team.setManager(rs.getString(5));
            teams.add(team);
        }
        
        return teams;
    }
	
}

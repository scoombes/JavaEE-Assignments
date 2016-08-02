import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seanjohn.nhl.business.Player;
import com.seanjohn.nhl.business.PlayerStats;
import com.seanjohn.nhl.business.Roster;
import com.seanjohn.nhl.data.RosterHIO;


public class TestingGround {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RosterHIO rosterIO = new RosterHIO();
		Roster player;
		List<PlayerStats> pslist;
		try {
			player = rosterIO.getPlayer(100108);
			pslist = rosterIO.getPlayerStats();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			player = new Roster();
			pslist = new ArrayList<PlayerStats>();
		}
		
		System.out.println(player.getPlayer().getLastName());
		Player aPlayer = player.getPlayer();
		List<PlayerStats> ps = aPlayer.getPlayerStats();
		List<PlayerStats> ps2 = player.getPlayerStats();
		
		
		for(PlayerStats ps3 : pslist){
			System.out.println(ps3.getRoster().getTeam().getTeamName());
			
		}
		
		for(PlayerStats pstats: ps){
			System.out.println("ONE");
			System.out.println(pstats.getAssists());
			System.out.println(pstats.getGoals());
			System.out.println(pstats.getPim());
			System.out.println(pstats.getShots());
			System.out.println(pstats.getSo());
			System.out.println(pstats.getGp());
		}
		
		for(PlayerStats pstats: ps2){
			System.out.println("TWO");
			System.out.println(pstats.getAssists());
			System.out.println(pstats.getGoals());
			System.out.println(pstats.getPim());
			System.out.println(pstats.getShots());
			System.out.println(pstats.getSo());
			System.out.println(pstats.getGp());
		}
		System.out.println("DONE");
		
	}

}

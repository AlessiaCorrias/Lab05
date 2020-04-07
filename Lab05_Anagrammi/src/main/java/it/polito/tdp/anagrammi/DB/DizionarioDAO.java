package it.polito.tdp.anagrammi.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DizionarioDAO {

	public boolean isCorretto(String parziale) {
		String sql = "SELECT id FROM parola " + 
				"WHERE nome = ? ";
		boolean result = false; 
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, parziale);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				if (!rs.equals(0) && rs != null)
					result = true;
			}
			

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
		return result;
		
	}

}

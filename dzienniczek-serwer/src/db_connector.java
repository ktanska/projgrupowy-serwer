import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db_connector {
	
	public Connection conn() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "jdbc:sqlite:C:\\Users\\kinga\\git\\repository2\\dzienniczek-serwer\\child-health.db";
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("notConnection");
		}
		System.out.println(conn);
		return conn;
	}

	public boolean logowanie(String log, String pass) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT * from Rodzic";
		Connection conn = conn();
		Statement st = conn.createStatement();

	      ResultSet rs = st.executeQuery(query);
	     
	      try {
			do
			  {
			    String login = rs.getString("login");
			    String haslo = rs.getString("password");
			    System.out.println(login);
			    System.out.println(haslo);
			    if (login.equals(log) && haslo.equals(pass)) {
			    	System.out.println("Zalogowano");
			    	conn.close();
			    	return true;
			    }
			    else {
			    	System.out.println("Brak u¿ytkownika w bazie");
			    	conn.close();
			    	return false;
			    }
			  } while (rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    conn.close();
		return false;
	}
		
	}

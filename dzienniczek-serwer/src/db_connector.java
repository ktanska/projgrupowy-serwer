import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

public class db_connector {
	
	public Connection conn() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// zmienic sciezke na wlasna
		//String url = "jdbc:sqlite:C:\\Users\\Lucek\\git\\projgrupowy-serwer\\dzienniczek-serwer\\child-health.db";
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
		// zapytanie sql
		String query = "SELECT * from Rodzic";
		// polaczenie 
		Connection conn = conn();
		Statement st = conn.createStatement();

		//wykonanie sql
	      ResultSet rs = st.executeQuery(query);
	     
	      try {
			do
			  {
			    String login = rs.getString("login");
			    String haslo = rs.getString("password");
			    String mail = rs.getString("mail");
			    System.out.println(login);
			    System.out.println(haslo);
			    System.out.println(mail);
			    if ((login.equals(log)|| mail.equals(log)) && haslo.equals(pass)) {
			    	System.out.println("Zalogowano");
			    	conn.close();
			    	return true;
			    }			    
			  } while (rs.next()); // sprawdz wszystkie wyniki
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    System.out.println("Brak u¿ytkownika w bazie");
	    conn.close();
		return false;
	}
	
	public boolean rejestrowanie(String login,String mail, String password) throws SQLException {
		// TODO Auto-generated method stub
		
		// polaczenie 
		Connection conn = conn();
		PreparedStatement Pst = conn.prepareStatement("insert into Rodzic values (NULL, ?, ?, ?);");

	     
	      try {
	    	  //st.executeUpdate("insert into Rodzic values (NULL, ?, ?, ?);");
	    	  Pst.setString(1, login);
	    	  Pst.setString(2, password);
	    	  Pst.setString(3, mail);
              Pst.execute();

      			System.out.println("Za³adowano dane rejestracji");
	    	  return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    conn.close();
		return false;
	}

	public void new_child(String log, String pass, String imie_dziecka, String nazw_dziecka, String pesel, String waga, String wzrost, String data_ur) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = conn();
		Statement st = conn.createStatement();
		String query = "SELECT PK FROM Rodzic WHERE login LIKE '"+log+"' AND password LIKE '"+pass+"'";
	    ResultSet rs = st.executeQuery(query);
	    String PK = rs.getString("PK");
	    System.out.println("PK"+ PK);
	    
	
	    String insert_child = "INSERT INTO Dziecko (data_ur, imie, nazwisko, PESEL, waga, wzrost, rodzic_klucz) VALUES (?,?,?,?,?,?,?)";
	    PreparedStatement pstmt = conn.prepareStatement(insert_child);
	    pstmt.setString(1, data_ur);
	    pstmt.setString(2, imie_dziecka);
	    pstmt.setString(3, nazw_dziecka);
	    pstmt.setString(4, pesel);
	    pstmt.setString(5, waga);
	    pstmt.setString(6, wzrost);
	    pstmt.setString(7, PK);
	    pstmt.executeUpdate();
		conn.close();
	}

	public String get_child_list(String log, String pass) throws SQLException {
		// TODO Auto-generated method stub
		String child_list = "";
		Connection conn = conn();
		Statement st = conn.createStatement();
		String query = "SELECT PK FROM Rodzic WHERE login LIKE '"+log+"' AND password LIKE '"+pass+"'";
	    ResultSet rs = st.executeQuery(query);
	    String PK = rs.getString("PK");
	    System.out.println("PK"+ PK);
	    String get_kids_query = "SELECT * FROM Dziecko WHERE rodzic_klucz LIKE '"+PK+"'";
	    ResultSet kids_rs = st.executeQuery(get_kids_query);
	    ResultSetMetaData rsmd = kids_rs.getMetaData();
	    try {
	    	while (kids_rs.next())
			  {
				JSONObject JS = new JSONObject();
	    		JS.put("imie", rs.getString("imie"));
	    		JS.put("nazwisko", rs.getString("nazwisko"));
	    		child_list = child_list + JS.toString() + ",";
			  }

	} catch (SQLException e) {
		e.printStackTrace();
	}
	    conn.close();
	    return child_list;
	}
}
		

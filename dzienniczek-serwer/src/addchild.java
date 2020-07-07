

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class addchild
 */
@WebServlet("/addchild")
public class addchild extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addchild() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		StringBuffer jb = new StringBuffer();
		 String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null) {
		    	System.out.println(line);
		    	final JSONObject obj =  new JSONObject(line.substring(9));
				System.out.println(line);
				String log = (String)obj.getString("login");
				String pass = (String)obj.getString("password");
				String imie_dziecka = (String)obj.getString("child_name");
				String nazw_dziecka = (String)obj.getString("child_surname");
				String pesel = (String)obj.getString("PESEL");
				String waga = (String)obj.getString("weight");
				String wzrost = (String)obj.getString("height");
				String data_ur = (String)obj.getString("birth_date");
				db_connector db = new db_connector();
				db.new_child(log, pass, imie_dziecka, nazw_dziecka, pesel, waga, wzrost, data_ur);
	    }
	  } catch (Exception e) { /*report an error*/ }
	}

}

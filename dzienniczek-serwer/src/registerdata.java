

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
 * Servlet implementation class registerdata
 */
@WebServlet("/registerdata")
public class registerdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerdata() {
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
		boolean zarejestrowano = false;
		System.out.println("Prztewarzam zadania");
		 StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
			  BufferedReader reader = request.getReader();
			    // odczyt z andoirda co przyszlo
			    while ((line = reader.readLine()) != null) {
			    	System.out.println(line);
			    	// wpisanie tego do JSONa
			    	final JSONObject obj =  new JSONObject(line.substring(9));
					System.out.println(line);
					String login = (String)obj.getString("login");
					String mail = (String)obj.getString("mail");
					String password = (String)obj.getString("password");
						db_connector db = new db_connector(); //po³¹czenie z baz¹ danych
						zarejestrowano = db.rejestrowanie(login, mail, password); //wywolanie funckji dodaj¹cej dane do bazy i sprawdzaj¹cej czy rejestracja siê powiod³a
			    }
			    	if(zarejestrowano == true) {
				    	PrintWriter output = response.getWriter();
						output.println("registration succeeded");
						output.flush();
				  }
			    	else {
			    		PrintWriter output = response.getWriter();
						output.println("registration failed");
						output.flush();
			    	}
			  } catch (Exception e) { /*report an error*/ }
	}

	
	

}

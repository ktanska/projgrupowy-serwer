

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
 * Servlet implementation class childlist
 */
@WebServlet("/childlist")
public class childlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public childlist() {
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
		String child_list = "";
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
				db_connector db = new db_connector();
				child_list = db.get_child_list(log, pass);
	    }
	  } catch (Exception e) { /*report an error*/ }
		PrintWriter output = response.getWriter();
		System.out.println("dzieci:"+child_list);
		child_list = "[" + child_list + "]";
		output.println(child_list);
		output.flush();
	}

}

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>  
<%@page import="java.util.Date"%>
<%@page import="java.lang.Object"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.Instant"%>
<%
String driver = "com.mysql.jdbc.Driver";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection conn = null;
Statement statement = null;
ResultSet resultSet = null;
System.out.println(request.getParameterNames());
String pass_parent=request.getParameter("pass");
String login_parent=request.getParameter("login");
%>
<!DOCTYPE HTML>
<html lang="pl">
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>Książeczka zdrowia dziecka</title>
</head>
<body>
<form method="post" name="mess">
<table bgcolor="silver">
<tr>
	<td>Login:</td><td><input type="text" name="login"></td>
</tr>
<tr>
	<td>Hasło:</td><td><input type="password" name="pass"></td>
</tr>
<tr>
	<br>
	<td colspan="2" align="center"><br>
		<input type="submit" value="Zaloguj" id="logbtn">
	</td>
</tr>
</table>
</form>
<%
if(login_parent != null && pass_parent != null) {
String query = "SELECT * from Rodzic";
		// polaczenie 
		conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\kinga\\git\\repository2\\dzienniczek-serwer\\child-health.db");
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
			    if ((login.equals(login_parent)|| mail.equals(login_parent)) && haslo.equals(pass_parent)) {
			    	System.out.println("Zalogowano");
			    	String redirectURL = "http://localhost:8080/dzienniczek-serwer/domowa.jsp";
        			response.sendRedirect(redirectURL);
        			return;
			    }			  
			  } while (rs.next()); 
			conn.close();// sprawdz wszystkie wyniki
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      }
	%>
	<form action="http://localhost:8080/dzienniczek-serwer/register.jsp">
    <input type="submit" value="Rejestracja" />
</form>
	</body>
<script>
$(document).ready(function() { 
    $("#logbtn").click(function() { 
        var fn = $("#login").val();
        var vn = $('#pass').val();
        $.post("login.jsp", { 
            login: fn,
            haslo_pacjenta: vn
        }, function(data) { 
            $("#msg").html(data); 
        }); 

    }); 
});
</script>
</html>
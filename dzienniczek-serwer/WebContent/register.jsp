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
String driver = "com.mysql.cj.jdbc.Driver";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
String login_parent=request.getParameter("login");
String pass_parent=request.getParameter("password");
String mail_parent=request.getParameter("mail");
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
	<td>Adres email:</td><td><input type="email" name="mail"></td>
</tr>
<tr>
	<td>Hasło:</td><td><input type="password" name="password"></td>
</tr>
<tr>
	<br>
	<td colspan="2" align="center"><br>
		<input type="submit" value="Zarejestruj" id="register">
	</td>
</tr>
</table>
</form>
</body>
<%
if(login_parent != null && pass_parent != null && mail_parent != null) {
Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\kinga\\git\\repository2\\dzienniczek-serwer\\child-health.db");
	PreparedStatement Pst = conn.prepareStatement("insert into Rodzic values (NULL, ?, ?, ?);");

	     
	      try {
	    	  //st.executeUpdate("insert into Rodzic values (NULL, ?, ?, ?);");
	    	  Pst.setString(1, login_parent);
	    	  Pst.setString(2, pass_parent);
	    	  Pst.setString(3, mail_parent);
              Pst.execute();

      			System.out.println("Załadowano dane rejestracji");
      			String redirectURL = "http://localhost:8080/dzienniczek-serwer/domowa.jsp";
    			response.sendRedirect(redirectURL);
    			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}%>
<script>
$(document).ready(function() { 
    $("#register").click(function() { 
        var fn = $("#login").val();
        var vn = $("#password").val();
        var nn = $("#mail").val();
        $.post("register.jsp", { 
            login: fn,
            password: vn,
            mail: nn
        }, function(data) { 
            $("#msg").html(data); 
        }); 

    }); 
});
</script>
</html>

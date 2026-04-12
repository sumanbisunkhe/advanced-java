<%@ page import="javax.servlet.http.*,javax.servlet.*" %>

<%
    HttpSession session1 = request.getSession(false);

    String user = (String) session1.getAttribute("username");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<h2>Welcome to Profile Page</h2>

<p><b>Username:</b> <%= user %></p>

<a href="logout.jsp">Logout</a>
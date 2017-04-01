<html>
<head>
    <title>Login</title>
</head>
<body>
<%@ page import="java.sql.*"%>
<%@ page import="Database.ConnectionHandler" %>
<%@ page import="HelperFunction.MD5Hashing" %>
<%@ page import="javax.xml.transform.Result" %>
<%
    String username=request.getParameter("username");
    session.putValue("username",username);

    String password=request.getParameter("password");
    MD5Hashing hashing = new MD5Hashing();
    String hashedPassword = hashing.getHashedPwd(password);

    ConnectionHandler connHandler = new ConnectionHandler();
    Connection connection = connHandler.openConnection();

    try{
        String query = "select * from RegularUser where username=?";
        PreparedStatement st= connection.prepareStatement(query);
        st.setString(1, username);
        ResultSet rs = st.executeQuery();
        if(rs.next())
        {
            if(rs.getString(3).equals(hashedPassword))
            {
                out.println("Welcome "+username+"!");

            }
            else
            {
                out.println("Invalid password. Please try again");
            }
        }
        else
        {
            out.println("Please check your username again");
        }
    }
    catch (Exception e){
        out.println("Unable to connect to database.");
    }
%>
</br>
</br>
<a href="/html/home.html">Home</a>
</body>
</html>
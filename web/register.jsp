<html>
<head>
    <title>Registration</title>
</head>
<body>
<%@ page import ="java.sql.*" %>
<%@ page import="Database.ConnectionHandler" %>
<%@ page import="HelperFunction.MD5Hashing" %>
<%
    String user=request.getParameter("username");
    session.putValue("username",user);

    String password = request.getParameter("password");
    MD5Hashing hashing = new MD5Hashing();
    String hashedPassword = hashing.getHashedPwd(password);
    String email=request.getParameter("email");

    ConnectionHandler connHandler = new ConnectionHandler();
    Connection connection = connHandler.openConnection();
    try{
        String query = "insert into RegularUser (username, password, email, ban) values (?,?,?, false)";
        PreparedStatement st= connection.prepareStatement(query);

        st.setString(1, user);
        st.setString(2, hashedPassword);
        st.setString(3, email);
        st.executeUpdate();
        out.println("Successfully registered");
    }
    catch (SQLException  e){
        if(e.getErrorCode() == 1062 ){
            out.println("Username already exists. Please try another one.");
        }
    }
    catch (Exception e){
        out.println("Unable to connect to database.");

    }
    connHandler.closeConnection(connection);

%>
<br/>
<br/>
<a href ="/html/Login.html">Login</a><br/><br/>
<a href="/html/home.html">Home</a>
</body>
</html>
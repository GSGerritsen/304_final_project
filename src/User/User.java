package User;


public class User {


    private String username;
    private String email;
    private boolean ban;
    private boolean admin;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean getBan() {
        return ban;
    }

    public boolean getAdmin() { return admin; }

    public User(String username, String email, boolean ban, boolean admin) {
        this.username = username;
        this.email = email;
        this.ban = ban;
        this.admin = admin;
    }
}

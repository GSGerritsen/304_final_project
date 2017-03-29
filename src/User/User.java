package User;


public class User {

    private int uid;
    private String username;
    private String email;
    private boolean ban;
    private boolean admin;

    public int getUid() { return uid; }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean getBan() { return ban; }
    private void setBan() { ban = true; }
    private void unBan() { ban = false; }

    public boolean getAdmin() { return admin; }

    public User(int uid, String username, String email, boolean ban, boolean admin) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.ban = ban;
        this.admin = admin;
    }


    public void banUser(int uid, User user) {
        if (admin && !user.getAdmin() && user.getUid() == uid)
            user.setBan();
    }
    public void unbanUser(int uid, User user) {
        if (admin && !user.getAdmin() && user.getUid() == uid)
            user.unBan();
    }
}

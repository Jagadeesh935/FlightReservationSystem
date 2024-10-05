public class Admin {
    private String username = "admin";
    private String password="admin";

    public boolean login(String username, String password){
        if (username.equals(this.username) && password.equals(this.password)) return true;
        System.out.println("Invalid Username and password");
        return false;
    }
}

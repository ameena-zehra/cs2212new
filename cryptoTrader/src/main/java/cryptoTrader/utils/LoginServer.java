package cryptoTrader.utils;

final class User {
    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class LoginServer {
    private User[] allowedUsers;

    public LoginServer() {
        this.allowedUsers = new User[] { 
            new User("nicholaschong", "nc"), 
            new User("anselzeng", "az"), 
            new User("sophialu", "sl"), 
            new User("ameenanaqvi", "an") 
        };
    }

    public boolean verifyCredentials(String username, String password) {
        for (int i=0; i<this.allowedUsers.length; i++) {
            User userToCheck = this.allowedUsers[i];
            if (
                userToCheck.username.equals(username) && 
                userToCheck.password.equals(password)
            ) {
                return true;
            }
        }
        return false;
    }
}

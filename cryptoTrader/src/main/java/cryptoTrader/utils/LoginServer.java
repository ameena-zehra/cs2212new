package cryptoTrader.utils;

final class User {
    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

// Singleton design pattern
public class LoginServer {
    private User[] allowedUsers;
    private LoginServer loginServer = null;

    public LoginServer() {
        this.allowedUsers = new User[] {  //List of allowed users
            new User("nicholaschong", "nc"), 
            new User("anselzeng", "az"), 
            new User("sophialu", "sl"), 
            new User("ameenanaqvi", "an") 
        };
    }
    /**
    * @param username entered by user
    * @param password eneterd by user
    * @return true or false, depending on if the username and password match an existing user
    */
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
    
    /**
    * Creates a login server
    * @return loginServer
    */
    public LoginServer getLoginServer() {
        if (this.loginServer == null) this.loginServer = new LoginServer();
        return this.loginServer;
    }
}

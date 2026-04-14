import java.text.SimpleDateFormat;
import java.util.Date;

class User {
    String username;
    String password;
    String email;
    String registeredDate;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registeredDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public User(String username, String password, String email, String registeredDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registeredDate = registeredDate;
    }

    public String toString() {
        return username + "|" + password + "|" + email + "|" + registeredDate;
    }
}